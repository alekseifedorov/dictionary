package my.assignment.service;

import my.assignment.model.Entry;

public interface DictionaryService {

    Entry createOrUpdateEntry(Entry entry);

    Entry getEntry(String word);

    void deleteEntry(String word);

    void entryExist(String word);
}
