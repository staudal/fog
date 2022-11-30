package com.backend.fog.controllers;

import com.backend.fog.entities.Customer;
import com.backend.fog.facades.OrderFacade;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "RemoveCustomerOrderServlet", value = "/RemoveCustomerOrderServlet")
public class RemoveCustomerOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID orderId = UUID.fromString(request.getParameter("orderId"));
        OrderFacade orderFacade = new OrderFacade();
        orderFacade.removeCustomerOrder(orderId);

        request.getSession().setAttribute("orders", orderFacade.getCustomerOrders((Customer) request.getSession().getAttribute("customer")));
        request.getRequestDispatcher("WEB-INF/customer/orders.jsp").forward(request, response);
    }
}
