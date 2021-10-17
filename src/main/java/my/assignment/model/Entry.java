package my.assignment.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Entry {
    private String key;
    private String value;
    private List<String> synonyms;
}
