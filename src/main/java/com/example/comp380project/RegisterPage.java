package com.example.comp380project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Cursor;

public class RegisterPage extends Application {
    private LoginSystem loginSystem;

    public RegisterPage(){
        this.loginSystem = new LoginSystem();
    }

    public RegisterPage(LoginSystem loginSystem){
        this.loginSystem = loginSystem;
    }

    public void start(Stage registerStage){
        CustomerFileReader.retrieveAllCustomers(); // Added to initialize next available customer ID

        registerStage.setTitle("Register");

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        TextField addressField = new TextField();
        addressField.setPromptText("Address");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");

        Label message = new Label();

        Button createUserButton = new Button("Confirm");
        createUserButton.setCursor(Cursor.HAND);
        createUserButton.setOnAction(e -> registerNewUser(firstNameField, lastNameField, emailField, addressField, usernameField, passwordField, confirmPasswordField, message));


        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(firstNameField, lastNameField, addressField, emailField, usernameField, passwordField, confirmPasswordField, createUserButton, message);

        Scene registerScene = new Scene(layout, 300,200);
        registerStage.setScene(registerScene);
        registerStage.show();
    }

    private void registerNewUser(TextField firstNameField, TextField lastNameField, TextField emailField, TextField addressField, TextField usernameField, TextField passwordField, TextField confirmPasswordField, Label message){
        if(!validateFields(firstNameField,lastNameField,emailField,addressField,usernameField, passwordField,confirmPasswordField, message)){
            return;
        }

        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String address = addressField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirmedPassword = confirmPasswordField.getText();

        if (!loginSystem.isUsernameAvailable(username)){
            message.setText("Username " + username + " already exists. Please choose a different username.");
            usernameField.clear();
        }
        else if (password.equals(confirmedPassword)){
            loginSystem.createNewUser(firstName,lastName, email, address, username, password);
            message.setText("New user registered!");
        }
       else{
           message.setText("Passwords do not match. PLease verify and try again.");
           passwordField.clear();
           confirmPasswordField.clear();
        }
    }

    private boolean validateFields(TextField firstNameField, TextField lastNameField, TextField emailField, TextField addressField, TextField usernameField, TextField passwordField, TextField confirmPasswordField, Label message) {
        if (firstNameField.getText().trim().isEmpty()) {
            message.setText("First name cannot be empty.");
            return false;
        }
        if (lastNameField.getText().trim().isEmpty()) {
            message.setText("Last name cannot be empty.");
            return false;
        }
        if (emailField.getText().trim().isEmpty()) {
            message.setText("Email cannot be empty.");
            return false;
        }
        if (addressField.getText().trim().isEmpty()) {
            message.setText("Address cannot be empty.");
            return false;
        }
        if (usernameField.getText().trim().isEmpty()) {
            message.setText("Username cannot be empty.");
            return false;
        }
        if (passwordField.getText().isEmpty()) {
            message.setText("Password cannot be empty.");
            return false;
        }
        if (confirmPasswordField.getText().isEmpty()) {
            message.setText("Confirm password cannot be empty.");
            return false;
        }
        return true;
    }

}
