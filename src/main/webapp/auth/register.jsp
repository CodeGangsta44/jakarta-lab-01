<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%@ include file="../common/header.jsp" %>
<div class="container container-md" style="padding-top: 30px">
    <div class="row">
        <div class="col">
            <div class="alert alert-success" role="alert">
                <h5 style="margin-bottom: 0">Registration form</h5>
            </div>
            <div class="card" style="padding: 10px">
                <form action="register" method="POST" style="margin-bottom: 0">
                    <div class="input-group mb-3">
                        <span class="input-group-text">Username</span>
                        <input name="username" type="text"
                               class="form-control ${status == 'NOT_UNIQUE_USERNAME' ? 'is-invalid' : ''}"
                               placeholder="Username" aria-label="Username"
                               value="${fn:escapeXml(username)}" aria-describedby="validationUsernameFeedback">
                        <div id="validationUsernameFeedback" class="invalid-feedback">
                            Used username is not unique. Please, choose another one.
                        </div>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text">Password</span>
                        <input name="password" autocomplete="one-time-code" type="password"
                               class="form-control ${status == 'PASSWORDS_NOT_EQUALS' ? 'is-invalid' : ''}"
                               placeholder="********" aria-label="Password" value="${fn:escapeXml(password)}">
                        <div id="validationPasswordFeedback" class="invalid-feedback">
                            Passwords should match. Please check entered data.
                        </div>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text">Repeat password</span>
                        <input name="repeatedPassword" autocomplete="one-time-code" type="password" class="form-control"
                               placeholder="********" aria-label="Repeat password" value="${fn:escapeXml(repeatedPassword)}">
                    </div>
                    <div class="input-group">
                        <button class="btn btn-success" type="submit">Register!</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
