package com.backend.fog.controllers.employee;

import com.backend.fog.entities.Customer;
import com.backend.fog.facades.OrderFacade;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "RemoveEmployeeOrderServlet", value = "/RemoveEmployeeOrderServlet")
public class RemoveEmployeeOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderFacade orderFacade = new OrderFacade();
        orderFacade.removeCustomerOrder(orderId);

        request.getSession().setAttribute("orders", orderFacade.getAllOrders());
        request.getRequestDispatcher("WEB-INF/employee/orders.jsp").forward(request, response);
    }
}
