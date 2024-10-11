package com.example.comp380project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class CartFileReader {
    private static final String CSV_CART_INFO = "data/ItemsInCart.csv";

    public void saveCart(Cart cart) throws IOException, ClassNotFoundException {
        List<Cart> existingCarts = retrieveAllCarts();

        for (Cart existingCart : existingCarts) {
            if (existingCart != null) {
                if (existingCart.getIdCart() == cart.getIdCart()) {
                    System.out.println("Cart " + cart.getIdCart() + " for customer " + cart.getCustomer().getFirstName() + " already exists. Not saving.");
                    return;
                }
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_CART_INFO, true))) {  // Append mode
            writer.println(cartToCSV(cart));
        }
    }

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

    private Cart csvToCart(String line){
        String[] data = line.split(",");
        if(data[0].equalsIgnoreCase("id")) return null;

        int id = Integer.parseInt(data[0]);
        Customer customer = new CustomerFileReader().csvToCustomer(data[1]);
        Map<Item,Integer> items = parseItems(data[2]);

        return new Cart(id, customer, items);
    }

    private String cartToCSV(Cart cart){
        return cart.getIdCart() +
                "," + cart.getCustomer() +
                "," + cart.getItems();
    }

    private Map<Item, Integer> parseItems (String line){
		Map<Item,Integer> items = new HashMap<>();
		
		String[] data = line.split(";");
		if (data[0].equalsIgnoreCase("id")) return null;
		
		for (String pair : data){
			String[] quantity = pair.split(":");
			
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
