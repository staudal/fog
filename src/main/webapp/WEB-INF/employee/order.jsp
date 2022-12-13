<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:loggedInAsEmployee>
    <%--  HERO START  --%>
    <section class="d-flex flex-column bg-dark text-center p-5">
        <h1 class="text-light m-auto fw-bold mb-1">Ordreoversigt</h1>
    </section>

    <section class="d-flex container justify-content-between align-items-stretch pt-4 gap-4">
        <div class="d-flex flex-column bg-light p-3 rounded w-100 border">
            <h2 class="fs-5 fw-bold mb-3">Kunde</h2>
            <p class="mb-1"><strong>Navn:</strong> ${requestScope.order.getCustomer().getFirstName()} ${requestScope.order.getCustomer().getLastName()}</p>
            <p class="mb-0"><strong>Email:</strong> ${requestScope.order.getCustomer().getEmail()}</p>
        </div>
        <div class="d-flex flex-column bg-light p-3 rounded w-100 border">
            <h2 class="fs-5 fw-bold mb-3">Ordre</h2>
            <p class="mb-0"><strong>Id:</strong> ${requestScope.order.getId()}</p>
            <c:if test="${requestScope.order.getStatus() == 1}">
                <p class="mb-0"><strong>Status:</strong> Forespørgsel modtaget</p>
            </c:if>
            <c:if test="${requestScope.order.getStatus() == 2}">
                <p class="mb-0"><strong>Status:</strong> Tilbud afsendt</p>
            </c:if>
            <c:if test="${requestScope.order.getStatus() == 3}">
                <p class="mb-0"><strong>Status:</strong> Tilbud betalt</p>
            </c:if>
            <c:if test="${requestScope.order.getDiscountPrice() == 0}">
                <p class="mb-0"><strong>Tilbudspris:</strong> Endnu ikke angivet</p>
            </c:if>
            <c:if test="${requestScope.order.getDiscountPrice() != 0}">
                <p class="mb-0"><strong>Tilbudspris:</strong> ${requestScope.order.getDiscountPrice()},00 DKK</p>
            </c:if>
        </div>
    </section>
    <section class="d-flex container flex-column justify-content-between align-items-center pt-4">
        <div class="w-100 bg-light justify-content-center rounded p-3 border">
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
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${requestScope.order.getCarportWidth()} cm</td>
                    <td>${requestScope.order.getCarportLength()} cm</td>
                </tr>
                </tbody>
            </table>
            <table class="table table-bordered mt-3 mb-0">
                <thead>
                <tr>
                    <th colspan="4" class="text-center table-primary">Skur</th>
                </tr>
                </thead>
                <thead>
                <tr>
                    <th>Bredde</th>
                    <th>Længde</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${requestScope.order.getShedWidth()} cm</td>
                    <td>${requestScope.order.getShedLength()} cm</td>
                </tr>
                </tbody>
            </table>
        </div>
    </section>
    <section class="d-flex container flex-column justify-content-between align-items-center pt-4">
        <div class="w-100 bg-light justify-content-center rounded p-3 border">
            <table class="table table-bordered mb-0">
                <thead>
                <tr>
                    <th colspan="7" class="text-center table-primary">Stykliste</th>
                </tr>
                </thead>
                <thead>
                <tr>
                    <th>Varetype</th>
                    <th>Dimensions</th>
                    <th>Længde</th>
                    <th>Antal</th>
                    <th>Pris pr. stk</th>
                    <th>Pris i alt</th>
                    <th>Note</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.products}" var="product">
                        <tr>
                            <td>${product.getCategory()}</td>
                            <td>${product.getDimensions()}</td>
                            <td>${product.getLength()} cm</td>
                            <td>${product.getQuantity()}</td>
                            <td>${product.getPrice()} DKK</td>
                            <td>${product.getPrice() * product.getQuantity()} DKK</td>
                            <td>${product.getDescription()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
    <form class="mb-0" method="post">
        <section class="d-flex container justify-content-between align-items-stretch pt-4 gap-4">
            <div class="bg-light w-100 p-3 rounded border">
                <h2 class="fs-5 fw-bold mb-3">Prisoversigt</h2>
                <p class="mb-0"><strong>Indkøbspris:</strong> ${requestScope.order.getTotalPrice()},00 DKK</p>
                <c:if test="${requestScope.order.getDiscountPrice() == 0}">
                    <p class="mb-0"><strong>Tilbudspris:</strong> Endnu ikke angivet</p>
                    <p class="mb-0"><strong>Dækningsbidrag:</strong> Endnu ikke beregnet</p>
                </c:if>
                <c:if test="${requestScope.order.getDiscountPrice() != 0}">
                    <p class="mb-0"><strong>Tilbudspris:</strong> ${requestScope.order.getDiscountPrice()},00 DKK</p>
                    <p class="mb-0"><strong>Dækningsbidrag:</strong> FUNGERER IKKE</p>
                </c:if>
            </div>
            <c:if test="${requestScope.order.getStatus() == 1}">
                <div class="bg-light w-100 p-3 rounded border">
                    <h2 class="fs-5 fw-bold mb-3">Tilbudsformular</h2>
                    <label for="discountPrice" class="form-label"><strong>Bemærk:</strong> Tilbudsprisen skal være højere end indkøbsprisen.</label>
                    <input id="discountPrice" type="number" name="price" class="form-control" placeholder="Angiv tilbudspris" required>
                </div>
            </c:if>
        </section>

        <c:if test="${requestScope.order.getStatus() == 1}">
            <section class="container pt-4">
                <div class="row">
                    <div class="col">
                        <button class="w-100 btn btn-primary mb-2" formaction="UpdateOrderStatusServlet" type="submit" name="orderId" value="${requestScope.order.getId()}">Afsend tilbud</button>
                    </div>
                    <div class="col">
                        <button class="w-100 btn btn-danger" formaction="RemoveEmployeeOrderServlet" type="submit" name="orderId" value="${requestScope.order.getId()}" formnovalidate>Annuller ordre</button>
                    </div>
                </div>
            </section>
        </c:if>
        <c:if test="${requestScope.order.getStatus() == 2}">
            <section class="container pt-4">
                <div class="row">
                    <div class="col">
                        <button class="w-100 btn btn-primary mb-2" formaction="ManuallyPayForOrderServlet" type="submit" name="orderId" value="${requestScope.order.getId()}">Kunde har betalt kontant/bankoverførsel</button>
                    </div>
                    <div class="col">
                        <button class="w-100 btn btn-danger" formaction="RemoveEmployeeOrderServlet" type="submit" name="orderId" value="${requestScope.order.getId()}" formnovalidate>Annuller ordre</button>
                    </div>
                </div>
            </section>
        </c:if>
        <c:if test="${requestScope.order.getStatus() == 3}">
            <section class="container pt-4">
                <div class="row">
                    <div class="col">
                        <button class="w-100 btn btn-danger" formaction="RemoveEmployeeOrderServlet" type="submit" name="orderId" value="${requestScope.order.getId()}" formnovalidate>Fjern ordre fra systemet</button>
                    </div>
                </div>
            </section>
        </c:if>

    </form>
</t:loggedInAsEmployee>