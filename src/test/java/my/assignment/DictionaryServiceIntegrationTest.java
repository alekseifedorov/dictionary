package my.assignment;

import my.assignment.model.Entry;
import my.assignment.service.DictionaryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(classes = TestConfig.class)
class DictionaryServiceIntegrationTest {

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private CacheManager cacheManager;

    @AfterEach
    void cleanUp() {
        cacheManager.getCache("dictionary").clear();
    }

    @Test
    void shouldCreateSynonyms() {
        dictionaryService.createOrUpdateEntry(Entry.builder().key("dictionary")
                .value("Woerterbuch").build());
        dictionaryService.createOrUpdateEntry(Entry.builder().key("encyclopaedia")
                .value("Enzyklopaedie").build());
        dictionaryService.createOrUpdateEntry(Entry.builder().key("lexicon")
                .value("Lexicon").build());
        Entry entry = dictionaryService.getEntry("dictionary");

        Set<String> synonyms = new HashSet<>();
        synonyms.add("lexicon");
        synonyms.add("encyclopaedia");
        entry.setSynonyms(synonyms);
        dictionaryService.createOrUpdateEntry(entry);

        assertThat(dictionaryService.getEntry("dictionary").getValue()).isEqualTo("Woerterbuch");
        assertThat(dictionaryService.getEntry("encyclopaedia").getSynonyms())
                .contains("dictionary");
    }

    @Test
    void shouldRemoveSynonym() {
        dictionaryService.createOrUpdateEntry(Entry.builder().key("dictionary")
                .value("Woerterbuch").build());
        dictionaryService.createOrUpdateEntry(Entry.builder().key("encyclopaedia")
                .value("Enzyklopaedie").build());
        dictionaryService.createOrUpdateEntry(Entry.builder().key("lexicon")
                .value("Lexicon").build());
        Entry entry = dictionaryService.getEntry("dictionary");

        Set<String> synonyms = new HashSet<>();
        synonyms.add("lexicon");
        synonyms.add("encyclopaedia");
        entry.setSynonyms(synonyms);
        dictionaryService.createOrUpdateEntry(entry);

        dictionaryService.deleteEntry("dictionary");
        assertThat(dictionaryService.getEntry("encyclopaedia").getSynonyms())
                .doesNotContain("dictionary");
    }
}
