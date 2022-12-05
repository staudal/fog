package com.backend.fog.controllers;

import com.backend.fog.facades.ProductFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CreateNewProductServlet", value = "/CreateNewProductServlet")
public class CreateNewProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        int mPrice = Integer.parseInt(request.getParameter("mPrice"));
        int length = Integer.parseInt(request.getParameter("length"));
        ProductFacade productFacade = new ProductFacade();

        productFacade.createNewProduct(productName, mPrice, length);

        request.getRequestDispatcher("WEB-INF/employee/products.jsp").forward(request, response);
    }
}
