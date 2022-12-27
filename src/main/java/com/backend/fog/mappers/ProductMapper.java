package com.backend.fog.mappers;

import com.backend.fog.entities.Product;
import com.backend.fog.persistence.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper {

    public Product getPole(DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Stolpe");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getBeam(int carportLength, DatabaseConnection databaseConnection) {
        Product product = new Product();
        // NOTE: IF THE LONGEST BEAM IS NOT LONG ENOUGH WE SPLIT IT INTO TWO BEAMS
        if (carportLength >= 0 && carportLength <= 720) {
            product = getClosestBeam(carportLength, databaseConnection);
        }

        if (carportLength > 720 && carportLength <= 800) {
            product = getClosestBeam(carportLength / 2, databaseConnection);
        }
        return product;
    }
    public Product getRafter(int carportWidth, DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            // NOTE: EACH RAFTER SHOULD HAVE A 35 CM OVERHANG IN BOTH SIDES WHICH IS WHY THE WIDTH IS ADDED 70 CM
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Spær");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                if (set.getInt("length") >= (carportWidth + 70)) {
                    product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getLowerSideFascia(int carportLength, DatabaseConnection databaseConnection) {
        Product product = new Product();

        // LENGTH:  0 - 600
        if (carportLength >= 0 && carportLength <= 600) {
            product = getLowerFasciaCloseTo(carportLength, databaseConnection);
        }

        // LENGTH:  601 - 800
        if (carportLength >= 601 && carportLength <= 800) {
            product = getLowerFasciaCloseTo(carportLength / 2, databaseConnection);
        }

        return product;
    }
    public Product getUpperSideFascia(int carportLength, DatabaseConnection databaseConnection) {
        Product product = new Product();

        // LENGTH:  0 - 600
        if (carportLength >= 0 && carportLength <= 600) {
            product = getUpperFasciaCloseTo(carportLength, databaseConnection);
        }

        // LENGTH:  601 - 800
        if (carportLength >= 601 && carportLength <= 800) {
            product = getUpperFasciaCloseTo(carportLength / 2, databaseConnection);
        }

        return product;
    }
    public Product getLowerFrontBackFascia(int carportWidth, DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Understern (for/bag)");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                if (set.getInt("length") >= carportWidth) {
                    product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getUpperFrontBackFascia(int carportWidth, DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Overstern (for/bag)");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                if (set.getInt("length") >= carportWidth) {
                    product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getRoof(int carportLength, DatabaseConnection databaseConnection) {
        Product product = new Product();
        // NOTE: IF THE LONGEST ROOF PLATE IS NOT LONG ENOUGH, WE SPLIT IT INTO TWO
        if (carportLength >= 0 && carportLength <= 600) {
            product = getClosestRoof(carportLength, databaseConnection);
        }

        // ADDING 10 TO COMPLY WITH THE OVERLAP OF THE ROOF PLATES
        if (carportLength > 600 && carportLength <= 800) {
            product = getClosestRoof((carportLength + 10) / 2, databaseConnection);
        }
        return product;
    }
    public Product getCarriageBolt(DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Bræddebolt");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getSquareWasher(DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Firkantskive");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getWindBracingStrap(DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Hulbånd");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getRafterConnectorLeft(DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Universalbeslag (venstre)");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getRafterConnectorRight(DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Universalbeslag (højre)");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getConnectorScrews(DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Beslagskruer");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getFasciaScrews(DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Sternskruer");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getTrapezoidScrews(DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Bundskruer");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    // SHED
    public Product getShedDoorZ(DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Z til skurdør");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getBeamForShed(int shedLength, DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Rem til skur");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                if (set.getInt("length") >= shedLength) {
                    product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getPoleForShed(DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Stolpe til skur");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getShedJoistSide(int shedLength, DatabaseConnection databaseConnection) {
        return getClosestJoist(shedLength, databaseConnection);
    }
    public Product getShedJoistFrontBack(int carportWidth, DatabaseConnection databaseConnection) {
        return getClosestJoist(carportWidth / 2, databaseConnection);
    }
    public Product getCladding(DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Beklædning af skur");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getShortCladdingScrews(DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Skruer til beklædning (korte)");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getLongCladdingScrews(DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Skruer til beklædning (lange)");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getDoorHinge(DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "T-hængsel til skurdør");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getDoorHandle(DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Stalddørsgreb til skur");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getJoistHinge(DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Beslag til løsholter");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    // HELPERS
    public Product getClosestBeam(int length, DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Rem");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                if (set.getInt("length") >= length) {
                    product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getClosestRoof(int length, DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Tag");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                if (set.getInt("length") >= length) {
                    product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getClosestJoist(int length, DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Løsholte til skur");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                if (set.getInt("length") >= length) {
                    product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getUpperFasciaCloseTo(int length, DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Overstern (side)");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                if (set.getInt("length") >= length) {
                    product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product getLowerFasciaCloseTo(int length, DatabaseConnection databaseConnection) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE category = ? ORDER BY length DESC";
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Understern (side)");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                if (set.getInt("length") >= length) {
                    product = new Product(set.getInt("id"), set.getString("name"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
}
