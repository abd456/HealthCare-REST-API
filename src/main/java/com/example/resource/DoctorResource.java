///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */


package com.example.resource;

import com.example.dao.DoctorDAO;
import com.example.exception.UserNotFoundException;
import com.example.model.Doctor;
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

@Path("/doctor")
public class DoctorResource {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DoctorResource.class);
    DoctorDAO doctorDAO = new DoctorDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Doctor> getAllDoctors() {
        return doctorDAO.getAllDoctors();
    }

    @GET
    @Path("/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorById(@PathParam("doctorId") int doctorId) {
        try {
            Doctor doctor = doctorDAO.getDoctorById(doctorId);
            if (doctor != null) {
                return Response.ok(doctor).build();
            } else {
                throw new UserNotFoundException("Doctor with ID " + doctorId + " not found.");
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
    public void addDoctor(Doctor doctor) {
        doctorDAO.addDoctor(doctor);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{doctorId}")
    public Response updateDoctor(@PathParam("doctorId") int doctorId, Doctor updatedDoctor) {
        Doctor existingDoctor = doctorDAO.getDoctorById(doctorId);

        if (existingDoctor != null) {
            updatedDoctor.setDoctorId(doctorId);
            doctorDAO.updateDoctor(updatedDoctor);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Doctor with ID " + doctorId + " not found.")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{doctorId}")
    public void deleteDoctor(@PathParam("doctorId") int doctorId) {
        doctorDAO.deleteDoctor(doctorId);
    }
}
