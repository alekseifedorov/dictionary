package my.assignment.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import my.assignment.model.Entry;
import my.assignment.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@AllArgsConstructor
@NoArgsConstructor
@Path("/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @POST
    @Path("/entry")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createOrUpdateEntry(Entry entry) {
       dictionaryService.createOrUpdateEntry(entry);
    }

    @GET
    @Path("/entry/{word}")
    @Produces(MediaType.APPLICATION_JSON)
    public Entry getEntry(@PathParam("word") String word) {
     return dictionaryService.getEntry(word);
    }

    @DELETE
    @Path("/entry/{word}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("word") String word) {
        dictionaryService.deleteEntry(word);
    }

    @HEAD
    @Path("/entry/{word}")
    public void exist(@PathParam("word") String word) {
        dictionaryService.getEntry(word);
    }
}
