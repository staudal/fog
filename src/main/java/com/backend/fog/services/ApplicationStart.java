package com.backend.fog.services;

import com.backend.fog.persistence.DatabaseConnection;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener
public class ApplicationStart implements ServletContextListener
{
    private static DatabaseConnection databaseConnection;

    public ApplicationStart() {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        databaseConnection = new DatabaseConnection();
    }

    public static DatabaseConnection getConnectionPool() {
        return databaseConnection;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        unregisterJDBCdrivers();
        databaseConnection.close();
    }

    private void unregisterJDBCdrivers() {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        // Loop through all drivers
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver.getClass().getClassLoader() == cl) {
                // This driver was registered by the webapp's ClassLoader, so deregister it:
                try {
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}


