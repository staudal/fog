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
            <h2 class="fs-5 fw-bold mb-3">Stykliste over byggeprojekt</h2>

            <!-- carport -->
            <table class="table table-bordered border-dark table-striped">
                <thead class="table-dark text-center fw-bold"><tr><td colspan="7">Carport</td></tr></thead>
                <thead class="table-primary border-dark text-center fw-bold">
                <tr>
                    <td colspan="7">Træ (carport)</td>
                </tr>
                </thead>
                <thead class="table-primary border-dark fw-bold">
                    <tr>
                        <td>Antal</td>
                        <td>Vare</td>
                        <td>Længde</td>
                        <td>Stykpris</td>
                        <td>Beskrivelse</td>
                        <td>Pris</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.woodsCarport}" var="wood">
                        <tr>
                            <td>${wood.getQuantity()}x</td>
                            <td>${wood.getName()}</td>
                            <td>${wood.getLength()} cm</td>
                            <td>${wood.getPrice()} DKK</td>
                            <td>${wood.getDescription()}</td>
                            <td>${wood.getPrice() * wood.getQuantity()} DKK</td>
                        </tr>
                    </c:forEach>
                    <tr class="fw-bold">
                        <td colspan="5" class="text-center">Pris for træ (til carport)</td>
                        <td>${requestScope.woodsCarportPrice} DKK</td>
                    </tr>
                </tbody>
                <thead class="table-primary border-dark text-center fw-bold">
                <tr>
                    <td colspan="7">Beslag & skruer (carport)</td>
                </tr>
                </thead>
                <thead class="table-primary border-dark fw-bold">
                <tr>
                    <td>Antal</td>
                    <td>Vare</td>
                    <td>Længde</td>
                    <td>Stykpris</td>
                    <td>Beskrivelse</td>
                    <td>Pris</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.screwsCarport}" var="screw">
                    <tr>
                        <td>${screw.getQuantity()}x</td>
                        <td>${screw.getName()}</td>
                        <td class="table-dark"></td>
                        <td>${screw.getPrice()} DKK</td>
                        <td>${screw.getDescription()}</td>
                        <td>${screw.getPrice() * screw.getQuantity()} DKK</td>
                    </tr>
                </c:forEach>
                <tr class="fw-bold">
                    <td colspan="5" class="text-center">Pris for beslag & skruer (til carport)</td>
                    <td>${requestScope.screwsCarportPrice} DKK</td>
                </tr>
                </tbody>
                <tfoot>
                    <tr class="table-dark border-dark fw-bold">
                        <td colspan="5" class="text-center">Pris i alt (carport)</td>
                        <td>${requestScope.woodsCarportPrice + requestScope.screwsCarportPrice} DKK</td>
                    </tr>
                </tfoot>
            </table>

            <!-- skur -->
            <table class="table table-bordered border-dark table-striped">
                <thead class="table-dark text-center fw-bold"><tr><td colspan="7">Skur</td></tr></thead>
                <thead class="table-primary border-dark text-center fw-bold">
                <tr>
                    <td colspan="7">Træ (skur)</td>
                </tr>
                </thead>
                <thead class="table-primary border-dark fw-bold">
                <tr>
                    <td>Antal</td>
                    <td>Vare</td>
                    <td>Længde</td>
                    <td>Stykpris</td>
                    <td>Beskrivelse</td>
                    <td>Pris</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.woodsShed}" var="wood">
                    <tr>
                        <td>${wood.getQuantity()}x</td>
                        <td>${wood.getName()}</td>
                        <td>${wood.getLength()} cm</td>
                        <td>${wood.getPrice()} DKK</td>
                        <td>${wood.getDescription()}</td>
                        <td>${wood.getPrice() * wood.getQuantity()} DKK</td>
                    </tr>
                </c:forEach>
                <tr class="fw-bold">
                    <td colspan="5" class="text-center">Pris for træ (til skur)</td>
                    <td>${requestScope.woodsShedPrice} DKK</td>
                </tr>
                </tbody>
                <thead class="table-primary border-dark text-center fw-bold">
                <tr>
                    <td colspan="7">Beslag & skruer (skur)</td>
                </tr>
                </thead>
                <thead class="table-primary border-dark fw-bold">
                <tr>
                    <td>Antal</td>
                    <td>Vare</td>
                    <td>Længde</td>
                    <td>Stykpris</td>
                    <td>Beskrivelse</td>
                    <td>Pris</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.screwsShed}" var="screw">
                    <tr>
                        <td>${screw.getQuantity()}x</td>
                        <td>${screw.getName()}</td>
                        <td class="table-dark"></td>
                        <td>${screw.getPrice()} DKK</td>
                        <td>${screw.getDescription()}</td>
                        <td>${screw.getPrice() * screw.getQuantity()} DKK</td>
                    </tr>
                </c:forEach>
                <tr class="fw-bold">
                    <td colspan="5" class="text-center">Pris for beslag & skruer (til skur)</td>
                    <td>${requestScope.screwsShedPrice} DKK</td>
                </tr>
                </tbody>
                <tfoot>
                <tr class="table-dark border-dark fw-bold">
                    <td colspan="5" class="text-center">Pris i alt (skur)</td>
                    <td>${requestScope.woodsShedPrice + requestScope.screwsShedPrice} DKK</td>
                </tr>
                </tfoot>
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