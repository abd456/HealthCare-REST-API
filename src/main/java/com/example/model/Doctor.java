/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

/**
 *
 * @author abdhu
 */
public class Doctor extends Person{
    
    int doctorId;
    String specialization;
    String contactDetails;

    public Doctor(int doctorId, String specialization, String contactDetails, int id, String name, String contactInfo, String address) {
        super(id, name, contactInfo, address);
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.contactDetails = contactDetails;
    }

    public Doctor() {   }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
    
    
    
}
