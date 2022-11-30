package com.backend.fog.controllers;

import com.backend.fog.entities.Customer;
import com.backend.fog.facades.OrderFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Navigator", value = "/Navigator")
public class NavigatorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String route = request.getParameter("route");
        OrderFacade orderFacade = new OrderFacade();

        if (route.equals("login")) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (route.equals("logout")) {
            request.getSession().invalidate();
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (route.equals("customerOverview")) {
            request.getRequestDispatcher("WEB-INF/customer/overview.jsp").forward(request, response);
        } else if (route.equals("customerOrders")) {
            request.getSession().setAttribute("orders", orderFacade.getCustomerOrders((Customer) request.getSession().getAttribute("customer")));
            request.getRequestDispatcher("WEB-INF/customer/orders.jsp").forward(request, response);
        } else if (route.equals("customerBuilder")) {
            request.getRequestDispatcher("WEB-INF/customer/builder.jsp").forward(request, response);
        } else if (route.equals("createAccount")) {
            request.getRequestDispatcher("createAccount.jsp").forward(request, response);
        } else if (route.equals("employeeOrders")) {
            request.getSession().setAttribute("orders", orderFacade.getAllOrders());
            request.getRequestDispatcher("WEB-INF/employee/orders.jsp").forward(request, response);
        } else if (route.equals("employeeOverview")) {
            request.getRequestDispatcher("WEB-INF/employee/overview.jsp").forward(request, response);
        }
    }
}
