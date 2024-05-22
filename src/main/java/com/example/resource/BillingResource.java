///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */

package com.example.resource;

import com.example.dao.BillingDAO;
import com.example.dao.AppointmentDAO;
import com.example.exception.UserNotFoundException;
import com.example.model.Billing;
import com.example.model.Appointment;
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

@Path("/billing")
public class BillingResource {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BillingResource.class);
    BillingDAO billingDAO = new BillingDAO();
    AppointmentDAO appointmentDAO = new AppointmentDAO();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Billing> getAllBillings() {
        return billingDAO.getAllBillings();
    }

    @GET
    @Path("/{billingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBillingById(@PathParam("billingId") int billingId) {
        try {
            Billing billing = billingDAO.getBillingById(billingId);
            if (billing != null) {
                return Response.ok(billing).build();
            } else {
                throw new UserNotFoundException("Billing with ID " + billingId + " not found.");
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
    public void addBilling(Billing billing) {
        int appointmentId = billing.getAppointmentId();
        Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
        billing.setAppointment(appointment);
        billingDAO.addBilling(billing);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{billingId}")
    public Response updateBilling(@PathParam("billingId") int billingId, Billing updatedBilling) {
        Billing existingBilling = billingDAO.getBillingById(billingId);

        if (existingBilling != null) {
            updatedBilling.setId(billingId);
            billingDAO.updateBilling(updatedBilling);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Billing with ID " + billingId + " not found.")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{billingId}")
    public void deleteBilling(@PathParam("billingId") int billingId) {
        billingDAO.deleteBilling(billingId);
    }
}
