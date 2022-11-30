<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:loggedOut>
  <div class="d-flex justify-content-center align-items-center w-100" style="height: calc(100vh - 54px);">
    <div class="w-100 d-flex flex-column justify-content-center" style="max-width: 600px;">
      <h2 class="mb-4 text-center">Opret Fog-konto</h2>
      <form class="mb-0 needs-validation d-flex flex-column" method="post" novalidate>
        <div class="mb-3 row position-relative">
          <label for="firstName" class="col-sm-2 col-form-label">Fornavn</label>
          <div class="col-sm-10">
            <input id="firstName" type="text" class="form-control" name="firstName" required>
          </div>
        </div>
        <div class="mb-3 row position-relative">
          <label for="lastName" class="col-sm-2 col-form-label">Efternavn</label>
          <div class="col-sm-10">
            <input id="lastName" type="text" class="form-control" name="lastName" required>
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
            <input id="password" type="password" class="form-control" name="password" required>
          </div>
        </div>
        <button formaction="CreateAccountServlet" class="btn btn-primary w-100 text-light mb-2" type="submit">Opret konto</button>
        <button formaction="Navigator" name="route" value="login" class="btn btn-light" type="submit">Gå til login i stedet</button>
      </form>
    </div>
  </div>
</t:loggedOut>