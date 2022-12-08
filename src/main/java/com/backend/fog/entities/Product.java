package com.backend.fog.entities;

public class Product {
    private int id;
    private int width;
    private int height;
    private int length;
    private int price;
    private String category;
    private String description;
    private int quantity;

    public Product() {
    }

    public Product(int id, int width, int height, int length, int price, String category, String description) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.length = length;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public Product(int id, int width, int height, int length, int price, String category, String description, int quantity) {
        this.id = id;
        this.width = width;
        this.height = height;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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
