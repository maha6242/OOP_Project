package com.hamza6dev.oopsieeee;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ChatVideoConsultation.videoConsultationManager;
import ChatVideoConsultation.VideoConsultationRequest;
import User.Doctor;
import User.Patient;
import User.Session;
import Appointment.Appointment;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;




public class PatientDashboard extends Application {

    private BorderPane root;

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();

        VBox sidebar = createSidebar();
        VBox header = createHeader();
        VBox mainContent = createMainContent();
        VBox rightPanel = createRightPanel();

        root.setLeft(sidebar);
        root.setTop(header);
        root.setCenter(mainContent);
        root.setRight(rightPanel);

        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setTitle("Patient Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #f5f5fc;");
        sidebar.setPrefWidth(200);

        Label title = new Label("Hospital");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.DARKMAGENTA);

        Button dashboard = createSidebarButton("Dashboard");
        Button appointments = createSidebarButton("Appointments");
        Button records = createSidebarButton("Medical Records");
        Button prescriptions = createSidebarButton("Prescriptions");
        Button profile = createSidebarButton("Profile");
        Button logout = new Button("Logout");
        logout.setMaxWidth(Double.MAX_VALUE);
        logout.setStyle("-fx-background-color: darkmagenta; -fx-text-fill: white;");
        
        //CHANGES MADE FOR VIDEO REQUEST 
        Button videoConsultBtn = createSidebarButton("Request Video Call");
        videoConsultBtn.setOnAction(e -> openVideoConsultDialog());


        sidebar.getChildren().addAll(title, dashboard, appointments, records, prescriptions, profile,videoConsultBtn, logout);
        return sidebar;
    }

    //ADDED VIDEOCONSULTBTN
    
    private Button createSidebarButton(String text) {
        Button button = new Button(text);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setStyle("-fx-background-color: transparent; -fx-font-size: 14px;");
        return button;
    }

    private VBox createHeader() {
        VBox header = new VBox();
        header.setPadding(new Insets(20));
        header.setStyle("-fx-background-color: #ffffff;");
        Label title = new Label("Welcome, Patient");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        header.getChildren().add(title);
        return header;
    }

    private VBox createMainContent() {
        VBox content = new VBox(20);
        content.setPadding(new Insets(20));

        HBox cards = new HBox(20);
        cards.getChildren().addAll(
                createCard("Upcoming Appointment", "10:00 AM"),
                createCard("Pending Fees", "$120"),
                createCard("Active Prescriptions", "2")
        );

        VBox recent = new VBox(10);
        recent.getChildren().addAll(
                new Label("Recent Activities:"),
                new Label("- 12 Apr: General Checkup"),
                new Label("- 10 Apr: Prescription Updated")
        );

        content.getChildren().addAll(cards, recent);
        return content;
    }

    private VBox createRightPanel() {
        VBox right = new VBox(15);
        right.setPadding(new Insets(20));
        right.setAlignment(Pos.TOP_CENTER);
        right.setStyle("-fx-background-color: #ffffff;");
        right.setPrefWidth(250);

        Image image;
        try {
            image = new Image("https://www.w3schools.com/howto/img_avatar.png", 80, 80, true, true);
            if (image.isError()) throw new Exception("Failed");
        } catch (Exception e) {
            image = new Image(getClass().getResourceAsStream("/default_avatar.png"));
        }

        ImageView profilePic = new ImageView(image);
        Label name = new Label("John Doe");
        name.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        VBox notifications = new VBox(5);
        notifications.getChildren().addAll(
                new Label("- New appointment confirmed"),
                new Label("- Lab results uploaded"),
                new Label("- Reminder: Checkup tomorrow")
        );

        Label notifTitle = new Label("Notifications");
        notifTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Button help = new Button("Need Help?");
        help.setStyle("-fx-background-color: darkmagenta; -fx-text-fill: white;");

        right.getChildren().addAll(profilePic, name, notifTitle, notifications, help);
        return right;
    }

    private VBox createCard(String title, String value) {
        VBox card = new VBox();
        card.setPadding(new Insets(15));
        card.setSpacing(10);
        card.setStyle("-fx-background-color: white; -fx-border-color: lightgray; -fx-border-radius: 10; -fx-background-radius: 10; -fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.05), 10, 0, 0, 5);");
        card.setPrefSize(200, 100);

        Label labelTitle = new Label(title);
        labelTitle.setFont(Font.font("Arial", 14));
        Label labelValue = new Label(value);
        labelValue.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        card.getChildren().addAll(labelTitle, labelValue);
        return card;
    }

    
    
    private void openVideoConsultDialog() {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Request Video Consultation");

        //Label doctorLabel = new Label("Doctor Email:");
        //TextField doctorField = new TextField();
        ComboBox<String> doctorComboBox = new ComboBox<>();
        doctorComboBox.setPromptText("Select a doctor");

        doctorComboBox.getItems().addAll(
            "Dr. Smith - drsmith@example.com",
            "Dr. Jones - drjones@example.com",
            "Dr. Lee - drlee@example.com"
        );

        Label dateLabel = new Label("Select Date:");
        DatePicker datePicker = new DatePicker();

        Label timeLabel = new Label("Select Time (HH:MM):");
        ComboBox<String> timeComboBox = new ComboBox<>();
        timeComboBox.setPromptText("Select Time");

        // Populate with time slots (every 30 mins from 08:00 to 18:00, you can customize this)
        for (int hour = 8; hour <= 18; hour++) {
            timeComboBox.getItems().add(String.format("%02d:00", hour));
            timeComboBox.getItems().add(String.format("%02d:30", hour));
        }


        
       
        //VBox content = new VBox(10, doctorLabel, doctorField, msgLabel, messageArea);
        VBox content = new VBox(10,
        	    new Label("Select Date:"),
        	    datePicker,
        	    new Label("Select Time:"),
        	    timeComboBox,
        	    new Label("Select Doctor:"),
        	    doctorComboBox 
        	);


        content.setPadding(new Insets(10));
        dialog.getDialogPane().setContent(content);

        ButtonType sendBtnType = new ButtonType("Send", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(sendBtnType, ButtonType.CANCEL);

        //CHANGES MADE 
        dialog.setResultConverter(btn -> {
            if (btn == sendBtnType) {
                //String doctorEmail = doctorField.getText().trim();
            	LocalDate selectedDate = datePicker.getValue();
            	String timeText = timeComboBox.getValue();

                LocalTime selectedTime;
                try {
                    selectedTime = LocalTime.parse(timeText); // Format: HH:mm (e.g., 14:30)
                } catch (Exception ex) {
                    new Alert(Alert.AlertType.ERROR, "Invalid time format. Use HH:mm").showAndWait();
                    return null;
                }

                if (selectedDate == null) {
                    new Alert(Alert.AlertType.ERROR, "Please select a date.").showAndWait();
                    return null;
                }

                LocalDateTime selectedDateTime = LocalDateTime.of(selectedDate, selectedTime);


                
                //CHANGES MADE FOR VIDEO CONSULTATION
            
               // User.Patient patient = new User.Patient("John Doe", "john@example.com", "...", "...", "...", "...");
                //User.Doctor doctor = new User.Doctor("Dr. X", doctorEmail, "...", "...", "...", "...");
                //Patient patient = Patient.createMockPatient("John");
                //Doctor doctor = Doctor.createMockDoctor("Dr. Smith");
                String selectedDoctor = doctorComboBox.getValue();
                if (selectedDoctor == null) {
                    new Alert(Alert.AlertType.ERROR, "Please select a doctor.").showAndWait();
                    return null;
                }
                String doctorName = selectedDoctor.split(" - ")[0];
                Doctor doctor = Doctor.createMockDoctor(doctorName);

                Patient patient = Session.getLoggedInPatient();

                //videoConsultation.VideoConsultationRequest request = new videoConsultation.VideoConsultationRequest(patient, doctor, message);
                //videoConsultation.videoConsultationManager.addRequest(request);

                //CHANGE MADE 
             // 1. Create and store the video consultation request
                VideoConsultationRequest request = new VideoConsultationRequest(patient, doctor, "");
                videoConsultationManager.addRequest(request);

                // 2. Also create a VIDEO type appointment for record-keeping
                try {
                	Appointment appointment = new Appointment(
                		    selectedDateTime,  // use chosen time
                		    doctor,
                		    patient,
                		    Appointment.AppointmentStatus.PENDING
                		);

                    // ✅ Set the type to VIDEO
                    appointment.setType(Appointment.AppointmentType.VIDEO);

                    // Save to your appointment system (you might need to adapt this part)
                    // AppointmentManager.addAppointment(appointment); // ← uncomment this if you have such a class

                } catch (Exception ex) {
                    ex.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Failed to create appointment: " + ex.getMessage());
                    errorAlert.showAndWait();
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Request sent!");
                alert.showAndWait();
            }
            return null;
        });

        dialog.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
