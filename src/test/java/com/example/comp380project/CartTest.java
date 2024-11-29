package com.example.comp380project;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void getIdCart() {
        Cart cart = new Cart();
        cart.setIdCart(5);
        assertEquals(5, cart.getIdCart());
    }

    @Test
    void getCustomer() {
        Cart cart = CartFileReader.retrieveCart(2);
        assertEquals(3, cart.getCustomer().getId());

    }

    @Test
    void getItems() {
        Cart cart = CartFileReader.retrieveCart(2);
        Map<Item,Integer> items = cart.getItems();
        assertEquals(1, items.size());
    }

    @Test
    void addItem() {
        Cart cart = new Cart();
        Item item = ItemFileReader.retrieveItem(1);
        cart.addItem(item);
        assertEquals(1, cart.getItems().size());
    }

    @Test
    void removeItem() {
        Cart cart = new Cart();
        Item item = ItemFileReader.retrieveItem(1);
        cart.addItem(item);
        cart.removeItem(item);
        assertEquals(0, cart.getItems().size());
    }

    @Test
    void getTotalAmount() {
        Cart cart = new Cart();
        Item item = ItemFileReader.retrieveItem(1);
        cart.addItem(item);
        cart.addItem(item);
        assertEquals(39.98, cart.getTotalAmount());
    }
}