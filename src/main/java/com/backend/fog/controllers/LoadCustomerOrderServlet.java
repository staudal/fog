package com.backend.fog.controllers;

import com.backend.fog.facades.OrderFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LoadCustomerOrderServlet", value = "/LoadCustomerOrderServlet")
public class LoadCustomerOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        OrderFacade orderFacade = new OrderFacade();

        request.setAttribute("order", orderFacade.getOrder(orderId));
        request.setAttribute("products", orderFacade.getProductsFromOrderLine(orderId));
        request.getRequestDispatcher("WEB-INF/customer/order.jsp").forward(request, response);
    }
}