package com.example.comp380project;
import java.io.*;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        CustomerFileReader f1 = new CustomerFileReader();
        CartFileReader f2 = new CartFileReader();
        ItemFileReader f3 = new ItemFileReader();

        //Creating objects for testing purposes
        Customer Alfredo = new Customer(1,"Alfredo", "Ulloa", "alfredoulloa@ymail.com", "Not Found");
        Customer person1 = new Customer(2,"John", "Doe", "johndoe@gmail.com", "123 Elm St");
        Customer p3 = new Customer(3, "Poopy", "Butthole", "poopybutthole@poop.com", "69420 Poop Ln");

        Cart shop = new Cart();
        shop.setCustomer(Alfredo);
        Cart coolItems = new Cart();
        coolItems.setCustomer(p3);

        // Adding items to the cart


        // Testing saving customer info functionality
        try {
            Item shirt = f3.retrieveItem(4);

            // Save customer to file
            f1.saveCustomer(Alfredo);
            f1.saveCustomer(person1);

            // Saving cart to file
            f2.saveCart(shop);
            f2.saveCart(coolItems);

            // Retrieve cart info from file
//            List<Cart> carts = f2.retrieveAllCarts();
//            for (Cart cart : carts){
//                if (cart != null){
//                    System.out.println(cart);
//                }
//            }

            // Retrieve customer from file
            List<Customer> customers = f1.retrieveAllCustomers();
            System.out.println("Retrieved Customers: " + customers);

            // Retrieving Customer from file
            Customer p4 = f1.retrieveCustomer(2);
            System.out.println("Mr Poopy Butthole's info is the following: " + p4);

            shop.addItem(shirt, 4);


            System.out.println(shop.getItems());
            System.out.println("$" + shop.getTotalAmount());
            System.out.println("This cart belongs to " + shop.getCustomer().getFirstName() + " " + shop.getCustomer().getLastName());
            System.out.println(shop.toString());
            System.out.println("Item info of blue is " + shirt);

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please make sure the file exists.");
        } catch (IOException e) {
            System.out.println("An IO error occurred.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found. Make sure you're retrieving the correct object.");
        }




    }
}
