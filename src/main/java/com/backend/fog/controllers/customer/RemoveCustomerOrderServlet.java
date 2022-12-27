package com.backend.fog.controllers.customer;

import com.backend.fog.entities.Customer;
import com.backend.fog.facades.OrderFacade;
import com.backend.fog.persistence.DatabaseConnection;
import com.backend.fog.services.ApplicationStart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RemoveCustomerOrderServlet", value = "/RemoveCustomerOrderServlet")
public class RemoveCustomerOrderServlet extends HttpServlet {

    private DatabaseConnection databaseConnection;

    @Override
    public void init() {
        this.databaseConnection = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        OrderFacade orderFacade = new OrderFacade(databaseConnection);
        orderFacade.removeCustomerOrder(orderId);

        request.getSession().setAttribute("orders", orderFacade.getCustomerOrders(customer.getId()));
        request.getRequestDispatcher("WEB-INF/customer/orders.jsp").forward(request, response);
    }
}
