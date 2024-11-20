package com.example.comp380project;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for the Cart object
 * Shows to which customer the cart belongs to as well as list of items attached to the cart
 */
public class Cart implements Serializable {
    private static int nextID = 1;

    private int idCart;
    private Customer customer;
    private Map<Item, Integer> items = new HashMap<>();

    /**
     * Default constructor for creating an empty cart
     */
    public Cart() {
        this.idCart = nextID++;
        this.customer  = getCustomer();
        this.items = new HashMap<>();
    }

    /**
     * Constructor that is used to retrieve the Cart object from CSV files
     * @param idCart
     * @param customer
     * @param items
     */
    public Cart(int idCart, Customer customer, Map<Item, Integer> items) {
        this.idCart = idCart;
        this.customer = customer;
        this.items = items;
    }

    /**
     * Method to retrieve id number of cart
     * @return idCart
     */
    public int getIdCart() {
        return idCart;
    }

    /**
     * Method that sets the next available id for cart
     * @param nextID next available ID
     */
    public static void setNextID(int nextID){
        Cart.nextID = nextID;
    }

    /**
     *
     * @param idCart
     */
    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    /**
     * Method that retrieves Customer object
     * @return Customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Method to set a customer for the cart
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Method that retrieves items currently in the cart
     * @return list of items and their quantity
     */
    public Map<Item, Integer> getItems() {
        return items;
    }

    /**
     * Method to add an item to the cart
     * @param item
     */
    public void addItem(Item item) {
        if (items.containsKey(item)){

            items.put(item,items.get(item) + 1);
        }else {
            items.put(item,1);
        }
    }

    /**
     * Method to remove an item from the cart
     * @param item
     */
    public void removeItem(Item item) {
        if (items.containsKey(item)) {
            int newQuantity = items.get(item)-1;
            if (newQuantity > 0) {
                items.put(item,newQuantity);
            } else {
                items.remove(item);
            }
        }
    }

    /**
     * Method that calculates total price of all items in the cart
     * @return total price
     */
    public double getTotalAmount() {
        double total = 0;

        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    /**
     * Overides the toString() method to return a suitable string to be used with storing the data in the CSV file.
     * @return
     */
    @Override
    public String toString() {
        Customer customer = this.getCustomer();
        int customerID = customer.getId();

        StringBuilder itemsCSV = new StringBuilder();
        for (Map.Entry<Item, Integer> entry : this.getItems().entrySet()) {
            if (itemsCSV.length() > 0) {
                itemsCSV.append(";");
            }
            itemsCSV.append(entry.getKey().getName())
                    .append(":")
                    .append(entry.getValue());
        }
        return this.getIdCart() + "," + customerID + "," + itemsCSV;
    }
}
