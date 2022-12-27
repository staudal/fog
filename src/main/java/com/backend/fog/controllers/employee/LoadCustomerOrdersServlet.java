package com.backend.fog.controllers.employee;

import com.backend.fog.facades.CustomerFacade;
import com.backend.fog.facades.OrderFacade;
import com.backend.fog.persistence.DatabaseConnection;
import com.backend.fog.services.ApplicationStart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LoadCustomerOrdersServlet", value = "/LoadCustomerOrdersServlet")
public class LoadCustomerOrdersServlet extends HttpServlet {

    private DatabaseConnection databaseConnection;

    @Override
    public void init() {
        this.databaseConnection = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        CustomerFacade customerFacade = new CustomerFacade(databaseConnection);
        OrderFacade orderFacade = new OrderFacade(databaseConnection);

        request.setAttribute("customer", customerFacade.getCustomerById(customerId));
        request.setAttribute("orders", orderFacade.getCustomerOrders(customerId));
        request.getRequestDispatcher("WEB-INF/employee/customerOrders.jsp").forward(request, response);
    }
}
