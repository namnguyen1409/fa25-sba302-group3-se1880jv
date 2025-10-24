package sba.group3.backendmvc.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import sba.group3.backendmvc.search.response.Jpa;
import sba.group3.backendmvc.search.response.Lucene;
import sba.group3.backendmvc.search.response.SearchAbleEntity;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SearchMetadataExtractor {

    ObjectMapper objectMapper;

    @NonFinal
    List<SearchAbleEntity> cachedEntities = null;

    public synchronized List<SearchAbleEntity> extractMetadata(boolean refresh) {
        if (cachedEntities != null && !refresh) {
            return cachedEntities;
        }

        long start = System.currentTimeMillis();
        List<SearchAbleEntity> result = new ArrayList<>();

        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
        String basePackage = "com.example.a2namnvhe180863_se1880.entity";

        for (var bd : scanner.findCandidateComponents(basePackage)) {
            try {
                Class<?> clazz = Class.forName(bd.getBeanClassName());

                Lucene lucene = scanLuceneFields(clazz, null, new HashSet<>());
                Jpa jpa = scanJpaFieldsRecursive(clazz, null, new HashSet<>());
                List<String> nestedFields = findNestedRelations(clazz);

                SearchAbleEntity entity = SearchAbleEntity.builder()
                        .entityName(clazz.getSimpleName())
                        .lucene(lucene)
                        .jpa(jpa)
                        .nestedFields(nestedFields)
                        .build();

                result.add(entity);

            } catch (Exception e) {
                log.warn("Could not scan entity: {} → {}", bd.getBeanClassName(), e.getMessage());
            }
        }

        cachedEntities = result;

        long elapsed = System.currentTimeMillis() - start;
        log.info("✅ Built SearchAbleEntity list for {} entities in {}ms", result.size(), elapsed);

        return result;
    }

    private Lucene scanLuceneFields(Class<?> clazz, String prefix, Set<Class<?>> visited) {
        if (clazz == null || visited.contains(clazz)) return new Lucene();
        visited.add(clazz);

        List<String> fulltext = new ArrayList<>();
        List<String> keyword = new ArrayList<>();
        List<String> numeric = new ArrayList<>();
        List<String> date = new ArrayList<>();
        List<String> bool = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) continue;
            String fieldName = prefix == null ? field.getName() : prefix + "." + field.getName();

            if (field.isAnnotationPresent(FullTextField.class)) {
                fulltext.add(fieldName);
            } else if (field.isAnnotationPresent(KeywordField.class)) {
                keyword.add(fieldName);
            } else if (field.isAnnotationPresent(GenericField.class)) {
                classifyGeneric(field, fieldName, numeric, date, bool, keyword);
            } else if (field.isAnnotationPresent(IndexedEmbedded.class)) {
                processIndexedEmbedded(field, fieldName, visited, fulltext, keyword, numeric, date, bool);
            }
        }

        return Lucene.builder()
                .fullTextFields(fulltext)
                .keywordFields(keyword)
                .numericFields(numeric)
                .dateFields(date)
                .booleanFields(bool)
                .build();
    }

    private void processIndexedEmbedded(
            Field field,
            String prefix,
            Set<Class<?>> visited,
            List<String> fulltext,
            List<String> keyword,
            List<String> numeric,
            List<String> date,
            List<String> bool
    ) {
        IndexedEmbedded annotation = field.getAnnotation(IndexedEmbedded.class);
        Class<?> nestedType = resolveGenericType(field);

        // Skip self-loop recursion (e.g., parentCategory)
        if (nestedType.equals(field.getDeclaringClass())) {
            log.debug("Skipping self-recursive field: {}", field.getName());
            return;
        }

        Map<String, List<String>> nested = collectLuceneNested(nestedType, prefix, visited);

        // Nếu includePaths được chỉ định → chỉ lấy đúng các field đó
        if (annotation.includePaths().length > 0) {
            Set<String> allowed = new HashSet<>();
            for (String path : annotation.includePaths()) {
                allowed.add(prefix + "." + path);
            }

            nested.replaceAll((key, list) -> list.stream()
                    .filter(allowed::contains)
                    .toList());
        }

        fulltext.addAll(nested.get("fulltext"));
        keyword.addAll(nested.get("keyword"));
        numeric.addAll(nested.get("numeric"));
        date.addAll(nested.get("date"));
        bool.addAll(nested.get("boolean"));
    }

    private Map<String, List<String>> collectLuceneNested(Class<?> clazz, String prefix, Set<Class<?>> visited) {
        Lucene l = scanLuceneFields(clazz, prefix, visited);
        Map<String, List<String>> map = new LinkedHashMap<>();
        map.put("fulltext", l.getFullTextFields());
        map.put("keyword", l.getKeywordFields());
        map.put("numeric", l.getNumericFields());
        map.put("date", l.getDateFields());
        map.put("boolean", l.getBooleanFields());
        return map;
    }

    private void classifyGeneric(Field field, String name,
                                 List<String> numeric, List<String> date, List<String> bool, List<String> keyword) {
        String type = field.getType().getSimpleName().toLowerCase(Locale.ROOT);
        if (type.matches(".*(int|long|double|float|bigdecimal).*")) numeric.add(name);
        else if (type.contains("date") || type.contains("instant") ||
                type.contains("time") || type.contains("calendar")) date.add(name);
        else if (type.contains("boolean")) bool.add(name);
        else keyword.add(name);
    }

    private Class<?> resolveGenericType(Field field) {
        Class<?> nestedType = field.getType();
        if (Collection.class.isAssignableFrom(nestedType)) {
            Type genericType = field.getGenericType();
            if (genericType instanceof ParameterizedType parameterizedType) {
                Type actualType = parameterizedType.getActualTypeArguments()[0];
                if (actualType instanceof Class<?> actualClass) {
                    nestedType = actualClass;
                }
            }
        }
        return nestedType;
    }

    private Jpa scanJpaFieldsRecursive(Class<?> clazz, String prefix, Set<Class<?>> visited) {
        if (clazz == null || visited.contains(clazz)) return Jpa.builder().build();
        visited.add(clazz);

        List<String> text = new ArrayList<>();
        List<String> number = new ArrayList<>();
        List<String> numeric = new ArrayList<>();
        List<String> bool = new ArrayList<>();
        List<String> date = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) continue;
            String name = prefix == null ? field.getName() : prefix + "." + field.getName();
            Class<?> type = field.getType();

            // Nếu là quan hệ entity
            if (field.isAnnotationPresent(ManyToOne.class)
                    || field.isAnnotationPresent(OneToOne.class)
                    || field.isAnnotationPresent(OneToMany.class)
                    || field.isAnnotationPresent(ManyToMany.class)) {

                // Đệ quy nếu là entity khác và chưa duyệt qua
                if (!visited.contains(type)
                        && type.getPackageName().startsWith("com.example.a2namnvhe180863_se1880.entity")
                        && !type.equals(clazz)) {

                    Class<?> nestedType = resolveGenericType(field);
                    Jpa nested = scanJpaFieldsRecursive(nestedType, name, visited);
                    text.addAll(nested.getTextFields());
                    number.addAll(nested.getNumberFields());
                    numeric.addAll(nested.getNumericFields());
                    bool.addAll(nested.getBooleanFields());
                }
                continue;
            }

            String typeName = type.getSimpleName().toLowerCase(Locale.ROOT);
            if (field.isAnnotationPresent(Id.class)) {
                numeric.add(name);
            } else if (field.isAnnotationPresent(Enumerated.class) || type.isEnum()) {
                text.add(name);
            } else if (typeName.contains("string")) {
                text.add(name);
            } else if (typeName.matches(".*(int|long|double|float|bigdecimal).*")) {
                number.add(name);
            } else if (typeName.contains("boolean")) {
                bool.add(name);
            } else if (typeName.contains("date") || typeName.contains("instant") ||
                    typeName.contains("time") || typeName.contains("calendar") ||
                    typeName.contains("offset") || typeName.contains("zoned")) {
                date.add(name);
            }
        }

        return Jpa.builder()
                .textFields(text)
                .numberFields(number)
                .numericFields(numeric)
                .booleanFields(bool)
                .dateFields(date)
                .build();
    }

    private List<String> findNestedRelations(Class<?> clazz) {
        List<String> nested = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) continue;
            if (field.isAnnotationPresent(ManyToOne.class)
                    || field.isAnnotationPresent(OneToMany.class)
                    || field.isAnnotationPresent(OneToOne.class)
                    || field.isAnnotationPresent(ManyToMany.class)) {
                nested.add(field.getName());
            }
        }
        return nested;
    }
}
