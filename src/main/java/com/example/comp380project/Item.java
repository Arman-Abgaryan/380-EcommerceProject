package com.example.comp380project;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class that hold Item(Clothing) information
 */
public class Item implements Serializable {
    private int id;
    private String name;
    private double price;
    private int stock;
    private String imagePath;
    private String size;
    private String category;

    /**
     * Constructor for Item
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param imagePath
     * @param size
     */
    public Item(int id, String name, double price, int stock, String imagePath,String size){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.imagePath = imagePath;
        this.size = size;
    }

    /**
     * Constructor for Item
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param imagePath
     * @param size
     * @param category
     */
    public Item(int id, String name, double price, int stock, String imagePath, String size, String category){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.imagePath = imagePath;
        this.size = size;
        this.category = category;
    }

    /**
     * Getter Method that returns item Size
     * @return size
     */
    public String getSize() {
        return size;
    }

    /**
     * Method to split different sizes in CSV by a ","
     * @return the different sizes from the CSV file split by a comma
     */
    public String[] getSizes() {
        // Split the sizesCSV into an array of sizes
        return size.split(",");
    }

    /**
     * Getter Method that returns item ID
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Getter Method that returns item Name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter Method that returns item Price
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Getter Method that returns item STock
     * @return stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Getter Method that returns item Category
     * @return Category
     */
    public String getCategory(){
        return category;
    }


    public void setStock(int stock) {
        this.stock = stock;
    }
    /**
     * Getter Method that returns item Image location
     * @return image location of the item
     */
    public String getImagePath(){
        return imagePath;
    }

    /**
     * Method to check if two objects are true
     * @param o the object that is being compared to the item
     * @return true if the object is equal to the item, false if the object is not equal to the item
     */
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

    /**
     * Method to place item id, name, price, and size into Hashcode
     * @return the hashcode of the object, including the items id, name, price, and size
     */
    @Override
    public int hashCode(){
        return Objects.hash(id,name,price,size);
    }


    /**
     * Converts the item to a human-readable string
     * @return a string separated by semicolons containing the items details
     */
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
