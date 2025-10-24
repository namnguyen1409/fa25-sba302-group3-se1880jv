package sba.group3.backendmvc.search.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Jpa {
    List<String> textFields;
    List<String> numberFields;
    List<String> numericFields;
    List<String> booleanFields;
    List<String> dateFields;
}
