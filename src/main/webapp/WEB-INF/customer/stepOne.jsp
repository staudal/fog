<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:loggedOut>
    <%--  HERO START  --%>
    <section class="d-flex flex-column bg-dark text-center p-5">
        <small class="text-muted">Step 1 af 3</small>
        <h1 class="text-light m-auto fw-bold mb-1">Angiv mål på carport</h1>
        <p class="text-white-50 mb-0">Indtast dine ønskede mål på carporten nedenfor</p>
    </section>
    <%--  HERO END  --%>
    <form action="SaveDimensions" method="post" class="mb-0">
        <div class="container-fluid container d-flex flex-column align-items-center justify-content-center mt-5">
            <div class="d-flex flex-column w-100 gap-4" style="max-width: 600px">
                <div class="d-flex gap-4">
                    <div class="w-100">
                        <label for="width" class="form-label">Bredde (i cm)</label>
                        <input id="width" type="number" class="form-control" placeholder="Angiv bredde" name="carportWidth" value="${sessionScope.carportWidth}" required>
                    </div>
                    <div class="w-100">
                        <label for="length" class="form-label">Længde (i cm)</label>
                        <input id="length" type="number" class="form-control" placeholder="Angiv længde" name="carportLength" value="${sessionScope.carportLength}" required>
                    </div>
                </div>
                <div class="d-flex gap-4">
                    <div class="w-100">
                        <label for="height" class="form-label">Højde (i cm)</label>
                        <input id="height" type="number" class="form-control" placeholder="Angiv højde" name="carportHeight" value="${sessionScope.carportHeight}" required>
                    </div>
                    <div class="w-100">
                        <label for="slope" class="form-label">Taghældning (i grader)</label>
                        <input id="slope" type="number" class="form-control" placeholder="Angiv tagets hældning" name="carportSlope" value="${sessionScope.carportSlope}" required>
                    </div>
                </div>
            </div>
        </div>
        <div class="position-fixed bottom-0 w-100 d-flex justify-content-center mb-4">
            <button type="submit" class="btn btn-primary">Videre</button>
        </div>
    </form>
</t:loggedOut>