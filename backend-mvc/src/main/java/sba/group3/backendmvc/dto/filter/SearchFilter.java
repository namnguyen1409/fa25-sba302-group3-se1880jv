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
}
