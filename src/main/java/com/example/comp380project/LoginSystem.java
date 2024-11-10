package com.example.comp380project;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Class that handles login procedures
 */
public class LoginSystem {
    private List<Customer> customers;

    public LoginSystem(){
        this.customers = CustomerFileReader.retrieveAllCustomers();
    }

    /**
     * Checks if username doesn't already exist in the system.
     * @param username
     * @param password
     * @return true if username is valid, false if username already exists.
     */
    public boolean newUser(String username, String password){
       for (Customer customer : customers){
           if (customer.getUsername().equals(username)){
               System.out.println("Username already exists.");
               return false;
           }
       }
       return true;
    }

    /**
     * Method that verifies credentials
     * @param username
     * @param password
     * @return
     */
    public boolean authenticateUser(String username, String password){
        for (Customer customer : customers){
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}


