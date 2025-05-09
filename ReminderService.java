/* package Notifications;

import Appointment.Appointment;
import D_P_Interaction.Prescription;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class ReminderService {

    private final Notifiable emailNotification;

    // Constructor injection for flexibility
    public ReminderService(Notifiable emailNotification) {
        this.emailNotification = emailNotification;
    }

    // Send appointment reminder
    public void sendAppointmentReminder(Appointment appointment) {
        String message = "📅 Appointment Reminder:\n" +
                "You have an appointment with Dr. " + appointment.getDoctor().getName() + " on " + appointment.getDateTime() + " at " + "3:00 PM" + ".\n" +
                "Please be on time.";
        emailNotification.sendNotification(message, appointment.getPatient().getEmail());
    }

    // Send medication reminder
    public void sendMedicationReminder(Prescription prescription) {
        String message = "💊 Medication Reminder:\n" +
                "Please take your medication: " + prescription.getMedications() + " at " + "3:00PM" + ".\n" +
                "Stay healthy!";
        emailNotification.sendNotification(message, prescription.getPatient().getEmail());
    }
    
        private AppointmentManager appointmentManager;

        public ReminderService(AppointmentManager appointmentManager) {
            this.appointmentManager = appointmentManager;
        }

        public void start() {
            Timer timer = new Timer(true); // daemon thread
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    checkAppointments();
                }
            }, 0, 60 * 1000); // check every 60 seconds
        }

        private void checkAppointments() {
            List<Appointment> allAppointments = appointmentManager.getAllAppointments(); // implement this if needed

            LocalDateTime now = LocalDateTime.now();

            for (Appointment appointment : allAppointments) {
                LocalDateTime appointmentTime = appointment.getDateTime();

                // Check if appointment is 2 hours from now (±1 min buffer)
                long minutesUntil = Duration.between(now, appointmentTime).toMinutes();
                if (minutesUntil >= 119 && minutesUntil <= 121) {

                    // Get patient's email
                    String toEmail = appointment.getPatient().getEmail();
                    String subject = "Appointment Reminder";
                    String message = String.format("Dear %s,\n\nThis is a reminder for your appointment with Dr. %s at %s.",
                            appointment.getPatient().getName(),
                            appointment.getDoctor().getName(),
                            appointmentTime);

                    EmailNotification.sendEmail(toEmail, subject, message);
                    System.out.println("Reminder sent to: " + toEmail);
                }
            }
        }
    


}
*/



package Notifications;

import Appointment.Appointment;
import Appointment.AppointmentManager;
import D_P_Interaction.Prescription;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ReminderService {

    private final Notifiable emailNotification;
    private final AppointmentManager appointmentManager;

    // Constructor injection for emailNotification and appointmentManager
    public ReminderService(Notifiable emailNotification, AppointmentManager appointmentManager) {
        this.emailNotification = emailNotification;
        this.appointmentManager = appointmentManager;
    }

    // Send appointment reminder
    public void sendAppointmentReminder(Appointment appointment) {
        String message = "📅 Appointment Reminder:\n" +
                "You have an appointment with Dr. " + appointment.getDoctor().getName() + " on " + appointment.getDateTime() + " at 3:00 PM.\n" +
                "Please be on time.";
        emailNotification.sendNotification(message, appointment.getPatient().getEmail());
    }

    // Send medication reminder
    public void sendMedicationReminder(Prescription prescription) {
        String message = "💊 Medication Reminder:\n" +
                "Please take your medication: " + prescription.getMedications() + " at 3:00 PM.\n" +
                "Stay healthy!";
        emailNotification.sendNotification(message, prescription.getPatient().getEmail());
    }

    // Start the timer to check for upcoming appointments
    public void start() {
        Timer timer = new Timer(true); // daemon thread
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkAppointments();
            }
        }, 0, 60 * 1000); // check every 60 seconds
    }

    // Check all appointments and send reminders if 2 hours away
    private void checkAppointments() {
        List<Appointment> allAppointments = appointmentManager.getAppointments(); // You need to implement this method
        LocalDateTime now = LocalDateTime.now();

        for (Appointment appointment : allAppointments) {
            LocalDateTime appointmentTime = appointment.getDateTime();
            long minutesUntil = Duration.between(now, appointmentTime).toMinutes();

            // Check if appointment is approximately 2 hours away
            if (minutesUntil >= 119 && minutesUntil <= 121) {
                String toEmail = appointment.getPatient().getEmail();
                String subject = "Appointment Reminder";
                String message = String.format("Dear %s,\n\nThis is a reminder for your appointment with Dr. %s at %s.",
                        appointment.getPatient().getName(),
                        appointment.getDoctor().getName(),
                        appointmentTime);

                // Send the email via the notification system
                emailNotification.sendNotification(message, toEmail);
                System.out.println("Reminder sent to: " + toEmail);
            }
        }
    }
}
