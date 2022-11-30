package com.backend.fog.controllers;

import com.backend.fog.entities.Customer;
import com.backend.fog.entities.Order;
import com.backend.fog.facades.OrderFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "LoadCustomerOrderServlet", value = "/LoadCustomerOrderServlet")
public class LoadCustomerOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID orderId = UUID.fromString(request.getParameter("orderId"));
        OrderFacade orderFacade = new OrderFacade();
        Order order = new Order(orderId, orderFacade.getWidth(orderId), orderFacade.getLength(orderId), orderFacade.getHeight(orderId), orderFacade.getSlope(orderId), orderFacade.getStatus(orderId), orderFacade.getTotalPrice(orderId), orderFacade.getDiscountPrice(orderId), (Customer) request.getSession().getAttribute("customer"));

        request.setAttribute("order", order);
        request.getRequestDispatcher("WEB-INF/customer/order.jsp").forward(request, response);
    }
}
