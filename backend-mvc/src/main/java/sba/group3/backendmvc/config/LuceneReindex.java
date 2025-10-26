package sba.group3.backendmvc.config;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LuceneReindex {

    EntityManager entityManager;

    @NonFinal
    @Value("${app.reindex}")
    boolean reindex;

    @Async
    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        if (!reindex) {
            return;
        }
        try {
            log.info("Starting Lucene mass index...");
            SearchSession searchSession = Search.session(entityManager);

            searchSession.massIndexer()
                    .threadsToLoadObjects(4)
                    .batchSizeToLoadObjects(50)
                    .typesToIndexInParallel(2)
                    .idFetchSize(100)
                    .purgeAllOnStart(true)
                    .startAndWait();
            log.info("Lucene mass index successfully started");
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Lucene mass index failed", e);
        }
    }


}
