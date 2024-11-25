package com.example.comp380project;

import java.util.Map;

public class Order {

    private static int nextOrderId = 0;

    private int orderId;
    private Customer customer;
    private Map<Item, Integer> items;
    private String address;

    public Order(Cart cart) {
        this.orderId = orderId;
        this.customer = cart.getCustomer();
        this.items = cart.getItems();
        this.address = customer.getAddress();
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public String getAddress() {
        return address;
    }

    public static void setNextOrderId(int nextOrderId) {
        Order.nextOrderId = nextOrderId;
    }
}
