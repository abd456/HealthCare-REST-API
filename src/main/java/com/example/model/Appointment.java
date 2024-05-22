/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

import com.example.dao.DoctorDAO;
import com.example.dao.PatientDAO;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author abdhu
 */
public class Appointment {
    int id;
    int patId;
    int docId;
    Patient patient;
    Doctor doctor;
    String date;
    String time;
    
    

    public Appointment(int id,int patId, int docId, String date, String time) {
        this.id = id;
        this.patId = patId;
        this.docId = docId;
        this.date = date;
        this.time = time;
    }

    public Appointment() {
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public int getId() {
        return id;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    
   
    

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    
    
}
