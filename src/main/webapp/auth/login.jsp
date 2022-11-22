<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%@ include file="../common/header.jsp" %>
<div class="container container-md" style="padding-top: 30px">
    <div class="row">
        <div class="col">
            <div class="alert alert-success" role="alert">
                <h5 style="margin-bottom: 0">Login form</h5>
            </div>
            <div class="card" style="padding: 10px">
                <c:if test="${afterRegistration}">
                    <div class="alert alert-success" role="alert">
                        You was successfully registered! Sign it to continue.
                    </div>
                </c:if>
                <form action="j_security_check" method="POST" style="margin-bottom: 0">
                    <div class="input-group mb-3">
                        <span class="input-group-text">Username</span>
                        <input name="j_username" type="text"
                               class="form-control" placeholder="Username" aria-label="Username">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text">Password</span>
                        <input name="j_password" autocomplete="one-time-code" type="password"
                               class="form-control" placeholder="********" aria-label="Password">
                    </div>
                    <c:if test="${failedAttempt}">
                        <div class="alert alert-danger" role="alert">
                            Incorrect username or password. Please, check the data and try again.
                        </div>
                    </c:if>
                    <div class="input-group">
                        <button class="btn btn-success" type="submit">Login</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
