package com.backend.fog.mappers;

import com.backend.fog.entities.Customer;
import com.backend.fog.entities.Order;
import com.backend.fog.entities.Product;
import com.backend.fog.facades.CustomerFacade;
import com.backend.fog.facades.ProductFacade;
import com.backend.fog.persistence.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class OrderMapper {
    public int createNewOrder(int carportWidth, int carportLength, int shedWidth, int shedLength, int customerId, int totalPrice, int discountPrice, int status, DatabaseConnection connection) {
        int key = 0;
        try {
            Statement statement = connection.connect().createStatement();
            statement.executeUpdate("INSERT INTO orders (carportWidth, carportLength, shedWidth, shedLength, customer_id, totalPrice, discountPrice, status) VALUES ('"+carportWidth+"', '"+carportLength+"', '"+shedWidth+"', '"+shedLength+"', '"+customerId+"', '"+totalPrice+"', '"+discountPrice+"', '"+status+"')", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs != null && rs.next()) {
                key = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return key;
    }

    public void createOrderLines(int orderId, int productId, int quantity, DatabaseConnection connection) {
        try {
            PreparedStatement statement = connection.connect().prepareStatement("INSERT INTO order_line (orders_id, products_id, amount) VALUES ('" + orderId + "', '" + productId + "', '" + quantity + "')");
            statement.executeUpdate();
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

    public int getStatus(UUID id, DatabaseConnection connection) {
        int status = 0;
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM orders WHERE id = ?");
            statement.setString(1, String.valueOf(id));

            ResultSet set = statement.executeQuery();
            if (set.next()) {
                status = set.getInt("status");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return status;
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

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    public int countOrdersForCustomer(int customerId, DatabaseConnection connection) {
        int count = 0;
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM orders WHERE customer_id = ?");
            statement.setInt(1, customerId);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                count += 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.connect();
        }
        return count;
    }

    public TreeMap<Integer, Order> getCustomerOrders(int customerId, DatabaseConnection connection) {
        TreeMap<Integer, Order> orders = new TreeMap<>();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM orders WHERE customer_id = ?");
            statement.setInt(1, customerId);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                orders.put(set.getInt("id"), new Order(set.getInt("id"), set.getInt("carportWidth"), set.getInt("carportLength"), set.getInt("shedWidth"), set.getInt("shedLength"), set.getInt("totalPrice"), set.getInt("discountPrice"), set.getInt("status")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return orders;
    }

    public Map<Integer, Order> getAllOrders(DatabaseConnection connection) {
        TreeMap<Integer, Order> orders = new TreeMap<>();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM orders");

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                orders.put(set.getInt("id"), new Order(set.getInt("id"), set.getInt("carportWidth"), set.getInt("carportLength"), set.getInt("shedWidth"), set.getInt("shedLength"), set.getInt("totalPrice"), set.getInt("discountPrice"), set.getInt("status")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return orders;
    }

    public Order getOrder(int id, DatabaseConnection connection) {
        CustomerFacade customerFacade = new CustomerFacade();
        Order order = new Order();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM orders WHERE id = ?");
            statement.setInt(1, id);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                order.setId(set.getInt("id"));
                order.setCarportWidth(set.getInt("carportWidth"));
                order.setCarportLength(set.getInt("carportLength"));
                order.setShedWidth(set.getInt("shedWidth"));
                order.setShedLength(set.getInt("shedLength"));
                order.setCustomer(customerFacade.getCustomerById(set.getInt("customer_id")));
                order.setTotalPrice(set.getInt("totalPrice"));
                order.setDiscountPrice(set.getInt("discountPrice"));
                order.setStatus(set.getInt("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return order;
    }

    public Product getProductFromId(int productId, int amount, DatabaseConnection connection) {
        ProductFacade productFacade = new ProductFacade();
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE id = ?");
            statement.setInt(1, productId);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product.setId(set.getInt("id"));
                product.setDimensions(set.getString("dimensions"));
                product.setLength(set.getInt("length"));
                product.setPrice(set.getInt("price"));
                product.setCategory(set.getString("category"));
                product.setDescription(set.getString("description"));
                product.setQuantity(amount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    public ArrayList<Product> getProductsFromOrderLine(int orderId, DatabaseConnection connection) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM order_line WHERE orders_id = ?");
            statement.setInt(1, orderId);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                products.add(getProductFromId(set.getInt("products_id"), set.getInt("amount"), connection));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return products;
    }

    public void removeCustomerOrder(int orderId, DatabaseConnection connection) {
        try {
            PreparedStatement statement1 = connection.connect().prepareStatement("DELETE FROM order_line WHERE orders_id = ?");
            PreparedStatement statement2 = connection.connect().prepareStatement("DELETE FROM orders WHERE id = ?");

            statement1.setInt(1, orderId);
            statement2.setInt(1, orderId);

            statement1.executeUpdate();
            statement2.executeUpdate();
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

    public void updateOrderDiscountPrice(int price, int orderId, DatabaseConnection connection) {
        try {
            PreparedStatement statement = connection.connect().prepareStatement("UPDATE orders SET discountPrice = ? WHERE id = ?");
            statement.setInt(1, price);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public void updateStatus(int status, int orderId, DatabaseConnection connection) {
        try {
            PreparedStatement statement = connection.connect().prepareStatement("UPDATE orders SET status = ? WHERE id = ?");
            statement.setInt(1, status);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }
}
