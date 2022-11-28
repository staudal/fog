package com.backend.fog.persistence;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private String username = System.getenv("JDBC_USER");
    private String password = System.getenv("JDBC_PASSWORD");
    private String url = System.getenv("JDBC_CONNECTION_STRING");

    private String USERNAME = System.getenv("JDBC_USER");
    private String PASSWORD = System.getenv("JDBC_PASSWORD");
    private String URL = System.getenv("JDBC_CONNECTION_STRING");

    private java.sql.Connection connect;

    public java.sql.Connection connect() {
        if (connect == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connect = DriverManager.getConnection(url, username, password);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return connect;
    }
}
