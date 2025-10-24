package sba.group3.backendmvc.search.engine.impl;


import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.search.builder.LuceneSearchBuilder;
import sba.group3.backendmvc.search.engine.SearchEngine;
import sba.group3.backendmvc.search.response.Lucene;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LuceneSearchEngine<T, ID extends Serializable> implements SearchEngine<T, ID> {

    EntityManager entityManager;
    Class<T> entityClass;
    Class<ID> idClass;
    Lucene config;

    public static Set<String> getSortableFields(Lucene lucene) {
        Set<String> sortable = new LinkedHashSet<>();
        if (lucene.getKeywordFields() != null) sortable.addAll(lucene.getKeywordFields());
        if (lucene.getNumericFields() != null) sortable.addAll(lucene.getNumericFields());
        if (lucene.getDateFields() != null) sortable.addAll(lucene.getDateFields());
        if (lucene.getBooleanFields() != null) sortable.addAll(lucene.getBooleanFields());
        // fullTextFields intentionally excluded
        return sortable;
    }

    @Override
    public Page<T> search(SearchFilter filter) {
        SearchSession session = Search.session(entityManager);
        int page = Math.max(filter.getPage(), 0);
        int size = Math.max(filter.getSize(), 1);

        var predicate = LuceneSearchBuilder.buildPredicate(
                session.scope(entityClass).predicate(),
                filter.getFilterGroup(),
                config
        );

        var sort = LuceneSearchBuilder.buildSort(
                session.scope(entityClass).sort(),
                filter.getSorts(),
                getSortableFields(config)
        );

        var result = session.search(entityClass)
                .where(f -> predicate)
                .sort(f -> sort)
                .fetch(page * size, size);

        long total = result.total().hitCount();
        List<T> hits = result.hits();

        log.info("[Lucene] page={} size={} total={} entity={}", page, size, total, entityClass.getSimpleName());
        return new PageImpl<>(hits, PageRequest.of(page, size), total);
    }

    @Override
    public List<T> searchAll(SearchFilter filter) {
        SearchSession session = Search.session(entityManager);

        var predicate = LuceneSearchBuilder.buildPredicate(
                session.scope(entityClass).predicate(),
                filter.getFilterGroup(),
                config
        );

        var sort = LuceneSearchBuilder.buildSort(
                session.scope(entityClass).sort(),
                filter.getSorts(),
                getSortableFields(config)
        );

        var hits = session.search(entityClass)
                .where(f -> predicate)
                .sort(f -> sort)
                .fetchAllHits();

        log.debug("[Lucene] {} total hits (ALL) for {}", hits.size(), entityClass.getSimpleName());
        return hits;
    }

    @Override
    public List<ID> searchIdsOnly(SearchFilter filter) {
        SearchSession session = Search.session(entityManager);

        var predicate = LuceneSearchBuilder.buildPredicate(
                session.scope(entityClass).predicate(),
                filter.getFilterGroup(),
                config
        );

        var sort = LuceneSearchBuilder.buildSort(
                session.scope(entityClass).sort(),
                filter.getSorts(),
                getSortableFields(config)
        );

        var ids = session.search(entityClass)
                .select(f -> f.id(idClass))
                .where(f -> predicate)
                .sort(f -> sort)
                .fetchAllHits();

        log.debug("[Lucene] {} ID hits (IDs only) for {}", ids.size(), entityClass.getSimpleName());
        return ids;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ID> searchIds(SearchFilter filter) {
        SearchSession session = Search.session(entityManager);

        var predicate = LuceneSearchBuilder.buildPredicate(
                session.scope(entityClass).predicate(),
                filter.getFilterGroup(),
                config
        );

        var sort = LuceneSearchBuilder.buildSort(
                session.scope(entityClass).sort(),
                filter.getSorts(),
                getSortableFields(config)
        );

        List<Object> rawIds = session.search(entityClass)
                .select(f -> f.id(Object.class))
                .where(f -> predicate)
                .sort(f -> sort)
                .fetchAllHits();

        List<ID> ids = rawIds.stream().map(id -> (ID) id).toList();
        log.debug("[Lucene] {} sorted ID hits for {}", ids.size(), entityClass.getSimpleName());
        return ids;
    }

}
