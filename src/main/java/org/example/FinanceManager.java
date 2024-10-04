package org.example;


import javafx.scene.control.*;
import org.example.DatabaseManager;
import org.example.PasswordUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FinanceManager extends Application{

    DatabaseManager dbManager = new DatabaseManager();
    @Override
    public void start (Stage primaryStage) {
        createLoginScene(primaryStage);
    }

    private void createLoginScene(Stage primaryStage) {

        // Create UI components
        Label titleLabel = new Label("Personal Finance Manager");

        Label userLabel = new Label("Username:");
        TextField userField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        Label messageLabel = new Label(); // Label to display login message

        // Set up the layout
        VBox vbox = new VBox(10); // 10 is the spacing between elements
        vbox.getChildren().addAll(titleLabel, userLabel, userField, passwordLabel, passwordField, loginButton, registerButton);

        // Create the scene and add the layout
        Scene scene = new Scene(vbox, 640, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Finance Manager");
        primaryStage.show();

        // Set up button actions
        loginButton.setOnAction(e -> handleLogin(userField.getText(), passwordField.getText(),primaryStage));
        registerButton.setOnAction(e -> handleRegister(userField.getText(), passwordField.getText()));
    }

    // Handle login logic
    private void handleLogin(String username, String password, Stage primaryStage) {
        String hashedPassword = org.example.PasswordUtil.hashPassword(password);
        boolean isAuthenticated = dbManager.authenticateUser(username, hashedPassword);

        if (isAuthenticated) {
            showAlert(Alert.AlertType.INFORMATION, "Login successful", "Welcome " + username + "!");

            // switch to welcome screen

            showWelcomeScreen(username, primaryStage);
        } else {
            showAlert(Alert.AlertType.ERROR, "Login failed", "Incorrect username or password.");
        }
    }

    // Handle registration logic
    private void handleRegister(String username, String password) {
        // Hash the password
        String hashedPassword = org.example.PasswordUtil.hashPassword(password);
        boolean isRegistered = dbManager.registerUser(username, "dummyEmail@example.com", hashedPassword);

        if (isRegistered) {
            showAlert(Alert.AlertType.INFORMATION,"User registered successfully!", "Registration complete"); // Replace with GUI feedback
        } else {
            System.out.println("Registration failed."); // Replace with GUI feedback
        }
    }

    // show login screen after user successfully logins
    private void showWelcomeScreen(String username, Stage primaryStage) {
        Label welcomeLabel = new Label("Welcome" + username + "!");

        VBox welcomeLayout = new VBox(20);
        welcomeLayout.getChildren().add(welcomeLabel);

        Scene welcomeScene = new Scene(welcomeLayout,300,150);
        primaryStage.setScene(welcomeScene);
        primaryStage.setTitle("Welcome");

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> createLoginScene(primaryStage));

        welcomeLayout.getChildren().add(logoutButton);
    }

    private void showAlert(Alert.AlertType alertType,String title,String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

}