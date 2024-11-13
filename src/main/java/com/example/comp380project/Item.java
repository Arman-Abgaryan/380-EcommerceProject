package com.example.comp380project;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {
    private int id;
    private String name;
    private double price;
    private int stock;
    private String imagePath;
    private String size;
    private String category;


    public Item(int id, String name, double price, int stock, String imagePath,String size){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.imagePath = imagePath;
        this.size = size;
    }

    public Item(int id, String name, double price, int stock, String imagePath, String size, String category){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.imagePath = imagePath;
        this.size = size;
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public String[] getSizes() {
        // Split the sizesCSV into an array of sizes
        return size.split(",");
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

    public String getCategory(){
        return category;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagePath(){
        return imagePath;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;

        return id == item.id &&
                Double.compare(item.price,price) == 0 &&
                Objects.equals(name,item.name) &&
                Objects.equals(size,item.size);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id,name,price,size);
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
