package com.backend.fog.mappers;

import com.backend.fog.entities.Product;
import com.backend.fog.persistence.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper {

    public void createNewProduct(String name, int mPrice, int length, DatabaseConnection connection) {
        try {
            PreparedStatement statement = connection.connect().prepareStatement("INSERT INTO products (name9) VALUES ('"+name+"', '"+mPrice+"', '"+length+"')");
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product getBeam(int carportHeight, DatabaseConnection connection) {
        Product beam = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE description = ? ORDER BY length DESC");
            statement.setString(1, "Stolpe");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") > (carportHeight + 90)) {
                    beam = new Product(set.getInt("id"), set.getString("name"), set.getInt("price"), set.getInt("length"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return beam;
    }

    public Product getLongestSupportBeam(DatabaseConnection connection) {
        Product supportBeam = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE description = ? ORDER BY length ASC");
            statement.setString(1, "Rem");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                supportBeam = new Product(set.getInt("id"), set.getString("name"), set.getInt("price"), set.getInt("length"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supportBeam;
    }

    public Product getSupportBeam(int carportLength, DatabaseConnection connection) {
        Product supportBeam = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE description = ? ORDER BY length DESC");
            statement.setString(1, "Rem");
            ResultSet set = statement.executeQuery();

            if (carportLength > 600) {
                supportBeam = getLongestSupportBeam(connection);
            } else {
                while (set.next()) {
                    if (set.getInt("length") >= carportLength) {
                        supportBeam = new Product(set.getInt("id"), set.getString("name"), set.getInt("price"), set.getInt("length"));
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supportBeam;
    }
}
