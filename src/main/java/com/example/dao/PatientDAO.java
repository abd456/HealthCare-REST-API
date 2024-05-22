
package com.example.dao;

import com.example.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientDAO.class);

    static List<Patient> patients = new ArrayList<>();

    static {
        patients.add(new Patient(101, "History of Alzheimer's disease", "Very poor", 1, "Sammy", "sammy@example.com", "Nuwara"));
        patients.add(new Patient(102, "History of Diarrhea", "Very poor", 2, "Nuwan", "nuwan@example.com", "Colombo"));
        patients.add(new Patient(103, "No significant medical history", "Very good", 3, "Lahiru", "lahiru@example.com", "Matara"));
        patients.add(new Patient(104, "History of Alzheimer's disease", "Very poor", 4, "Jason", "jason@example.com", "Galle"));
    }

    public List<Patient> getAllPatients() {
        LOGGER.info("Fetching all patients");
        return patients;
    }

    public Patient getPatientById(int id) {
        LOGGER.info("Fetching patient by ID: {}", id);
        for (Patient patient : patients) {
            if (patient.getPatientId() == id) {
                return patient;
            }
        }
        return null;
    }

    public void addPatient(Patient patient) {
        LOGGER.info("Adding new patient: {}", patient);
        patients.add(patient);
    }

    public void updatePatient(Patient updatedPatient) {
        LOGGER.info("Updating patient: {}", updatedPatient);
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            if (patient.getPatientId() == updatedPatient.getPatientId()) {
                patients.set(i, updatedPatient);
                LOGGER.info("Patient updated successfully: {}", updatedPatient);
                return;
            }
        }
        LOGGER.error("Failed to update patient. Patient with ID {} not found.", updatedPatient.getPatientId());
    }

    public void deletePatient(int id) {
        LOGGER.info("Deleting patient with ID: {}", id);
        patients.removeIf(patient -> patient.getPatientId() == id);
    }
}
