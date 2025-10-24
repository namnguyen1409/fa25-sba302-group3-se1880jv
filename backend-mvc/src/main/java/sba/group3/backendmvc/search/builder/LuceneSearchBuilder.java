package sba.group3.backendmvc.search.builder;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.hibernate.search.engine.search.predicate.dsl.PredicateFinalStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.hibernate.search.engine.search.sort.dsl.SearchSortFactory;
import org.hibernate.search.engine.search.sort.dsl.SortFinalStep;
import sba.group3.backendmvc.dto.filter.Filter;
import sba.group3.backendmvc.dto.filter.FilterGroup;
import sba.group3.backendmvc.dto.filter.SortRequest;
import sba.group3.backendmvc.search.response.Lucene;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
public final class LuceneSearchBuilder {

    private final static List<String> fullTextOperator = List.of(
            "fulltext", "fuzzy"
    );

    private LuceneSearchBuilder() {
    }

    public static PredicateFinalStep buildPredicate(
            SearchPredicateFactory f,
            FilterGroup group,
            Lucene config
    ) {
        if (group == null) return f.matchAll();

        BooleanPredicateClausesStep<?> bool = f.bool();

        if (group.getFilters() != null) {
            for (Filter filter : group.getFilters()) {
                if (filter == null || (filter.getField() == null && (filter.getFields() == null))) {
                    continue;
                }
                try {
                    var predicate = buildFilterPredicate(f, filter, config);
                    if (predicate != null) {
                        switch (group.getOperator()) {
                            case AND -> bool.must(predicate);
                            case OR -> bool.should(predicate);
                            case NOT -> bool.mustNot(predicate);
                        }
                    }
                } catch (Exception e) {
                    log.warn("Failed to build Lucene predicate for field '{}': {}", filter.getField(), e.getMessage());
                }
            }
        }

        if (group.getSubGroups() != null) {
            for (FilterGroup sub : group.getSubGroups()) {
                PredicateFinalStep subPredicate = buildPredicate(f, sub, config);
                if (subPredicate != null) {
                    switch (group.getOperator()) {
                        case AND -> bool.must(subPredicate);
                        case OR -> bool.should(subPredicate);
                        case NOT -> bool.mustNot(subPredicate);
                    }
                }
            }
        }

        return bool;
    }

    private static PredicateFinalStep buildFilterPredicate(
            SearchPredicateFactory f,
            Filter filter,
            Lucene config
    ) {

        if (filter == null) return null;

        String operator = filter.getOperator();
        Object value = filter.getValue();
        String field = filter.getField();

        if (fullTextOperator.contains(operator.toLowerCase())) {
            var fields = getValidFields(config.getFullTextFields(), filter.getFields())
                    .toArray(String[]::new);
            return switch (operator) {
                case "fulltext" -> f.match().fields(fields).matching(value);

                case "fuzzy" -> f.match().fields(fields).matching(value)
                        .fuzzy(autoFuzziness((String) value), 2);
                default -> null;
            };
        }

        if (field == null) return null;
        String valStr = value == null ? null : value.toString();

        if (config.getNumericFields().contains(field) || config.getDateFields().contains(field)) {
            return buildComparablePredicate(f, field, operator, value);
        }

        if (config.getBooleanFields().contains(field)) {
            boolean boolVal = Boolean.parseBoolean(valStr);
            return switch (operator) {
                case "eq" -> f.match().field(field).matching(boolVal);
                case "ne" -> f.bool().mustNot(f.match().field(field).matching(boolVal));
                default -> null;
            };
        }

        if (config.getKeywordFields().contains(field)) {
            return buildKeywordPredicate(f, field, operator, value);
        }

        return null;
    }

    private static int autoFuzziness(String value) {
        if (value == null) return 0;
        int len = value.length();
        if (len <= 3) return 0;
        if (len <= 6) return 1;
        return 2;
    }

    private static PredicateFinalStep buildComparablePredicate(SearchPredicateFactory f, String field, String operator, Object value) {
        return switch (operator) {
            case "eq" -> f.match().field(field).matching(value);
            case "ne" -> f.bool().mustNot(f.match().field(field).matching(value));
            case "gt" -> f.range().field(field).greaterThan(value);
            case "gte" -> f.range().field(field).atLeast(value);
            case "lt" -> f.range().field(field).lessThan(value);
            case "lte" -> f.range().field(field).atMost(value);
            case "between" -> {
                if (value instanceof List<?> vals && vals.size() == 2)
                    yield f.range().field(field).between(vals.get(0), vals.get(1));
                else yield null;
            }
            case "in" -> {
                if (value instanceof List<?> vals)
                    yield f.terms().field(field).matchingAny(vals);
                else yield null;
            }
            case "notIn" -> {
                if (value instanceof List<?> vals)
                    yield f.bool().mustNot(f.terms().field(field).matchingAny(vals));
                else yield null;
            }
            default -> null;
        };
    }

    private static PredicateFinalStep buildKeywordPredicate(SearchPredicateFactory f, String field, String operator, Object value) {
        String valStr = value == null ? "" : value.toString();
        return switch (operator) {
            case "eq" -> f.match().field(field).matching(valStr);
            case "ne" -> f.bool().mustNot(f.match().field(field).matching(valStr));
            case "in" -> {
                if (value instanceof List<?> vals)
                    yield f.terms().field(field).matchingAny(vals);
                else yield null;
            }
            case "startsWith" -> f.wildcard().field(field).matching(valStr.toLowerCase() + "*");
            case "endsWith" -> f.wildcard().field(field).matching("*" + valStr.toLowerCase());
            case "contains" -> f.wildcard().field(field).matching("*" + valStr.toLowerCase() + "*");
            default -> null;
        };
    }

    public static SortFinalStep buildSort(SearchSortFactory f, List<SortRequest> sorts, Set<String> sortableFields) {
        if (sorts == null || sorts.isEmpty()) {
            return f.score();
        }

        var composite = f.composite();
        for (SortRequest s : sorts) {
            if (s.getField() == null || !sortableFields.contains(s.getField())) continue;
            var sort = "DESC".equalsIgnoreCase(s.getDirection().name())
                    ? f.field(s.getField()).desc()
                    : f.field(s.getField()).asc();
            composite.add(sort);
        }

        if (composite == null) return f.score();
        return composite;
    }

    private static List<String> getValidFields(List<String> allowedFields, List<String> fields) {
        if (fields == null || fields.isEmpty()) {
            return new ArrayList<>(allowedFields);
        }
        var allowedFieldList = new ArrayList<String>();
        for (String field : fields) {
            if (!allowedFields.contains(field)) continue;
            allowedFieldList.add(field);
        }
        return allowedFieldList;
    }
}
