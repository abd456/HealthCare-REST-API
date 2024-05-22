///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */



package com.example.dao;

import com.example.model.Doctor;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoctorDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorDAO.class);

    static List<Doctor> doctorsList = new ArrayList<>();
    
    static {
        doctorsList.add(new Doctor(901, "Eye Specialist", "0770986354", 5, "Dr. Saman Weerasinghe", "saman@example.com", "Osro Mawanella"));
        doctorsList.add(new Doctor(902, "Ear Specialist", "0775588554", 6, "Dr. John Kothala", "john@example.com", "Osro Kegalle"));
        doctorsList.add(new Doctor(903, "V.O.G", "0770986354", 7, "Dr. Ranil Weerasinghe", "ranil@example.com", "Apolo"));
        doctorsList.add(new Doctor(904, "Nose Doctor", "0770986354", 8, "Dr. Sajith Rajangana", "sajith@example.com", "Asiri Mawanella"));
    }
   
    public List<Doctor> getAllDoctors(){
        LOGGER.info("Getting all doctors");
        return doctorsList;
    }
    
    public Doctor getDoctorById(int id){
        LOGGER.info("Getting doctor by ID: {}", id);
        for (Doctor doctor : doctorsList) {
            if (doctor.getDoctorId()==id){
                return doctor;
            }
        }
        return null;
    }
    
    public void addDoctor(Doctor doctor){
        LOGGER.info("Adding new doctor: {}", doctor.getName());
        doctorsList.add(doctor);
    }
    
    public void updateDoctor(Doctor updatedDoctor){
        LOGGER.info("Updating doctor with ID: {}", updatedDoctor.getDoctorId());
        for (int i = 0; i < doctorsList.size(); i++) {
            Doctor doctor = doctorsList.get(i);
            if (updatedDoctor.getDoctorId()==doctor.getDoctorId()){
                doctorsList.set(i,updatedDoctor);
                LOGGER.info("Doctor updated: {}", updatedDoctor);
            }
        }
    }
    
    public void deleteDoctor(int id){
        LOGGER.info("Deleting doctor with ID: {}", id);
        doctorsList.removeIf(Doctor->Doctor.getDoctorId()==id);
    }
}
