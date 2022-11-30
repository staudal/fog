<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:loggedOut>
    <div class="d-flex justify-content-center align-items-center w-100" style="height: calc(100vh - 54px);">
        <div class="w-100 d-flex flex-column justify-content-center" style="max-width: 600px;">
            <h2 class="mb-4 text-center">Log ind</h2>
            <form class="mb-0 needs-validation d-flex flex-column" method="post" novalidate>
                <div class="mb-3 row">
                    <label for="role" class="col-sm-2 col-form-label">Rolle</label>
                    <div class="col-sm-10">
                        <select id="role" class="form-select w-100" name="role" required>
                            <option selected value="customer">Kunde</option>
                            <option value="employee">Medarbejder</option>
                            <option value="admin">Administrator</option>
                        </select>
                    </div>
                </div>
                <div class="mb-3 row position-relative">
                    <label for="email" class="col-sm-2 col-form-label">Email</label>
                    <div class="col-sm-10">
                        <input id="email" type="email" class="form-control ${requestScope.emailErrorClass}" name="email" required>
                        ${requestScope.emailErrorMessage}
                    </div>
                </div>
                <div class="mb-3 row position-relative">
                    <label for="password" class="col-sm-2 col-form-label">Kode</label>
                    <div class="col-sm-10">
                        <input id="password" type="password" class="form-control ${requestScope.passwordErrorClass}" name="password" required>
                            ${requestScope.passwordErrorMessage}
                    </div>
                </div>
                <button formaction="LoginServlet" class="btn btn-primary w-100 text-light mb-2" type="submit">Log ind</button>
                <button formaction="Navigator" name="route" type="submit" value="createAccount" class="btn btn-light">Har du ikke en konto?</button>
            </form>
        </div>
    </div>
</t:loggedOut>