


package com.example.resource;

import com.example.dao.PersonDAO;
import com.example.exception.UserNotFoundException;
import com.example.model.Person;
import java.util.List;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/person")
public class PersonResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);

    private PersonDAO personDAO = new PersonDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPeople() {
        return personDAO.getAllPeople();
    }

    @GET
    @Path("/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonById(@PathParam("personId") int personId) {
        try {
            Person person = personDAO.getPersonById(personId);
            if (person != null) {
                return Response.ok(person).build();
            } else {
                throw new UserNotFoundException("Person with ID " + personId + " not found.");
            }
        } catch (UserNotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ex.getMessage())
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        } 
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPerson(Person person) {
        personDAO.addPerson(person);
    }

    @PUT
    @Path("/update/{personId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("personId") int personId, Person updatedPerson) {
        Person existingPerson = personDAO.getPersonById(personId);

        if (existingPerson != null) {
            updatedPerson.setId(personId);
            personDAO.updatePerson(updatedPerson);
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Person with ID " + personId + " not found.")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{personId}")
    public void deleteModule(@PathParam("personId") int personId) {
        personDAO.deletePerson(personId);
    }
}
