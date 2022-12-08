package com.backend.fog.controllers;

import com.backend.fog.entities.Customer;
import com.backend.fog.entities.Product;
import com.backend.fog.errors.ErrorHandler;
import com.backend.fog.facades.OrderFacade;
import com.backend.fog.facades.ProductFacade;
import com.backend.fog.logics.Calculator;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

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
        int carportSlope = 0;
        int customerId = customer.getId();
        int discountPrice = 0;
        int status = 1;

        if (carportWidth > 600) {
            ErrorHandler errorHandler = new ErrorHandler();
            request.setAttribute("carportLength", carportLength);
            request.setAttribute("carportHeight", carportHeight);
            request.setAttribute("widthErrorClass", errorHandler.errorClass());
            request.getRequestDispatcher("WEB-INF/customer/builder.jsp").forward(request, response);
        } else if (carportLength > 800) {
            ErrorHandler errorHandler = new ErrorHandler();
            request.setAttribute("carportWidth", carportWidth);
            request.setAttribute("carportHeight", carportHeight);
            request.setAttribute("lengthErrorClass", errorHandler.errorClass());
            request.getRequestDispatcher("WEB-INF/customer/builder.jsp").forward(request, response);
        } else if (carportHeight > 390) {
            ErrorHandler errorHandler = new ErrorHandler();
            request.setAttribute("carportWidth", carportWidth);
            request.setAttribute("carportLength", carportLength);
            request.setAttribute("heightErrorClass", errorHandler.errorClass());
            request.getRequestDispatcher("WEB-INF/customer/builder.jsp").forward(request, response);
        } else {
            // Defining the products
            Product pole = productFacade.getPole(carportHeight);
            Product supportBeam = productFacade.getSupportBeam(carportLength);
            Product rafter = productFacade.getRafter(carportWidth);
            Product frontBackUpperFascia = productFacade.getFrontBackUpperFascia(carportWidth);
            Product frontBackLowerFascia = productFacade.getFrontBackLowerFascia(carportWidth);
            Product sideUpperFascia = productFacade.getSideUpperFascia(carportLength);
            Product sideLowerFascia = productFacade.getSideLowerFascia(carportLength);

            // Calculating the price of the order and defining total price
            int priceOfBeams = calculator.calculatePriceOfPoles(carportLength, carportWidth, pole.getPrice());
            int priceOfSupportBeams = calculator.calculatePriceOfSupportBeams(carportLength, carportWidth, supportBeam.getPrice());
            int priceOfRafters = calculator.calculatePriceOfRafters(carportLength, rafter.getPrice());
            int priceOfFascias = calculator.calculatePriceOfFrontBackFascia(frontBackUpperFascia.getPrice()) + calculator.calculatePriceOfFrontBackFascia(frontBackLowerFascia.getPrice()) + calculator.calculatePriceOfSideFascia(carportLength, sideUpperFascia.getPrice()) + calculator.calculatePriceOfSideFascia(carportLength, sideUpperFascia.getPrice());
            int totalPrice = priceOfBeams + priceOfSupportBeams + priceOfRafters + priceOfFascias;

            // Adding info to database
            int orderId = orderFacade.createNewOrder(carportWidth, carportHeight, carportLength, carportSlope, customerId, totalPrice, discountPrice, status);
            orderFacade.createOrderLines(orderId, pole.getId(), calculator.calculateNumberOfPoles(carportLength, carportWidth));
            orderFacade.createOrderLines(orderId, supportBeam.getId(), calculator.calculateNumberOfSupportBeam(carportLength, carportWidth));
            orderFacade.createOrderLines(orderId, rafter.getId(), calculator.calculateNumberOfRafters(carportLength));
            orderFacade.createOrderLines(orderId, frontBackUpperFascia.getId(), calculator.calculateNumberOfFrontBackFascia());
            orderFacade.createOrderLines(orderId, frontBackLowerFascia.getId(), calculator.calculateNumberOfFrontBackFascia());
            orderFacade.createOrderLines(orderId, sideUpperFascia.getId(), calculator.calculateNumberOfSideFascia(carportLength));
            orderFacade.createOrderLines(orderId, sideLowerFascia.getId(), calculator.calculateNumberOfSideFascia(carportLength));

            // Loading orders into session scope to be displayed on the orders page
            request.getSession().setAttribute("orders", orderFacade.getCustomerOrders(customer.getId()));
            request.getRequestDispatcher("WEB-INF/customer/orders.jsp").forward(request, response);
        }
    }
}
