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
                        <label for="carportWidth" class="form-label">Bredde af carport</label>
                        <select class="form-select" id="carportWidth" required name="carportWidth">
                            <option selected disabled value="0">Vælg bredde</option>
                            <option value="240">240 cm</option>
                            <option value="270">270 cm</option>
                            <option value="300">300 cm</option>
                            <option value="330">330 cm</option>
                            <option value="360">360 cm</option>
                            <option value="390">390 cm</option>
                            <option value="420">420 cm</option>
                            <option value="450">450 cm</option>
                            <option value="480">480 cm</option>
                            <option value="510">510 cm</option>
                            <option value="540">540 cm</option>
                            <option value="570">570 cm</option>
                            <option value="600">600 cm</option>
                        </select>
                    </div>
                    <div class="position-relative">
                        <label for="carportLength" class="form-label">Længde af carport</label>
                        <select class="form-select" id="carportLength" required name="carportLength">
                            <option selected disabled value="0">Vælg længde</option>
                            <option value="240">240 cm</option>
                            <option value="270">270 cm</option>
                            <option value="300">300 cm</option>
                            <option value="330">330 cm</option>
                            <option value="360">360 cm</option>
                            <option value="390">390 cm</option>
                            <option value="420">420 cm</option>
                            <option value="450">450 cm</option>
                            <option value="480">480 cm</option>
                            <option value="510">510 cm</option>
                            <option value="540">540 cm</option>
                            <option value="570">570 cm</option>
                            <option value="600">600 cm</option>
                            <option value="630">630 cm</option>
                            <option value="660">660 cm</option>
                            <option value="690">690 cm</option>
                            <option value="720">720 cm</option>
                            <option value="750">750 cm</option>
                            <option value="780">780 cm</option>
                        </select>
                    </div>
                    <div class="position-relative">
                        <label for="shedWidth" class="form-label">Bredde af skur</label>
                        <select class="form-select" id="shedWidth" name="shedWidth">
                            <option selected value="0">Intet skur</option>
                            <option value="1">Hele carportens bredde</option>
                            <option value="2">Halvdelen af carportens bredde</option>
                        </select>
                    </div>
                    <div class="position-relative">
                        <label for="shedLength" class="form-label">Længde af skur</label>
                        <select class="form-select" id="shedLength" name="shedLength">
                            <option selected value="0">Intet skur</option>
                            <option value="150">150 cm</option>
                            <option value="180">180 cm</option>
                            <option value="210">210 cm</option>
                            <option value="240">240 cm</option>
                            <option value="270">270 cm</option>
                            <option value="300">300 cm</option>
                            <option value="330">330 cm</option>
                            <option value="360">360 cm</option>
                            <option value="390">390 cm</option>
                            <option value="420">420 cm</option>
                            <option value="450">450 cm</option>
                            <option value="480">480 cm</option>
                            <option value="510">510 cm</option>
                            <option value="540">540 cm</option>
                            <option value="570">570 cm</option>
                            <option value="600">600 cm</option>
                            <option value="630">630 cm</option>
                            <option value="660">660 cm</option>
                            <option value="690">690 cm</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Modtag tilbud</button>
                </div>
            </div>
        </div>
    </form>
</t:loggedInAsCustomer>