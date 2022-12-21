<%@tag description="Simple PageTemplate Tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-light" style="background: #144985;">
    <form action="Navigator" class="mb-0 w-100" method="post">
        <div class="container-fluid container d-flex justify-content-between align-items-center">
            <button class="btn fs-4 fw-bold text-light" name="route" value="employeeOverview" type="submit">Fog</button>
            <div class="d-flex">
                <button class="btn text-light nav-link" name="route" value="employeeOrders">Ordre</button>
                <button class="btn text-light nav-link" name="route" value="employeeCustomers">Kunder</button>
            </div>
            <button class="btn btn-outline-light" name="route" value="logout">Log ud</button>
        </div>
    </form>
</nav>
<jsp:doBody/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>