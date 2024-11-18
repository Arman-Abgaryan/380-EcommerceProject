package com.example.comp380project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Class that handles creation of the loginPage.
 */
public class LoginPage extends Application {
    private Storefront storefront;

    /**
     * Default constructor that creates a loginPage without storefront being initialized.
     */
    public LoginPage(){
        this.storefront = null;
    }

    /**
     * Constructor used when storefront is initialized.
     * @param storefront
     */
    public LoginPage(Storefront storefront) {
        this.storefront = storefront;
    }

    /**
     * Handles the layout creation of the loginPage.
     * @param loginStage
     */
    public void start(Stage loginStage) {
        loginStage.setTitle("Login");
        loginStage.setResizable(false);

        Image logo = new Image(getClass().getResourceAsStream("/AJAD Edited Logo.png"));
        ImageView AJADlogo = new ImageView(logo);
        AJADlogo.setFitHeight(150);
        AJADlogo.setFitWidth(150);

        Label header = new Label("Welcome!");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        header.setAlignment(Pos.CENTER);
        header.setPrefSize(200,200);

        Label loginMessage = new Label();

        Hyperlink registerUser = new Hyperlink("Not a user? Register here.");
        registerUser.setFocusTraversable(false);
        registerUser.setOnAction(e ->{
            loginStage.close();
            goToRegisterPage();
        });

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setOnAction(e -> handleLogin(usernameField.getText().trim(), passwordField.getText(), loginStage, loginMessage)); // Enables use of enter key to proceed


        Button loginButton = new Button("Login");
        loginButton.setCursor(Cursor.HAND);
        loginButton.setOnAction(e -> handleLogin(usernameField.getText().trim(), passwordField.getText(), loginStage, loginMessage));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(50));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: white");
        layout.getChildren().addAll(AJADlogo, header, usernameField, passwordField, loginButton, loginMessage, registerUser);

        Scene loginScene = new Scene(layout, 325, 350);
        loginStage.setScene(loginScene);
        loginStage.show();
    }

    /**
     * Method that handles login procedures
     * @param username
     * @param password
     * @param loginStage
     * @param loginMessage
     */
    private void handleLogin(String username, String password, Stage loginStage, Label loginMessage) {
        if (username.isEmpty() || password.isEmpty()){
            loginMessage.setText("Please enter username and password.");
            loginMessage.setStyle("-fx-text-fill: red;");
            return;
        }

        LoginSystem loginSystem = new LoginSystem();
        if (loginSystem.login(username, password)){
            loginMessage.setText("Login succesful!");
            loginMessage.setStyle("-fx-text-fill: green;");

            Customer customer = CustomerFileReader.retrieveCustomer(username);
            Cart cart = CartFileReader.retrieveCart(customer.getId());

            if(storefront != null){
                storefront.refreshUI(customer,cart);
            }
            else{
                storefront = new Storefront(customer,cart);
                storefront.start(new Stage());
            }
            loginStage.close();
        }
        else {
            loginMessage.setText("Login failed!");
            loginMessage.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * Method that brings up the registerPage for new users
     */
    private void goToRegisterPage(){
        RegisterPage registerPage = new RegisterPage();
        Stage registerStage = new Stage();
        registerStage.setMinHeight(400);
        registerPage.start(registerStage);
    }
}
