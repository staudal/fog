package com.backend.fog.facades;

import com.backend.fog.entities.Customer;
import com.backend.fog.mappers.CustomerMapper;
import com.backend.fog.persistence.DatabaseConnection;

import java.util.ArrayList;

public class CustomerFacade {
    DatabaseConnection databaseConnection;
    CustomerMapper customerMapper = new CustomerMapper();

    public CustomerFacade(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public int createNewCustomer(String firstName, String lastName, String email, String password) {
        return customerMapper.createNewCustomer(firstName, lastName, email, password, databaseConnection);
    }

    public boolean validateEmail(String email) {
        return customerMapper.validateEmail(email, databaseConnection);
    }

    public boolean validatePassword(String email, String password) {
        return customerMapper.validatePassword(email, password, databaseConnection);
    }

    public int getId(String email) {
        return customerMapper.getId(email, databaseConnection);
    }

    public ArrayList<Customer> getAllCustomers() {
        return customerMapper.getAllCustomers(databaseConnection);
    }

    public Customer getCustomerById(int id) {
        return customerMapper.getCustomerById(id, databaseConnection);
    }

    public Customer getCustomerByEmail(String email) {
        return customerMapper.getCustomerByEmail(email, databaseConnection);
    }
}