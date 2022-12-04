<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:loggedInAsEmployee>
    <section class="d-flex flex-column bg-dark text-center p-5">
        <h1 class="text-light m-auto fw-bold mb-1">Kunder</h1>
    </section>

    <section class="container p-4">
        <table class="table">
            <thead>
            <tr>
                <th>KundeID</th>
                <th>Fornavn</th>
                <th>Efternavn</th>
                <th>Email</th>
                <th>Antal ordre</th>
                <th>Se ordre</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.customers}" var="customer">
                <tr>
                    <td>${customer.getId()}</td>
                    <td>${customer.getFirstName()}</td>
                    <td>${customer.getLastName()}</td>
                    <td>${customer.getEmail()}</td>
                    <td>${customer.getNumberOfOrders()}</td>
                    <td>
                        <form action="LoadCustomerOrdersServlet" method="post" class="mb-0">
                            <button class="btn btn-primary badge" name="customerId" value="${customer.getId()}" type="submit">Se ordre</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>
</t:loggedInAsEmployee>