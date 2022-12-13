package com.backend.fog.entities;

public class Product {
    private int id;
    private String name;
    private int kind;
    private int use;
    private int length;
    private int price;
    private String category;
    private String description;
    private int quantity;

    public Product() {
    }

    public Product(int id, String name, int length, int price, String category, String description) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public Product(int id, String name, int length, int price, String category, String description, int quantity) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getUse() {
        return use;
    }

    public void setUse(int use) {
        this.use = use;
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
