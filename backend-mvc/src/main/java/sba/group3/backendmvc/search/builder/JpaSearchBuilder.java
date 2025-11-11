package sba.group3.backendmvc.search.builder;

import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import sba.group3.backendmvc.dto.filter.Filter;
import sba.group3.backendmvc.dto.filter.FilterGroup;
import sba.group3.backendmvc.dto.filter.SearchFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public final class JpaSearchBuilder {

    private JpaSearchBuilder() {
    }

    public static <T> Specification<T> build(SearchFilter filter) {
        return (root, query, cb) -> {
            assert query != null;
            query.distinct(true);
            if (filter == null || filter.getFilterGroup() == null) return cb.conjunction();
            return buildGroupPredicate(filter.getFilterGroup(), root, cb);
        };
    }

    // group filter
    private static <T> Predicate buildGroupPredicate(FilterGroup group, Root<T> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        for (Filter f : safe(group.getFilters())) {
            Predicate p = buildFilterPredicates(f, root, cb);
            if (p != null) predicates.add(p);
        }

        for (FilterGroup sub : safe(group.getSubGroups())) {
            Predicate p = buildGroupPredicate(sub, root, cb);
            if (p != null) predicates.add(p);
        }

        return switch (group.getOperator()) {
            case AND -> cb.and(predicates.toArray(new Predicate[0]));
            case OR -> cb.or(predicates.toArray(new Predicate[0]));
            case NOT -> cb.not(cb.and(predicates.toArray(new Predicate[0])));
        };
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <T> Predicate buildFilterPredicates(Filter filter, Root<T> root, CriteriaBuilder cb) {
        if (filter == null || filter.isEmpty()) return cb.conjunction();
        String operator = filter.getOperator();
        Object value = filter.getValue();
        if ("fulltext".equalsIgnoreCase(operator)) return null;
        Path<?> path = getPath(root, filter.getField());
        Class<?> type = path.getJavaType();

        try {
            return switch (operator) {
                // eq / not eq
                case "eq" -> cb.equal(path, convertValue(value, type));
                case "ne" -> cb.notEqual(path, convertValue(value, type));

                case "gt" -> cb.greaterThan((Path<Comparable>) path, (Comparable) convertValue(value, type));
                case "lt" -> cb.lessThan((Path<Comparable>) path, (Comparable) convertValue(value, type));
                case "gte" -> cb.greaterThanOrEqualTo((Path<Comparable>) path, (Comparable) convertValue(value, type));
                case "lte" -> cb.lessThanOrEqualTo((Path<Comparable>) path, (Comparable) convertValue(value, type));
                case "between" -> {
                    if (value instanceof List<?> vals && vals.size() == 2) {
                        var from = (Comparable) convertValue(vals.getFirst(), type);
                        var to = (Comparable) convertValue(vals.getLast(), type);
                        yield cb.between((Path<Comparable>) path, from, to);
                    } else yield null;
                }
                case "member" -> cb.isMember(value.toString(), root.get(filter.getField()));
                case "memberIn" -> {
                    if (value instanceof List<?> vals) {
                        List<Predicate> orPreds = new ArrayList<>();
                        for (Object v : vals) {
                            orPreds.add(cb.isMember(v.toString(), root.get(filter.getField())));
                        }
                        yield cb.or(orPreds.toArray(new Predicate[0]));
                    }
                    yield null;
                }
                case "memberAll" -> {
                    if (value instanceof List<?> vals) {
                        List<Predicate> andPreds = new ArrayList<>();
                        for (Object v : vals) {
                            andPreds.add(cb.isMember(v.toString(), root.get(filter.getField())));
                        }
                        yield cb.and(andPreds.toArray(new Predicate[0]));
                    }
                    yield null;
                }
                case "in" -> {
                    CriteriaBuilder.In<Object> inClause = cb.in(path);
                    if (value instanceof List<?> vals) {
                        for (Object val : vals) inClause.value(convertValue(val, type));
                    }
                    yield inClause;
                }

                case "notIn" -> {
                    CriteriaBuilder.In<Object> inClause = cb.in(path);
                    if (value instanceof List<?> vals) {
                        for (Object val : vals) inClause.value(convertValue(val, type));
                    }
                    yield cb.not(inClause);
                }
                case "contains" -> {
                    var valStr = value.toString().trim();
                    if (valStr.isEmpty()) yield cb.conjunction();
                    var val = "%" + valStr + "%";
                    yield cb.like(path.as(String.class), val);
                }
                case "containsIgnoreCase" -> {
                    var valStr = value.toString().trim();
                    if (valStr.isEmpty()) yield cb.conjunction();
                    var val = "%" + valStr.toLowerCase() + "%";
                    yield cb.like(cb.lower(path.as(String.class)), val);
                }
                case "startsWith" -> {
                    var valStr = value.toString().trim();
                    if (valStr.isEmpty()) yield cb.conjunction();
                    var val = valStr + "%";
                    yield cb.like(path.as(String.class), val);
                }
                case "startsWithIgnoreCase" -> {
                    var valStr = value.toString().trim();
                    if (valStr.isEmpty()) yield cb.conjunction();
                    var val = valStr.toLowerCase() + "%";
                    yield cb.like(cb.lower(path.as(String.class)), val);
                }
                case "endsWith" -> {
                    var valStr = value.toString().trim();
                    if (valStr.isEmpty()) yield cb.conjunction();
                    var val = "%" + valStr;
                    yield cb.like(path.as(String.class), val);
                }
                case "endsWithIgnoreCase" -> {
                    var valStr = value.toString().trim();
                    if (valStr.isEmpty()) yield cb.conjunction();
                    var val = "%" + valStr.toLowerCase();
                    yield cb.like(cb.lower(path.as(String.class)), val);
                }

                case "exists" -> cb.isNotNull(path);
                case "not exists" -> cb.isNull(path);

                default -> {
                    log.warn("Unknown search operator {} for field {}", operator, filter.getField());
                    yield null;
                }
            };
        } catch (Exception e) {
            log.warn("Failed to build filter predicate for field '{}': {}", filter.getField(), e.getMessage());
            return null;
        }

    }

    private static <T> List<T> safe(List<T> list) {
        return list == null ? List.of() : list;
    }

    @SuppressWarnings("unchecked")
    private static <T> Path<T> getPath(From<?, ?> root, String fieldPath) {
        if (fieldPath == null || fieldPath.isBlank()) {
            throw new IllegalArgumentException("Field path cannot be null or blank");
        }

        String[] parts = fieldPath.split("\\.");
        From<?, ?> from = root;
        Path<?> path = root;

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (i < parts.length - 1) {
                boolean alreadyJoined = false;
                for (Join<?, ?> join : from.getJoins()) {
                    if (join.getAttribute().getName().equals(part)) {
                        from = join;
                        alreadyJoined = true;
                        break;
                    }
                }
                if (!alreadyJoined) {
                    from = from.join(part, JoinType.LEFT);
                }
                path = from;
            } else {
                path = path.get(part);
            }
        }

        return (Path<T>) path;
    }

    private static Object convertValue(Object value, Class<?> targetType) {
        if (value == null) return null;
        if (targetType.isInstance(value)) return value;

        try {
            if (targetType.equals(UUID.class)) return UUID.fromString((String) value);
            if (targetType.equals(Integer.class)) return Integer.valueOf(value.toString());
            if (targetType.equals(Long.class)) return Long.valueOf(value.toString());
            if (targetType.equals(Double.class)) return Double.valueOf(value.toString());
            if (targetType.equals(Float.class)) return Float.valueOf(value.toString());
            if (targetType.equals(Boolean.class)) return Boolean.valueOf(value.toString());
            if (Comparable.class.isAssignableFrom(targetType) && targetType.equals(java.time.Instant.class))
                return java.time.Instant.parse(value.toString());
            return targetType.cast(value);
        } catch (Exception e) {
            log.warn("Failed to convert value '{}' to type '{}'", value, targetType.getSimpleName());
            return value;
        }
    }

}
