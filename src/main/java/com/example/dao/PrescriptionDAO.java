///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */


package com.example.dao;

import com.example.model.Doctor;
import com.example.model.MedicalRecord;
import com.example.model.Patient;
import com.example.model.Prescription;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrescriptionDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionDAO.class);

    private PatientDAO patientDAO = new PatientDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();
    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();
    private static List<Prescription> prescriptions = new ArrayList<>();

    static {
        prescriptions.add(new Prescription(1, "Aspirin", "10mg", "Take with water", 101, 902, 1, 7));
        prescriptions.add(new Prescription(2, "Paracetamol", "500mg", "Take after meals", 102, 903, 2, 5));
        prescriptions.add(new Prescription(3, "Antibiotic", "250mg", "Take twice daily", 103, 904, 3, 10));
        prescriptions.add(new Prescription(4, "Cough syrup", "5ml", "Take before bedtime", 104, 903, 4, 3));
    }

    public List<Prescription> getAllPrescriptions() {
        LOGGER.info("Getting all prescriptions");
        return prescriptions;
    }

    public Prescription getPrescriptionById(int id) {
        LOGGER.info("Getting prescription by ID: {}", id);
        for (Prescription prescription : prescriptions) {
            if (prescription.getId() == id) {
                return prescription;
            }
        }
        return null;
    }

    public void addPrescription(Prescription prescription) {
        int patId = prescription.getPatId();
        int docId = prescription.getDocId();
        int medRecId = prescription.getMedRecId();

        Patient patient = patientDAO.getPatientById(patId);
        Doctor doctor = doctorDAO.getDoctorById(docId);
        MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecordById(medRecId);

        prescription.setPatient(patient);
        prescription.setDoctor(doctor);
        prescription.setMedicalRecord(medicalRecord);
        prescriptions.add(prescription);
        LOGGER.info("Prescription added: {}", prescription);
    }

    public void updatePrescription(Prescription updatedPrescription) {
        LOGGER.info("Updating prescription with ID: {}", updatedPrescription.getId());
        for (int i = 0; i < prescriptions.size(); i++) {
            Prescription prescription = prescriptions.get(i);
            if (updatedPrescription.getId() == prescription.getId()) {
                int patId = updatedPrescription.getPatId();
                int docId = updatedPrescription.getDocId();
                int medRecId = updatedPrescription.getMedRecId();

                Patient patient = patientDAO.getPatientById(patId);
                Doctor doctor = doctorDAO.getDoctorById(docId);
                MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecordById(medRecId);

                updatedPrescription.setPatient(patient);
                updatedPrescription.setDoctor(doctor);
                updatedPrescription.setMedicalRecord(medicalRecord);

                prescriptions.set(i, updatedPrescription);
                LOGGER.info("Prescription updated: {}", updatedPrescription);
                return;
            }
        }
        LOGGER.warn("Prescription with ID {} not found for updating", updatedPrescription.getId());
    }

    public void deletePrescription(int id) {
        LOGGER.info("Deleting prescription with ID: {}", id);
        prescriptions.removeIf(p -> p.getId() == id);
    }
}
