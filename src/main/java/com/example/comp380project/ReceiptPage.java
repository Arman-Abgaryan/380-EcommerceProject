package com.example.comp380project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Map;

public class ReceiptPage {

    private Cart cart;
    private Storefront storefront;

    public ReceiptPage(Cart cart, Storefront storefront) {
        this.cart = cart;
        this.storefront = storefront;
    }

    public Scene getReceiptScene(Stage primaryStage) {
        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #f7f7f7, #ffffff); -fx-padding: 20px;");

        double tax = 0.07;

        // Title Section
        Label title = new Label("Checkout");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-font-family: 'Arial';");
        title.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(title, Pos.CENTER);
        layout.setTop(title);

        // Items Section (Left)
        VBox itemsList = new VBox(15);

        // Price Summary Labels
        Label subtotalLabel = new Label();
        Label taxLabel = new Label();
        Label totalLabel = new Label();

        // Initialize the item list and price summary
        refreshItemsList(itemsList, subtotalLabel, taxLabel, totalLabel, tax);

        ScrollPane itemsScrollPane = new ScrollPane(itemsList);
        itemsScrollPane.setFitToWidth(true);
        itemsScrollPane.setStyle("-fx-background-color: white;");
        itemsScrollPane.setPrefHeight(400);

        Label itemsLabel = new Label("Your Items");
        itemsLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'Verdana';");
        VBox itemsSection = new VBox(10, itemsLabel, itemsScrollPane);
        itemsSection.setPadding(new Insets(10));
        itemsSection.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 10; -fx-padding: 10px;");
        itemsSection.setAlignment(Pos.TOP_CENTER);

        // Information Section (Right) with ScrollPane
        VBox rightSectionContent = new VBox(15);
        rightSectionContent.setPadding(new Insets(10));
        rightSectionContent.setAlignment(Pos.TOP_CENTER);

        // Price Summary
        subtotalLabel.setStyle("-fx-font-size: 16px;");
        taxLabel.setStyle("-fx-font-size: 14px;");
        totalLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: green;");

        VBox priceTotals = new VBox(10, subtotalLabel, taxLabel, totalLabel);
        priceTotals.setAlignment(Pos.CENTER_LEFT);

        // Shipping Address Section
        VBox shippingSection = createFormSection("Shipping Address", new String[]{
                "Recipient's Name", "Street Address", "City", "State", "Postal Code"
        });

        // Email Section
        VBox emailSection = createFormSection("Email for Receipt", new String[]{"Enter your email"});

        // Payment Section
        VBox paymentSection = createFormSection("Payment Information", new String[]{
                "Cardholder Name", "Card Number (e.g., 1234 5678 9012 3456)", "Expiry Date (MM/YY)", "CVV"
        });

        // Confirm Payment Button
        Button confirmPaymentButton = new Button("Confirm Payment");
        confirmPaymentButton.setCursor(Cursor.HAND);
        confirmPaymentButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 12px; -fx-padding: 10px;");
        confirmPaymentButton.setOnAction(event -> {
            if (validateInputFields(shippingSection, emailSection, paymentSection)) {
                showAlert("Payment Success", "Your payment was processed successfully!", Alert.AlertType.INFORMATION);
                cart.getItems().clear();
                storefront.createStoreFront();
                primaryStage.setScene(storefront.getScene());
            } else {
                showAlert("Payment Failed", "Please fill in all required fields correctly.", Alert.AlertType.ERROR);
            }
        });

        // Back to Home Button
        Button backButton = new Button("Back to Home");
        backButton.setCursor(Cursor.HAND);
        backButton.setStyle("-fx-background-color: blue; -fx-text-fill: white; -fx-font-size: 12px; -fx-padding: 10px;");
        backButton.setOnAction(event -> {
            storefront.createStoreFront();
            primaryStage.setScene(storefront.getScene());
        });

        rightSectionContent.getChildren().addAll(priceTotals, shippingSection, emailSection, paymentSection, confirmPaymentButton, backButton);

        // Wrap right section in a ScrollPane
        ScrollPane rightScrollPane = new ScrollPane(rightSectionContent);
        rightScrollPane.setFitToWidth(true);
        rightScrollPane.setStyle("-fx-background-color: white;");

        // Split Screen Layout
        HBox mainContent = new HBox(20, itemsSection, rightScrollPane);
        HBox.setHgrow(itemsSection, Priority.ALWAYS);
        HBox.setHgrow(rightScrollPane, Priority.ALWAYS);
        mainContent.setPadding(new Insets(20));

        layout.setCenter(mainContent);

        return new Scene(layout, 1100, 700);
    }
    private VBox createFormSection(String sectionTitle, String[] fieldPrompts) {
        Label sectionLabel = new Label(sectionTitle);
        sectionLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-font-family: 'Verdana'; -fx-padding: 5px 0;");

        VBox formFields = new VBox(10);
        for (String prompt : fieldPrompts) {
            TextField field = new TextField();
            field.setPromptText(prompt);
            field.setMaxWidth(300);
            formFields.getChildren().add(field);
        }


        VBox section = new VBox(10, sectionLabel, formFields);
        section.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 10; -fx-padding: 10px;");
        section.setAlignment(Pos.CENTER_LEFT);

        return section;
    }

    private void refreshItemsList(VBox itemsList, Label subtotalLabel, Label taxLabel, Label totalLabel, double tax) {
        itemsList.getChildren().clear();
        for (Map.Entry<Item, Integer> entry : cart.getItems().entrySet()) {
            Item item = entry.getKey();
            int quantity = entry.getValue();

            HBox itemBox = new HBox(10);
            itemBox.setAlignment(Pos.CENTER_LEFT);

            // Item Thumbnail
            Image itemImage = new Image(getClass().getResourceAsStream(item.getImagePath()), 70, 70, true, true);
            ImageView imageView = new ImageView(itemImage);

            // Item Info with Size
            Label itemLabel = new Label(item.getSize() + " " + item.getName() + " x" + quantity + " - $" + String.format("%.2f", item.getPrice() * quantity));
            itemLabel.setStyle("-fx-font-size: 14px;");

            // Increase Button
            Button increaseButton = new Button("+");
            increaseButton.setCursor(Cursor.HAND);
            increaseButton.setStyle("-fx-background-color: lightgreen; -fx-font-size: 12px;");
            increaseButton.setOnAction(event -> {
                cart.addItem(item);
                refreshItemsList(itemsList, subtotalLabel, taxLabel, totalLabel, tax);
            });

            // Decrease Button
            Button decreaseButton = new Button("-");
            decreaseButton.setCursor(Cursor.HAND);
            decreaseButton.setStyle("-fx-background-color: salmon; -fx-font-size: 12px;");
            decreaseButton.setOnAction(event -> {
                cart.removeItem(item);
                refreshItemsList(itemsList, subtotalLabel, taxLabel, totalLabel, tax);
            });

            itemBox.getChildren().addAll(imageView, itemLabel, increaseButton, decreaseButton);
            itemsList.getChildren().add(itemBox);
        }

        // Update the price summary
        refreshPriceSummary(subtotalLabel, taxLabel, totalLabel, tax);
    }

    private void refreshPriceSummary(Label subtotalLabel, Label taxLabel, Label totalLabel, double tax) {
        double subtotal = cart.getTotalAmount();
        double taxAmount = subtotal * tax;
        double total = subtotal + taxAmount;

        subtotalLabel.setText("Subtotal: $" + String.format("%.2f", subtotal));
        taxLabel.setText("Tax: $" + String.format("%.2f", taxAmount));
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }

    private boolean validateInputFields(VBox shippingSection, VBox emailSection, VBox paymentSection) {
        return shippingSection.getChildren().stream().anyMatch(this::isValidTextField) &&
                emailSection.getChildren().stream().anyMatch(this::isValidTextField) &&
                paymentSection.getChildren().stream().anyMatch(this::isValidTextField);
    }

    private boolean isValidTextField(Object node) {
        if (node instanceof TextField) {
            TextField field = (TextField) node;
            return !field.getText().trim().isEmpty();
        }
        return true;
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

}
