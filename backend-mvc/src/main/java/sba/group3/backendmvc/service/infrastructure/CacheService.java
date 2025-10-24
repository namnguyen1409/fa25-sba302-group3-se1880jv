package sba.group3.backendmvc.service.infrastructure;

import java.time.Duration;
import java.util.function.Supplier;

public interface CacheService {
    void put(String key, Object value, Duration ttl);

    <T> T get(String key, Class<T> type);

    Boolean exists(String key);

    void evict(String key);

    void evictByPattern(String pattern);

    <T> T getOrCompute(String key, Class<T> type, Duration ttl, Supplier<T> dbLoader);
}
