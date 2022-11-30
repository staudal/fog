<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:loggedInAsCustomer>
    <%--  HERO START  --%>
    <section class="d-flex flex-column bg-dark text-center p-5">
        <h1 class="text-light m-auto fw-bold mb-1">Ordreoversigt</h1>
    </section>

    <section class="d-flex container justify-content-between pt-4 gap-4">
        <div class="d-flex flex-column bg-light p-3 rounded w-100">
            <h2 class="fs-5 fw-bold mb-3">Kunde</h2>
            <p class="mb-1"><strong>Navn:</strong> ${sessionScope.customer.getFirstName()} ${sessionScope.customer.getLastName()}</p>
            <p class="mb-0"><strong>Email:</strong> ${sessionScope.customer.getEmail()}</p>
        </div>
        <div class="d-flex flex-column bg-light p-3 rounded w-100">
            <h2 class="fs-5 fw-bold mb-3">Ordre</h2>
            <p class="mb-0"><strong>Id:</strong> ${requestScope.order.getId()}</p>
            <p class="mb-0"><strong>Status:</strong> ${requestScope.order.getStatus()}</p>
        </div>
    </section>
    <section class="d-flex container flex-column justify-content-between align-items-center pt-4">
        <div class="w-100 bg-light justify-content-center rounded p-3">
            <table class="table table-bordered mb-0">
                <thead>
                    <tr>
                        <th colspan="4" class="text-center table-primary">Carport</th>
                    </tr>
                </thead>
                <thead>
                    <tr>
                        <th>Bredde</th>
                        <th>Længde</th>
                        <th>Højde</th>
                        <th>Hældning</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${requestScope.order.getWidth()}</td>
                        <td>${requestScope.order.getLength()}</td>
                        <td>${requestScope.order.getHeight()}</td>
                        <td>${requestScope.order.getSlope()}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </section>
    <section class="d-flex container justify-content-between align-items-center pt-4 gap-4">
        <div class="bg-light w-100 p-3 rounded text-center">
            <p class="mb-0">Salgspris: 10.000,00 DKK</p>
        </div>
        <div class="bg-light w-100 p-3 rounded text-center">
            <p class="mb-0"><strong>Tilbudspris: Endnu ikke givet</strong></p>
        </div>
    </section>
    <section class="container pt-4">
        <form action="" class="mb-0">
            <button class="w-100 btn btn-primary" disabled>Betal ordre</button>
        </form>
        <form action="RemoveCustomerOrderServlet" method="post" class="mb-0 mt-4">
            <button class="w-100 btn btn-danger" type="submit" name="orderId" value="${requestScope.order.getId()}">Annuller ordre</button>
        </form>
    </section>
</t:loggedInAsCustomer>