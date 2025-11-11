package sba.group3.backendmvc.search;


import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;
import org.springframework.stereotype.Component;
import sba.group3.backendmvc.dto.filter.SearchMode;
import sba.group3.backendmvc.entity.user.User;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class SearchMetadataProvider {

    static List<String> SENSITIVE_FIELDS = List.of(
            User.Fields.password
    );

    List<EntityMeta> cachedMetadata = new ArrayList<>();

    @PostConstruct
    public void init() {
        cachedMetadata.clear();
        cachedMetadata.addAll(buildSearchableMetadata("sba.group3.backendmvc.entity"));
    }

    public EntityMeta getEntityMeta(Class<?> entityClass) {
        return cachedMetadata.stream()
                .filter(entityMeta -> entityMeta.entity.equalsIgnoreCase(entityClass.getSimpleName()))
                .findFirst()
                .orElse(null);
    }

    public List<EntityMeta> buildSearchableMetadata(String basePackage) {
        List<EntityMeta> entityList = new ArrayList<>();

        var scanner = new org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new org.springframework.core.type.filter.AnnotationTypeFilter(Entity.class));

        for (var bd : scanner.findCandidateComponents(basePackage)) {
            try {
                Class<?> clazz = Class.forName(bd.getBeanClassName());
                List<FieldMeta> metas = extractFieldMeta(clazz);
                entityList.add(EntityMeta.builder()
                        .entity(clazz.getSimpleName())
                        .fields(metas)
                        .build());
            } catch (Exception e) {
                log.warn("❌ Could not process entity {}: {}", bd.getBeanClassName(), e.getMessage());
            }
        }

        return entityList;
    }

    private List<FieldMeta> extractFieldMeta(Class<?> clazz) {
        List<FieldMeta> list = new ArrayList<>();

        for (Field f : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(f.getModifiers())) continue;

            String name = f.getName();
            if (SENSITIVE_FIELDS.contains(name)) continue;

            List<String> engines = new ArrayList<>();
            String type = inferType(f.getType());

            if (f.isAnnotationPresent(FullTextField.class)
                    || f.isAnnotationPresent(KeywordField.class)
                    || f.isAnnotationPresent(GenericField.class)
                    || f.isAnnotationPresent(IndexedEmbedded.class)) {
                engines.add(SearchMode.LUCENE.name());
            }

            // JPA filterable types
            if (isJpaFilterableType(f)) {
                engines.add(SearchMode.JPA.name());
            }

            // skip non-searchable
            if (engines.isEmpty()) continue;

            list.add(FieldMeta.builder()
                    .name(name)
                    .type(type)
                    .engine(engines)
                    .build());
        }

        return list;
    }

    private boolean isJpaFilterableType(Field f) {
        Class<?> type = f.getType();
        String t = type.getSimpleName().toLowerCase(Locale.ROOT);
        return t.contains("string") || t.contains("int") || t.contains("long")
                || t.contains("double") || t.contains("float") || t.contains("bigdecimal")
                || t.contains("boolean")
                || Temporal.class.isAssignableFrom(type)
                || java.util.Date.class.isAssignableFrom(type);
    }

    private String inferType(Class<?> type) {
        String t = type.getSimpleName().toLowerCase(Locale.ROOT);
        if (t.contains("string")) return "text";
        if (t.contains("int") || t.contains("long") || t.contains("float") || t.contains("double")) return "number";
        if (t.contains("bool")) return "boolean";
        if (t.contains("date") || t.contains("time")) return "date";
        return "object";
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FieldMeta {
        private String name;
        private String type;
        private List<String> engine;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EntityMeta {
        private String entity;
        private List<FieldMeta> fields;
    }
}
