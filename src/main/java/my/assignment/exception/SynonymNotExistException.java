package my.assignment.exception;

import lombok.Getter;

@Getter
public class SynonymNotExistException extends RuntimeException {

    private final String synonym;

    public SynonymNotExistException(String message, String synonym) {
        super(message);
        this.synonym = synonym;
    }
}

