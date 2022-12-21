package com.backend.fog.controllers.customer;

import com.backend.fog.errors.ErrorHandler;
import com.backend.fog.facades.CustomerFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
            int customerId = customerFacade.createNewCustomer(firstName, lastName, email, password);
            request.getSession().setAttribute("customer", customerFacade.getCustomerById(customerId));
            request.getRequestDispatcher("WEB-INF/customer/builder.jsp").forward(request, response);
        }
    }
}
