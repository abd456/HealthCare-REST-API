///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */


package com.example.resource;

import com.example.dao.AppointmentDAO;
import com.example.exception.UserNotFoundException;
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

@Path("/appointment")
public class AppointmentResource {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AppointmentResource.class);
    AppointmentDAO appointmentDAO = new AppointmentDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Appointment> getAllAppointments() {
        return AppointmentDAO.getAllAppointments();
    }

    @GET
    @Path("/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentById(@PathParam("appointmentId") int appointmentId) {
        try {
            Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
            if (appointment != null) {
                return Response.ok(appointment).build();
            } else {
                throw new UserNotFoundException("Appointment with ID " + appointmentId + " not found.");
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
    public void addAppointment(Appointment appointment) {
        appointmentDAO.addAppointment(appointment);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{appointmentId}")
    public Response updateAppointment(@PathParam("appointmentId") int appointmentId, Appointment updatedAppointment) {
        Appointment existingAppointment = appointmentDAO.getAppointmentById(appointmentId);

        if (existingAppointment != null) {
            updatedAppointment.setId(appointmentId);
            appointmentDAO.updateAppointment(updatedAppointment);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Appointment with ID " + appointmentId + " not found.")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{appointmentId}")
    public void deleteAppointment(@PathParam("appointmentId") int appointmentId) {
        appointmentDAO.deleteAppointment(appointmentId);
    }
}
