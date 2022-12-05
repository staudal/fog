package com.backend.fog.controllers;

import com.backend.fog.facades.OrderFacade;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "PayForOrderServlet", value = "/PayForOrderServlet")
public class PayForOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderFacade orderFacade = new OrderFacade();

        orderFacade.updateStatus(3, orderId);
        request.setAttribute("order", orderFacade.getOrder(orderId));
        request.setAttribute("products", orderFacade.getProductsFromOrderLine(orderId));
        request.getRequestDispatcher("WEB-INF/customer/order.jsp").forward(request, response);
    }
}
