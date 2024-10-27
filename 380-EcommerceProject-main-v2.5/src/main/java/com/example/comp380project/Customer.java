package com.example.comp380project;

import java.io.Serializable;

public class Customer implements Serializable{
    private static int nextID = 1;

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;

    public Customer(int id,String firstName, String lastName, String email, String address) {
        this.id = id;
        this.firstName =firstName;
        this.lastName =lastName;
        this.email =email;
        this.address =address;
    }

    public Customer(String firstName, String lastName, String email, String address){
        this.id = nextID++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;

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

    // To make it more human-readable
    @Override
    public String toString() {
        return
                + id + ";"
                + firstName + ";"
                + lastName + ";"
                + email + ";"
                + address
                ;
    }
}

