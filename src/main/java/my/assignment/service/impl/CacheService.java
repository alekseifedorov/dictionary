package my.assignment.service.impl;

import lombok.RequiredArgsConstructor;
import my.assignment.model.Entry;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Cache service to implement dictionary.
 * Redis can replace the default Spring cache.
 * Document-based DB such as Elasticsearch or MongoDB can be used as well.
 */
@Service
@RequiredArgsConstructor
public class CacheService {

    @CachePut(value = "dictionary", key = "#word")
    public Entry putEntry(String word, Entry entry) {
        return entry;
    }

    @Cacheable(value = "dictionary", key = "#word")
    public Entry getEntry(String word) {
        return null;
    }

    @CacheEvict(value = "dictionary", key = "#word")
    public void removeEntry(String word) {
        // evict value in cache
    }
}
