package my.assignment.exception;

import lombok.Getter;

@Getter
public class EntryNotExistException extends RuntimeException {

    private final String word;

    public EntryNotExistException(String message, String word) {
        super(message);
        this.word = word;
    }
}

