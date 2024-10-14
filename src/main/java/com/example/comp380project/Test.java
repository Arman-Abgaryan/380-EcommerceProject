package com.example.comp380project;
import java.io.*;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        // Read/Write objects
        CustomerFileReader f1 = new CustomerFileReader();
        CartFileReader f2 = new CartFileReader();
        ItemFileReader f3 = new ItemFileReader();

        //Creating objects for testing purposes
        Customer c1 = new Customer("Alfredo", "Ulloa", "alfredoulloa@ymail.com", "Not Found");
        Customer c2 = new Customer("John", "Doe", "johndoe@gmail.com", "123 Elm St");
        Customer c3 = new Customer("Albert", "Smith", "albert.smith@mail.com.com", "694 Daisy Ln");
        Customer c4 = new Customer("Michael", "Torres", "michael.torres.2@us.mil", "1248 Freedom Dr");
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Cart cart3 = new Cart();
        Cart cart4 = new Cart();

        // Setting customer to Carts
        cart1.setCustomer(c1);
        cart2.setCustomer(c3);
        cart3.setCustomer(c2);
        cart4.setCustomer(c4);

        // Testing read/write functionality
        try {
            Item shirt = f3.retrieveItem(4);
            Item sweater = f3.retrieveItem(1);

            // Save customer to file
            f1.saveCustomer(c1);
            f1.saveCustomer(c2);
            f1.saveCustomer(c3);
            f1.saveCustomer(c4);

            cart1.addItem(shirt,3);
            cart2.addItem(shirt, 2);
            cart3.addItem(shirt,5);
            cart3.addItem(sweater, 1);
            cart4.addItem(shirt,10);

            // Saving cart to file
            f2.saveCart(cart1);
            f2.saveCart(cart2);
            f2.saveCart(cart3);
            f2.saveCart(cart4);

            // Retrieve customer from file
            List<Customer> customers = f1.retrieveAllCustomers();

            System.out.println("Retrieved Customers: " + customers);

            // Retrieving Customer from file
            System.out.println("--------------------------------");
            System.out.println("Test for retrieving customer object.");
            Customer p4 = f1.retrieveCustomer(2);
            System.out.println("Customer info: " + p4);
            System.out.println("--------------------------------");

            System.out.println("Test for retrieving cart object.");
            Cart retrieved = f2.retrieveCart(2);
            System.out.println("Retrieved cart is: " + retrieved);
            System.out.println("Cart belongs to " + retrieved.getCustomer().getFirstName() + " " + retrieved.getCustomer().getLastName());
            System.out.println("--------------------------------");

            System.out.println("Test for verifying cart object.");
            System.out.println("ID: " + cart3.getIdCart() + "\n" + "customer: " + cart3.getCustomer().getFirstName() + "\n" + "Items: " + cart3.getItems());
            System.out.println("--------------------------------");

            System.out.println("$" + cart1.getTotalAmount());
            System.out.println("This cart belongs to " + cart1.getCustomer().getFirstName() + " " + cart1.getCustomer().getLastName());
            System.out.println(cart1.toString());
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
