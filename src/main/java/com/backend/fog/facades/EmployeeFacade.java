package com.backend.fog.facades;

import com.backend.fog.mappers.EmployeeMapper;
import com.backend.fog.persistence.DatabaseConnection;

public class EmployeeFacade {
    DatabaseConnection databaseConnection;
    EmployeeMapper employeeMapper = new EmployeeMapper();

    public EmployeeFacade(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public boolean validateEmail(String email) {
        return employeeMapper.validateEmail(email, databaseConnection);
    }

    public boolean validatePassword(String email, String password) {
        return employeeMapper.validatePassword(email, password, databaseConnection);
    }

    public String getId(String email) {
        return employeeMapper.getId(email, databaseConnection);
    }

    public String getFirstname(String email) {
        return employeeMapper.getFirstName(email, databaseConnection);
    }

    public String getLastname(String email) {
        return employeeMapper.getLastName(email, databaseConnection);
    }

    public String getPassword(String email) {
        return employeeMapper.getPassword(email, databaseConnection);
    }
}
