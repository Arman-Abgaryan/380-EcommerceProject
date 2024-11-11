package com.example.comp380project;

import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.awt.*;

public class RegisterPage {
    private LoginSystem loginSystem;

    public RegisterPage(LoginSystem loginSystem){
        this.loginSystem = loginSystem;
    }

    public void start(Stage registerStage){
        registerStage.setTitle("Register");

        TextField firstNameField = new TextField("First Name");
        TextField lastNameField = new TextField("Last Name");
        TextField addressField = new TextField("Address");
        TextField emailField = new TextField("Email");

        TextField usernameField = new TextField("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");


    }
}
