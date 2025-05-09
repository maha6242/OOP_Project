/*
package email;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f8f8f8;");

        // Add sender email and password fields
        TextField senderField = new TextField();
        senderField.setPromptText("Your Gmail (sender)");

        PasswordField appPasswordField = new PasswordField();
        appPasswordField.setPromptText("Your App Password");

        // Recipient details
        TextField recipientField = new TextField();
        recipientField.setPromptText("Recipient Email");

        TextField subjectField = new TextField();
        subjectField.setPromptText("Subject");

        TextArea messageArea = new TextArea();
        messageArea.setPromptText("Message");
        messageArea.setPrefRowCount(6);

        Label statusLabel = new Label();

        Button sendButton = new Button("Send Email");
        sendButton.setOnAction(e -> {
            String sender = senderField.getText();
            String password = appPasswordField.getText();
            String recipient = recipientField.getText();
            String subject = subjectField.getText();
            String body = messageArea.getText();

            boolean success = sendEmail(sender, password, recipient, subject, body);
            statusLabel.setText(success ? "✅ Email sent!" : "❌ Failed to send email.");
        });

        root.getChildren().addAll(
                new Label("Send Email"),
                senderField,
                appPasswordField,
                recipientField,
                subjectField,
                messageArea,
                sendButton,
                statusLabel
        );

        Scene scene = new Scene(root, 450, 500);
        primaryStage.setTitle("Email Sender");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Actual email sending logic
    public static boolean sendEmail(String sender, String password, String recipient, String subject, String body) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(sender, password);
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/


/* package email;

import Notifications.EmailNotification;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class EmailPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Navigation bar
        HBox navBar = new HBox();
        navBar.setPadding(new Insets(20));
        navBar.setSpacing(30);
        navBar.setStyle("-fx-background-color: white;");
        navBar.setAlignment(Pos.CENTER_LEFT);

        Label logo = new Label("CheapAHH");
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        logo.setTextFill(Color.BLUE);
        navBar.getChildren().add(logo);

        // Main container
        VBox container = new VBox(30);
        container.setPadding(new Insets(40));
        container.setAlignment(Pos.TOP_CENTER);

        Text title = new Text("Send Email Notification");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setFill(Color.BLUE);

        VBox form = new VBox(15);
        form.setPadding(new Insets(30));
        form.setMaxWidth(500);
        form.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 10, 0, 0, 4);");

        // Input fields
        TextField senderEmailField = new TextField();
        senderEmailField.setPromptText("Sender Email");
        senderEmailField.setPrefHeight(40);

        PasswordField senderPasswordField = new PasswordField();
        senderPasswordField.setPromptText("App Password");
        senderPasswordField.setPrefHeight(40);

        TextField recipientField = new TextField();
        recipientField.setPromptText("Recipient Email");
        recipientField.setPrefHeight(40);

        TextArea messageArea = new TextArea();
        messageArea.setPromptText("Type your message...");
        messageArea.setPrefHeight(150);

        Button sendBtn = new Button("Send Email");
        sendBtn.setPrefHeight(40);
        sendBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px;");

        sendBtn.setOnAction(e -> {
            String senderEmail = senderEmailField.getText().trim();
            String senderPassword = senderPasswordField.getText().trim();
            String recipient = recipientField.getText().trim();
            String message = messageArea.getText().trim();

            if (senderEmail.isEmpty() || senderPassword.isEmpty() || recipient.isEmpty() || message.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Please fill in all fields.");
                return;
            }

            EmailNotification email = new EmailNotification(senderEmail, senderPassword);
            email.sendNotification(message, recipient);
        });

        
        
        
        
        
        
        
        
        
        
        
        
        form.getChildren().addAll(senderEmailField, senderPasswordField, recipientField, messageArea, sendBtn);
        container.getChildren().addAll(title, form);
        container.setStyle("-fx-background-color: #f8f8f8;");

        VBox root = new VBox(navBar, container);
        Scene scene = new Scene(root, 1080, 720);
        primaryStage.setTitle("Email Notification");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void showAlert(Alert.AlertType type, String content) {
        Alert alert = new Alert(type);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/



package email;

import Notifications.EmailNotification;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Navigation bar
        HBox navBar = new HBox();
        navBar.setPadding(new Insets(20));
        navBar.setSpacing(30);
        navBar.setStyle("-fx-background-color: white;");
        navBar.setAlignment(Pos.CENTER_LEFT);

        Label logo = new Label("CheapAHH");
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        logo.setTextFill(Color.BLUE);
        navBar.getChildren().add(logo);

        // Main container
        VBox container = new VBox(30);
        container.setPadding(new Insets(40));
        container.setAlignment(Pos.TOP_CENTER);

        Text title = new Text("Send Email Notification");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setFill(Color.BLUE);

        VBox form = new VBox(15);
        form.setPadding(new Insets(30));
        form.setMaxWidth(500);
        form.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 10, 0, 0, 4);");

        // Input fields
        TextField senderEmailField = new TextField();
        senderEmailField.setPromptText("Sender Email");
        senderEmailField.setPrefHeight(40);

        PasswordField senderPasswordField = new PasswordField();
        senderPasswordField.setPromptText("App Password");
        senderPasswordField.setPrefHeight(40);

        TextField recipientField = new TextField();
        recipientField.setPromptText("Recipient Email");
        recipientField.setPrefHeight(40);

        TextField subjectField = new TextField();
        subjectField.setPromptText("Subject");
        subjectField.setPrefHeight(40);  // New field for subject

        TextArea messageArea = new TextArea();
        messageArea.setPromptText("Type your message...");
        messageArea.setPrefHeight(150);

        Button sendBtn = new Button("Send Email");
        sendBtn.setPrefHeight(40);
        sendBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px;");

        sendBtn.setOnAction(e -> {
            String senderEmail = senderEmailField.getText().trim();
            String senderPassword = senderPasswordField.getText().trim();
            String recipientEmail = recipientField.getText().trim();
            String subject = subjectField.getText().trim();  // Get the subject
            String message = messageArea.getText().trim();

            if (senderEmail.isEmpty() || senderPassword.isEmpty() || recipientEmail.isEmpty() || message.isEmpty() || subject.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Missing Fields", "Please fill in all fields.");
                return;
            }

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            try {
                Message email = new MimeMessage(session);
                email.setFrom(new InternetAddress(senderEmail));
                email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                email.setSubject(subject);  // Set the subject of the email
                email.setText(message);
                Transport.send(email);

                showAlert(Alert.AlertType.INFORMATION, "Success", "Email sent to: " + recipientEmail);

            } catch (AuthenticationFailedException ex) {
                showAlert(Alert.AlertType.ERROR, "Authentication Failed",
                        "It looks like your email or password is incorrect.\n\n"
                                + "If you're using Gmail, you must enable 2-Step Verification and generate an App Password.\n\n"
                                + "Steps:\n"
                                + "1. Go to your Google Account > Security\n"
                                + "2. Turn on 2-Step Verification\n"
                                + "3. Under 'Signing in to Google', choose 'App Passwords'\n"
                                + "4. Generate a password and paste it here instead of your regular password.");
            } catch (MessagingException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to send email. Please try again.");
            }
        });

        form.getChildren().addAll(senderEmailField, senderPasswordField, recipientField, subjectField, messageArea, sendBtn);  // Add subjectField to form
        container.getChildren().addAll(title, form);
        container.setStyle("-fx-background-color: #f8f8f8;");

        VBox root = new VBox(navBar, container);
        Scene scene = new Scene(root, 1080, 720);
        primaryStage.setTitle("Email Notification");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // Updated showAlert method with three parameters
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

