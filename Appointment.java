package Appointment;

import User.Doctor;


import User.Patient;
import User.User;
import Exceptions.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

// a central class for Appointment storage
public class Appointment {
    // enum for status
    public enum AppointmentStatus {
        PENDING,   // Waiting for approval
        APPROVED,  // Approved by admin or doctor
        CANCELED,  // Canceled by patient or admin
        COMPLETED, // Successfully completed appointment
        NO_SHOW,   // Patient didn't attend the appointment
        REJECTED   // Rejected by the doctor/admin
    }

    // private class attributes
    private final String appointmentID = User.randomIdGenerator();
    private LocalDateTime dateTime; // Date and time of the appointment
    private final Doctor doctor;
    private final Patient patient;
    private AppointmentStatus status;
    private AppointmentType type = AppointmentType.IN_PERSON; //CHANGE MADE 
    

    // Constructor
    public Appointment(LocalDateTime dateTime, Doctor doctor, Patient patient, AppointmentStatus status) throws InvalidAppointmentException {
        if (dateTime == null) {
            throw new InvalidAppointmentException("Appointment date and time cannot be null.");
        }
        if (doctor == null) {
            throw new InvalidAppointmentException("Doctor cannot be null.");
        }
        if (patient == null) {
            throw new InvalidAppointmentException("Patient cannot be null.");
        }
        this.dateTime = dateTime;
        this.doctor = doctor;
        this.patient = patient;
        this.status = status;
    }


    // getters
    public String getAppointmentID() {
        return appointmentID;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    // update status
    public void updateStatus(AppointmentStatus status) {
        this.status = status;
    }

    public void updateDateTime(LocalDateTime newDateTime) {
        this.dateTime = newDateTime;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentID + ", Appointment Date and Time: " + dateTime + ", Doctor: " + doctor.getName() + ", Patient: " + patient.getName() + ", Status: " + status;
    }
    
    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        if (type != null) {
            this.type = type;
        }
    }

    
  /*  public Appointment(Patient patient, String doctorName, String speciality, LocalDate date) throws InvalidAppointmentException {
        if (patient == null || doctorName == null || speciality == null || date == null) {
            throw new InvalidAppointmentException("All appointment details must be provided.");
        }

        this.dateTime = date.atStartOfDay(); // convert LocalDate to LocalDateTime
        this.patient = patient;
        this.doctor = new Doctor(doctorName, speciality); // Create a new Doctor object directly
        this.status = AppointmentStatus.PENDING;
    }
*/
    public enum AppointmentType {
        IN_PERSON,
        VIDEO
    }

    
}