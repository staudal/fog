<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:loggedInAsCustomer>
    <%--  HERO START  --%>
    <section class="d-flex flex-column bg-dark text-center p-5">
        <h1 class="text-light m-auto fw-bold mb-1">Ordreoversigt</h1>
    </section>

    <c:if test="${requestScope.order.getStatus() == 1}">
        <section class="pt-4 container">
            <div class="progress">
                <div class="progress-bar progress-bar-animated progress-bar-striped" role="progressbar" style="width: 33%;" aria-valuenow="33" aria-valuemin="0" aria-valuemax="100">33%</div>
            </div>
        </section>
    </c:if>
    <c:if test="${requestScope.order.getStatus() == 2}">
        <section class="pt-4 container">
            <div class="progress">
                <div class="progress-bar progress-bar-animated progress-bar-striped" role="progressbar" style="width: 66%;" aria-valuenow="66" aria-valuemin="0" aria-valuemax="100">66%</div>
            </div>
        </section>
    </c:if>
    <c:if test="${requestScope.order.getStatus() == 3}">
        <section class="pt-4 container">
            <div class="progress">
                <div class="progress-bar progress-bar-animated progress-bar-striped" role="progressbar" style="width: 100%;" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100">100%</div>
            </div>
        </section>
    </c:if>

    <section class="d-flex container justify-content-between align-items-stretch pt-4 gap-4">
        <div class="d-flex flex-column bg-light p-3 rounded w-100 border">
            <h2 class="fs-5 fw-bold mb-3">Kunde</h2>
            <p class="mb-1"><strong>Navn:</strong> ${sessionScope.customer.getFirstName()} ${sessionScope.customer.getLastName()}</p>
            <p class="mb-0"><strong>Email:</strong> ${sessionScope.customer.getEmail()}</p>
        </div>
        <div class="d-flex flex-column bg-light p-3 rounded w-100 border">
            <h2 class="fs-5 fw-bold mb-3">Ordre</h2>
            <p class="mb-0"><strong>Id:</strong> ${requestScope.order.getId()}</p>
            <c:if test="${requestScope.order.getStatus() == 1}">
                <p class="mb-0"><strong>Status:</strong> Afventer tilbud</p>
            </c:if>
            <c:if test="${requestScope.order.getStatus() == 2}">
                <p class="mb-0"><strong>Status:</strong> Tilbud modtaget</p>
            </c:if>
            <c:if test="${requestScope.order.getStatus() == 3}">
                <p class="mb-0"><strong>Status:</strong> Tilbud betalt</p>
            </c:if>
            <c:if test="${requestScope.order.getDiscountPrice() == 0}">
                <p class="mb-0"><strong>Tilbudspris:</strong> Bliver beregnet af medarbejder</p>
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
                        <th>Højde</th>
                        <th>Hældning</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${requestScope.order.getWidth()} cm</td>
                        <td>${requestScope.order.getLength()} cm</td>
                        <td>${requestScope.order.getHeight()} cm</td>
                        <td>${requestScope.order.getSlope()}°</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </section>
    <c:if test="${requestScope.order.getStatus() == 3}">
        <section class="d-flex container flex-column justify-content-between align-items-center pt-4">
            <div class="w-100 bg-light justify-content-center rounded p-3 border">
                <table class="table table-bordered mb-0">
                    <thead>
                    <tr>
                        <th colspan="4" class="text-center table-primary">Stykliste</th>
                    </tr>
                    </thead>
                    <thead>
                    <tr>
                        <th>Vare</th>
                        <th>Længde</th>
                        <th>Antal</th>
                        <th>Meterpris</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.products}" var="product">
                        <tr>
                            <td>${product.getName()}</td>
                            <td>${product.getLength()}</td>
                            <td>${product.getQuantity()}</td>
                            <td>${product.getPrice()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </c:if>
    <c:if test="${requestScope.order.getStatus() == 1}">
        <section class="container pt-4 gap-4">
            <div class="row">
                <div class="col"><button class="btn btn-primary w-100" disabled>Gå til betaling</button></div>
                <div class="col"><form action="RemoveCustomerOrderServlet" method="post" class="mb-0 w-100">
                    <button class="w-100 btn btn-danger" type="submit" name="orderId" value="${requestScope.order.getId()}">Annuller ordre</button>
                </form></div>
            </div>
        </section>
    </c:if>
    <c:if test="${requestScope.order.getStatus() == 2}">
        <section class="container pt-4 gap-4">
            <div class="row">
                <div class="col"><form action="PayForOrderServlet" method="post" class="mb-0">
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-primary w-100" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Gå til betaling
                    </button>

                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="mb-3">
                                        <label for="cardNumber" class="form-label">Kortnummer</label>
                                        <input type="text" class="form-control" id="cardNumber" placeholder="1234-5678-8473-2843" required>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <label for="expirationDate" class="form-label">Udløbsdato</label>
                                            <input type="text" id="expirationDate" class="form-control" placeholder="07/26" required>
                                        </div>
                                        <div class="col">
                                            <label for="pin" class="form-label">PIN</label>
                                            <input type="text" id="pin" class="form-control" placeholder="374" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Luk vindue</button>
                                    <button type="submit" class="btn btn-primary" name="orderId" value="${requestScope.order.getId()}">Betal ordre</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form></div>
                <div class="col"><form action="RemoveCustomerOrderServlet" method="post" class="mb-0">
                    <button class="w-100 btn btn-danger" type="submit" name="orderId" value="${requestScope.order.getId()}">Annuller ordre</button>
                </form></div>
            </div>
        </section>
    </c:if>
</t:loggedInAsCustomer>