package com.example.comp380project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPage extends Application {
    private Storefront storefront;

    public LoginPage(){
        this.storefront = new Storefront();
    }

    public LoginPage(Storefront storefront) {
        this.storefront = storefront;
    }

    public void start(Stage loginStage) {
        loginStage.setTitle("Login");

        Label loginMessage = new Label();

        Hyperlink registerUser = new Hyperlink("Not a user? Register here.");
        registerUser.setFocusTraversable(false);
        registerUser.setOnAction(e -> goToRegisterPage());

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setOnAction(e -> handleLogin(usernameField.getText().trim(), passwordField.getText(), loginStage, loginMessage)); // Enables use of enter key to proceed


        Button loginButton = new Button("Login");
        loginButton.setCursor(Cursor.HAND);
        loginButton.setOnAction(e -> handleLogin(usernameField.getText().trim(), passwordField.getText(), loginStage, loginMessage));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(usernameField, passwordField, loginButton, loginMessage, registerUser);

        Scene loginScene = new Scene(layout, 300, 200);
        loginStage.setScene(loginScene);
        loginStage.show();
    }

    private void handleLogin(String username, String password, Stage loginStage, Label loginMessage) {
        LoginSystem loginSystem = new LoginSystem();
        if (loginSystem.login(username, password)){
            loginMessage.setText("Login succesful!");
            loginMessage.setStyle("-fx-text-fill: green;");
            storefront.start(new Stage());
            loginStage.close();
        }
        else {
            loginMessage.setText("Login failed!");
            loginMessage.setStyle("-fx-text-fill: red;");
        }

       /* if (loginSystem.authenticateUser(username, password)){
            loginMessage.setText("Login Successful!");
            loginMessage.setStyle("-fx-text-fill: green;");
            storefront.start(new Stage());
            loginStage.close();
        } else {
            loginMessage.setText("Login failed!");
            loginMessage.setStyle("-fx-text-fill: red;");
        }*/
    }

    private void goToRegisterPage(){
        // Method to handle going to RegisterPage
    }

    private

    public static void main(String[] args) {
        launch(args);
    }
}
