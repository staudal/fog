package com.backend.fog.facades;

import com.backend.fog.entities.Customer;
import com.backend.fog.entities.Order;
import com.backend.fog.mappers.OrderMapper;
import com.backend.fog.persistence.DatabaseConnection;

import java.util.Map;
import java.util.UUID;

public class OrderFacade {
    DatabaseConnection connection = new DatabaseConnection();
    OrderMapper orderMapper = new OrderMapper();

    public void createNewOrder(Order order) {
        orderMapper.createNewOrder(order, connection);
    }

    public Map<UUID, Order> getCustomerOrders(Customer customer) {
        return orderMapper.getCustomerOrders(customer, connection);
    }

    public Map<UUID, Order> getAllOrders() {
        return orderMapper.getAllOrders(connection);
    }

    public Order getOrder(UUID id) {
        return orderMapper.getOrder(id, connection);
    }

    public int getWidth(UUID id) {
        return orderMapper.getWidth(id, connection);
    }

    public int getHeight(UUID id) {
        return orderMapper.getHeight(id, connection);
    }

    public int getLength(UUID id) {
        return orderMapper.getLength(id, connection);
    }

    public int getSlope(UUID id) {
        return orderMapper.getSlope(id, connection);
    }

    public int getTotalPrice(UUID id) {
        return orderMapper.getTotalPrice(id, connection);
    }

    public int getDiscountPrice(UUID id) {
        return orderMapper.getDiscountPrice(id, connection);
    }

    public void removeCustomerOrder(UUID id) {
        orderMapper.removeCustomerOrder(id, connection);
    }

    public void updateOrderTotalPrice(int price) {
        orderMapper.updateOrderTotalPrice(price, connection);
    }

    public void updateOrderDiscountPrice(int price, UUID id) {
        orderMapper.updateOrderDiscountPrice(price, id, connection);
    }

    public int getStatus(UUID id) {
        return orderMapper.getStatus(id, connection);
    }

    public void updateStatus(int status, UUID id) {
        orderMapper.updateStatus(status, id, connection);
    }
}
