package com.example.project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class HelloController implements Initializable{

    //Select Size Choice Box
    @FXML
    private Label myPrice;

    @FXML
    private ChoiceBox<String> myChoiceBox;




    ////////Lable doesnt change
    //Change Pirce
    public void changePrice(){
        String mySize = myChoiceBox.getValue();
        if (mySize != null){
            switch(mySize){
                case "Small":
                    myPrice.setText("$12.99");
                    break;
                case "Medium":
                    myPrice.setText("$14.99");
                    break;
                case "Large":
                    myPrice.setText("$16.99");
                    break;
            }
        }
    }


    //Switch to Cart
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchtoCart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("cart.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    // Search Function
    ArrayList<String> items = new ArrayList<>(
            Arrays.asList("Shirt","shirt")
    );

    @FXML
    private TextField searchBar;

    @FXML
    private ListView<String> listView;

    @FXML//button
    void search (ActionEvent event){
        listView.getItems().clear();
        listView.getItems().addAll(searchList(searchBar.getText(),items));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getItems().addAll(items);

        myChoiceBox.getItems().addAll("Small","Medium","Large");
        myChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,newValue) -> {

        });

    }



    private List<String> searchList (String searchWords, List<String> listOfStrings){

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }
}