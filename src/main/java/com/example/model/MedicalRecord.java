/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

import com.example.dao.PatientDAO;

/**
 *
 * @author abdhu
 */
public class MedicalRecord {
    int id;
    int patId;
    int docId;
    Patient patient;
    Doctor doctor;
    String diagnosis;
    String treatment;
    String releventData;
    
    PatientDAO patientDAO = new PatientDAO();

    public MedicalRecord(int id, int patId, int docId, String diagnosis, String treatment, String releventData) {
        this.id = id;
        this.patId = patId;
        this.docId = docId;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.releventData = releventData;
    }

    public MedicalRecord() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getPatId() {
        return patId;
    }

    public void setPatId(int patId) {
        this.patId = patId;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getReleventData() {
        return releventData;
    }

    public void setReleventData(String releventData) {
        this.releventData = releventData;
    }
    
    
    
}
