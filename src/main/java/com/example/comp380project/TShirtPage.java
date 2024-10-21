package com.example.comp380project;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TShirtPage {

    public Scene getTShirtPage(Stage stage) {
        HBox pageLabel = new HBox(); // HBox for Label
            pageLabel.setPadding(new Insets(0, 0, 0, 0));
            Label label = new Label("T-Shirts");
            label.setFont(Font.font("verdana", FontWeight.BOLD, 20));
            pageLabel.setAlignment(Pos.TOP_LEFT);
            pageLabel.getChildren().addAll(label);

        HBox logoHolder = new HBox();
            logoHolder.setAlignment(Pos.TOP_CENTER);
            logoHolder.setPadding(new Insets(-40, 0, 0, 0)); //Adjusts the positioning of the logo to be centered in top center

            // Image of Logo
            Image logo = new Image(getClass().getResourceAsStream("/AJAD Edited Logo.png"));
            ImageView AJADlogo = new ImageView(logo);
            AJADlogo.setFitHeight(100);
            AJADlogo.setFitWidth(100);
            logoHolder.getChildren().addAll(AJADlogo);
            

        HBox viewCart = new HBox();
            viewCart.setAlignment(Pos.TOP_RIGHT);
            viewCart.setPadding(new Insets(-70, 95, 0, 0));
            Button cart = new Button("Cart");
            cart.setStyle("-fx-background-color: #12A822;");
            viewCart.getChildren().addAll(cart);

        VBox topHolder = new VBox(); // Vertical Box holder for pageLabel and logoHolder
            topHolder.setAlignment(Pos.TOP_CENTER);
            topHolder.getChildren().addAll(pageLabel, logoHolder, viewCart);

        HBox TShirtHolder = new HBox(200); //Horizontal Box that holds all T-Shirts

            VBox BlackTShirtBox = new VBox();
                // Image for the Black T-Shirt
                Image BlackTShirtImage = new Image (getClass().getResourceAsStream("/Black TShirt.jpg"));
                ImageView blackTShirt = new ImageView(BlackTShirtImage);
                blackTShirt.setFitHeight(250);
                blackTShirt.setFitWidth(250);

                BlackTShirtBox.setAlignment(Pos.CENTER);
                BlackTShirtBox.setSpacing(10);
                Label BTshirt = new Label("Black T-Shirt");
                Label BShirtPrice = new Label("$19.99"); // Placeholder until pulled from database
                Button AddtoCart = new Button("Add to Cart");
                BlackTShirtBox.getChildren().addAll(blackTShirt, BTshirt, BShirtPrice, AddtoCart);

            VBox WhiteTShirtBox = new VBox();
                // Image for the White T-Shirt
                Image WhiteTShirtImage = new Image (getClass().getResourceAsStream("/White T.jpg"));
                ImageView whiteTShirt = new ImageView(WhiteTShirtImage);
                whiteTShirt.setFitHeight(250);
                whiteTShirt.setFitWidth(250);

                WhiteTShirtBox.setAlignment(Pos.CENTER);
                WhiteTShirtBox.setSpacing(10);
                Label WTshirt = new Label("White T-Shirt");
                Label WShirtPrice = new Label("$19.99"); // // Placeholder until pulled from database
                Button AddtoCart3 = new Button("Add to Cart");
                WhiteTShirtBox.getChildren().addAll(whiteTShirt, WTshirt, WShirtPrice, AddtoCart3);

            VBox GreyTShirtBox = new VBox();
                // Image for the Grey T-Shirt
                Image GreyTShirtImage = new Image (getClass().getResourceAsStream("/Grey TShirt.jpg"));
                ImageView greyTShirt = new ImageView(GreyTShirtImage);
                greyTShirt.setFitHeight(250);
                greyTShirt.setFitWidth(250);

                GreyTShirtBox.setAlignment(Pos.CENTER);
                GreyTShirtBox.setSpacing(10);
                Label GTShirt = new Label("Grey T-Shirt");
                Label GShirtPrice = new Label("$19.99"); // // Placeholder until pulled from database
                Button AddtoCart2 = new Button("Add to Cart");
                GreyTShirtBox.getChildren().addAll(greyTShirt, GTShirt, GShirtPrice, AddtoCart2);


            TShirtHolder.setAlignment(Pos.CENTER);
            TShirtHolder.getChildren().addAll(BlackTShirtBox, WhiteTShirtBox, GreyTShirtBox);


        BorderPane boxHolder = new BorderPane(); // holder for all VBoxes and HBoxes
            boxHolder.setStyle("-fx-background-color: white"); //Sets the background of the page to white

            boxHolder.setTop(topHolder);
            BorderPane.setAlignment(topHolder, Pos.TOP_LEFT);

            boxHolder.setCenter(TShirtHolder);
            BorderPane.setAlignment(TShirtHolder, Pos.CENTER);

        Scene blankTshirtScene = new Scene(boxHolder);
        return blankTshirtScene;
    }
}
