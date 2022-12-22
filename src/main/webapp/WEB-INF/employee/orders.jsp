<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:loggedInAsEmployee>
    <%--  HERO START  --%>
    <section class="d-flex flex-column bg-dark text-center p-5">
        <h1 class="text-light m-auto fw-bold mb-1">Ordre</h1>
    </section>
    <%--  HERO END  --%>
    <form action="LoadEmployeeOrderServlet" method="post" class="mb-0 mt-4 mb-4">
        <div class="container">
            <div class="row gap-4">
                <div class="col border p-4 bg-light rounded">
                    <h2 class="text-center fs-4 mb-4">Forespørgsel modtaget</h2>
                    <div class="d-flex flex-column gap-2">
                        <c:forEach items="${sessionScope.orders.values()}" var="order">
                            <c:if test="${order.getStatus() == 1}">
                                <button class="btn btn-primary w-100" type="submit" name="orderId" value="${order.getId()}">${order.getId()}</button>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
                <div class="col border p-4 bg-light rounded">
                    <h2 class="text-center fs-4 mb-4">Tilbud afsendt</h2>
                    <div class="d-flex flex-column gap-2">
                        <c:forEach items="${sessionScope.orders.values()}" var="order">
                            <c:if test="${order.getStatus() == 2}">
                                <button class="btn btn-primary w-100" type="submit" name="orderId" value="${order.getId()}">${order.getId()}</button>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
                <div class="col border p-4 bg-light rounded">
                    <h2 class="text-center fs-4 mb-4">Tilbud betalt</h2>
                    <div class="d-flex flex-column gap-2">
                        <c:forEach items="${sessionScope.orders.values()}" var="order">
                            <c:if test="${order.getStatus() == 3}">
                                <button class="btn btn-primary w-100" type="submit" name="orderId" value="${order.getId()}">${order.getId()}</button>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </form>
</t:loggedInAsEmployee>