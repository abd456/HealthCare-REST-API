/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

/**
 *
 * @author abdhu
 */
public class Patient extends Person {
    int patientId;
    String medicalHistory;
    String healthStatus;

    public Patient(int patientId, String medicalHistory, String healthStatus, int id, String name, String contactInfo, String address) {
        super(id, name, contactInfo, address);
        this.patientId = patientId;
        this.medicalHistory = medicalHistory;
        this.healthStatus = healthStatus;
    }

//    public Patient(int patientId, String medicalHistory, String healthStatus) {
//        this.patientId = patientId;
//        this.medicalHistory = medicalHistory;
//        this.healthStatus = healthStatus;
//    }
//
//    

    public Patient() {  }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }
  
}
