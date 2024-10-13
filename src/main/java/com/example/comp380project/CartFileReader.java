package com.example.comp380project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class CartFileReader {
    private static final String CSV_CART_INFO = "data/ItemsInCart.csv";

    // Write Cart object to file
    public void saveCart(Cart cart) throws IOException, ClassNotFoundException {
        // Retrieve list of carts already saved in file
        List<Cart> existingCarts = retrieveAllCarts();

        // Check if cart being saved already exists
        for (Cart existingCart : existingCarts) {
            if (existingCart != null) {
                if (existingCart.getIdCart() == cart.getIdCart()) {
                    System.out.println("Cart " + cart.getIdCart() + " for customer " + cart.getCustomer().getFirstName() + " already exists. Not saving.");
                    return;
                }
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_CART_INFO, true))) {
            writer.println(cartToCSV(cart));
        }
    }

    // Retrieve Cart by ID
    public Cart retrieveCart (int id) throws IOException, ClassNotFoundException{
        List<Cart> existingCarts = retrieveAllCarts();
        for (Cart cart : existingCarts){
            if (cart.getIdCart() == id){
                return cart;
            }
        }
        return null;
    }

    // Helper method for saveCart
    public List<Cart> retrieveAllCarts() throws FileNotFoundException, IOException, ClassNotFoundException {
        List<Cart> carts = new ArrayList<>();
        File file = new File(CSV_CART_INFO);

        if (!file.exists()) {
            return carts;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                carts.add(csvToCart(line));
            }
        }
        return carts;
    }

    public Cart csvToCart(String line) {
        String[] data = line.split(",");
        if (data.length < 3 || data[0].equalsIgnoreCase("idCart")) return null;

        int id = Integer.parseInt(data[0]);

        // Parse customer data (assuming the order: id, firstName, lastName, email, phoneNumber)
        Customer customer = null;
        try{
            int customerId = Integer.parseInt(data[1]);
            customer = new CustomerFileReader().retrieveCustomer(customerId);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error retrieving customer" + e.getMessage());
            return null;
        }

        // Parse items from the CSV string (starting from index 6)
        Map<Item, Integer> items = parseItems(data[2]);

        return new Cart(id, customer, items);
    }

    private String itemsToCSV(Map<Item, Integer> items) {
        StringBuilder itemsCSV = new StringBuilder();
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            if (itemsCSV.length() > 0) {
                itemsCSV.append(";"); // Separate items with semicolons
            }
            itemsCSV.append(entry.getKey().getId()) // Assuming Item has getId() or similar
                    .append(":")
                    .append(entry.getValue());
        }
        return itemsCSV.toString();
    }

    private String cartToCSV(Cart cart){
        return cart.getIdCart() +
                "," + cart.getCustomer().getId() +
                "," + itemsToCSV(cart.getItems());
    }

    private Map<Item, Integer> parseItems (String line){
		Map<Item,Integer> items = new HashMap<>();
		
		String[] data = line.split(";");
		if (data[0].equalsIgnoreCase("id")) return null;
		
		for (String pair : data){
			String[] quantity = pair.split("=");
			
			if (quantity.length != 2){
				System.out.println("Invalid item entry " + pair);
				continue;
			}
			
			String itemName = quantity[0];
			int item_quantity = Integer.parseInt(quantity[1]);
			
			Item item = new ItemFileReader().csvToItem(itemName);
			
			items.put(item, item_quantity);
		}
		return items;

    }

}
