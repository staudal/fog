<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:loggedInAsEmployee>
  <%--  HERO START  --%>
  <section class="d-flex flex-column bg-dark text-center p-5">
    <h1 class="text-light m-auto fw-bold mb-1">God arbejdslyst, ${sessionScope.employee.getFirstName()}!</h1>
  </section>
  <%--  HERO END  --%>
</t:loggedInAsEmployee>