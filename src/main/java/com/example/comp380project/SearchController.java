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

        System.out.println(items.toString()); //DEBUG

        for (Item item : items) {
            String itemName = item.getName();
            if (itemName.toLowerCase().contains(query.toLowerCase())) {
                items_found.add(item);
            }
        }
        return items_found;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter an item to search.\n");
        String query = in.next();
        List<Item> items_found = search(query);
        System.out.print(items_found.toString());
        //System.out.print(item.getName());
    }
}
