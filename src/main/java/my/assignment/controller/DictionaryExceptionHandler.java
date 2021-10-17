package my.assignment.controller;

import my.assignment.exception.EntryNotExistException;
import my.assignment.exception.SynonymNotExistException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DictionaryExceptionHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable t) {
        if (t instanceof EntryNotExistException) {
            EntryNotExistException exc = (EntryNotExistException) t;
            String message = String.format("Entry not exist: %s", exc.getWord());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(message)
                    .build();
        }
        if (t instanceof SynonymNotExistException) {
            SynonymNotExistException exc = (SynonymNotExistException) t;
            String message = String.format("Synonym not exist: %s", exc.getSynonym());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(message)
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(t.getMessage())
                .build();
    }
}
