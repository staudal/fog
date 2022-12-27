package com.backend.fog.controllers;

import com.backend.fog.entities.Employee;
import com.backend.fog.errors.ErrorHandler;
import com.backend.fog.facades.CustomerFacade;
import com.backend.fog.facades.EmployeeFacade;
import com.backend.fog.persistence.DatabaseConnection;
import com.backend.fog.services.ApplicationStart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    private DatabaseConnection databaseConnection;

    @Override
    public void init() {
        this.databaseConnection = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // CUSTOMER
        if (role.equals("customer")) {
            CustomerFacade customerFacade = new CustomerFacade(databaseConnection);
            // CHECKING IF EMAIL DOESN'T EXISTS
            if (!customerFacade.validateEmail(email)) {
                request.setAttribute("emailErrorMessage", ErrorHandler.emailNotFound());
                request.setAttribute("emailErrorClass", ErrorHandler.errorClass());
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            // CHECKING IF PASSWORD IS INCORRECT
            if (!customerFacade.validatePassword(email, password)) {
                request.setAttribute("passwordErrorMessage", ErrorHandler.wrongPassword());
                request.setAttribute("passwordErrorClass", ErrorHandler.errorClass());
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            // IF INFO IS CORRECT
            if (customerFacade.validateEmail(email) && customerFacade.validatePassword(email, password)) {
                request.getSession().setAttribute("customer", customerFacade.getCustomerByEmail(email));
                request.getRequestDispatcher("WEB-INF/customer/overview.jsp").forward(request, response);
            }
        }

        // EMPLOYEE
        if (role.equals("employee")) {
            EmployeeFacade employeeFacade = new EmployeeFacade(databaseConnection);
            // CHECKING IF EMAIL DOESN'T EXIST
            if (!employeeFacade.validateEmail(email)) {
                request.setAttribute("emailErrorMessage", ErrorHandler.emailNotFound());
                request.setAttribute("emailErrorClass", ErrorHandler.errorClass());
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            // CHECKING IF PASSWORD IS INCORRECT
            if (!employeeFacade.validatePassword(email, password)) {
                request.setAttribute("passwordErrorMessage", ErrorHandler.wrongPassword());
                request.setAttribute("passwordErrorClass", ErrorHandler.errorClass());
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            // IF INFO IS CORRECT
            if (employeeFacade.validateEmail(email) && employeeFacade.validatePassword(email, password)) {
                Employee employee = new Employee(UUID.fromString(employeeFacade.getId(email)), employeeFacade.getFirstname(email), employeeFacade.getLastname(email), email, employeeFacade.getPassword(email));
                request.getSession().setAttribute("employee", employee);
                request.getRequestDispatcher("WEB-INF/employee/overview.jsp").forward(request, response);
            }
        }
    }
}
