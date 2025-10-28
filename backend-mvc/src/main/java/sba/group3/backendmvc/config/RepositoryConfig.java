package sba.group3.backendmvc.config;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sba.group3.backendmvc.repository.impl.BaseRepositoryImpl;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "sba.group3.backendmvc.repository",
        repositoryBaseClass = BaseRepositoryImpl.class,
        repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean.class
)
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class RepositoryConfig {
}
