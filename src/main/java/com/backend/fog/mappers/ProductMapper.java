package com.backend.fog.mappers;

import com.backend.fog.entities.Product;
import com.backend.fog.persistence.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper {

    public Product getPole(DatabaseConnection connection) {
        Product pole = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Stolpe");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                pole = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pole;
    }
    public Product getBeam(int carportLength, DatabaseConnection connection) {
        Product beam = new Product();
        // NOTE: IF THE LONGEST BEAM IS NOT LONG ENOUGH WE SPLIT IT INTO TWO BEAMS
        if (carportLength >= 0 && carportLength <= 720) {
            beam = getClosestBeam(carportLength, connection);
        }

        if (carportLength > 720 && carportLength <= 800) {
            beam = getClosestBeam(carportLength / 2, connection);
        }
        return beam;
    }
    public Product getRafter(int carportWidth, DatabaseConnection connection) {
        Product rafter = new Product();
        try {
            // NOTE: EACH RAFTER SHOULD HAVE A 35 CM OVERHANG IN BOTH SIDES WHICH IS WHY THE WIDTH IS ADDED 70 CM
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Spær");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= (carportWidth + 70)) {
                    rafter = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rafter;
    }
    public Product getLowerSideFascia(int carportLength, DatabaseConnection connection) {
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
    public Product getUpperSideFascia(int carportLength, DatabaseConnection connection) {
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
    public Product getLowerFrontBackFascia(int carportWidth, DatabaseConnection connection) {
        Product fascia = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Understern (for/bag)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= carportWidth) {
                    fascia = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fascia;
    }
    public Product getUpperFrontBackFascia(int carportWidth, DatabaseConnection connection) {
        Product fascia = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Overstern (for/bag)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= carportWidth) {
                    fascia = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fascia;
    }
    public Product getRoof(int carportLength, DatabaseConnection connection) {
        Product roof = new Product();
        // NOTE: IF THE LONGEST ROOF PLATE IS NOT LONG ENOUGH, WE SPLIT IT INTO TWO
        if (carportLength >= 0 && carportLength <= 600) {
            roof = getClosestRoof(carportLength, connection);
        }

        // ADDING 10 TO COMPLY WITH THE OVERLAP OF THE ROOF PLATES
        if (carportLength > 600 && carportLength <= 800) {
            roof = getClosestRoof((carportLength + 10) / 2, connection);
        }
        return roof;
    }
    public Product getCarriageBolt(DatabaseConnection connection) {
        Product bolt = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Bræddebolt");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                bolt = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bolt;
    }
    public Product getSquareWasher(DatabaseConnection connection) {
        Product washer = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Firkantskive");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                washer = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return washer;
    }
    public Product getWindBracingStrap(DatabaseConnection connection) {
        Product windBracingStrap = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Hulbånd");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                windBracingStrap = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return windBracingStrap;
    }
    public Product getRafterConnectorLeft(DatabaseConnection connection) {
        Product rafterConnector = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Universalbeslag (venstre)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                rafterConnector = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rafterConnector;
    }
    public Product getRafterConnectorRight(DatabaseConnection connection) {
        Product rafterConnector = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Universalbeslag (højre)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                rafterConnector = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rafterConnector;
    }
    public Product getConnectorScrews(DatabaseConnection connection) {
        Product connectorScrews = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Beslagskruer");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                connectorScrews = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connectorScrews;
    }
    public Product getFasciaScrews(DatabaseConnection connection) {
        Product connectorScrews = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Sternskruer");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                connectorScrews = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connectorScrews;
    }
    public Product getTrapezoidScrews(DatabaseConnection connection) {
        Product trapezoidScrews = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Bundskruer");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                trapezoidScrews = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trapezoidScrews;
    }

    // SHED
    public Product getShedDoorZ(DatabaseConnection connection) {
        Product zForDoor = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Z til skurdør");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                zForDoor = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return zForDoor;
    }
    public Product getBeamForShed(int shedLength, DatabaseConnection connection) {
        Product beam = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Rem til skur");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= (shedLength * 2)) {
                    beam = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return beam;
    }
    public Product getPoleForShed(int shedWidth, DatabaseConnection connection) {
        Product pole = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Stolpe til skur");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                pole = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pole;
    }
    public Product getShedJoistSide(int shedLength, DatabaseConnection connection) {
        return getClosestJoist(shedLength, connection);
    }
    public Product getShedJoistFrontBack(int carportWidth, DatabaseConnection connection) {
        return getClosestJoist(carportWidth / 2, connection);
    }
    public Product getCladding(DatabaseConnection connection) {
        Product cladding = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Beklædning af skur");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                cladding = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cladding;
    }
    public Product getShortCladdingScrews(DatabaseConnection connection) {
        Product screws = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Skruer til beklædning (korte)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                screws = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return screws;
    }
    public Product getLongCladdingScrews(DatabaseConnection connection) {
        Product screws = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Skruer til beklædning (lange)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                screws = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return screws;
    }
    public Product getDoorHinge(DatabaseConnection connection) {
        Product hinge = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "T-hængsel til skurdør");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                hinge = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hinge;
    }
    public Product getDoorHandle(DatabaseConnection connection) {
        Product handle = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Staldørsgreb til skur");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                handle = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return handle;
    }
    public Product getJoistHinge(DatabaseConnection connection) {
        Product hinge = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Beslag til løsholter");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                hinge = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hinge;
    }

    // HELPERS
    public Product getClosestBeam(int length, DatabaseConnection connection) {
        Product supportBeam = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Rem");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= length) {
                    supportBeam = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supportBeam;
    }
    public Product getClosestRoof(int length, DatabaseConnection connection) {
        Product roof = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Tag");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= length) {
                    roof = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roof;
    }
    public Product getClosestJoist(int length, DatabaseConnection connection) {
        Product joist = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Løsholte til skur");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= length) {
                    joist = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return joist;
    }
    public Product getUpperFasciaCloseTo(int length, DatabaseConnection connection) {
        Product fascia = new Product();
        try {
            PreparedStatement statement = connection.connect().prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY length DESC");
            statement.setString(1, "Overstern (side)");
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt("length") >= length) {
                    fascia = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
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
                    fascia = new Product(set.getInt("id"), set.getString("dimensions"), set.getInt("length"), set.getInt("price"), set.getString("category"), set.getString("description"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fascia;
    }
}
