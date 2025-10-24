package sba.group3.backendmvc.search;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.SmartLifecycle;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Component;
import sba.group3.backendmvc.repository.impl.BaseRepositoryImpl;
import sba.group3.backendmvc.search.response.SearchAbleEntity;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchMetadataInjector implements SmartLifecycle {

    private final ApplicationContext context;
    private final SearchMetadataExtractor extractor;

    private boolean running = false;

    @Override
    public void start() {
        Repositories repositories = new Repositories(context);
        List<SearchAbleEntity> entities = extractor.extractMetadata(false);

        for (Class<?> domainType : repositories) {
            Object repoBean = repositories.getRepositoryFor(domainType).orElse(null);
            if (repoBean == null) {
                ;
                continue;
            }

            Object target = unwrapProxy(repoBean);
            if (!(target instanceof BaseRepositoryImpl<?, ?> repoImpl)) continue;

            String entityName = domainType.getSimpleName();
            SearchAbleEntity meta = entities.stream()
                    .filter(e -> e.getEntityName().equalsIgnoreCase(entityName))
                    .findFirst()
                    .orElse(null);

            if (meta == null) {
                continue;
            }

            var lucene = meta.getLucene();
            repoImpl.setConfig(lucene);

        }

        running = true;
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    private Object unwrapProxy(Object proxy) {
        try {
            if (AopUtils.isAopProxy(proxy) && proxy instanceof Advised advised) {
                Object target = advised.getTargetSource().getTarget();
                if (target != null) {
                    return target;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return proxy;
    }
}
