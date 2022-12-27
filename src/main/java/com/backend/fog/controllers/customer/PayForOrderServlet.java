package com.backend.fog.controllers.customer;

import com.backend.fog.entities.Order;
import com.backend.fog.entities.Product;
import com.backend.fog.facades.OrderFacade;
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

@WebServlet(name = "PayForOrderServlet", value = "/PayForOrderServlet")
public class PayForOrderServlet extends HttpServlet {

    private DatabaseConnection databaseConnection;

    @Override
    public void init() {
        this.databaseConnection = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderFacade orderFacade = new OrderFacade(databaseConnection);
        orderFacade.updateStatus(3, orderId);

        // retrieving the order from the database using the orderID
        Order order = orderFacade.getOrder(orderId);

        // retrieving the products for the order and throwing it into an ArrayList
        ArrayList<Product> allProducts = orderFacade.getProductsFromOrderLine(orderId);

        // splitting up the products into individual ArrayLists
        ArrayList<Product> woodsCarport = orderFacade.getCarportWoods(allProducts);
        ArrayList<Product> woodsShed = orderFacade.getShedWoods(allProducts);
        ArrayList<Product> screwsCarport = orderFacade.getCarportScrews(allProducts);
        ArrayList<Product> screwsShed = orderFacade.getShedScrews(allProducts);

        // creating the svg drawing
        Drawer drawer = new Drawer(woodsCarport, screwsCarport, woodsShed, screwsShed, orderFacade, order.getCarportLength(), order.getCarportWidth(), order.getShedLength(), order.getShedWidth());
        SVG svg = drawer.createCarportDrawing();

        request.setAttribute("svg", svg);
        request.setAttribute("order", order);
        request.setAttribute("woodsCarport", woodsCarport);
        request.setAttribute("woodsShed", woodsShed);
        request.setAttribute("screwsCarport", screwsCarport);
        request.setAttribute("screwsShed", screwsShed);
        request.getRequestDispatcher("WEB-INF/customer/order.jsp").forward(request, response);
    }
}
