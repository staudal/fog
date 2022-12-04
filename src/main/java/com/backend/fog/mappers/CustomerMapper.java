package com.backend.fog.mappers;

import com.backend.fog.entities.Customer;
import com.backend.fog.entities.Order;
import com.backend.fog.facades.OrderFacade;
import com.backend.fog.persistence.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class CustomerMapper {
    public int createNewCustomer(String firstName, String lastName, String email, String password, DatabaseConnection connection) {
        int key = 0;
        try {
            Statement statement = connection.connect().createStatement();
            statement.executeUpdate("INSERT INTO customers (firstName, lastName, email, password) VALUES ('"+firstName+"', '"+lastName+"', '"+email+"', '"+password+"')", Statement.RETURN_GENERATED_KEYS);
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

    public boolean validateEmail(String email, DatabaseConnection connection) {
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM customers WHERE email = ?");
            statement.setString(1, email);

            ResultSet set = statement.executeQuery();
            return set.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public boolean validatePassword(String email, String password, DatabaseConnection connection) {
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM customers WHERE email = ?");
            statement.setString(1, email);

            ResultSet set = statement.executeQuery();

            if (set.next()) {
                return set.getString("email").equals(email) && set.getString("password").equals(password);
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public int getId(String email, DatabaseConnection connection) {
        int id = 0;
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM customers WHERE email = ?");
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                id = set.getInt("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return id;
    }

    public String getFirstName(String email, DatabaseConnection connection) {
        String firstName = "";
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM customers WHERE email = ?");
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                firstName = set.getString("firstName");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return firstName;
    }

    public String getLastName(String email, DatabaseConnection connection) {
        String lastName = "";
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM customers WHERE email = ?");
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                lastName = set.getString("lastName");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return lastName;
    }

    public String getPassword(String email, DatabaseConnection connection) {
        String password = "";
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM customers WHERE email = ?");
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                password = set.getString("password");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return password;
    }

    public ArrayList<Customer> getAllCustomers(DatabaseConnection connection) {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            OrderFacade orderFacade = new OrderFacade();
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM customers");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                customers.add(new Customer(set.getInt("id"), set.getString("firstName"), set.getString("lastName"), set.getString("email"), set.getString("password"), orderFacade.countOrdersForCustomer(set.getInt("id"))));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return customers;
    }

    public Customer getCustomerById(int id, DatabaseConnection connection) {
        Customer customer = new Customer();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM customers WHERE id = ?");
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            OrderFacade orderFacade = new OrderFacade();

            while (set.next()) {
                customer = new Customer(set.getInt("id"), set.getString("firstName"), set.getString("lastName"), set.getString("email"), set.getString("password"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return customer;
    }

    public Customer getCustomerByEmail(String email, DatabaseConnection connection) {
        Customer customer = new Customer();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM customers WHERE email = ?");
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            OrderFacade orderFacade = new OrderFacade();

            while (set.next()) {
                customer = new Customer(set.getInt("id"), set.getString("firstName"), set.getString("lastName"), set.getString("email"), set.getString("password"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return customer;
    }
}
