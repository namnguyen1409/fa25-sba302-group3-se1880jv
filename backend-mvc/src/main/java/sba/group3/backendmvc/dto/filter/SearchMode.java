package sba.group3.backendmvc.dto.filter;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum SearchMode {
    LUCENE,
    JPA,
    HYBRID;
}
