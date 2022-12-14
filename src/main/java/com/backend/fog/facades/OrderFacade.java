package com.backend.fog.facades;

import com.backend.fog.entities.Order;
import com.backend.fog.entities.Product;
import com.backend.fog.mappers.OrderMapper;
import com.backend.fog.persistence.DatabaseConnection;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class OrderFacade {
    DatabaseConnection connection = new DatabaseConnection();
    OrderMapper orderMapper = new OrderMapper();

    public int createNewOrder(int carportWidth, int carportLength, int shedWidth, int shedLength, int customerId, int totalPrice, int discountPrice, int status) {
        return orderMapper.createNewOrder(carportWidth, carportLength, shedWidth, shedLength, customerId, totalPrice, discountPrice, status, connection);
    }

    public void createOrderLines(int orderId, int productId, int quantity) {
        orderMapper.createOrderLines(orderId, productId, quantity, connection);
    }

    public TreeMap<Integer, Order> getCustomerOrders(int customerId) {
        return orderMapper.getCustomerOrders(customerId, connection);
    }

    public Map<Integer, Order> getAllOrders() {
        return orderMapper.getAllOrders(connection);
    }

    public Order getOrder(int id) {
        return orderMapper.getOrder(id, connection);
    }

    public int countOrdersForCustomer(int customerId) {
        return orderMapper.countOrdersForCustomer(customerId, connection);
    }

    public ArrayList<Product> getProductsFromOrderLine(int orderId) {
        return orderMapper.getProductsFromOrderLine(orderId, connection);
    }

    public ArrayList<Product> getRafters(ArrayList<Product> products) {
        return orderMapper.getRafters(products, connection);
    }

    public int getWidth(UUID id) {
        return orderMapper.getWidth(id, connection);
    }

    public int getLength(UUID id) {
        return orderMapper.getLength(id, connection);
    }

    public int getTotalPrice(UUID id) {
        return orderMapper.getTotalPrice(id, connection);
    }

    public int getDiscountPrice(UUID id) {
        return orderMapper.getDiscountPrice(id, connection);
    }

    public void removeCustomerOrder(int id) {
        orderMapper.removeCustomerOrder(id, connection);
    }

    public void updateOrderTotalPrice(int price) {
        orderMapper.updateOrderTotalPrice(price, connection);
    }

    public void updateOrderDiscountPrice(int price, int orderId) {
        orderMapper.updateOrderDiscountPrice(price, orderId, connection);
    }

    public int getStatus(UUID id) {
        return orderMapper.getStatus(id, connection);
    }

    public void updateStatus(int status, int orderId) {
        orderMapper.updateStatus(status, orderId, connection);
    }
}
