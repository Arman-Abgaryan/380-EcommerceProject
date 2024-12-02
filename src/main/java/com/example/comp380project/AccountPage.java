package com.example.comp380project;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


import javafx.scene.control.Label;


/**
 * Class that creates the Account Page
 */
public class AccountPage {


    Customer customer;
    private Storefront storefront;
    private Stage primaryStage;
    private Cart cart;




    /**
     * Constructor for an Account Page that displays a customers account information
     *
     * @param customer the customer whose account information is being displayed
     * @param storefront the storefront is associated with the specific customer
     * @param primaryStage the main window that displays the Account Page
     */
    public AccountPage(Customer customer, Storefront storefront, Stage primaryStage) {
        this.customer = customer;
        this.storefront = storefront;
        this.primaryStage = primaryStage;
        this.cart = CartFileReader.retrieveCart(customer.getId());
    }


    /**
     * Creates the Account Page, and it's specific elements in the GUI
     *
     * The Account Page includes:
     * - a top section that holds a "back to home" button, the AJAD logo, a "cart" button, and an "Account Information" label
     * - a center section that displays the customers account information in different boxes
     *
     * @return a Scene that displays the Account Page
     */
    public Scene getAccountPage(){
        CustomerFileReader.retrieveAllCustomers();
        TopBoxFactory topBoxFactory = new TopBoxFactory(storefront, cart, primaryStage);




        Label accountInfo = new Label("Account Information:");
        accountInfo.setAlignment(Pos.TOP_LEFT);
        accountInfo.setFont(Font.font("Verdana", 20));
        accountInfo.setPadding(new Insets(15, 0, 0, 15));


        Label username = new Label(customer.getUsername());
        username.setAlignment(Pos.TOP_LEFT);
        username.setFont(Font.font("Verdana", 20));
        username.setPadding(new Insets(0, 0, 0, 15));


        VBox customerNameBox = createInfoBoxes("Name:",customer.getFirstName() + " " + customer.getLastName());
        VBox customerEmailBox = createInfoBoxes("Email:", customer.getEmail());
        VBox customerAddressBox = createInfoBoxes("Address:", customer.getAddress());




        GridPane InfoBoxesLayout = new GridPane();
        InfoBoxesLayout.setStyle("-fx-background-color: white");
        InfoBoxesLayout.setAlignment(Pos.CENTER);
        InfoBoxesLayout.setHgap(60);
        InfoBoxesLayout.setVgap(40);


        InfoBoxesLayout.add(customerNameBox, 0, 0);
        InfoBoxesLayout.add(customerEmailBox, 0, 1);
        InfoBoxesLayout.add(customerAddressBox, 1, 0);




        BorderPane accountPageLayout = new BorderPane();
        accountPageLayout.setStyle("-fx-background-color: white"); //Sets the background of the page to white


        accountPageLayout.setTop(new VBox(topBoxFactory.getTopSection(), username, accountInfo));
        accountPageLayout.setCenter(InfoBoxesLayout);


        return new Scene(accountPageLayout);
    }




    /**
     * A helper method creates a VBox called infoBox that holds a particular piece of the customer's information
     * such as their full name, email, and address
     *
     * @param boxTitle Label for the specific type of information that is being displayed
     * @param boxContent Label that displays the account information
     * @return infoBox
     */
    public VBox createInfoBoxes (String boxTitle, String boxContent) {
        VBox infoBox = new VBox();
        infoBox.setAlignment(Pos.CENTER);
        infoBox.setSpacing(10);
        infoBox.setPrefSize(320, 200);
        infoBox.setStyle("-fx-background-color: #e8e8e8; -fx-background-radius: 30;");


        Label boxTitleLabel = new Label(boxTitle);
        boxTitleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));


        Label boxContentLabel = new Label(boxContent);
        boxContentLabel.setFont(Font.font("Verdana", 16));


        infoBox.getChildren().addAll(boxTitleLabel, boxContentLabel);


        return infoBox;
    }


}