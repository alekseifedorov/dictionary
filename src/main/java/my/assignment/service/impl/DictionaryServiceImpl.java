package my.assignment.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.assignment.exception.EntryNotExistException;
import my.assignment.exception.SynonymNotExistException;
import my.assignment.model.Entry;
import my.assignment.service.DictionaryService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final CacheService cacheService;

    @Override
    public Entry createOrUpdateEntry(Entry entry) {
        log.info("Create or update new entry {}", entry);
        return doCreateOrUpdateEntry(entry);
    }

    @Override
    public Entry getEntry(String word) {
        log.info("Get entry [{}]", word);
        return doGetEntry(word);
    }

    @Override
    public void deleteEntry(String word) {
        log.info("Delete entry [{}]", word);
        Entry entry = doGetEntry(word);
        if (!CollectionUtils.isEmpty(entry.getSynonyms())) {
            entry.getSynonyms().forEach(s ->
                    Optional.ofNullable(cacheService.getEntry(s))
                            .ifPresent(e -> {
                                entry.getSynonyms().remove(e.getKey());
                                cacheService.putEntry(entry.getKey(), entry);
                            })
            );
        }
        cacheService.removeEntry(word);
    }

    @Override
    public void entryExist(String word) {
        log.info("Entry exist [{}]", word);
        doGetEntry(word);
    }

    private Entry doGetEntry(String word) {
        return Optional.ofNullable(cacheService.getEntry(word))
                .orElseThrow(() -> new EntryNotExistException("Entry not found", word));
    }

    private Entry doCreateOrUpdateEntry(Entry e) {
        if (!CollectionUtils.isEmpty(e.getSynonyms())) {
            e.getSynonyms().forEach(s -> {
                        Entry entry = Optional.ofNullable(cacheService.getEntry(s))
                                .orElseThrow(() -> new SynonymNotExistException("Synonym not exist", s));
                        List<String> synonyms = Optional.ofNullable(entry.getSynonyms()).orElse(new ArrayList<>());
                        synonyms.add(e.getKey());
                        entry.setSynonyms(synonyms);
                        cacheService.putEntry(entry.getKey(), entry);
                    }
            );
        }
        return cacheService.putEntry(e.getKey(), e);
    }
}
