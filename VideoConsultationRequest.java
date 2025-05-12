package ChatVideoConsultation;


import User.Doctor;
import User.Patient;

public class VideoConsultationRequest {
    private Patient patient;
    private Doctor doctor;
    private String message;
    private boolean isScheduled;

    public VideoConsultationRequest(Patient patient, Doctor doctor, String message) {
        this.patient = patient;
        this.doctor = doctor;
        this.message = message;
        this.isScheduled = false;
    }

    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public String getMessage() { return message; }
    public boolean isScheduled() { return isScheduled; }
    public void setScheduled(boolean scheduled) { isScheduled = scheduled; }
    public void markScheduled() {this.isScheduled = true;
    }
    
}
