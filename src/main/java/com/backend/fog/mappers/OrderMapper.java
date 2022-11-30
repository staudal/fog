package com.backend.fog.mappers;

import com.backend.fog.entities.Customer;
import com.backend.fog.entities.Order;
import com.backend.fog.persistence.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class OrderMapper {
    public void createNewOrder(Order order, DatabaseConnection connection) {
        String sql = "INSERT INTO orders (id, carportWidth, carportHeight, carportLength, carportSlope, status, totalPrice, discountPrice   , customer_id) VALUES ('"+order.getId()+"', '"+order.getWidth()+"', '"+order.getHeight()+"', '"+order.getLength()+"', '"+order.getSlope()+"','"+"Forespørgsel afsendt"+"', '"+order.getTotalPrice()+"', '"+order.getDiscountPrice()+"', '"+order.getCustomer().getId()+"')";
        try {
            connection.connect().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public int getWidth(UUID id, DatabaseConnection connection) {
        try {
            int width = 0;
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM orders WHERE id = ?");
            statement.setString(1, String.valueOf(id));

            ResultSet set = statement.executeQuery();
            if (set.next()) {
                width = set.getInt("carportWidth");
            }
            return width;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public int getHeight(UUID id, DatabaseConnection connection) {
        try {
            int height = 0;
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM orders WHERE id = ?");
            statement.setString(1, String.valueOf(id));

            ResultSet set = statement.executeQuery();
            if (set.next()) {
                height = set.getInt("carportHeight");
            }
            return height;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public int getLength(UUID id, DatabaseConnection connection) {
        try {
            int length = 0;
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM orders WHERE id = ?");
            statement.setString(1, String.valueOf(id));

            ResultSet set = statement.executeQuery();
            if (set.next()) {
                length = set.getInt("carportLength");
            }
            return length;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public int getSlope(UUID id, DatabaseConnection connection) {
        try {
            int slope = 0;
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM orders WHERE id = ?");
            statement.setString(1, String.valueOf(id));

            ResultSet set = statement.executeQuery();
            if (set.next()) {
                slope = set.getInt("carportSlope");
            }
            return slope;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public String getStatus(UUID id, DatabaseConnection connection) {
        try {
            String status = "";
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM orders WHERE id = ?");
            statement.setString(1, String.valueOf(id));

            ResultSet set = statement.executeQuery();
            if (set.next()) {
                status = set.getString("status");
            }
            return status;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public int getTotalPrice(UUID id, DatabaseConnection connection) {
        try {
            int totalPrice = 0;
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM orders WHERE id = ?");
            statement.setString(1, String.valueOf(id));

            ResultSet set = statement.executeQuery();
            if (set.next()) {
                totalPrice = set.getInt("totalPrice");
            }
            return totalPrice;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public int getDiscountPrice(UUID id, DatabaseConnection connection) {
        try {
            int discountPrice = 0;
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM orders WHERE id = ?");
            statement.setString(1, String.valueOf(id));

            ResultSet set = statement.executeQuery();
            if (set.next()) {
                discountPrice = set.getInt("discountPrice");
            }
            return discountPrice;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public Customer getCustomer(UUID id, DatabaseConnection connection) {
        Customer customer = new Customer();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM customers WHERE id = ?");
            statement.setString(1, String.valueOf(id));

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                customer = new Customer(UUID.fromString(set.getString("id")), set.getString("firstName"), set.getString("lastName"), set.getString("email"), set.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    public Map<UUID, Order> getCustomerOrders(Customer customer, DatabaseConnection connection) {
        try {
            TreeMap<UUID, Order> orders = new TreeMap<>();
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM orders WHERE customer_id = ?");
            statement.setString(1, String.valueOf(customer.getId()));

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                orders.put(UUID.fromString(set.getString("id")), new Order(UUID.fromString(set.getString("id")), set.getInt("carportWidth"), set.getInt("carportLength"), set.getInt("carportHeight"), set.getInt("carportSlope"), set.getString("status"), set.getInt("totalPrice"), set.getInt("discountPrice"), customer));
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public Map<UUID, Order> getAllOrders(DatabaseConnection connection) {
        try {
            TreeMap<UUID, Order> orders = new TreeMap<>();
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM orders");

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                orders.put(UUID.fromString(set.getString("id")), new Order(UUID.fromString(set.getString("id")), set.getInt("carportWidth"), set.getInt("carportLength"), set.getInt("carportHeight"), set.getInt("carportSlope"), set.getString("status"), set.getInt("totalPrice"), set.getInt("discountPrice"), getCustomer(UUID.fromString(set.getString("customer_id")), connection)));
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public Order getOrder(UUID id, DatabaseConnection connection) {
        Order order = new Order();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM orders WHERE id = ?");
            statement.setString(1, String.valueOf(id));

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                order = new Order(UUID.fromString(set.getString("id")), set.getInt("carportWidth"), set.getInt("carportLength"), set.getInt("carportHeight"), set.getInt("carportSlope"), set.getString("status"), set.getInt("totalPrice"), set.getInt("discountPrice"), getCustomer(UUID.fromString(set.getString("customer_id")), connection));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return order;
    }

    public void removeCustomerOrder(UUID id, DatabaseConnection connection) {
        try {
            PreparedStatement statement = connection.connect().prepareStatement("DELETE FROM orders WHERE id = ?");
            statement.setString(1, String.valueOf(id));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public void updateOrderTotalPrice(int price, DatabaseConnection connection) {
        try {
            PreparedStatement statement = connection.connect().prepareStatement("UPDATE orders SET totalPrice = ?");
            statement.setInt(1, price);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public void updateOrderDiscountPrice(int price, DatabaseConnection connection) {
        try {
            PreparedStatement statement = connection.connect().prepareStatement("UPDATE orders SET discountPrice = ?");
            statement.setInt(1, price);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public void updateStatus(String status, DatabaseConnection connection) {
        try {
            PreparedStatement statement = connection.connect().prepareStatement("UPDATE orders SET status = ?");
            statement.setString(1, status);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }
}
