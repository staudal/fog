package com.backend.fog.controllers.employee;

import com.backend.fog.entities.Order;
import com.backend.fog.entities.Product;
import com.backend.fog.facades.OrderFacade;
import com.backend.fog.logics.Calculator;
import com.backend.fog.logics.Drawer;
import com.backend.fog.persistence.DatabaseConnection;
import com.backend.fog.services.ApplicationStart;
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

    private DatabaseConnection databaseConnection;

    @Override
    public void init() {
        this.databaseConnection = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Locale.setDefault(new Locale("US"));

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderFacade orderFacade = new OrderFacade(databaseConnection);

        // retrieving the order from the database using the orderID
        Order order = orderFacade.getOrder(orderId);

        // retrieving the products for the order and throwing it into an ArrayList
        ArrayList<Product> allProducts = orderFacade.getProductsFromOrderLine(orderId);

        // splitting up the products into individual ArrayLists
        ArrayList<Product> woodsCarport = orderFacade.getCarportWoods(allProducts);
        ArrayList<Product> woodsShed = orderFacade.getShedWoods(allProducts);
        ArrayList<Product> screwsCarport = orderFacade.getCarportScrews(allProducts);
        ArrayList<Product> screwsShed = orderFacade.getShedScrews(allProducts);

        // Defining the total price for each of the above-mentioned categories
        Calculator calculator = new Calculator();
        int woodsCarportPrice = calculator.calculatePartsPrice(woodsCarport);
        int woodsShedPrice = calculator.calculatePartsPrice(woodsShed);
        int screwsCarportPrice = calculator.calculatePartsPrice(screwsCarport);
        int screwsShedPrice = calculator.calculatePartsPrice(screwsShed);

        // creating the svg drawing
        Drawer drawer = new Drawer(woodsCarport, screwsCarport, woodsShed, screwsShed, orderFacade, order.getCarportLength(), order.getCarportWidth(), order.getShedLength(), order.getShedWidth());
        SVG svg = drawer.createCarportDrawing();

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
