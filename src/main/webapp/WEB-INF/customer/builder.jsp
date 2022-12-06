<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:loggedInAsCustomer>
    <%--  HERO START  --%>
    <section class="d-flex flex-column bg-dark text-center p-5">
        <h1 class="text-light m-auto fw-bold mb-1">QuickBuild</h1>
        <p class="text-white-50 mb-0">Indtast mål på carport nedenfor</p>
    </section>
    <%--  HERO END  --%>
    <form action="BuilderServlet" method="post" class="mb-0 needs-validation">
        <div class="container-fluid container d-flex flex-column align-items-center justify-content-center mt-5">
            <div class="gap-4 w-100" style="max-width: 600px">
                <div class="d-flex flex-column gap-3">
                    <div class="position-relative">
                        <label for="width" class="form-label">Bredde af carport <em>(max 600 cm)</em></label>
                        <div class="w-100">
                            <input id="width" type="text" class="form-control ${requestScope.widthErrorClass}" name="carportWidth" value="${requestScope.carportWidth}" required>
                        </div>
                    </div>
                    <div class="position-relative">
                        <label for="length" class="form-label">Længde af carport <em>(max 800 cm)</em></label>
                        <div class="w-100">
                            <input id="length" type="text" class="form-control ${requestScope.lengthErrorClass}" value="${requestScope.carportLength}" name="carportLength" required>
                        </div>
                    </div>
                    <div class="position-relative">
                        <label for="height" class="form-label">Højde af carport <em>(max 390 cm)</em></label>
                        <div class="w-100">
                            <input id="height" type="text" class="form-control ${requestScope.heightErrorClass}" value="${requestScope.carportHeight}" name="carportHeight" required>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Modtag tilbud</button>
                </div>
            </div>
        </div>
    </form>
</t:loggedInAsCustomer>