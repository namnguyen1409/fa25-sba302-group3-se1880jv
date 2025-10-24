package sba.group3.backendmvc.search.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lucene {
    List<String> fullTextFields;
    List<String> keywordFields;
    List<String> numericFields;
    List<String> dateFields;
    List<String> booleanFields;

}
