package com.backend.fog.mappers;

import com.backend.fog.entities.Product;
import com.backend.fog.persistence.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper {

    public void createNewProduct(String name, int mPrice, int length, DatabaseConnection connection) {
        try {
            PreparedStatement statement = connection.connect().prepareStatement("INSERT INTO products (name, mPrice, length) VALUES ('"+name+"', '"+mPrice+"', '"+length+"')");
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product getBeam(DatabaseConnection connection) {
        Product beam = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE id = ?");
            statement.setInt(1, 1);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                beam = new Product(set.getInt("id"), set.getString("name"), set.getInt("mPrice"), set.getInt("length"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return beam;
    }
}
