package com.backend.fog.mappers;

import com.backend.fog.entities.Product;
import com.backend.fog.persistence.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper {

    public Product getPole(DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Stolpe");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getBeam(int carportLength, DatabaseConnection connection) {
        Product product = new Product();
        // NOTE: IF THE LONGEST BEAM IS NOT LONG ENOUGH WE SPLIT IT INTO TWO BEAMS
        if (carportLength >= 0 && carportLength <= 720) {
            product = getClosestBeam(carportLength, connection);
        }

        if (carportLength > 720 && carportLength <= 800) {
            product = getClosestBeam(carportLength / 2, connection);
        }
        return product;
    }
    public Product getRafter(int carportWidth, DatabaseConnection connection) {
        Product product = new Product();
        try {
            // NOTE: EACH RAFTER SHOULD HAVE A 35 CM OVERHANG IN BOTH SIDES WHICH IS WHY THE WIDTH IS ADDED 70 CM
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Spær");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= (carportWidth + 70)) {
                    product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getLowerSideFascia(int carportLength, DatabaseConnection connection) {
        Product product = new Product();

        // LENGTH:  0 - 600
        if (carportLength >= 0 && carportLength <= 600) {
            product = getLowerFasciaCloseTo(carportLength, connection);
        }

        // LENGTH:  601 - 800
        if (carportLength >= 601 && carportLength <= 800) {
            product = getLowerFasciaCloseTo(carportLength / 2, connection);
        }

        return product;
    }
    public Product getUpperSideFascia(int carportLength, DatabaseConnection connection) {
        Product product = new Product();

        // LENGTH:  0 - 600
        if (carportLength >= 0 && carportLength <= 600) {
            product = getUpperFasciaCloseTo(carportLength, connection);
        }

        // LENGTH:  601 - 800
        if (carportLength >= 601 && carportLength <= 800) {
            product = getUpperFasciaCloseTo(carportLength / 2, connection);
        }

        return product;
    }
    public Product getLowerFrontBackFascia(int carportWidth, DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Understern (for/bag)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= carportWidth) {
                    product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getUpperFrontBackFascia(int carportWidth, DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Overstern (for/bag)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= carportWidth) {
                    product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getRoof(int carportLength, DatabaseConnection connection) {
        Product product = new Product();
        // NOTE: IF THE LONGEST ROOF PLATE IS NOT LONG ENOUGH, WE SPLIT IT INTO TWO
        if (carportLength >= 0 && carportLength <= 600) {
            product = getClosestRoof(carportLength, connection);
        }

        // ADDING 10 TO COMPLY WITH THE OVERLAP OF THE ROOF PLATES
        if (carportLength > 600 && carportLength <= 800) {
            product = getClosestRoof((carportLength + 10) / 2, connection);
        }
        return product;
    }
    public Product getCarriageBolt(DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Bræddebolt");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getSquareWasher(DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Firkantskive");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getWindBracingStrap(DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Hulbånd");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getRafterConnectorLeft(DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Universalbeslag (venstre)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getRafterConnectorRight(DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Universalbeslag (højre)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getConnectorScrews(DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Beslagskruer");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getFasciaScrews(DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Sternskruer");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getTrapezoidScrews(DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Bundskruer");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }

    // SHED
    public Product getShedDoorZ(DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Z til skurdør");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getBeamForShed(int shedLength, DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Rem til skur");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= shedLength) {
                    product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getPoleForShed(int shedWidth, DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Stolpe til skur");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getShedJoistSide(int shedLength, DatabaseConnection connection) {
        return getClosestJoist(shedLength, connection);
    }
    public Product getShedJoistFrontBack(int carportWidth, DatabaseConnection connection) {
        return getClosestJoist(carportWidth / 2, connection);
    }
    public Product getCladding(DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Beklædning af skur");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getShortCladdingScrews(DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Skruer til beklædning (korte)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getLongCladdingScrews(DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Skruer til beklædning (lange)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getDoorHinge(DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "T-hængsel til skurdør");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getDoorHandle(DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Stalddørsgreb til skur");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getJoistHinge(DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Beslag til løsholter");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }

    // HELPERS
    public Product getClosestBeam(int length, DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Rem");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= length) {
                    product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getClosestRoof(int length, DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Tag");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= length) {
                    product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getClosestJoist(int length, DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Løsholte til skur");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= length) {
                    product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getUpperFasciaCloseTo(int length, DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Overstern (side)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= length) {
                    product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
    public Product getLowerFasciaCloseTo(int length, DatabaseConnection connection) {
        Product product = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Understern (side)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= length) {
                    product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return product;
    }
}
