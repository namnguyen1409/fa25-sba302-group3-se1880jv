package sba.group3.backendmvc.service.infrastructure.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.service.infrastructure.CacheService;

import java.time.Duration;
import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CacheServiceImpl implements CacheService {

    RedisTemplate<String, Object> redisTemplate;

    @Override
    public void put(String key, Object value, Duration ttl) {
        try {
            redisTemplate.opsForValue().set(key, value, ttl);
            log.debug("Cached key={} with ttl={}", key, ttl);
        } catch (Exception e) {
            log.error("Failed to cache key={}: {}", key, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key, Class<T> type) {
        try {
            var value = redisTemplate.opsForValue().get(key);
            if (type.isInstance(value)) {
                log.debug("Retrieved key={} from cache", key);
                return (T) value;
            }
            log.debug("Key={} not found in cache or type mismatch", key);
            return null;
        } catch (Exception e) {
            log.error("Failed to retrieve key={}: {}", key, e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void evict(String key) {
        try {
            redisTemplate.delete(key);
            log.debug("Evicted key={}", key);
        } catch (Exception e) {
            log.error("Failed to evict key={}: {}", key, e.getMessage());
        }
    }

    @Override
    public void evictByPattern(String pattern) {
        var keys = redisTemplate.keys(pattern);
        if (!keys.isEmpty()) {
            redisTemplate.delete(keys);
            log.debug("Evicted keys matching pattern={}", pattern);
        }
    }

    @Override
    public <T> T getOrCompute(String key, Class<T> type, Duration ttl, Supplier<T> dbLoader) {
        T cached = get(key, type);
        if (cached != null) return cached;

        T value = dbLoader.get();
        if (value != null) {
            put(key, value, ttl);
        }
        return value;
    }

}
