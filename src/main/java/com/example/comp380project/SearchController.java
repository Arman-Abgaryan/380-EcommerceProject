package com.example.comp380project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class that handles search functions
 */
public class SearchController  {

    /**
     * Search function to retrieve items
     * @param query
     * @return list of items that match query
     */
    public static List<Item> search(String query) {
        if (query.equalsIgnoreCase("tshirt")){
            query = "t-shirt";
        }
        query = query.toLowerCase();

        // Retrieve all items in stock
        List<Item> items = ItemFileReader.retrieveAllItems();

        // List that contains items matching search string
        List<Item> items_found = new ArrayList<>();

        for (Item item : items) {
            String itemName = item.getName();
            String category = item.getCategory();

            if (itemName.toLowerCase().contains(query) || category.toLowerCase().contains(query)) {
                items_found.add(item);
            }
        }
        return items_found;
    }
}
