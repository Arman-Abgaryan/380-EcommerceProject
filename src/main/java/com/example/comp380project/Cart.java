package com.example.comp380project;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a shopping cart for a customer.
 * Stores items with their quantities and provides methods to manage the cart's content.
 */
public class Cart implements Serializable {

    /**
     * A static counter to assign unique IDs to carts.
     */
    private static int nextID = 1;

    /**
     * Unique ID for this cart.
     */
    private int idCart;
    private Customer customer;

    /**
     * Items in the cart and their quantities.
     */
    private Map<Item, Integer> items = new HashMap<>();

    /**
     * Creates a new cart with a unique ID and no items.
     */
    public Cart() {
        this.idCart = nextID++;
        this.customer  = getCustomer();
        this.items = new HashMap<>();
    }

    /**
     * Creates a cart with the given ID, customer, and items.
     *
     * @param idCart   the cart ID
     * @param customer the customer
     * @param items    the items in the cart
     */
    public Cart(int idCart, Customer customer, Map<Item, Integer> items) {
        this.idCart = idCart;
        this.customer = customer;
        this.items = items;
    }

    /**
     * Retrieves the ID of the cart
     * @return idCart as an int
     */
    public int getIdCart() {
        return idCart;
    }

    /**
     * Sets the static ID for the next cart
     * @param nextID the new ID to assign to the next cart, as an integer
     */
    public static void setNextID(int nextID){
        Cart.nextID = nextID;
    }

    /**
     * Sets the ID of the current cart
     * @param idCart the ID that is assigned to the cart, as an integer
     */
    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    /**
     * Retrieves the customer that is associated with the current cart
     * @return customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Assigns a customer to a cart
     * @param customer the customer that is assigned to the cart
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Retrieves a map of the items in the customers cart
     * @return items in the customers cart
     */
    public Map<Item, Integer> getItems() {
        return items;
    }

    /**
     * Adds an item to the cart, increasing the quantity if it already exists.
     *
     * @param item the item to add
     */
    public void addItem(Item item) {
        if (items.containsKey(item)){

            items.put(item,items.get(item) + 1);
        }else {
            items.put(item,1);
        }

    }

    /**
     * Removes an item from the cart, decreasing its quantity or removing it entirely.
     *
     * @param item the item to remove
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
     * Calculates the total price of all items in the cart.
     *
     * @return the total amount
     */
    public double getTotalAmount() {
        double total = 0;

        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();

        }
        return total;
    }

    /**
     * Returns a CSV representation of the cart.
     *
     * @return a CSV string with cart details
     */
    @Override
    public String toString() {
        // Convert the customer object to a CSV-friendly format
        Customer customer = this.getCustomer();
        int customerID = customer.getId();

        // Convert items map to a CSV-friendly format
        StringBuilder itemsCSV = new StringBuilder();
        for (Map.Entry<Item, Integer> entry : this.getItems().entrySet()) {
            if (itemsCSV.length() > 0) {
                itemsCSV.append(";");
            }
            itemsCSV.append(entry.getKey().getName())
                    .append(":")
                    .append(entry.getValue());
        }

        // Combine cart id, customer id,  and items into a single CSV string
        return this.getIdCart() + "," + customerID + "," + itemsCSV;
    }
}
