///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */



package com.example.dao;

import com.example.model.Doctor;
import com.example.model.MedicalRecord;
import com.example.model.Patient;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author abdhu
 */
public class MedicalRecordDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordDAO.class);
    DoctorDAO doctorDAO = new DoctorDAO();
    PatientDAO patientDAO = new PatientDAO();
    static List<MedicalRecord> medicalRecords = new ArrayList<>();
    
    static {
        medicalRecords.add(new MedicalRecord(1, 101, 902, "Fever", "Rest and plenty of fluids", "None"));
        medicalRecords.add(new MedicalRecord(2, 102, 903, "Headache", "Painkillers and rest", "MRI report attached"));
        medicalRecords.add(new MedicalRecord(3, 103, 901, "Sore throat", "Antibiotics and warm drinks", "Previous prescription included"));
        medicalRecords.add(new MedicalRecord(4, 104, 904, "Fractured arm", "Splint applied", "X-ray image provided"));
    }

    public List<MedicalRecord> getAllMedicalRecords(){
        LOGGER.info("Getting all medical records");
        return medicalRecords;
    }
    
    public MedicalRecord getMedicalRecordById(int id){
        LOGGER.info("Getting medical record by ID: {}", id);
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getId()==id){
                return medicalRecord;
            }
        }
        return null;
    }
    
    public void addMedicalRecord(MedicalRecord medicalRecord){
        LOGGER.info("Adding new medical record: {}", medicalRecord);
        int patId = medicalRecord.getPatId();
        int docId = medicalRecord.getDocId();
        
        Patient patient = patientDAO.getPatientById(patId);
        Doctor doctor = doctorDAO.getDoctorById(docId);
        
        medicalRecord.setPatient(patient);
        medicalRecord.setDoctor(doctor);
        medicalRecords.add(medicalRecord);
    }
    
    public void updateMedicalRecord(MedicalRecord updatedMedicalRecord){
        LOGGER.info("Updating medical record: {}", updatedMedicalRecord);
        for (int i = 0; i < medicalRecords.size(); i++) {
            MedicalRecord medicalRecord = medicalRecords.get(i);
            if (updatedMedicalRecord.getId() == medicalRecord.getId()){
                int patId = updatedMedicalRecord.getPatId();
                int docId = updatedMedicalRecord.getDocId();

                Patient patient = patientDAO.getPatientById(patId);
                Doctor doctor = doctorDAO.getDoctorById(docId);

                updatedMedicalRecord.setPatient(patient);
                updatedMedicalRecord.setDoctor(doctor);
                
                medicalRecords.set(i,updatedMedicalRecord);
            }
        }
    }
    
    public void deleteMedicalRecord(int id){
        LOGGER.info("Deleting medical record with ID: {}", id);
        medicalRecords.removeIf(medicalRecord -> medicalRecord.getId() == id);
    }
    
}
