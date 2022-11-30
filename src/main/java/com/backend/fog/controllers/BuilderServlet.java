package com.backend.fog.controllers;

import com.backend.fog.entities.Customer;
import com.backend.fog.entities.Order;
import com.backend.fog.facades.OrderFacade;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "BuilderServlet", value = "/BuilderServlet")
public class BuilderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int carportWidth = Integer.parseInt(request.getParameter("carportWidth"));
        int carportHeight = Integer.parseInt(request.getParameter("carportHeight"));
        int carportLength = Integer.parseInt(request.getParameter("carportLength"));
        int carportSlope = Integer.parseInt(request.getParameter("carportSlope"));

        Order order = new Order(carportWidth, carportLength, carportHeight, carportSlope, "Forespørgsel afsendt", 10000, 0, (Customer) request.getSession().getAttribute("customer"));

        OrderFacade orderFacade = new OrderFacade();
        orderFacade.createNewOrder(order);
        request.getSession().setAttribute("orders", orderFacade.getCustomerOrders((Customer) request.getSession().getAttribute("customer")));
        request.getRequestDispatcher("WEB-INF/customer/orders.jsp").forward(request, response);
    }
}
