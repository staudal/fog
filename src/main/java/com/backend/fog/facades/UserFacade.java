package com.backend.fog.facades;

import com.backend.fog.entities.Customer;
import com.backend.fog.mappers.UserMapper;
import com.backend.fog.persistence.Connection;

public class UserFacade {
    Connection connection = new Connection();
    UserMapper userMapper = new UserMapper();

    public void CreateNewUser(Customer customer) {
        userMapper.createNewCustomer(customer, connection);
    }
}