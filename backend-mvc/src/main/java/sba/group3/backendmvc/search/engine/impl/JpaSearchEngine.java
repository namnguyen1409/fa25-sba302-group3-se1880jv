package sba.group3.backendmvc.search.engine.impl;

import com.cosium.spring.data.jpa.entity.graph.domain2.NamedEntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphSimpleJpaRepository;
import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.filter.SortRequest;
import sba.group3.backendmvc.search.builder.JpaSearchBuilder;
import sba.group3.backendmvc.search.engine.SearchEngine;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JpaSearchEngine<T, ID extends Serializable> implements SearchEngine<T, ID> {

    EntityGraphSimpleJpaRepository<T, ID> entityGraphSimpleJpaRepository;
    Specification<T> baseSpecification;
    EntityManager entityManager;
    Class<T> entityClass;

    @Override
    public Page<T> search(SearchFilter filter) {
        Specification<T> spec = JpaSearchBuilder.build(filter);
        Specification<T> finalSpec = combine(spec, baseSpecification);

        int page = Math.max(filter.getPage(), 0);
        int size = Math.min(Math.max(filter.getSize(), 1), 100);
        Pageable pageable = PageRequest.of(page, size, toSort(filter));
        String graphName = entityClass.getSimpleName() + ".default";
        Page<T> pageResult;
        try {
            pageResult = entityGraphSimpleJpaRepository.findAll(finalSpec, pageable, NamedEntityGraph.fetching(graphName));
        } catch (Exception e) {
            pageResult = entityGraphSimpleJpaRepository.findAll(finalSpec, pageable);
        }

        log.info("[JPA Search] {} results for {} (page={}, size={})",
                pageResult.getTotalElements(), entityClass.getSimpleName(), page, size);

        return pageResult;
    }

    @Override
    public List<T> searchAll(SearchFilter filter) {
        Specification<T> spec = JpaSearchBuilder.build(filter);
        Specification<T> finalSpec = combine(spec, baseSpecification);
        String graphName = entityClass.getSimpleName() + ".default";
        List<T> results = entityGraphSimpleJpaRepository.findAll(finalSpec, NamedEntityGraph.loading(graphName));

        log.debug("[JPA SearchAll] {} results for {}", results.size(), entityClass.getSimpleName());
        return results;
    }

    @Override
    public List<ID> searchIds(SearchFilter filter) {
        List<T> entities = searchAll(filter);
        List<ID> ids = entities.stream().map(this::getEntityId).toList();
        log.debug("[JPA SearchIds] {} results for {}", ids.size(), entityClass.getSimpleName());
        return ids;
    }

    @Override
    public List<ID> searchIdsOnly(SearchFilter filter) {
        return searchIds(filter);
    }

    private Sort toSort(SearchFilter filter) {
        if (filter == null || filter.getSorts() == null || filter.getSorts().isEmpty()) {
            return Sort.unsorted();
        }
        List<Sort.Order> orders = filter.getSorts().stream()
                .map(this::toOrder)
                .collect(Collectors.toList());
        return Sort.by(orders);
    }

    private Sort.Order toOrder(SortRequest sortReq) {
        Sort.Direction dir = Optional.ofNullable(sortReq.getDirection()).orElse(Sort.Direction.ASC);
        return new Sort.Order(dir, sortReq.getField());
    }

    private Specification<T> combine(Specification<T> s1, Specification<T> s2) {
        if (s1 == null && s2 == null) return Specification.unrestricted();
        if (s1 == null) return s2;
        if (s2 == null) return s1;
        return s1.and(s2);
    }

    @SuppressWarnings("unchecked")
    protected ID getEntityId(T entity) {
        return (ID) entityManager.getEntityManagerFactory()
                .getPersistenceUnitUtil()
                .getIdentifier(entity);
    }
}
