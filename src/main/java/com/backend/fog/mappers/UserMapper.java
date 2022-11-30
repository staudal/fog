package com.backend.fog.mappers;

import com.backend.fog.entities.Customer;
import com.backend.fog.persistence.Connection;

import java.sql.SQLException;

public class UserMapper {
    public void createNewCustomer(Customer customer, Connection connection) {
        String sql = "INSERT INTO customers (id, firstName, lastName, email, password) VALUES ('"+customer.getId()+"', '"+customer.getFirstName()+"', '"+customer.getLastName()+"', '"+customer.getEmail()+"', '"+customer.getPassword()+"')";
        try {
            connection.connect().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
