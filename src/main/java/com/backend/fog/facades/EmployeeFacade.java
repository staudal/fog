package com.backend.fog.facades;

import com.backend.fog.mappers.EmployeeMapper;
import com.backend.fog.persistence.DatabaseConnection;

public class EmployeeFacade {
    DatabaseConnection connection = new DatabaseConnection();
    EmployeeMapper employeeMapper = new EmployeeMapper();

    public boolean validateEmail(String email) {
        return employeeMapper.validateEmail(email, connection);
    }

    public boolean validatePassword(String email, String password) {
        return employeeMapper.validatePassword(email, password, connection);
    }

    public String getId(String email) {
        return employeeMapper.getId(email, connection);
    }

    public String getFirstname(String email) {
        return employeeMapper.getFirstName(email, connection);
    }

    public String getLastname(String email) {
        return employeeMapper.getLastName(email, connection);
    }

    public String getPassword(String email) {
        return employeeMapper.getPassword(email, connection);
    }
}
