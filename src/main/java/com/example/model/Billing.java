/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

/**
 *
 * @author abdhu
 */
public class Billing {
        int id;
        int payments;
        int appointmentId;
        
        Appointment appointment;
        int outstandingBalances;

    public Billing(int id, int payments,int appointmentId, int outstandingBalances) {
        this.id = id;
        this.payments = payments;
        this.appointmentId = appointmentId;
        this.outstandingBalances = outstandingBalances;
    }

    public Billing() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPayments() {
        return payments;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
    
    public int getOutstandingBalances() {
        return outstandingBalances;
    }

    public void setOutstandingBalances(int outstandingBalances) {
        this.outstandingBalances = outstandingBalances;
    }
        
        

    
}
