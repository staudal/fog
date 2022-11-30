package com.backend.fog.controllers;

import com.backend.fog.facades.OrderFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "UpdateOrderStatusServlet", value = "/UpdateOrderStatusServlet")
public class UpdateOrderStatusServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID orderId = UUID.fromString(request.getParameter("orderId"));
        int price = Integer.parseInt(request.getParameter("price"));
        OrderFacade orderFacade = new OrderFacade();

        orderFacade.updateOrderDiscountPrice(price);
        request.setAttribute("order", orderFacade.getOrder(orderId));
        request.getRequestDispatcher("WEB-INF/employee/order.jsp").forward(request, response);
    }
}
