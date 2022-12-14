package com.backend.fog.controllers.customer;

import com.backend.fog.entities.Customer;
import com.backend.fog.entities.Product;
import com.backend.fog.errors.ErrorHandler;
import com.backend.fog.facades.OrderFacade;
import com.backend.fog.facades.ProductFacade;
import com.backend.fog.logics.Calculator;
import com.backend.fog.persistence.DatabaseConnection;
import com.backend.fog.services.ApplicationStart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "BuilderServlet", value = "/BuilderServlet")
public class BuilderServlet extends HttpServlet {

    private DatabaseConnection databaseConnection;

    @Override
    public void init() {
        this.databaseConnection = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Customer customer = (Customer) request.getSession().getAttribute("customer");
        OrderFacade orderFacade = new OrderFacade(databaseConnection);
        ProductFacade productFacade = new ProductFacade(databaseConnection);
        Calculator calculator = new Calculator();

        int carportWidth = 0;
        int carportLength = 0;
        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        int shedLength = Integer.parseInt(request.getParameter("shedLength"));
        int customerId = customer.getId();
        int discountPrice = 0;
        int status = 1;

        // DEFINING CARPORT DIMENSIONS: if width and length is not null
        if (request.getParameter("carportWidth") != null && request.getParameter("carportLength") != null) {
            carportWidth = Integer.parseInt(request.getParameter("carportWidth"));
            carportLength = Integer.parseInt(request.getParameter("carportLength"));
        }

        // ERROR HANDLER: both width and length is undefined
        if (request.getParameter("carportWidth") == null && request.getParameter("carportLength") == null) {
            request.setAttribute("widthErrorClass", ErrorHandler.errorClass());
            request.setAttribute("lengthErrorClass", ErrorHandler.errorClass());
            request.setAttribute("widthErrorMessage", ErrorHandler.widthNotDefined());
            request.setAttribute("lengthErrorMessage", ErrorHandler.lengthNotDefined());
            request.getRequestDispatcher("WEB-INF/customer/builder.jsp").forward(request, response);
        }

        // ERROR HANDLER: only width is undefined
        else if (request.getParameter("carportWidth") == null) {
            request.setAttribute("widthErrorClass", ErrorHandler.errorClass());
            request.setAttribute("widthErrorMessage", ErrorHandler.widthNotDefined());
            request.getRequestDispatcher("WEB-INF/customer/builder.jsp").forward(request, response);
        }

        // ERROR HANDLER: only length is undefined
        else if (request.getParameter("carportLength") == null) {
            request.setAttribute("lengthErrorClass", ErrorHandler.errorClass());
            request.setAttribute("lengthErrorMessage", ErrorHandler.lengthNotDefined());
            request.getRequestDispatcher("WEB-INF/customer/builder.jsp").forward(request, response);
        }

        // ERROR HANDLER: if shed width is undefined but shed length isn't
        else if (shedWidth == 0 && shedLength != 0) {
            request.setAttribute("shedWidthErrorClass", ErrorHandler.errorClass());
            request.setAttribute("shedWidthErrorMessage", ErrorHandler.shedWidthUndefined());
            request.getRequestDispatcher("WEB-INF/customer/builder.jsp").forward(request, response);
        }

        // ERROR HANDLER: if shed length is undefined but shed width isn't
        else if (shedLength == 0 && shedWidth != 0) {
            request.setAttribute("proportionsErrorClass", ErrorHandler.errorClass());
            request.setAttribute("shedLengthErrorMessage", ErrorHandler.shedLengthUndefined());
            request.getRequestDispatcher("WEB-INF/customer/builder.jsp").forward(request, response);
        }

        // ERROR HANDLER: if shed length is longer than carport length
        else if (carportLength < shedLength) {
            request.setAttribute("proportionsErrorClass", ErrorHandler.errorClass());
            request.setAttribute("proportionsErrorMessage", ErrorHandler.proportionsErrorMessage());
            request.getRequestDispatcher("WEB-INF/customer/builder.jsp").forward(request, response);
        }

        // ORDER CREATOR: if no shed is selected
        else if (shedWidth == 0) {
            // Defining the products
            Product pole = productFacade.getPole();
            Product beam = productFacade.getBeam(carportLength);
            Product rafter = productFacade.getRafter(carportWidth);
            Product lowerSideFascia = productFacade.getLowerSideFascia(carportLength);
            Product upperSideFascia = productFacade.getUpperSideFascia(carportLength);
            Product lowerFrontBackFascia = productFacade.getLowerFrontBackFascia(carportWidth);
            Product upperFrontBackFascia = productFacade.getUpperFrontBackFascia(carportWidth);
            Product roof = productFacade.getRoof(carportLength);
            Product carriageBolt = productFacade.getCarriageBolt();
            Product squareWasher = productFacade.getSquareWasher();
            Product windBracingStrap = productFacade.getWindBracingStrap();
            Product rafterConnectorLeft = productFacade.getRafterConnectorLeft();
            Product rafterConnectorRight = productFacade.getRafterConnectorRight();
            Product connectorScrews = productFacade.getConnectorScrews();
            Product fasciaScrews = productFacade.getFasciaScrews();
            Product trapezoidScrews = productFacade.getTrapezoidScrews();

            // Calculating the price of the order and defining total price
            int polePrice = calculator.polePrice(carportLength, pole.getPrice());
            int beamPrice = calculator.beamPrice(carportLength, beam.getPrice());
            int rafterPrice = calculator.rafterPrice(carportLength, rafter.getPrice());
            int lowerSideFasciaPrice = calculator.lowerSideFasciaPrice(carportLength, lowerSideFascia.getPrice());
            int upperSideFasciaPrice = calculator.upperSideFasciaPrice(carportLength, upperSideFascia.getPrice());
            int lowerFrontBackFasciaPrice = calculator.lowerFrontBackFasciaPrice(lowerFrontBackFascia.getPrice());
            int upperFrontBackFasciaPrice = calculator.upperFrontBackFasciaPrice(upperFrontBackFascia.getPrice());
            int roofPrice = calculator.roofPrice(carportLength, carportWidth, roof.getPrice());
            int carriageBoltPrice = calculator.carriageBoltPrice(carportLength, carriageBolt.getPrice());
            int squareWasherPrice = calculator.squareWasherPrice(carportLength, squareWasher.getPrice());
            int windBracingStrapPrice = calculator.windBracingStrapPrice(windBracingStrap.getPrice());
            int rafterConnectorLeftPrice = calculator.rafterConnectorLeftPrice(carportLength, rafterConnectorLeft.getPrice());
            int rafterConnectorRightPrice = calculator.rafterConnectorRightPrice(carportLength, rafterConnectorRight.getPrice());
            int connectorScrewsPrice = connectorScrews.getPrice();
            int fasciaScrewsPrice = fasciaScrews.getPrice();
            int trapezoidScrewsPrice = calculator.trapezoidScrewPrice(carportWidth, carportLength, trapezoidScrews.getPrice());
            int totalPrice = polePrice + beamPrice + rafterPrice + lowerSideFasciaPrice + upperSideFasciaPrice + lowerFrontBackFasciaPrice + upperFrontBackFasciaPrice + roofPrice + carriageBoltPrice + squareWasherPrice + windBracingStrapPrice + rafterConnectorLeftPrice + rafterConnectorRightPrice + connectorScrewsPrice + fasciaScrewsPrice + trapezoidScrewsPrice;

            // Adding info to database
            int orderId = orderFacade.createNewOrder(carportWidth, carportLength, 0, 0, customerId, totalPrice, discountPrice, status);
            orderFacade.createOrderLines(orderId, pole.getId(), calculator.poleNumber(carportLength));
            orderFacade.createOrderLines(orderId, beam.getId(), calculator.beamNumber(carportLength));
            orderFacade.createOrderLines(orderId, rafter.getId(), calculator.rafterNumber(carportLength));
            orderFacade.createOrderLines(orderId, lowerSideFascia.getId(), calculator.lowerSideFasciaNumber(carportLength));
            orderFacade.createOrderLines(orderId, upperSideFascia.getId(), calculator.upperSideFasciaNumber(carportLength));
            orderFacade.createOrderLines(orderId, lowerFrontBackFascia.getId(), calculator.lowerFrontBackFasciaNumber());
            orderFacade.createOrderLines(orderId, upperFrontBackFascia.getId(), calculator.upperFrontBackFasciaNumber());
            orderFacade.createOrderLines(orderId, roof.getId(), calculator.roofNumber(carportLength, carportWidth));
            orderFacade.createOrderLines(orderId, carriageBolt.getId(), calculator.carriageBoltNumber(carportLength));
            orderFacade.createOrderLines(orderId, squareWasher.getId(), calculator.squareWasherNumber(carportLength));
            orderFacade.createOrderLines(orderId, windBracingStrap.getId(), calculator.windBracingStrapNumber());
            orderFacade.createOrderLines(orderId, rafterConnectorLeft.getId(), calculator.rafterConnectorLeftNumber(carportLength));
            orderFacade.createOrderLines(orderId, rafterConnectorRight.getId(), calculator.rafterConnectorRightNumber(carportLength));
            orderFacade.createOrderLines(orderId, connectorScrews.getId(), 1);
            orderFacade.createOrderLines(orderId, fasciaScrews.getId(), 1);
            orderFacade.createOrderLines(orderId, trapezoidScrews.getId(), calculator.trapezoidScrewNumber(carportWidth, carportLength));

            // Loading orders into session scope to be displayed on the orders page
            request.getSession().setAttribute("orders", orderFacade.getCustomerOrders(customer.getId()));
            request.getRequestDispatcher("WEB-INF/customer/orders.jsp").forward(request, response);
        }

        // ORDER CREATOR: if a shed is selected
        else {
            // Defining the products
            Product pole = productFacade.getPole();
            Product beam = productFacade.getBeam(carportLength);
            Product rafter = productFacade.getRafter(carportWidth);
            Product lowerSideFascia = productFacade.getLowerSideFascia(carportLength);
            Product upperSideFascia = productFacade.getUpperSideFascia(carportLength);
            Product lowerFrontBackFascia = productFacade.getLowerFrontBackFascia(carportWidth);
            Product upperFrontBackFascia = productFacade.getUpperFrontBackFascia(carportWidth);
            Product roof = productFacade.getRoof(carportLength);
            Product carriageBolt = productFacade.getCarriageBolt();
            Product squareWasher = productFacade.getSquareWasher();
            Product windBracingStrap = productFacade.getWindBracingStrap();
            Product rafterConnectorLeft = productFacade.getRafterConnectorLeft();
            Product rafterConnectorRight = productFacade.getRafterConnectorRight();
            Product connectorScrews = productFacade.getConnectorScrews();
            Product fasciaScrews = productFacade.getFasciaScrews();
            Product trapezoidScrews = productFacade.getTrapezoidScrews();

            // Defining the products (shed)
            Product zForShedDoor = productFacade.getShedDoorZ();
            Product beamForShed = productFacade.getBeamForShed(shedLength);
            Product poleForShed = productFacade.getPoleForShed();
            Product shedJoistSide = productFacade.getShedJoistSide(shedLength);
            Product shedJoistFrontBack = productFacade.getShedJoistFrontBack(carportWidth);
            Product cladding = productFacade.getCladding();
            Product shortCladdingScrews = productFacade.getShortCladdingScrews();
            Product longCladdingScrews = productFacade.getLongCladdingScrews();
            Product doorHinge = productFacade.getDoorHinge();
            Product doorHandle = productFacade.getDoorHandle();
            Product shedJoistHinge = productFacade.getJoistHinge();

            // Calculating the price of the order and defining total price
            int polePrice = calculator.polePrice(carportLength, pole.getPrice());
            int beamPrice = calculator.beamPrice(carportLength, beam.getPrice());
            int rafterPrice = calculator.rafterPrice(carportLength, rafter.getPrice());
            int lowerSideFasciaPrice = calculator.lowerSideFasciaPrice(carportLength, lowerSideFascia.getPrice());
            int upperSideFasciaPrice = calculator.upperSideFasciaPrice(carportLength, upperSideFascia.getPrice());
            int lowerFrontBackFasciaPrice = calculator.lowerFrontBackFasciaPrice(lowerFrontBackFascia.getPrice());
            int upperFrontBackFasciaPrice = calculator.upperFrontBackFasciaPrice(upperFrontBackFascia.getPrice());
            int roofPrice = calculator.roofPrice(carportLength, carportWidth, roof.getPrice());
            int carriageBoltPrice = calculator.carriageBoltPrice(carportLength, carriageBolt.getPrice());
            int squareWasherPrice = calculator.squareWasherPrice(carportLength, squareWasher.getPrice());
            int windBracingStrapPrice = calculator.windBracingStrapPrice(windBracingStrap.getPrice());
            int rafterConnectorLeftPrice = calculator.rafterConnectorLeftPrice(carportLength, rafterConnectorLeft.getPrice());
            int rafterConnectorRightPrice = calculator.rafterConnectorRightPrice(carportLength, rafterConnectorRight.getPrice());
            int connectorScrewsPrice = connectorScrews.getPrice();
            int fasciaScrewsPrice = fasciaScrews.getPrice();
            int trapezoidScrewsPrice = calculator.trapezoidScrewPrice(carportWidth, carportLength, trapezoidScrews.getPrice());
            // Calculating the price of the order and defining total price (shed)
            int zForShedDoorPrice = zForShedDoor.getPrice();
            int beamForShedPrice = beamForShed.getPrice();
            int poleForShedPrice = calculator.shedPolePrice(shedWidth, poleForShed.getPrice());
            int shedJoistSidePrice = calculator.shedJoistSidePrice(shedJoistSide.getPrice());
            int shedJoistFrontBackPrice = calculator.shedJoistFrontBackPrice(shedWidth, shedJoistFrontBack.getPrice());
            int claddingPrice = calculator.claddingPrice(shedWidth, shedLength, carportWidth, cladding.getPrice());
            int shortCladdingScrewsPrice = shortCladdingScrews.getPrice() * 2;
            int longCladdingScrewsPrice = longCladdingScrews.getPrice() * 2;
            int doorHingePrice = doorHinge.getPrice() * 2;
            int doorHandlePrice = doorHandle.getPrice();
            int shedJoistHingePrice = calculator.joistHingePrice(shedWidth, shedJoistHinge.getPrice());
            int totalPrice = polePrice + beamPrice + rafterPrice + lowerSideFasciaPrice + upperSideFasciaPrice + lowerFrontBackFasciaPrice + upperFrontBackFasciaPrice + roofPrice + carriageBoltPrice + squareWasherPrice + windBracingStrapPrice + rafterConnectorLeftPrice + rafterConnectorRightPrice + connectorScrewsPrice + fasciaScrewsPrice + trapezoidScrewsPrice + zForShedDoorPrice + beamForShedPrice + poleForShedPrice + shedJoistSidePrice + shedJoistFrontBackPrice + claddingPrice + longCladdingScrewsPrice + shortCladdingScrewsPrice + doorHingePrice + doorHandlePrice + shedJoistHingePrice;

            // Adding info to database
            int orderId = 0;
            if (shedWidth == 1) {
                orderId = orderFacade.createNewOrder(carportWidth, carportLength, carportWidth - 70, shedLength, customerId, totalPrice, discountPrice, status);
            } else if (shedWidth == 2) {
                orderId = orderFacade.createNewOrder(carportWidth, carportLength, (carportWidth - 70) / 2, shedLength, customerId, totalPrice, discountPrice, status);
            }
            orderFacade.createOrderLines(orderId, pole.getId(), calculator.poleNumber(carportLength));
            orderFacade.createOrderLines(orderId, beam.getId(), calculator.beamNumber(carportLength));
            orderFacade.createOrderLines(orderId, rafter.getId(), calculator.rafterNumber(carportLength));
            orderFacade.createOrderLines(orderId, lowerSideFascia.getId(), calculator.lowerSideFasciaNumber(carportLength));
            orderFacade.createOrderLines(orderId, upperSideFascia.getId(), calculator.upperSideFasciaNumber(carportLength));
            orderFacade.createOrderLines(orderId, lowerFrontBackFascia.getId(), calculator.lowerFrontBackFasciaNumber());
            orderFacade.createOrderLines(orderId, upperFrontBackFascia.getId(), calculator.upperFrontBackFasciaNumber());
            orderFacade.createOrderLines(orderId, roof.getId(), calculator.roofNumber(carportLength, carportWidth));
            orderFacade.createOrderLines(orderId, carriageBolt.getId(), calculator.carriageBoltNumber(carportLength));
            orderFacade.createOrderLines(orderId, squareWasher.getId(), calculator.squareWasherNumber(carportLength));
            orderFacade.createOrderLines(orderId, windBracingStrap.getId(), calculator.windBracingStrapNumber());
            orderFacade.createOrderLines(orderId, rafterConnectorLeft.getId(), calculator.rafterConnectorLeftNumber(carportLength));
            orderFacade.createOrderLines(orderId, rafterConnectorRight.getId(), calculator.rafterConnectorRightNumber(carportLength));
            orderFacade.createOrderLines(orderId, connectorScrews.getId(), 1);
            orderFacade.createOrderLines(orderId, fasciaScrews.getId(), 1);
            orderFacade.createOrderLines(orderId, trapezoidScrews.getId(), calculator.trapezoidScrewNumber(carportWidth, carportLength));

            // Adding info to database (shed)
            orderFacade.createOrderLines(orderId, zForShedDoor.getId(), 1);
            orderFacade.createOrderLines(orderId, beamForShed.getId(), 2);
            orderFacade.createOrderLines(orderId, poleForShed.getId(), calculator.shedPoleNumber(shedWidth));
            orderFacade.createOrderLines(orderId, shedJoistSide.getId(), calculator.shedJoistSideNumber());
            orderFacade.createOrderLines(orderId, shedJoistFrontBack.getId(), calculator.shedJoistFrontBackNumber(shedWidth));
            orderFacade.createOrderLines(orderId, cladding.getId(), calculator.claddingNumber(shedWidth, shedLength, carportWidth));
            orderFacade.createOrderLines(orderId, shortCladdingScrews.getId(), 2);
            orderFacade.createOrderLines(orderId, longCladdingScrews.getId(), 2);
            orderFacade.createOrderLines(orderId, doorHinge.getId(), 2);
            orderFacade.createOrderLines(orderId, doorHandle.getId(), 1);
            orderFacade.createOrderLines(orderId, shedJoistHinge.getId(), calculator.joistHingeNumber(shedWidth));

            // Loading orders into session scope to be displayed on the orders page
            request.getSession().setAttribute("orders", orderFacade.getCustomerOrders(customer.getId()));
            request.getRequestDispatcher("WEB-INF/customer/orders.jsp").forward(request, response);
        }
    }
}
