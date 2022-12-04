package com.backend.fog.facades.controllers;

import com.backend.fog.entities.Customer;
import com.backend.fog.entities.Employee;
import com.backend.fog.errors.ErrorHandler;
import com.backend.fog.facades.CustomerFacade;
import com.backend.fog.facades.EmployeeFacade;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // CUSTOMER
        if (role.equals("customer")) {
            ErrorHandler errorHandler = new ErrorHandler();
            CustomerFacade customerFacade = new CustomerFacade();
            // CHECKING IF EMAIL DOESN'T EXISTS
            if (!customerFacade.validateEmail(email)) {
                request.setAttribute("emailErrorMessage", errorHandler.emailNotFound());
                request.setAttribute("emailErrorClass", errorHandler.errorClass());
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            // CHECKING IF PASSWORD IS INCORRECT
            if (!customerFacade.validatePassword(email, password)) {
                request.setAttribute("passwordErrorMessage", errorHandler.wrongPassword());
                request.setAttribute("passwordErrorClass", errorHandler.errorClass());
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
            ErrorHandler errorHandler = new ErrorHandler();
            EmployeeFacade employeeFacade = new EmployeeFacade();
            // CHECKING IF EMAIL DOESN'T EXIST
            if (!employeeFacade.validateEmail(email)) {
                request.setAttribute("emailErrorMessage", errorHandler.emailNotFound());
                request.setAttribute("emailErrorClass", errorHandler.errorClass());
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            // CHECKING IF PASSWORD IS INCORRECT
            if (!employeeFacade.validatePassword(email, password)) {
                request.setAttribute("passwordErrorMessage", errorHandler.wrongPassword());
                request.setAttribute("passwordErrorClass", errorHandler.errorClass());
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
