package sba.group3.backendmvc.search.engine.impl;


import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.search.engine.SearchEngine;

import java.io.Serializable;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HybridSearchEngine<T, ID extends Serializable> implements SearchEngine<T, ID> {

    LuceneSearchEngine<T, ID> luceneSearchEngine;
    JpaSearchEngine<T, ID> jpaSearchEngine;
    @SuppressWarnings("unused")
    EntityManager entityManager;

    @Override
    public Page<T> search(SearchFilter filter) {
        log.debug("[Hybrid] Searching {} with hybrid mode", luceneSearchEngine.getClass().getSimpleName());
        var luceneResult = luceneSearchEngine.searchIdsOnly(filter);

        if (luceneResult.isEmpty()) {
            log.debug("[Hybrid] No Lucene hits → fallback to JPA");
            return jpaSearchEngine.search(filter);
        }

        var jpaResult = jpaSearchEngine.searchAll(filter)
                .stream()
                .filter(e -> luceneResult.contains(jpaSearchEngine.getEntityId(e)))
                .toList();

        int page = Math.max(filter.getPage(), 0);
        int size = Math.max(filter.getSize(), 1);
        int start = Math.min(page * size, jpaResult.size());
        int end = Math.min(start + size, jpaResult.size());
        List<T> content = jpaResult.subList(start, end);

        return new PageImpl<>(content, PageRequest.of(page, size), jpaResult.size());
    }

    @Override
    public List<T> searchAll(SearchFilter filter) {
        var luceneIds = luceneSearchEngine.searchIdsOnly(filter);
        if (luceneIds.isEmpty()) return jpaSearchEngine.searchAll(filter);
        return jpaSearchEngine.searchAll(filter).stream()
                .filter(e -> luceneIds.contains(jpaSearchEngine.getEntityId(e)))
                .toList();
    }

    @Override
    public List<ID> searchIdsOnly(SearchFilter filter) {
        var ids = luceneSearchEngine.searchIdsOnly(filter);
        if (ids.isEmpty()) return jpaSearchEngine.searchIdsOnly(filter);
        return ids;
    }

    @Override
    public List<ID> searchIds(SearchFilter filter) {
        return searchIdsOnly(filter);
    }

}
