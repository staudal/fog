package com.backend.fog.mappers;

import com.backend.fog.entities.Product;
import com.backend.fog.persistence.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper {

    public Product getPole(int carportHeight, DatabaseConnection connection) {
        Product pole = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Stolpe");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") > (carportHeight + 90)) {
                    pole = new Product(set.getInt("id"), set.getInt("width"), set.getInt("height"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pole;
    }

    public Product getSupportBeamCloseTo(int length, DatabaseConnection connection) {
        Product supportBeam = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Rem");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= length) {
                    supportBeam = new Product(set.getInt("id"), set.getInt("width"), set.getInt("height"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supportBeam;
    }

    public Product getUpperFasciaCloseTo(int length, DatabaseConnection connection) {
        Product fascia = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Overstern (side)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= length) {
                    fascia = new Product(set.getInt("id"), set.getInt("width"), set.getInt("height"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fascia;
    }

    public Product getLowerFasciaCloseTo(int length, DatabaseConnection connection) {
        Product fascia = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Understern (side)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= length) {
                    fascia = new Product(set.getInt("id"), set.getInt("width"), set.getInt("height"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fascia;
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
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Spær");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= (carportWidth + 70)) {
                    rafter = new Product(set.getInt("id"), set.getInt("width"), set.getInt("height"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rafter;
    }

    public Product getFrontBackLowerFascia(int carportWidth, DatabaseConnection connection) {
        Product fascia = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Únderstern (for/bag)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= carportWidth) {
                    fascia = new Product(set.getInt("id"), set.getInt("width"), set.getInt("height"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fascia;
    }

    public Product getFrontBackUpperFascia(int carportWidth, DatabaseConnection connection) {
        Product fascia = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Overstern (for/bag)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= carportWidth) {
                    fascia = new Product(set.getInt("id"), set.getInt("width"), set.getInt("height"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fascia;
    }

    public Product getSideUpperFascia(int carportLength, DatabaseConnection connection) {
        Product fascia = new Product();

        // LENGTH:  0 - 600
        if (carportLength >= 0 && carportLength <= 600) {
            fascia = getUpperFasciaCloseTo(carportLength, connection);
        }

        // LENGTH:  601 - 800
        if (carportLength >= 601 && carportLength <= 800) {
            fascia = getUpperFasciaCloseTo(carportLength / 2, connection);
        }

        return fascia;
    }

    public Product getSideLowerFascia(int carportLength, DatabaseConnection connection) {
        Product fascia = new Product();

        // LENGTH:  0 - 600
        if (carportLength >= 0 && carportLength <= 600) {
            fascia = getLowerFasciaCloseTo(carportLength, connection);
        }

        // LENGTH:  601 - 800
        if (carportLength >= 601 && carportLength <= 800) {
            fascia = getLowerFasciaCloseTo(carportLength / 2, connection);
        }

        return fascia;
    }
}
