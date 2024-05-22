///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */


package com.example.resource;

import com.example.dao.PatientDAO;
import com.example.exception.UserNotFoundException;
import com.example.model.Patient;
import java.util.List;
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
import org.slf4j.LoggerFactory;

@Path("/patient")
public class PatientResource {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);
    PatientDAO patientDAO = new PatientDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Patient> getAllPatients() {
        LOGGER.info("getting all patients...");
        return patientDAO.getAllPatients();
    }

    @GET
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatientById(@PathParam("patientId") int patientId) {
        try {
            Patient patient = patientDAO.getPatientById(patientId);
            if (patient != null) {
                return Response.ok(patient).build();
            } else {
                throw new UserNotFoundException("Patient with ID " + patientId + " not found.");
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
    public void addPatient(Patient patient) {
        patientDAO.addPatient(patient);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{patientId}")
    public Response updatePatient(@PathParam("patientId") int patientId, Patient updatedPatient) {
        Patient existingPatient = patientDAO.getPatientById(patientId);

        if (existingPatient != null) {
            updatedPatient.setPatientId(patientId);
            patientDAO.updatePatient(updatedPatient);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Patient with ID " + patientId + " not found.")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{patientId}")
    public void deletePatient(@PathParam("patientId") int patientId) {
        patientDAO.deletePatient(patientId);
    }
}
