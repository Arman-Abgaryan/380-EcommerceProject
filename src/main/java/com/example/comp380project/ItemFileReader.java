package com.example.comp380project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ItemFileReader {
    private static final String CSV_ITEM_INFO = "data/Items.csv";

    // Add new item to stock
    public void saveItem(Item item) throws IOException, ClassNotFoundException {
        List<Item>currentStock = retrieveAllItems();

        for (Item existingItem : currentStock){
            if (existingItem != null) {
                if (existingItem.getId() == item.getId()) {
                    System.out.println("Item " + item.getName() + " with ID " + item.getId() + " already exists. Not saving.");
                    return;
                }
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_ITEM_INFO, true))) {  // Append mode
            writer.println(itemToCSV(item));
        }
    }

    // Return list of customers
    public List<Item> retrieveAllItems() throws FileNotFoundException, IOException, ClassNotFoundException {
        List<Item> items = new ArrayList<>();
        File file = new File(CSV_ITEM_INFO);

        if (!file.exists()) {
            return items;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                items.add(csvToItem(line));
            }
        }
        return items;
    }

    private String itemToCSV(Item item){
        return item.getId() +
                "," + item.getName() +
                "," + item.getPrice() +
                "," + item.getStock();
    }

    private Item csvToItem(String line){
        String[] data = line.split(",");
        if(data[0].equalsIgnoreCase("id")) return null;

        int id = Integer.parseInt(data[0]);
        String name = data[1];
        double price = Double.parseDouble(data[2]);
        int stock = Integer.parseInt(data[3]);

        return new Item(id, name, price, stock);
    }
}
