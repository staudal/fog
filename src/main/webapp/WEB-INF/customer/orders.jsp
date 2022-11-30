<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:loggedInAsCustomer>
    <%--  HERO START  --%>
    <section class="d-flex flex-column bg-dark text-center p-5">
        <h1 class="text-light m-auto fw-bold mb-1">Mine ordre</h1>
    </section>
    <%--  HERO END  --%>
    <form action="LoadCustomerOrderServlet" method="post" class="mb-0">
        <div class="d-flex flex-column gap-4 mt-4 justify-content-center align-items-center">
            <c:forEach items="${sessionScope.orders.values()}" var="order">
                <button class="btn btn-primary" type="submit" style="max-width: 600px;" name="orderId" value="${order.getId()}">${order.getId()}</button>
            </c:forEach>
        </div>
    </form>
</t:loggedInAsCustomer>