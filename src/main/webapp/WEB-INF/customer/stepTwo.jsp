<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:loggedOut>
    <%--  HERO START  --%>
    <section class="d-flex flex-column bg-dark text-center p-5">
        <small class="text-muted">Step 2 af 3</small>
        <h1 class="text-light m-auto fw-bold mb-1">Opret bruger</h1>
        <p class="text-white-50 mb-0">For at modtage et tilbud skal du oprette dig i systemet</p>
    </section>
    <%--  HERO END  --%>
    <form method="post" class="mb-0">
        <div class="container-fluid container d-flex flex-column align-items-center justify-content-center mt-5">
            <div class="d-flex flex-column w-100" style="max-width: 600px">
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="name@example.com" required>
                </div>
                <div class="mb-3">
                    <label for="firstName" class="form-label">Fornavn</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Henning" required>
                </div>
                <div class="mb-3">
                    <label for="lastName" class="form-label">Efternavn</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Larsen" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Adgangskode</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
            </div>
        </div>
        <div class="position-fixed bottom-0 w-100 d-flex justify-content-center mb-4 gap-3">
            <button formaction="Navigator" type="submit" class="btn btn-secondary" name="route" value="customer_stepOne" formnovalidate>Tilbage</button>
            <button formaction="SaveUser" type="submit" class="btn btn-primary">Indsend forespørgsel</button>
        </div>
    </form>
</t:loggedOut>