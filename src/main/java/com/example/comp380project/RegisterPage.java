package com.example.comp380project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

        Button createUserButton = new Button("Confirm");
        createUserButton.setCursor(Cursor.HAND);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(firstNameField, lastNameField, addressField, emailField, usernameField, passwordField, confirmPasswordField, createUserButton);

        Scene registerScene = new Scene(layout, 300,200);
        registerStage.setScene(registerScene);
        registerStage.show();
    }
}
