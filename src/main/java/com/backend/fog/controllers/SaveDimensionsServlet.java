package com.backend.fog.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SaveDimensions", value = "/SaveDimensions")
public class SaveDimensionsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carportWidth = request.getParameter("carportWidth");
        String carportLength = request.getParameter("carportLength");
        String carportHeight = request.getParameter("carportHeight");
        String carportSlope = request.getParameter("carportSlope");

        request.getSession().setAttribute("carportWidth", carportWidth);
        request.getSession().setAttribute("carportLength", carportLength);
        request.getSession().setAttribute("carportHeight", carportHeight);
        request.getSession().setAttribute("carportSlope", carportSlope);

        request.getRequestDispatcher("WEB-INF/customer/stepTwo.jsp").forward(request, response);
    }
}