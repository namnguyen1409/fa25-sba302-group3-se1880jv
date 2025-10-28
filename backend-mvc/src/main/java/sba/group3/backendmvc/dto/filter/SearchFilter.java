package sba.group3.backendmvc.dto.filter;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@ToString
public class SearchFilter {
    @Builder.Default
    int page = 0;
    @Builder.Default
    int size = 20;
    List<SortRequest> sorts;
    FilterGroup filterGroup;
    @Builder.Default
    SearchMode searchMode = SearchMode.JPA;

    public void addMandatoryCondition(Filter mandatoryFilter) {

        if (this.filterGroup == null) {
            this.filterGroup = new FilterGroup();
            this.filterGroup.setOperator(LogicalOperator.AND);
        }

        FilterGroup mandatoryGroup = new FilterGroup();
        mandatoryGroup.setOperator(LogicalOperator.AND);
        mandatoryGroup.setFilters(List.of(mandatoryFilter));

        if ((this.filterGroup.getFilters() == null || this.filterGroup.getFilters().isEmpty())
                && (this.filterGroup.getSubGroups() == null || this.filterGroup.getSubGroups().isEmpty())) {
            this.filterGroup = mandatoryGroup;
            return;
        }

        FilterGroup combined = new FilterGroup();
        combined.setOperator(LogicalOperator.AND);
        combined.setSubGroups(List.of(this.filterGroup, mandatoryGroup));

        this.filterGroup = combined;
    }

    public void addMandatoryConditions(List<Filter> mandatoryFilters) {
        if (mandatoryFilters == null || mandatoryFilters.isEmpty()) return;

        // Nếu chưa có filterGroup thì tạo mới
        if (this.filterGroup == null) {
            this.filterGroup = new FilterGroup();
            this.filterGroup.setOperator(LogicalOperator.AND);
        }

        // Tạo nhóm AND chứa toàn bộ filters bắt buộc
        FilterGroup mandatoryGroup = new FilterGroup();
        mandatoryGroup.setOperator(LogicalOperator.AND);
        mandatoryGroup.setFilters(mandatoryFilters);

        boolean noExisting =
                (this.filterGroup.getFilters() == null || this.filterGroup.getFilters().isEmpty())
                        && (this.filterGroup.getSubGroups() == null || this.filterGroup.getSubGroups().isEmpty());

        if (noExisting) {
            this.filterGroup = mandatoryGroup;
            return;
        }

        // Combine: (existing AND mandatoryGroup)
        FilterGroup combined = new FilterGroup();
        combined.setOperator(LogicalOperator.AND);
        combined.setSubGroups(List.of(this.filterGroup, mandatoryGroup));

        this.filterGroup = combined;
    }

}
