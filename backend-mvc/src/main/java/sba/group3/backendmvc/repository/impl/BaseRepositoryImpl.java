package sba.group3.backendmvc.repository.impl;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphSimpleJpaRepository;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.filter.SearchMode;
import sba.group3.backendmvc.repository.BaseRepository;
import sba.group3.backendmvc.search.engine.SearchEngine;
import sba.group3.backendmvc.search.engine.impl.HybridSearchEngine;
import sba.group3.backendmvc.search.engine.impl.JpaSearchEngine;
import sba.group3.backendmvc.search.engine.impl.LuceneSearchEngine;
import sba.group3.backendmvc.search.response.Lucene;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Getter
public class BaseRepositoryImpl<T, ID extends Serializable>
        extends EntityGraphSimpleJpaRepository<T, ID>
        implements BaseRepository<T, ID> {

    protected final EntityManager entityManager;
    protected final Class<T> entityClass;
    protected final Class<ID> idClass;

    protected Lucene config;

    // Engines
    protected SearchEngine<T, ID> luceneEngine;
    protected SearchEngine<T, ID> jpaEngine;
    protected SearchEngine<T, ID> hybridEngine;

    public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInfo, EntityManager entityManager) {
        super(entityInfo, entityManager);
        this.entityManager = entityManager;
        this.entityClass = entityInfo.getJavaType();
        this.idClass = entityInfo.getIdType();
    }

    public void setConfig(Lucene config) {
        this.config = config;
        initEngines();
    }

    protected void initEngines() {
        this.jpaEngine = new JpaSearchEngine<>(
                this,
                getBaseSpecification(),
                entityManager,
                entityClass
        );

        this.luceneEngine = new LuceneSearchEngine<>(
                entityManager,
                entityClass,
                idClass,
                config
        );

        this.hybridEngine = new HybridSearchEngine<>(
                (LuceneSearchEngine<T, ID>) luceneEngine,
                (JpaSearchEngine<T, ID>) jpaEngine,
                entityManager
        );

        log.info("[SearchRepository] Engines initialized for {}", entityClass.getSimpleName());
        log.debug("[SearchRepository] Lucene Engine Config: {}", config);
    }

    @Override
    public Page<T> search(SearchFilter filter) {
        return getEngine(filter.getSearchMode()).search(filter);
    }

    @Override
    public List<T> searchAll(SearchFilter filter) {
        return getEngine(filter.getSearchMode()).searchAll(filter);
    }

    @Override
    public List<ID> searchIds(SearchFilter filter) {
        return getEngine(filter.getSearchMode()).searchIds(filter);
    }

    @Override
    public List<ID> searchIdsOnly(SearchFilter filter) {
        return getEngine(filter.getSearchMode()).searchIdsOnly(filter);
    }

    protected SearchEngine<T, ID> getEngine(SearchMode mode) {
        if (jpaEngine == null) {
            log.warn("[SearchRepository] Engines not initialized for {}, initializing JPA only", entityClass.getSimpleName());
            initEngines(); // tự động build lại engine nếu chưa có
        }

        return switch (mode) {
            case LUCENE -> luceneEngine != null ? luceneEngine : jpaEngine;
            case JPA -> jpaEngine;
            case HYBRID -> hybridEngine != null ? hybridEngine : jpaEngine;
        };
    }


    protected Specification<T> getBaseSpecification() {
        return null;
    }

}
