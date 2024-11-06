package com.example.comp380project;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class ReceiptPage {

    private Cart cart;
    private Storefront storefront;

    public ReceiptPage(Cart cart, Storefront storefront){
        this.cart = cart;
        this.storefront = storefront;
    }

    public Scene getReceiptScene(Stage primaryStage){
        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: white; -fx-padding: 20px;");

        //Taxes and PromoCode
        double tax = 0.07;
        AtomicReference<Double> promoDiscount = new AtomicReference<>((double) 0);

        Label subtotalLabel = new Label("Subtotal: $" + String.format("%.2f", cart.getTotalAmount()));
        Label taxLabel = new Label("Tax: $"+ String.format("%.2f",cart.getTotalAmount() * tax));
        Label totalLabel = new Label("Total: $"+ String.format("%.2f",cart.getTotalAmount()*(1+tax)));

        VBox priceTotals = new VBox(10);
        priceTotals.setAlignment(Pos.CENTER);
        priceTotals.getChildren().addAll(subtotalLabel,taxLabel,totalLabel);

        //Promo
        TextField promoCodeField = new TextField();
        promoCodeField.setPromptText("ENTER PROMO");

        Button applyPromo = new Button("Apply Promo");
        applyPromo.setOnAction(event -> {
            String promoCode = promoCodeField.getText().trim().toUpperCase();
            if(promoCode.equals("PROMO")){
                promoDiscount.set(0.1);
                double discountedTotal = cart.getTotalAmount() * (1 + tax)*(1 - promoDiscount.get());
                totalLabel.setText("Discounted Total: $" + String.format("%.2f",discountedTotal));

            }else {
                promoDiscount.set(0.0);
                totalLabel.setText("Total: $" + String.format("%.2f",cart.getTotalAmount() * (1+tax)));

            }
        });

        //Back Button
        Button backButton = new Button("Back to Home");
        backButton.setCursor(Cursor.HAND);
        backButton.setOnAction(event -> {
            storefront.createStoreFront();
            primaryStage.setScene(storefront.getScene());
        });

        layout.setCenter(priceTotals);
        BorderPane.setAlignment(priceTotals,Pos.CENTER);

        //PROMO section
        HBox promoPane = new HBox(10);
        promoPane.setAlignment(Pos.CENTER);

        promoPane.getChildren().addAll(promoCodeField,applyPromo);

        layout.setTop(promoPane);
        BorderPane.setAlignment(promoPane,Pos.CENTER);

        layout.setBottom(backButton);
        BorderPane.setAlignment(backButton,Pos.BOTTOM_CENTER);

        Scene receiptScene = new Scene(layout,400,300);
        return receiptScene;

    }


}
