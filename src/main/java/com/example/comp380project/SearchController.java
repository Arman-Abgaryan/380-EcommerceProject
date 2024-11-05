package com.example.comp380project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchController  {

    String query;

    public String getQuery() {
        return query;
    }

    public static List<Item> search(String query) {

        // Retrieve all items in stock
        List<Item> items = ItemFileReader.retrieveAllItems();

        // List that contains items matching search string
        List<Item> items_found = new ArrayList<>();

        for (Item item : items) {
            String itemName = item.getName();
            if (itemName.toLowerCase().contains(query.toLowerCase())) {
                items_found.add(item);
            }
        }
        return items_found;
    }
}
