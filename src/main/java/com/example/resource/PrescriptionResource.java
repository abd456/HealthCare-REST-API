///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */

package com.example.resource;

import com.example.dao.PrescriptionDAO;
import com.example.exception.UserNotFoundException;
import com.example.model.Prescription;
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

@Path("/prescription")
public class PrescriptionResource {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PrescriptionResource.class);
    PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prescription> getAllPrescriptions() {
        return prescriptionDAO.getAllPrescriptions();
    }

    @GET
    @Path("/{prescriptionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrescriptionById(@PathParam("prescriptionId") int prescriptionId) {
        try {
            Prescription prescription = prescriptionDAO.getPrescriptionById(prescriptionId);
            if (prescription != null) {
                return Response.ok(prescription).build();
            } else {
                throw new UserNotFoundException("Prescription with ID " + prescriptionId + " not found.");
            }
        } catch (UserNotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ex.getMessage())
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public void addPrescription(Prescription prescription) {
        prescriptionDAO.addPrescription(prescription);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{prescriptionId}")
    public Response updatePrescription(@PathParam("prescriptionId") int prescriptionId, Prescription updatedPrescription) {
        Prescription existingPrescription = prescriptionDAO.getPrescriptionById(prescriptionId);

        if (existingPrescription != null) {
            updatedPrescription.setId(prescriptionId);
            prescriptionDAO.updatePrescription(updatedPrescription);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Prescription with ID " + prescriptionId + " not found.")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{prescriptionId}")
    public void deletePrescription(@PathParam("prescriptionId") int prescriptionId) {
        prescriptionDAO.deletePrescription(prescriptionId);
    }
}
