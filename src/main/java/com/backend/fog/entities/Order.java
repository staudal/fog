package com.backend.fog.entities;

import java.util.ArrayList;

public class Order {
    private int id;
    private int width;
    private int length;
    private int height;
    private int slope;
    private int totalPrice;
    private int discountPrice;
    private int status;
    private Customer customer;
    private ArrayList<Product> partsList;

    public Order() {

    }

    public Order(int id, int width, int length, int height, int slope, int totalPrice, int discountPrice, int status) {
        this.id = id;
        this.width = width;
        this.length = length;
        this.height = height;
        this.slope = slope;
        this.totalPrice = totalPrice;
        this.discountPrice = discountPrice;
        this.status = status;
    }

    // ONLY USED FOR PARTS LIST
    public Order(int id, int width, int length, int height, int slope, int totalPrice, int discountPrice, int status, ArrayList<Product> partsList) {
        this.id = id;
        this.width = width;
        this.length = length;
        this.height = height;
        this.slope = slope;
        this.totalPrice = totalPrice;
        this.discountPrice = discountPrice;
        this.status = status;
        this.partsList = partsList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSlope() {
        return slope;
    }

    public void setSlope(int slope) {
        this.slope = slope;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<Product> getPartsList() {
        return partsList;
    }

    public void setPartsList(ArrayList<Product> partsList) {
        this.partsList = partsList;
    }
}
