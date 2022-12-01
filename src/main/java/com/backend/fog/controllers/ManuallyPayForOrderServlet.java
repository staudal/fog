package com.backend.fog.controllers;

import com.backend.fog.facades.OrderFacade;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "ManuallyPayForOrderServlet", value = "/ManuallyPayForOrderServlet")
public class ManuallyPayForOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID orderId = UUID.fromString(request.getParameter("orderId"));
        OrderFacade orderFacade = new OrderFacade();

        orderFacade.updateStatus(3, orderId);
        request.setAttribute("order", orderFacade.getOrder(orderId));
        request.getRequestDispatcher("WEB-INF/employee/order.jsp").forward(request, response);
    }
}
