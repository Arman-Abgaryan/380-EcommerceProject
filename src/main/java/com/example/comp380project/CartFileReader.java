package com.example.comp380project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class CartFileReader {
    private static final String CSV_CART_INFO = "data/Cart.csv";

    // Write Cart object to file
    public static void saveCart(Cart cart) {
        // Retrieve list of carts already saved in file
        List<Cart> existingCarts = retrieveAllCarts();

        // Check if cart being saved already exists
        for (Cart existingCart : existingCarts) {
            if (existingCart != null  && existingCart.getIdCart() == cart.getIdCart()) {
                System.out.println("Cart " + existingCart.getIdCart() + " for customer " + existingCart.getCustomer().getFirstName() + " already exists. Not saving.");
                return;
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_CART_INFO, true))) {
            writer.println(cartToCSV(cart));
        }catch (IOException e){}
    }

    // Retrieve Cart by ID
    public static Cart retrieveCart(int id) {
        List<Cart> existingCarts = retrieveAllCarts();

        for (Cart cart : existingCarts){
            if (cart == null) continue;
            if (cart.getIdCart() == id){
                return cart;
            }
        }
        return null;
    }

    // Helper method for saveCart
    public static List<Cart> retrieveAllCarts() {
        List<Cart> carts = new ArrayList<>();
        File file = new File(CSV_CART_INFO);
        int maxID = 0;

        if (!file.exists()) {
            return carts;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                carts.add(csvToCart(line));
            }
        }catch (IOException e){}
        // Removing null value
        carts.remove(0);

        /*---------------------------*/
        // Setting next available ID
        // Need to create a separate method in the future to initialize the next available ID on startup
        maxID = carts.getLast().getIdCart();
        Cart.setNextID(maxID);
        /*---------------------------*/
        return carts;
    }

    // Create Cart object from data in Cart.csv
    public static Cart csvToCart(String line) {
        String[] data = line.split(",");

        if (data.length < 3 || data[0].equalsIgnoreCase("idCart")) return null;

        int id = Integer.parseInt(data[0]);
        Customer customer;
        int customerId = Integer.parseInt(data[1]);
        customer = new CustomerFileReader().retrieveCustomer(customerId);

        Map<Item, Integer> items = parseItems(data[2]);

        return new Cart(id, customer, items);
    }

    private static String itemsToCSV(Map<Item, Integer> items) {
        StringBuilder itemsCSV = new StringBuilder();
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            if (itemsCSV.length() > 0) {
                itemsCSV.append(";");
            }
            itemsCSV.append(entry.getKey().getId())
                    .append(":")
                    .append(entry.getValue());
        }
        return itemsCSV.toString();
    }

    private static String cartToCSV(Cart cart){
        return cart.getIdCart() +
                "," + cart.getCustomer().getId() +
                "," + itemsToCSV(cart.getItems());
    }

    private static Map<Item, Integer> parseItems (String line) {
		Map<Item,Integer> items = new HashMap<>();

        String[] data = line.split(";");
        for( int i = 0; i < data.length; i++){
            String[] itemData = data[i].split(":");

            int itemId = Integer.parseInt(itemData[0]);
            int quantity = Integer.parseInt(itemData[1]);

            Item item = new ItemFileReader().retrieveItem(itemId);
            items.put(item, quantity);
        }
        return items;
    }
}
