package com.backend.fog.controllers;

import com.backend.fog.entities.Order;
import com.backend.fog.entities.Product;
import com.backend.fog.facades.OrderFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "LoadCustomerOrderServlet", value = "/LoadCustomerOrderServlet")
public class LoadCustomerOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderFacade orderFacade = new OrderFacade();

        // Retrieving the order from the database using the orderID
        Order order = orderFacade.getOrder(orderId);

        // Retrieving the products for the order and throwing it into an ArrayList
        ArrayList<Product> allProducts = orderFacade.getProductsFromOrderLine(orderId);

        // Splitting up the products into screws and bolts
        ArrayList<Product> woods = new ArrayList<>();
        ArrayList<Product> screws = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getKind() == 1) {
                woods.add(product);
            } else {
                screws.add(product);
            }
        }

        // Splitting up the screws and bolts into two categories (carport, shed)
        ArrayList<Product> woodsCarport = new ArrayList<>();
        ArrayList<Product> screwsCarport = new ArrayList<>();
        ArrayList<Product> woodsShed = new ArrayList<>();
        ArrayList<Product> screwsShed = new ArrayList<>();
        for (Product wood : woods) {
            if (wood.getUse() == 0) {
                woodsCarport.add(wood);
            } else {
                woodsShed.add(wood);
            }
        }
        for (Product screw : screws) {
            if (screw.getUse() == 0) {
                screwsCarport.add(screw);
            } else {
                screwsShed.add(screw);
            }
        }

        // Defining the total price for each of the above-mentioned categories
        int woodsCarportPrice = 0;
        for (Product product : woodsCarport) {
            woodsCarportPrice += product.getPrice() * product.getQuantity();
        }

        int screwsCarportPrice = 0;
        for (Product product : screwsCarport) {
            screwsCarportPrice += product.getPrice() * product.getQuantity();
        }

        int woodsShedPrice = 0;
        for (Product product : woodsShed) {
            woodsShedPrice += product.getPrice() * product.getQuantity();
        }

        int screwsShedPrice = 0;
        for (Product product : screwsShed) {
            screwsShedPrice += product.getPrice() * product.getQuantity();
        }

        request.setAttribute("order", order);
        request.setAttribute("woodsCarport", woodsCarport);
        request.setAttribute("woodsShed", woodsShed);
        request.setAttribute("screwsCarport", screwsCarport);
        request.setAttribute("screwsShed", screwsShed);
        request.setAttribute("woodsCarportPrice", woodsCarportPrice);
        request.setAttribute("screwsCarportPrice", screwsCarportPrice);
        request.setAttribute("woodsShedPrice", woodsShedPrice);
        request.setAttribute("screwsShedPrice", screwsShedPrice);
        request.getRequestDispatcher("WEB-INF/customer/order.jsp").forward(request, response);
    }
}
