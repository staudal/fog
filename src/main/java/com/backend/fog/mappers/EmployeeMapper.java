package com.backend.fog.mappers;

import com.backend.fog.persistence.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper {
    public boolean validateEmail(String email, DatabaseConnection databaseConnection) {
        String sql = "SELECT * FROM employees WHERE email = ?";
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
        String sql = "SELECT * FROM employees WHERE email = ?";
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

    public String getId(String email, DatabaseConnection databaseConnection) {
        String id = "";
        String sql = "SELECT * FROM employees WHERE email = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                id = set.getString("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public String getFirstName(String email, DatabaseConnection databaseConnection) {
        String firstName = "";
        String sql = "SELECT * FROM employees WHERE email = ?";
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

    public String getLastName(String email, DatabaseConnection databaseConnection) {
        String lastName = "";
        String sql = "SELECT * FROM employees WHERE email = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                lastName = set.getString("lastName");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lastName;
    }

    public String getPassword(String email, DatabaseConnection databaseConnection) {
        String password = "";
        String sql = "SELECT * FROM employees WHERE email = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                password = set.getString("password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return password;
    }
}
