package my.assignment.controller;

import lombok.extern.slf4j.Slf4j;
import my.assignment.exception.EntryNotExistException;
import my.assignment.exception.SynonymNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class DictionaryExceptionHandler {

    @ExceptionHandler(EntryNotExistException.class)
    public ResponseEntity<String> handleEntryNotExistEntry(EntryNotExistException e) {
        String message = String.format("Entry not exist: %s", e.getWord());
        log.error(message);
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SynonymNotExistException.class)
    public ResponseEntity<String> handleSynonymNotExist(SynonymNotExistException e) {
        String message = String.format("Synonym not exist: %s", e.getSynonym());
        log.error(message);
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleError(Exception e) {

        log.error("Error: {}", e.getCause(), e);

        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
