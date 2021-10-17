package my.assignment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "Dictionary REST API")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @POST
    @Path("/entry")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create or update an entry")
    public void createOrUpdateEntry(Entry entry) {
        dictionaryService.createOrUpdateEntry(entry);
    }

    @GET
    @Path("/entry/{word}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Gets an entry with synonyms", response = Entry.class)
    public Entry getEntry(@PathParam("word") String word) {
        return dictionaryService.getEntry(word);
    }

    @DELETE
    @Path("/entry/{word}")
    @ApiOperation(value = "Delete a word")
    public void delete(@PathParam("word") String word) {
        dictionaryService.deleteEntry(word);
    }

    @HEAD
    @Path("/entry/{word}")
    @ApiOperation(value = "Check whether a given word exists in the dictionary")
    public void exist(@PathParam("word") String word) {
        dictionaryService.getEntry(word);
    }
}
