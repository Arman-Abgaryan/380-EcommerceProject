package com.example.comp380project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Class that handles login procedures
 */
public class LoginSystem {
    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private static final long LOCKOUT_TIME = 60000;
    private Map<String,Integer> attempts = new HashMap<>();
    private Map<String,Long> lockoutTime = new HashMap<>();
    private List<Customer> customers;

    public LoginSystem(){
        this.customers = CustomerFileReader.retrieveAllCustomers();
    }

    /**
     * Method that handles all login operations
     * @param username
     * @param password
     * @return true on successful login, false otherwise
     */
    public boolean login(String username, String password){
        if (isLockedOut(username)){
            System.out.println("Account is locked. Please try again at a later time.");
        }
        if(authenticateUser(username,password)){
            attempts.put(username,0);
            System.out.println("Login successful.");
            return true;
        }
        else{
            int attempt = attempts.getOrDefault(username,0);
            attempt++;
            attempts.put(username,0);
            if(attempt >= MAX_LOGIN_ATTEMPTS){
                lockoutTime.put(username,System.currentTimeMillis());
                System.out.println("Account is locked due to multiple failed attempts.");
            }
            return false;
        }
    }

    public void createNewUser(String firstName, String lastName, String email, String address, String username, String password){
        if (isUsernameAvailable(username)){
            Customer customer = new Customer(firstName,lastName,email,address,username,password);
            CustomerFileReader.saveCustomer(customer);
            System.out.println("New customer " + customer.getFirstName() + " saved succesfully.");

        }
        else System.out.println("Error");
    }

    /**
     * Checks if username doesn't already exist in the system.
     * @param username
     * @param password
     * @return true if username is valid, false if username already exists.
     */
    public boolean isUsernameAvailable(String username){
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
    private boolean authenticateUser(String username, String password){
        for (Customer customer : customers){
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    /**
     * Mehod that checks if a user is locked out
     * @param username
     * @return true if user is locked out, false otherwise
     */
    private boolean isLockedOut(String username){
        Long lockTime = lockoutTime.get(username);
        if (lockTime != null){
            if (System.currentTimeMillis() - lockTime < LOCKOUT_TIME) return true;
            else{
                lockoutTime.remove(username);
                return false;
            }
        }
        return false;
    }
}


