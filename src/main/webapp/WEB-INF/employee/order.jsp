<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:loggedInAsEmployee>
    <%--  HERO START  --%>
    <section class="d-flex flex-column bg-dark text-center p-5">
        <h1 class="text-light m-auto fw-bold mb-1">Ordreoversigt</h1>
    </section>

    <section class="d-flex container justify-content-between pt-4 gap-4">
        <div class="d-flex flex-column bg-light p-3 rounded w-100">
            <h2 class="fs-5 fw-bold mb-3">Kunde</h2>
            <p class="mb-1"><strong>Navn:</strong> ${requestScope.order.getCustomer().getFirstName()} ${requestScope.order.getCustomer().getLastName()}</p>
            <p class="mb-0"><strong>Email:</strong> ${requestScope.order.getCustomer().getEmail()}</p>
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
    <form class="mb-0 needs-validation" method="post" novalidate>
        <section class="d-flex container justify-content-between align-items-stretch pt-4 gap-4">
            <div class="bg-light w-100 p-3 rounded">
                <h2 class="fs-5 fw-bold text-center w-100">Prisoversigt</h2>
                <p>Indkøbspris: ${requestScope.order.getTotalPrice()},00 DKK</p>
                <c:if test="${requestScope.order.getDiscountPrice() == 0}">
                    <p class="mb-0">Tilbudspris: Endnu ikke givet</p>
                </c:if>
                <c:if test="${requestScope.order.getDiscountPrice() != 0}">
                    <p>Tilbudsprisen: ${requestScope.order.getDiscountPrice()},00 DKK</p>
                </c:if>
            </div>
            <div class="bg-light w-100 p-3 rounded text-center">
                <h2 class="fs-5 fw-bold text-center w-100">Tilbudsformular</h2>
                <input id="discountPrice" type="number" name="price" class="form-control" placeholder="Angiv tilbudspris" required>
            </div>
        </section>
        <section class="container pt-4">
            <button class="w-100 btn btn-primary mb-2" formaction="UpdateOrderStatusServlet" type="submit" name="orderId" value="${requestScope.order.getId()}">Afsend tilbud</button>
            <button class="w-100 btn btn-danger" formaction="RemoveEmployeeOrderServlet" type="submit" name="orderId" value="${requestScope.order.getId()}" formnovalidate>Annuller ordre</button>
        </section>
    </form>
</t:loggedInAsEmployee>