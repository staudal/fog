package com.backend.fog.entities;

public class Product {
    private int id;
    private String dimensions;
    private int length;
    private int price;
    private String category;
    private String description;
    private int quantity;

    public Product() {
    }

    public Product(int id, String dimensions, int length, int price, String category, String description) {
        this.id = id;
        this.dimensions = dimensions;
        this.length = length;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public Product(int id, String dimensions, int length, int price, String category, String description, int quantity) {
        this.id = id;
        this.dimensions = dimensions;
        this.length = length;
        this.price = price;
        this.category = category;
        this.description = description;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
