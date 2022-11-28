package com.backend.fog.entities;

import java.util.UUID;

public class Carport {
    private UUID id;
    private int width;
    private int length;
    private int height;
    private int slope;
    private Customer customer;

    public Carport(int width, int length, int height, int slope, Customer customer) {
        id = UUID.randomUUID();
        this.width = width;
        this.length = length;
        this.height = height;
        this.slope = slope;
        this.customer = customer;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
