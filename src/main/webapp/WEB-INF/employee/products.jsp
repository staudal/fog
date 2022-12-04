<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:loggedInAsEmployee>
    <section class="d-flex flex-column bg-dark text-center p-5">
        <h1 class="text-light m-auto fw-bold mb-1">Produkter</h1>
    </section>

    <section class="container p-4">
        <div class="d-flex">
            <form action="CreateNewProductServlet" method="post">
                <div class="mb-3">
                    <label for="productName" class="form-label">Produktnavn</label>
                    <input type="text" class="form-control" id="productName" name="productName">
                </div>
                <div class="mb-3">
                    <label for="price" class="form-label">Meterpris</label>
                    <input type="number" class="form-control" id="price" name="mPrice">
                </div>
                <div class="mb-3">
                    <label for="length" class="form-label">Længde</label>
                    <input type="number" class="form-control" id="length" name="length">
                </div>
                <button class="btn btn-primary" type="submit">Opret</button>
            </form>
        </div>
    </section>
</t:loggedInAsEmployee>