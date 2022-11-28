package com.backend.fog.controllers;

import com.backend.fog.entities.Carport;
import com.backend.fog.entities.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SaveUser", value = "/SaveUser")
public class SaveUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");

        Customer customer = new Customer(firstName, lastName, email, password);

        int carportWidth = (int) request.getSession().getAttribute("carportWidth");
        int carportLength = (int) request.getSession().getAttribute("carportLength");
        int carportHeight = (int) request.getSession().getAttribute("carportHeight");
        int carportSlope = (int) request.getSession().getAttribute("carportSlope");

        Carport carport = new Carport(carportWidth, carportLength, carportHeight, carportSlope, customer);

        request.getSession().setAttribute("customer", customer);
        request.getSession().setAttribute("carport", carport);
        request.getRequestDispatcher("WEB-INF/customer/orders.jsp").forward(request, response);
    }
}
