package com.hamza6dev.oopsieeee;

import javafx.application.Application;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import ChatVideoConsultation.videoConsultationManager;
import ChatVideoConsultation.VideoConsultationRequest;
import User.Patient;
import User.Doctor;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import Appointment.Appointment;
import Appointment.AppointmentManager;

public class DoctorDashboard extends Application {
	private VBox sidebar;
	private final videoConsultationManager consultationManager = new videoConsultationManager();

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // === Sidebar ===
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(30));
        sidebar.setStyle("-fx-background-color: #2D3E50;");
        sidebar.setPrefWidth(200);

        Label title = new Label("Doctor Panel");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Button dashboardBtn = createSidebarButton("Dashboard");
        Button patientsBtn = createSidebarButton("Patients");
        Button appointmentsBtn = createSidebarButton("Appointments");
        Button messagesBtn = createSidebarButton("Messages");
        Button logoutBtn = createSidebarButton("Logout");

        
        //CHANGES MADE FOR VIDEO  	
        
        Button videoRequestsBtn = createSidebarButton("Video Requests");
        videoRequestsBtn.setOnAction(e -> openVideoRequestsDialog());
        Button scheduleVideoCallBtn = createSidebarButton("Schedule Video Call");
        scheduleVideoCallBtn.setOnAction(e -> openScheduleVideoDialog());

        sidebar.getChildren().addAll(
            title, dashboardBtn, patientsBtn, appointmentsBtn, messagesBtn,
            videoRequestsBtn,scheduleVideoCallBtn, logoutBtn
        );

        
        
        // === Top bar ===
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(20));
        topBar.setAlignment(Pos.CENTER_RIGHT);
        topBar.setStyle("-fx-background-color: #F4F6F8;");

        Label doctorName = new Label("Dr. John Smith");
        doctorName.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        topBar.getChildren().add(doctorName);

        // === Dashboard Content ===
        VBox content = new VBox(20);
        content.setPadding(new Insets(30));

        // Welcome Message
        Label welcomeLabel = new Label("Welcome back, Dr. John!");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        // Analytics Cards
        HBox cards = new HBox(20);
        cards.getChildren().addAll(
                createCard("Patients Today", "23", "#E57373"),
                createCard("Appointments", "15", "#64B5F6"),
                createCard("Pending Reviews", "4", "#FFD54F")
        );

        // Appointments Table
        TableView<String> table = new TableView<>();
        table.setPrefHeight(300);

        TableColumn<String, String> nameCol = new TableColumn<>("Patient Name");
        TableColumn<String, String> timeCol = new TableColumn<>("Time");
        TableColumn<String, String> reasonCol = new TableColumn<>("Reason");

        nameCol.setMinWidth(200);
        timeCol.setMinWidth(150);
        reasonCol.setMinWidth(300);

        table.getColumns().addAll(nameCol, timeCol, reasonCol);

        content.getChildren().addAll(welcomeLabel, cards, new Label("Today's Appointments:"), table);

        // === Final Layout ===
        root.setLeft(sidebar);
        root.setTop(topBar);
        root.setCenter(content);

        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("Doctor Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createSidebarButton(String text) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px;");
        btn.setAlignment(Pos.CENTER_LEFT);
        return btn;
    }

    private VBox createCard(String title, String value, String colorHex) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(15));
        card.setPrefSize(180, 100);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: " + colorHex + "; -fx-background-radius: 10px;");

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        titleLabel.setTextFill(Color.WHITE);

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        valueLabel.setTextFill(Color.WHITE);

        card.getChildren().addAll(titleLabel, valueLabel);
        return card;
    }
    
    
    
    //CHANGE FOR REQUEST HANDLING 
    
    private void openVideoRequestsDialog() {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Incoming Video Consultation Requests");

        VBox list = new VBox(10);
        list.setPadding(new Insets(10));

        // Loop through requests
        for (VideoConsultationRequest request : ChatVideoConsultation.videoConsultationManager.getRequests()) {
            if (request.getDoctor().getEmail().equalsIgnoreCase("dr.john@example.com")) { // Adjust based on logged-in doctor
                VBox item = new VBox(5);
                item.setStyle("-fx-border-color: #ccc; -fx-padding: 10;");

                Label patientInfo = new Label("From: " + request.getPatient().getName());
                Label msg = new Label("Message: " + request.getMessage());

                HBox buttons = new HBox(10);
                Button accept = new Button("Accept");
                Button reject = new Button("Reject");

                accept.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                reject.setStyle("-fx-background-color: red; -fx-text-fill: white;");

                accept.setOnAction(e -> {
                	ChatVideoConsultation.videoConsultationManager.removeRequest(request);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Accepted. You can now schedule the call.");
                    alert.showAndWait();
                    dialog.close(); // Refresh by closing for now
                });

                reject.setOnAction(e -> {
                	ChatVideoConsultation.videoConsultationManager.removeRequest(request);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Request Rejected.");
                    alert.showAndWait();
                    dialog.close();
                });

                buttons.getChildren().addAll(accept, reject);
                item.getChildren().addAll(patientInfo, msg, buttons);
                list.getChildren().add(item);
            }
        }

        //VIDEO CALL BUTTON 
        Button scheduleVideoCallBtn = createSidebarButton("Schedule Video Call");
        scheduleVideoCallBtn.setOnAction(e -> openScheduleVideoDialog());
        sidebar.getChildren().add(scheduleVideoCallBtn);

        
        ScrollPane scroll = new ScrollPane(list);
        scroll.setFitToWidth(true);
        scroll.setPrefHeight(400);

        dialog.getDialogPane().setContent(scroll);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.showAndWait();
    }

    //CHANGE MADE TO HANDLE VIDEO CALL 
    private void openScheduleVideoDialog() {

        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Schedule Video Call");

        // Fetch requests
        List<VideoConsultationRequest> pendingRequests = videoConsultationManager.getPendingRequests();

        VBox requestBox = new VBox(10);
        ToggleGroup group = new ToggleGroup();

        for (VideoConsultationRequest request : pendingRequests) {
            RadioButton rb = new RadioButton("From: " + request.getPatient().getName() + " - Msg: " + request.getMessage());
            rb.setUserData(request);
            rb.setToggleGroup(group);
            requestBox.getChildren().add(rb);
        }

        DatePicker datePicker = new DatePicker();
        TextField timeField = new TextField();
        timeField.setPromptText("Enter time (e.g., 14:00)");

        VBox layout = new VBox(10, new Label("Select Request:"), requestBox,
                new Label("Date:"), datePicker, new Label("Time:"), timeField);
        layout.setPadding(new Insets(10));
        dialog.getDialogPane().setContent(layout);

        ButtonType confirmBtn = new ButtonType("Schedule", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmBtn, ButtonType.CANCEL);

        dialog.setResultConverter(btn -> {
            if (btn == confirmBtn) {
                RadioButton selected = (RadioButton) group.getSelectedToggle();
                if (selected != null) {
                    VideoConsultationRequest request = (VideoConsultationRequest) selected.getUserData();
                    LocalDate date = datePicker.getValue();
                    String timeText = timeField.getText();

                    try {
                        LocalTime time = LocalTime.parse(timeText);
                        LocalDateTime dateTime = LocalDateTime.of(date, time);

                        Appointment appointment = new Appointment(dateTime, request.getDoctor(), request.getPatient(), Appointment.AppointmentStatus.APPROVED);
                        appointment.setType(Appointment.AppointmentType.VIDEO); // ⚠️ Make sure setType exists

                        AppointmentManager.getInstance().addAppointment(appointment);
                        request.markScheduled(); // Optional: update request status

                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Video call scheduled!");
                        alert.showAndWait();
                    } catch (Exception e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.ERROR, "Invalid date/time.").showAndWait();
                    }
                }
            }
            return null;
        });

        dialog.showAndWait();
    }


    public static void main(String[] args) {
        launch(args);
    }
}