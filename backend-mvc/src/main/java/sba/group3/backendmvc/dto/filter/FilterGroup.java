package sba.group3.backendmvc.dto.filter;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class FilterGroup {

    @Builder.Default
    LogicalOperator operator = LogicalOperator.AND;
    List<Filter> filters;
    @Builder.Default
    List<FilterGroup> subGroups = new ArrayList<>();

    public boolean isEmpty() {
        return (filters == null || filters.isEmpty())
                && (subGroups == null || subGroups.isEmpty());
    }
}
