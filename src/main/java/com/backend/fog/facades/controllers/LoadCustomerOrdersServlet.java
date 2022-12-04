package com.backend.fog.facades.controllers;

import com.backend.fog.facades.CustomerFacade;
import com.backend.fog.facades.OrderFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "LoadCustomerOrdersServlet", value = "/LoadCustomerOrdersServlet")
public class LoadCustomerOrdersServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        CustomerFacade customerFacade = new CustomerFacade();
        OrderFacade orderFacade = new OrderFacade();

        request.setAttribute("customer", customerFacade.getCustomerById(customerId));
        request.setAttribute("orders", orderFacade.getCustomerOrders(customerId));
        request.getRequestDispatcher("WEB-INF/employee/customerOrders.jsp").forward(request, response);
    }
}
