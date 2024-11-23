package com.example.comp380project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that reads Item informaton from CSV file
 */
public class ItemFileReader {
    private static final String CSV_ITEM_INFO = "data/Items.csv";

    /**
     * Adds a new item to the stock by saving it to the Items.CSV file
     * @param item the item that is being added to the Items.CSV file
     */
    // Add new item to stock
    public static void saveItem(Item item) {
        List<Item> currentStock = retrieveAllItems();

        for (Item existingItem : currentStock) {
            if (existingItem != null) {
                if (existingItem.getId() == item.getId()) {
                    System.out.println("Item " + item.getName() + " with ID " + item.getId() + " already exists. Not saving.");
                    return;
                }
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_ITEM_INFO, true))) {   // Append mode
            writer.println(itemToCSV(item));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that returns item based on ID
     * @param id ID of the item that is being returned
     * @return the item with its specific ID or nothing if no matiching ID is found
     */
    public static Item retrieveItem (int id){
        List<Item> existingItems = retrieveAllItems();
        for (Item item : existingItems){
            if (item == null) continue;
            if (item.getId() == id){
                return item;
            }
        }
        return null;
    }

    /**
     * Method that retrieves items from the CSV and places them into a ArrayList
     * @return the items from the ArrayList
     */
    public static List<Item> retrieveAllItems() {
        List<Item> items = new ArrayList<>();
        File file = new File(CSV_ITEM_INFO);

        if (!file.exists()) {
            return items;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                items.add(csvToItem(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        items.remove(0);
        return items;
    }

    /**
     * How CSV is read
     * @param item item whose details are being read
     * @return items details separated by semicolons
     */
    public static String itemToCSV(Item item){
        return item.getId() +
                ";" + item.getName() +
                ";" + item.getPrice() +
                ";" + item.getStock() +
                "l" + item.getSize() +
                ";" + item.getImagePath() +
                ";" + item.getCategory();
    }

    /**
     * Method to place item Information into Item object
     *
     * This method contains:
     * - The items ID as an integer
     * - The items Name as a string
     * - The items price as an integer
     * - The items stock as an integer
     * - The items imagePath as a string, which represents the location of the image of the item
     * - The sizes available for the item as a string
     * - The category of the item as a string
     *
     * @param line a string that contains the details of an item separated by semicolons
     * @return the item and its details
     */
    public static Item csvToItem(String line){
        String[] data = line.split(";");
        if(data[0].equalsIgnoreCase("id")) return null;

        int id = Integer.parseInt(data[0]);
        String name = data[1];
        double price = Double.parseDouble(data[2]);
        int stock = Integer.parseInt(data[3]);
        String imagePath = data.length > 4 ? data[4]: null;
        String size = data[5];
        String category = data[6].trim();

        return new Item(id, name, price, stock,imagePath,size, category);
    }
    }
