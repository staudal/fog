package com.backend.fog.controllers;

import com.backend.fog.entities.Order;
import com.backend.fog.entities.Product;
import com.backend.fog.facades.OrderFacade;
import com.backend.fog.logics.Drawer;
import com.backend.fog.services.SVG;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

@WebServlet(name = "LoadEmployeeOrderServlet", value = "/LoadEmployeeOrderServlet")
public class LoadEmployeeOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Locale.setDefault(new Locale("US"));

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


        // Drawing the SVG drawing
        String viewbox = String.format("%d %d %d %d", 0, 0, order.getCarportLength(), order.getCarportWidth());
        SVG svg = new SVG(0, 0, order.getCarportWidth(), order.getCarportLength(), viewbox);
        Drawer drawer = new Drawer(woodsCarport, screwsCarport, woodsShed, screwsShed, orderFacade, order.getCarportLength(), order.getCarportWidth(), order.getShedLength(), order.getShedWidth());

        // half size shed
        if ((order.getCarportWidth() - 70) / 2 == order.getShedWidth()) {
            drawer.drawShedBackground(svg);
            drawer.drawShedPoles(svg, false);
            drawer.drawWindBracers(svg);
            drawer.drawPoles(svg);
            drawer.drawBeams(svg);
            drawer.drawRafters(svg);
        }

        // full size shed
        else {
            drawer.drawShedBackground(svg);
            drawer.drawShedPoles(svg, true);
            drawer.drawWindBracers(svg);
            drawer.drawPoles(svg);
            drawer.drawBeams(svg);
            drawer.drawRafters(svg);
        }

        request.setAttribute("svg", svg);
        request.setAttribute("order", order);
        request.setAttribute("woodsCarport", woodsCarport);
        request.setAttribute("woodsShed", woodsShed);
        request.setAttribute("screwsCarport", screwsCarport);
        request.setAttribute("screwsShed", screwsShed);
        request.setAttribute("woodsCarportPrice", woodsCarportPrice);
        request.setAttribute("screwsCarportPrice", screwsCarportPrice);
        request.setAttribute("woodsShedPrice", woodsShedPrice);
        request.setAttribute("screwsShedPrice", screwsShedPrice);
        request.getRequestDispatcher("WEB-INF/employee/order.jsp").forward(request, response);
    }
}
