package com.backend.fog.mappers;

import com.backend.fog.entities.Customer;
import com.backend.fog.facades.OrderFacade;
import com.backend.fog.persistence.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class CustomerMapper {
    public int createNewCustomer(String firstName, String lastName, String email, String password, DatabaseConnection databaseConnection) {
        int key = 0;
        String sql = "INSERT INTO customers (firstName, lastName, email, password) VALUES (?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, password);
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

    public boolean validateEmail(String email, DatabaseConnection databaseConnection) {
        String sql = "SELECT * FROM customers WHERE email = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            return set.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validatePassword(String email, String password, DatabaseConnection databaseConnection) {
        String sql = "SELECT * FROM customers WHERE email = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return set.getString("email").equals(email) && set.getString("password").equals(password);
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getId(String email, DatabaseConnection databaseConnection) {
        int id = 0;
        String sql = "SELECT * FROM customers WHERE email = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                id = set.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public String getFirstName(String email, DatabaseConnection databaseConnection) {
        String firstName = "";
        String sql = "SELECT * FROM customers WHERE email = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                firstName = set.getString("firstName");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return firstName;
    }

    public ArrayList<Customer> getAllCustomers(DatabaseConnection databaseConnection) {
        OrderFacade orderFacade = new OrderFacade(databaseConnection);
        ArrayList<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                customers.add(new Customer(set.getInt("id"), set.getString("firstName"), set.getString("lastName"), set.getString("email"), set.getString("password"), orderFacade.countOrdersForCustomer(set.getInt("id"))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    public Customer getCustomerById(int id, DatabaseConnection databaseConnection) {
        Customer customer = new Customer();
        String sql = "SELECT * FROM customers WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                customer = new Customer(set.getInt("id"), set.getString("firstName"), set.getString("lastName"), set.getString("email"), set.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    public Customer getCustomerByEmail(String email, DatabaseConnection databaseConnection) {
        Customer customer = new Customer();
        String sql = "SELECT * FROM customers WHERE email = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                customer = new Customer(set.getInt("id"), set.getString("firstName"), set.getString("lastName"), set.getString("email"), set.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }
}
