package sba.group3.backendmvc.search.engine;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;

import java.io.Serializable;
import java.util.List;

public interface SearchEngine<T, ID extends Serializable> {
    Page<T> search(SearchFilter filter);

    List<T> searchAll(SearchFilter filter);

    List<ID> searchIds(SearchFilter filter);

    List<ID> searchIdsOnly(SearchFilter filter);
}
