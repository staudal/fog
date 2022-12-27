package com.backend.fog.mappers;

import com.backend.fog.entities.Order;
import com.backend.fog.entities.Product;
import com.backend.fog.facades.CustomerFacade;
import com.backend.fog.persistence.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class OrderMapper {
    public int createNewOrder(int carportWidth, int carportLength, int shedWidth, int shedLength, int customerId, int totalPrice, int discountPrice, int status, DatabaseConnection databaseConnection) {
        int key = 0;
        String sql = "INSERT INTO orders (carportWidth, carportLength, shedWidth, shedLength, customer_id, totalPrice, discountPrice, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, carportWidth);
            statement.setInt(2, carportLength);
            statement.setInt(3, shedWidth);
            statement.setInt(4, shedLength);
            statement.setInt(5, customerId);
            statement.setInt(6, totalPrice);
            statement.setInt(7, discountPrice);
            statement.setInt(8, status);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs != null && rs.next()) {
                key = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return key;
    }

    public void createOrderLines(int orderId, int productId, int quantity, DatabaseConnection databaseConnection) {
        String sql = "INSERT INTO order_line (orders_id, products_id, amount) VALUES (?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            statement.setInt(2, productId);
            statement.setInt(3, quantity);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countOrdersForCustomer(int customerId, DatabaseConnection databaseConnection) {
        int count = 0;
        String sql = "SELECT * FROM orders WHERE customer_id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                count += 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public TreeMap<Integer, Order> getCustomerOrders(int customerId, DatabaseConnection databaseConnection) {
        TreeMap<Integer, Order> orders = new TreeMap<>();
        String sql = "SELECT * FROM orders WHERE customer_id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                orders.put(set.getInt("id"), new Order(set.getInt("id"), set.getInt("carportWidth"), set.getInt("carportLength"), set.getInt("shedWidth"), set.getInt("shedLength"), set.getInt("totalPrice"), set.getInt("discountPrice"), set.getInt("status")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public Map<Integer, Order> getAllOrders(DatabaseConnection databaseConnection) {
        TreeMap<Integer, Order> orders = new TreeMap<>();
        String sql = "SELECT * FROM orders";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                orders.put(set.getInt("id"), new Order(set.getInt("id"), set.getInt("carportWidth"), set.getInt("carportLength"), set.getInt("shedWidth"), set.getInt("shedLength"), set.getInt("totalPrice"), set.getInt("discountPrice"), set.getInt("status")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public Order getOrder(int id, DatabaseConnection databaseConnection) {
        CustomerFacade customerFacade = new CustomerFacade(databaseConnection);
        Order order = new Order();
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
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
        }
        return order;
    }

    public Product getProductFromId(int productId, int amount, DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product.setId(set.getInt("id"));
                product.setName(set.getString("name"));
                product.setKind(set.getInt("kind"));
                product.setUse(set.getInt("use"));
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

    public ArrayList<Product> getProductsFromOrderLine(int orderId, DatabaseConnection databaseConnection) {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM order_line WHERE orders_id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                products.add(getProductFromId(set.getInt("products_id"), set.getInt("amount"), databaseConnection));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public void removeCustomerOrder(int orderId, DatabaseConnection databaseConnection) {
        String sql1 = "DELETE FROM order_line WHERE orders_id = ?";
        String sql2 = "DELETE FROM orders WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            PreparedStatement statement2 = connection.prepareStatement(sql2);

            statement1.setInt(1, orderId);
            statement2.setInt(1, orderId);

            statement1.executeUpdate();
            statement2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOrderDiscountPrice(int price, int orderId, DatabaseConnection databaseConnection) {
        String sql = "UPDATE orders SET discountPrice = ? WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, price);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStatus(int status, int orderId, DatabaseConnection databaseConnection) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, status);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product getRafter(ArrayList<Product> products) {
        Product product = new Product();
        for (Product rafter : products) {
            if (rafter.getCategory().equals("Spær")) {
                product = new Product(rafter.getId(), rafter.getName(), rafter.getLength(), rafter.getPrice(), rafter.getCategory(), rafter.getDescription(), rafter.getQuantity());
            }
        }
        return product;
    }

    public Product getBeam(ArrayList<Product> products) {
        Product product = new Product();
        for (Product rafter : products) {
            if (rafter.getCategory().equals("Rem")) {
                product = new Product(rafter.getId(), rafter.getName(), rafter.getLength(), rafter.getPrice(), rafter.getCategory(), rafter.getDescription(), rafter.getQuantity());
            }
        }
        return product;
    }

    public Product getPole(ArrayList<Product> products) {
        Product product = new Product();
        for (Product rafter : products) {
            if (rafter.getCategory().equals("Stolpe")) {
                product = new Product(rafter.getId(), rafter.getName(), rafter.getLength(), rafter.getPrice(), rafter.getCategory(), rafter.getDescription(), rafter.getQuantity());
            }
        }
        return product;
    }

    public ArrayList<Product> getCarportWoods(ArrayList<Product> allProducts) {
        ArrayList<Product> carportWoods = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getKind() == 1 && product.getUse() == 0) {
                carportWoods.add(product);
            }
        }
        return carportWoods;
    }

    public ArrayList<Product> getShedWoods(ArrayList<Product> allProducts) {
        ArrayList<Product> shedWoods = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getKind() == 1 && product.getUse() == 1) {
                shedWoods.add(product);
            }
        }
        return shedWoods;
    }

    public ArrayList<Product> getCarportScrews(ArrayList<Product> allProducts) {
        ArrayList<Product> carportScrews = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getKind() == 0 && product.getUse() == 0) {
                carportScrews.add(product);
            }
        }
        return carportScrews;
    }

    public ArrayList<Product> getShedScrews(ArrayList<Product> allProducts) {
        ArrayList<Product> shedScrews = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getKind() == 0 && product.getUse() == 1) {
                shedScrews.add(product);
            }
        }
        return shedScrews;
    }
}
