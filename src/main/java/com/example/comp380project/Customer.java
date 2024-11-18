package com.example.comp380project;

import java.io.Serializable;

/**
 * Class that holds Customer Information
 */
public class Customer implements Serializable{
    private static int nextID = 1;

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String username;
    private String password;


    /**
     * Constructor for existing CUstomer
     * @param id
     * @param firstName
     * @param lastName
     * @param email
     * @param address
     * @param username
     * @param password
     */
    public Customer(int id,String firstName, String lastName, String email, String address, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor for new Customer
     * @param firstName
     * @param lastName
     * @param email
     * @param address
     * @param username
     * @param password
     */
    public Customer(String firstName, String lastName, String email, String address, String username, String password){
        this.id = nextID++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    /**
     * Getter Method to get ID
     * @return id
     */
    int getId(){
        return id;
    }

    /**
     * Sets ID for next customer
     * @param nextID
     */
    public static void setNextID(int nextID) {
        Customer.nextID = nextID;
    }

    /**
     * Getter Method to get customer's first name
     * @return FirstName
     */
    String getFirstName() {
        return firstName;
    }

    /**
     * Getter Method to get customer's last name
     * @return LastName
     */
    String getLastName() {
        return lastName;
    }

    /**
     * Getter Method to get customer's email
     * @return Email
     */
    String getEmail() {
        return email;
    }

    /**
     * Getter Method to get customer's Username
     * @return Address
     */
    String getAddress() {
        return address;
    }

    /**
     * Getter Method to get customer's Username
     * @return Username
     */
    String getUsername(){
        return username;
    }

    /**
     * Getter Method to get customer's password
     * @return Password
     */
    String getPassword(){
        return password;
    }

}

