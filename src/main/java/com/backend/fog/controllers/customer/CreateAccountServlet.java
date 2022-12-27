package com.backend.fog.controllers.customer;

import com.backend.fog.errors.ErrorHandler;
import com.backend.fog.facades.CustomerFacade;
import com.backend.fog.persistence.DatabaseConnection;
import com.backend.fog.services.ApplicationStart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CreateAccountServlet", value = "/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {

    private DatabaseConnection databaseConnection;

    @Override
    public void init() {
        this.databaseConnection = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // CHECKING IF EMAIL EXISTS ALREADY
        CustomerFacade customerFacade = new CustomerFacade(databaseConnection);
        if (customerFacade.validateEmail(email)) {
            request.setAttribute("emailErrorMessage", ErrorHandler.emailAreadyExists());
            request.setAttribute("emailErrorClass", ErrorHandler.errorClass());
            request.getRequestDispatcher("createAccount.jsp").forward(request, response);
        } else {
            int customerId = customerFacade.createNewCustomer(firstName, lastName, email, password);
            request.getSession().setAttribute("customer", customerFacade.getCustomerById(customerId));
            request.getRequestDispatcher("WEB-INF/customer/builder.jsp").forward(request, response);
        }
    }
}
