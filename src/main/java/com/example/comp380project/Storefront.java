package com.example.comp380project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Storefront extends Application {
    Stage window;
    Scene scene;
    Button button;

    @Override
    public void start(Stage StorefrontStage) throws Exception {
        window = StorefrontStage;
        window.setTitle("AJAD Ecommerce");
        StorefrontStage.setWidth(1200);
        StorefrontStage.setHeight(800);

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("T-Shirts", "Pants", "Shoes");
        choiceBox.setValue("- Select -");

        button = new Button("Go");
        button.setOnAction(e -> getChoice(choiceBox));

        VBox selection = new VBox();
        selection.setStyle("-fx-background-color: white");
        Image logo = new Image(getClass().getResourceAsStream("/AJAD Edited Logo.png"));
        ImageView AJADlogo = new ImageView(logo);
        AJADlogo.setFitHeight(300);
        AJADlogo.setFitWidth(300);

        selection.setPadding(new Insets(0, 0, 0, 0));
        selection.setSpacing(20); //Sets space between drop-down menu and button
        selection.setAlignment(Pos.CENTER);
        selection.getChildren().addAll(AJADlogo, choiceBox, button);

        scene = new Scene(selection);
        window.setScene(scene);
        window.show();
    }

    private void getChoice(ChoiceBox<String> choiceBox) {
        String choice = choiceBox.getValue();

        if (choice.equals("T-Shirts")) {
            TShirtPage tShirtPage = new TShirtPage();
            Scene blankShirtScene = tShirtPage.getTShirtPage(window);
            window.setScene(blankShirtScene);
        } else if (choice.equals("Pants")) {
            System.out.println("Navigating to: " + choice);
        } else if (choice.equals("Shoes")) {
            System.out.println("Navigating to: " + choice);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
