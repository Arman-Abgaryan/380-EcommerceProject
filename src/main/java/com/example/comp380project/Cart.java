package com.example.comp380project;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {
    private static int nextID = 1;

    private int idCart;
    private Customer customer;
    private Map<Item, Integer> items;

    public Cart() {
        this.idCart = nextID++;
        items = new HashMap<>();
    }

    public Cart(int idCart, Customer customer, Map<Item, Integer> items) {
        this.idCart = idCart;
        this.customer = customer;
        this.items = new HashMap<>();
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    // Add an item to cart
    public void addItem(Item item, int quantity) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + quantity);
        } else {
            items.put(item, quantity);
        }
    }

    // Remove an item from cart
    public void removeItem(Item item) {
        items.remove(item);
    }

    // Calculate total amount
    public double getTotalAmount() {
        double total = 0;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

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
        return this.getIdCart() + "," + customerID + "," + itemsCSV.toString();
    }


}
