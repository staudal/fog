package com.backend.fog.facades;

import com.backend.fog.entities.Order;
import com.backend.fog.entities.Product;
import com.backend.fog.mappers.OrderMapper;
import com.backend.fog.persistence.DatabaseConnection;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class OrderFacade {
    DatabaseConnection databaseConnection;
    OrderMapper orderMapper = new OrderMapper();

    public OrderFacade(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public int createNewOrder(int carportWidth, int carportLength, int shedWidth, int shedLength, int customerId, int totalPrice, int discountPrice, int status) {
        return orderMapper.createNewOrder(carportWidth, carportLength, shedWidth, shedLength, customerId, totalPrice, discountPrice, status, databaseConnection);
    }

    public void createOrderLines(int orderId, int productId, int quantity) {
        orderMapper.createOrderLines(orderId, productId, quantity, databaseConnection);
    }

    public TreeMap<Integer, Order> getCustomerOrders(int customerId) {
        return orderMapper.getCustomerOrders(customerId, databaseConnection);
    }

    public Map<Integer, Order> getAllOrders() {
        return orderMapper.getAllOrders(databaseConnection);
    }

    public Order getOrder(int id) {
        return orderMapper.getOrder(id, databaseConnection);
    }

    public int countOrdersForCustomer(int customerId) {
        return orderMapper.countOrdersForCustomer(customerId, databaseConnection);
    }

    public ArrayList<Product> getProductsFromOrderLine(int orderId) {
        return orderMapper.getProductsFromOrderLine(orderId, databaseConnection);
    }

    public void removeCustomerOrder(int id) {
        orderMapper.removeCustomerOrder(id, databaseConnection);
    }

    public void updateOrderDiscountPrice(int price, int orderId) {
        orderMapper.updateOrderDiscountPrice(price, orderId, databaseConnection);
    }

    public void updateStatus(int status, int orderId) {
        orderMapper.updateStatus(status, orderId, databaseConnection);
    }

    public Product getRafter(ArrayList<Product> products) {
        return orderMapper.getRafter(products);
    }

    public Product getBeam(ArrayList<Product> products) {
        return orderMapper.getBeam(products);
    }

    public Product getPole(ArrayList<Product> products) {
        return orderMapper.getPole(products);
    }

    public ArrayList<Product> getCarportWoods(ArrayList<Product> allProducts) {
        return orderMapper.getCarportWoods(allProducts);
    }

    public ArrayList<Product> getShedWoods(ArrayList<Product> allProducts) {
        return orderMapper.getShedWoods(allProducts);
    }

    public ArrayList<Product> getCarportScrews(ArrayList<Product> allProducts) {
        return orderMapper.getCarportScrews(allProducts);
    }

    public ArrayList<Product> getShedScrews(ArrayList<Product> allProducts) {
        return orderMapper.getShedScrews(allProducts);
    }
}
