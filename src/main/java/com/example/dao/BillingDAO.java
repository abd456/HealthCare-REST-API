///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */


package com.example.dao;

import com.example.model.Appointment;
import com.example.model.Billing;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BillingDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingDAO.class);
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    private static List<Billing> billings = new ArrayList<>();
    
    static {
        billings.add(new Billing(1, 1299, 101, 401));
        billings.add(new Billing(2, 3499, 102, 1500));
        billings.add(new Billing(3, 581, 103, 919));
        billings.add(new Billing(4, 4500, 104, 7800));
    }
   
    public List<Billing> getAllBillings(){
        LOGGER.info("Getting all billings");
        return billings;
    }
    
    public Billing getBillingById(int id){
        LOGGER.info("Getting billing by ID: {}", id);
        for (Billing billing : billings) {
            if (billing.getId() == id){
                return billing;
            }
        }
        return null;
    }
    
    public void addBilling(Billing billing){
        LOGGER.info("Adding a new billing");
        int appointmentId = billing.getAppointmentId();
        Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
        billing.setAppointment(appointment);
        billings.add(billing);
    }
    
    public void updateBilling(Billing updatedBilling){
        LOGGER.info("Updating billing with ID: {}", updatedBilling.getId());
        for (int i = 0; i < billings.size(); i++) {
            Billing billing = billings.get(i);
            if (updatedBilling.getId() == billing.getId()){
                int appointmentId = updatedBilling.getAppointmentId();;
                Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
                updatedBilling.setAppointment(appointment);
                billings.set(i, updatedBilling);
            }
        }
    }
    
    public void deleteBilling(int id){
        LOGGER.info("Deleting billing with ID: {}", id);
        billings.removeIf(billing -> billing.getId() == id);
    }
}
