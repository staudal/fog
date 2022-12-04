package com.backend.fog.entities;

public class Product {
    private int id;
    private String name;
    private int mPrice;
    private int length;
    private int quantity;

    public Product() {
    }

    public Product(int id, String name, int mPrice, int length) {
        this.id = id;
        this.name = name;
        this.mPrice = mPrice;
        this.length = length;
    }

    public Product(int id, String name, int mPrice, int length, int quantity) {
        this.id = id;
        this.name = name;
        this.mPrice = mPrice;
        this.length = length;
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

    public int getPrice() {
        return mPrice;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
