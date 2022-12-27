package com.backend.fog.entities;

import java.util.ArrayList;

public class Order {
    private int id;
    private int carportWidth;
    private int carportLength;
    private int shedWidth;
    private int shedLength;
    private int totalPrice;
    private int discountPrice;
    private int status;
    private Customer customer;
    private ArrayList<Product> partsList;

    public Order() {

    }

    public Order(int id, int carportWidth, int carportLength, int shedWidth, int shedLength, int totalPrice, int discountPrice, int status) {
        this.id = id;
        this.carportWidth = carportWidth;
        this.carportLength = carportLength;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
        this.totalPrice = totalPrice;
        this.discountPrice = discountPrice;
        this.status = status;
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

    public int getCarportWidth() {
        return carportWidth;
    }

    public void setCarportWidth(int carportWidth) {
        this.carportWidth = carportWidth;
    }

    public int getCarportLength() {
        return carportLength;
    }

    public void setCarportLength(int carportLength) {
        this.carportLength = carportLength;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
    }

    public int getShedLength() {
        return shedLength;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
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
