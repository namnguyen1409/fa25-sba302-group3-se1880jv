package sba.group3.backendmvc.search.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchAbleEntity {
    String entityName;
    Jpa jpa;
    Lucene lucene;
    List<String> nestedFields;
}
