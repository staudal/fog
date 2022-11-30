package com.backend.fog.facades;

import com.backend.fog.entities.Customer;
import com.backend.fog.mappers.CustomerMapper;
import com.backend.fog.persistence.DatabaseConnection;

public class CustomerFacade {
    DatabaseConnection connection = new DatabaseConnection();
    CustomerMapper customerMapper = new CustomerMapper();

    public void CreateNewCustomer(Customer customer) {
        customerMapper.createNewCustomer(customer, connection);
    }

    public boolean validateEmail(String email) {
        return customerMapper.validateEmail(email, connection);
    }

    public boolean validatePassword(String email, String password) {
        return customerMapper.validatePassword(email, password, connection);
    }

    public String getId(String email) {
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
}