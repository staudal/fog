package com.backend.fog.mappers;

import com.backend.fog.entities.Customer;
import com.backend.fog.persistence.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper {
    public void createNewCustomer(Customer customer, DatabaseConnection connection) {
        String sql = "INSERT INTO customers (id, firstName, lastName, email, password) VALUES ('"+customer.getId()+"', '"+customer.getFirstName()+"', '"+customer.getLastName()+"', '"+customer.getEmail()+"', '"+customer.getPassword()+"')";
        try {
            connection.connect().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
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

    public String getId(String email, DatabaseConnection connection) {
        try {
            String id = "";
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM customers WHERE email = ?");
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                id = set.getString("id");
            }

            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public String getFirstName(String email, DatabaseConnection connection) {
        try {
            String firstName = "";
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM customers WHERE email = ?");
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                firstName = set.getString("firstName");
            }

            return firstName;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public String getLastName(String email, DatabaseConnection connection) {
        try {
            String lastName = "";
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM customers WHERE email = ?");
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                lastName = set.getString("lastName");
            }

            return lastName;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public String getPassword(String email, DatabaseConnection connection) {
        try {
            String password = "";
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM customers WHERE email = ?");
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                password = set.getString("password");
            }

            return password;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }
}
