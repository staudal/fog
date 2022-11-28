<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:loggedOut>
    <div class="d-flex justify-content-center align-items-center w-100 vh-100">
        <form action="Navigator" method="post">
            <button class="btn btn-primary" name="route" value="customer_stepOne">Gå til PortBuilder</button>
        </form>
    </div>
</t:loggedOut>