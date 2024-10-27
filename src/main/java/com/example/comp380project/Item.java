package com.example.comp380project;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String name;
    private double price;
    private int stock;
    private String imagePath;


    public Item(int id, String name, double price, int stock, String imagePath){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.imagePath = imagePath;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagePath(){
        return imagePath;
    }
    


    // To make it more human-readable
    @Override
    public String toString() {
        return
                + id + ";"
                + name + ";"
                + price + ";"
                + stock
                ;
        }
}
