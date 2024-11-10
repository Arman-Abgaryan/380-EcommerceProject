package com.example.comp380project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Label loginMessage = new Label();

        Button loginButton = new Button("Login");
        loginButton.setCursor(Cursor.HAND);
        loginButton.setOnAction(e -> handleLogin(usernameField.getText().trim(), passwordField.getText(), loginStage, loginMessage));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(usernameField, passwordField, loginButton, loginMessage);

        Scene loginScene = new Scene(layout, 300, 200);
        loginStage.setScene(loginScene);
        loginStage.show();
    }

    private void handleLogin(String username, String password, Stage loginStage, Label loginMessage) {
        LoginSystem loginSystem = new LoginSystem();

        if (loginSystem.authenticateUser(username, password)){
            loginMessage.setText("Login Successful!");
            loginMessage.setStyle("-fx-text-fill: green;");
            storefront.start(new Stage());
            loginStage.close();
        } else {
            loginMessage.setText("Login failed!");
            loginMessage.setStyle("-fx-text-fill: red;");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
