///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */

package com.example.dao;

import com.example.model.Appointment;
import com.example.model.Doctor;
import com.example.model.Patient;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author abdhu
 */
public class AppointmentDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentDAO.class);
    PatientDAO patientDAO = new PatientDAO();
    DoctorDAO doctorDAO = new DoctorDAO();
    
    static List<Appointment> appointments = new ArrayList<>();

    static {
        appointments.add(new Appointment(1, 101, 902, "2024-03-03", "20:30"));
        appointments.add(new Appointment(2, 103, 901, "2024-02-28", "10:20"));
        appointments.add(new Appointment(3, 102, 901, "2024-02-28", "10:20"));
        appointments.add(new Appointment(4, 104, 904, "2024-03-12", "10:20"));
    }
    
    public static List<Appointment> getAllAppointments(){
        LOGGER.info("Retrieving all appointments");
        return appointments;
    }
    
    public Appointment getAppointmentById(int id){
        LOGGER.info("Retrieving appointment by ID: {}", id);
        for (Appointment appointment : appointments) {
            if (appointment.getId() == id){
                return appointment;
            }
        }
        return null;
    }
    
    public void addAppointment(Appointment appointment){
        LOGGER.info("Adding new appointment");
        int patId = appointment.getPatId();
        int docId = appointment.getDocId();
        
        Patient patient = patientDAO.getPatientById(patId);
        Doctor doctor = doctorDAO.getDoctorById(docId);
        
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointments.add(appointment);
    }
    
    public void updateAppointment(Appointment updatedAppointment){
        LOGGER.info("Updating appointment with ID: {}", updatedAppointment.getId());
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (updatedAppointment.getId() == appointment.getId()){
                int patId = updatedAppointment.getPatId();
                int docId = updatedAppointment.getDocId();
                
                Patient patient  = patientDAO.getPatientById(patId);
                Doctor doctor = doctorDAO.getDoctorById(docId);
                
                updatedAppointment.setDoctor(doctor);
                updatedAppointment.setPatient(patient);
                appointments.set(i,updatedAppointment);
            }
        }
    }
    
    public void deleteAppointment(int id){
        LOGGER.info("Deleting appointment with ID: {}", id);
        appointments.removeIf(appointment -> appointment.getId() == id);
    }
    
}
