package sba.group3.backendmvc.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import sba.group3.backendmvc.dto.filter.SearchFilter;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID>,
        JpaSpecificationExecutor<T>,
        EntityGraphJpaSpecificationExecutor<T> {
    Page<T> search(SearchFilter filter);

    List<ID> searchIds(SearchFilter filter);

    List<T> searchAll(SearchFilter filter);

    List<ID> searchIdsOnly(SearchFilter filter);
}
