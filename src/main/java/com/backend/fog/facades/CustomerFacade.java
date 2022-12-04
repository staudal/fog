package com.backend.fog.facades;

import com.backend.fog.entities.Customer;
import com.backend.fog.entities.Order;
import com.backend.fog.mappers.CustomerMapper;
import com.backend.fog.persistence.DatabaseConnection;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class CustomerFacade {
    DatabaseConnection connection = new DatabaseConnection();
    CustomerMapper customerMapper = new CustomerMapper();

    public int createNewCustomer(String firstName, String lastName, String email, String password) {
        return customerMapper.createNewCustomer(firstName, lastName, email, password, connection);
    }

    public boolean validateEmail(String email) {
        return customerMapper.validateEmail(email, connection);
    }

    public boolean validatePassword(String email, String password) {
        return customerMapper.validatePassword(email, password, connection);
    }

    public int getId(String email) {
        return customerMapper.getId(email, connection);
    }

    public String getFirstname(String email) {
        return customerMapper.getFirstName(email, connection);
    }

    public String getLastname(String email) {
        return customerMapper.getLastName(email, connection);
    }

    public String getPassword(String email) {
        return customerMapper.getPassword(email, connection);
    }

    public ArrayList<Customer> getAllCustomers() {
        return customerMapper.getAllCustomers(connection);
    }

    public Customer getCustomerById(int id) {
        return customerMapper.getCustomerById(id, connection);
    }

    public Customer getCustomerByEmail(String email) {
        return customerMapper.getCustomerByEmail(email, connection);
    }
}