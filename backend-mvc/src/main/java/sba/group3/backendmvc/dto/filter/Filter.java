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
public class Filter {

    String field;
    @Builder.Default
    List<String> fields = new ArrayList<>();

    String operator; // eq, ne, gt, lt, gte, lte, in, nin, contains, startsWith, endsWith

    Object value;

    public boolean isEmpty() {
        return (field == null || field.isEmpty())
                && (fields == null || fields.isEmpty())
                && (operator == null || operator.isEmpty())
                && value == null;
    }

}
