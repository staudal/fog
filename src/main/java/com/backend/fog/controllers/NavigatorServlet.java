package com.backend.fog.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Navigator", value = "/Navigator")
public class NavigatorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String route = request.getParameter("route");

        if (route.equals("customer_stepOne")) {
            request.getRequestDispatcher("WEB-INF/customer/stepOne.jsp").forward(request, response);
        } else if (route.equals("customer_stepTwo")) {
            request.getRequestDispatcher("WEB-INF/customer/stepTwo.jsp").forward(request, response);
        }
    }
}
