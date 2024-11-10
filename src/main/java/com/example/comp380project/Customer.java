package com.example.comp380project;

import java.io.Serializable;

public class Customer implements Serializable{
    private static int nextID = 1;

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String username;
    private String password;


    public Customer(int id,String firstName, String lastName, String email, String address, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    public Customer(String firstName, String lastName, String email, String address, String username, String password){
        this.id = nextID++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    int getId(){
        return id;
    }

    public static void setNextID(int nextID) {
        Customer.nextID = nextID;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    String getEmail() {
        return email;
    }

    String getAddress() {
        return address;
    }

    String getUsername(){
        return username;
    }

    String getPassword(){
        return password;
    }

    // To make it more human-readable
    @Override
    public String toString() {
        return
                "Id: " + id + "\n" +
                "First name: " + firstName + "\n" +
                "Last name: " + lastName + "\n" +
                "Email: " + email + "\n" +
                "Address: " + address
                ;
    }
}

