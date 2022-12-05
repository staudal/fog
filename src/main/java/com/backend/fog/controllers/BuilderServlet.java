package com.backend.fog.controllers;

import com.backend.fog.entities.Customer;
import com.backend.fog.entities.Order;
import com.backend.fog.entities.Product;
import com.backend.fog.facades.OrderFacade;
import com.backend.fog.facades.ProductFacade;
import com.backend.fog.logics.Calculator;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@WebServlet(name = "BuilderServlet", value = "/BuilderServlet")
public class BuilderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        OrderFacade orderFacade = new OrderFacade();
        ProductFacade productFacade = new ProductFacade();
        Calculator calculator = new Calculator();

        // Defining the order and adding it to the database
        int carportWidth = Integer.parseInt(request.getParameter("carportWidth"));
        int carportHeight = Integer.parseInt(request.getParameter("carportHeight"));
        int carportLength = Integer.parseInt(request.getParameter("carportLength"));
        int carportSlope = Integer.parseInt(request.getParameter("carportSlope"));
        int customerId = customer.getId();
        int discountPrice = 0;
        int status = 1;

        // Defining the products
        Product beam = productFacade.getBeam(carportHeight);

        // Calculating the price of the order and defining total price
        int priceOfBeams = calculator.calculatePriceOfBeams(carportLength, carportWidth, beam.getPrice());
        int totalPrice = priceOfBeams;

        // Adding info to database
        int orderId = orderFacade.createNewOrder(carportWidth, carportHeight, carportLength, carportSlope, customerId, totalPrice, discountPrice, status);
        orderFacade.createOrderLines(orderId, beam.getId(), calculator.calculateNumberOfBeams(carportLength, carportWidth));

        // Loading orders into session scope to be displayed on the orders page
        request.getSession().setAttribute("orders", orderFacade.getCustomerOrders(customer.getId()));
        request.getRequestDispatcher("WEB-INF/customer/orders.jsp").forward(request, response);
    }
}
