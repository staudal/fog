package com.backend.fog.controllers;

import com.backend.fog.entities.Customer;
import com.backend.fog.errors.ErrorHandler;
import com.backend.fog.facades.CustomerFacade;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CreateAccountServlet", value = "/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // CHECKING IF EMAIL EXISTS ALREADY
        CustomerFacade customerFacade = new CustomerFacade();
        if (customerFacade.validateEmail(email)) {
            ErrorHandler errorHandler = new ErrorHandler();
            request.setAttribute("emailErrorMessage", errorHandler.emailAreadyExists());
            request.setAttribute("emailErrorClass", errorHandler.errorClass());
            request.getRequestDispatcher("createAccount.jsp").forward(request, response);
        } else {
            Customer customer = new Customer(firstName, lastName, email, password);
            customerFacade.CreateNewCustomer(customer);
            request.getSession().setAttribute("customer", customer);
            request.getRequestDispatcher("WEB-INF/customer/builder.jsp").forward(request, response);
        }
    }
}
