package my.assignment.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class Synonym {
    private Set<String> values;
}
