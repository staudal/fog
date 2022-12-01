package com.backend.fog.entities;

import java.util.UUID;

public class Order {
    private UUID id;
    private int width;
    private int length;
    private int height;
    private int slope;
    private int totalPrice;
    private int discountPrice;
    private int status;
    private Customer customer;

    public Order() {

    }

    public Order(int width, int length, int height, int slope, int status, int totalPrice, int discountPrice, Customer customer) {
        this.id = UUID.randomUUID();
        this.width = width;
        this.length = length;
        this.height = height;
        this.slope = slope;
        this.status = status;
        this.totalPrice = totalPrice;
        this.discountPrice = discountPrice;
        this.customer = customer;
    }

    public Order(UUID id, int width, int length, int height, int slope, int status, int totalPrice, int discountPrice, Customer customer) {
        this.id = id;
        this.width = width;
        this.length = length;
        this.height = height;
        this.slope = slope;
        this.status = status;
        this.totalPrice = totalPrice;
        this.discountPrice = discountPrice;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
