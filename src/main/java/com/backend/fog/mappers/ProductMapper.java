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

    public Product getSupportBeamCloseTo(int length, DatabaseConnection connection) {
        Product supportBeam = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE description = ? ORDER BY length DESC");
            statement.setString(1, "Rem");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= length) {
                    supportBeam = new Product(set.getInt("id"), set.getString("name"), set.getInt("price"), set.getInt("length"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supportBeam;
    }

    public Product getSupportBeam(int carportLength, DatabaseConnection connection) {
        Product supportBeam = new Product();
        // LENGTH:  0 - 310
        if (carportLength >= 0 && carportLength <= 310) {
            supportBeam = getSupportBeamCloseTo(carportLength, connection);
        }

        // LENGTH:  311 - 620
        if (carportLength >= 311 && carportLength <= 620) {
            supportBeam = getSupportBeamCloseTo(carportLength / 2, connection);
        }

        // LENGTH:  621 - 800
        if (carportLength >= 621 && carportLength <= 800) {
            supportBeam = getSupportBeamCloseTo(carportLength / 3, connection);
        }

        return supportBeam;
    }

    public Product getRafter(int carportWidth, DatabaseConnection connection) {
        Product rafter = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE description = ? ORDER BY length DESC");
            statement.setString(1, "Spær");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= (carportWidth + 70)) {
                    rafter = new Product(set.getInt("id"), set.getString("name"), set.getInt("price"), set.getInt("length"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rafter;
    }
}
