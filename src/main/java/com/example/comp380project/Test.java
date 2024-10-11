package com.example.comp380project;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Customer Alfredo = new Customer(1,"Alfredo", "Ulloa", "alfredoulloa@ymail.com", "Not Found");
        Cart shop = new Cart();
        shop.setCustomer(Alfredo);
        Item sweater = new Item(1,"Sweater", 49.99, 10);
        Item shirt = new Item(2,"Shirt", 39.99, 10);

        shop.addItem(sweater, 2);
        shop.addItem(shirt, 4);

        Customer person1 = new Customer(2,"John", "Doe", "johndoe@gmail.com", "123 Elm St");

        // Testing saving customer info functionality
        try {
            CustomerFileReader f1 = new CustomerFileReader();

            // Save customer to file
            f1.saveCustomer(Alfredo);
            f1.saveCustomer(person1);

            CartFileReader f4 = new CartFileReader();

            f4.saveCart(shop);


            // Retrieve customer from file
            List<Customer> customers = f1.retrieveAllCustomers();
            System.out.println("Retrieved Customers: " + customers);

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please make sure the file exists.");
        } catch (IOException e) {
            System.out.println("An IO error occurred.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found. Make sure you're retrieving the correct object.");
        }

        shop.addItem(sweater, 2);
        System.out.println("$" + shop.getTotalAmount());

        System.out.println(shop.getItems());
        shop.addItem(shirt, 4);
        System.out.println(shop.getItems());
        System.out.println("$" + shop.getTotalAmount());
        System.out.println(shop.getCustomer().getFirstName());


    }
}
