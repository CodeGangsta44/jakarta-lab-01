<%--
  Created by IntelliJ IDEA.
  User: windows
  Date: 05.06.22
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create connection</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%@ include file="../common/header.jsp" %>
<div class="container container-md">
    <div class="row" style="padding-top: 20px">
        <div class="col">
            <div class="alert alert-success" role="alert">
                <h5 style="margin-bottom: 0">Execution configuration</h5>
            </div>
            <div class="card" style="padding: 10px">
                <form action="connection" method="POST" style="margin-bottom: 0">
                    <input name="id" type="hidden" value="${connection.id}">
                    <input name="method" type="hidden" value="${connection.id ne null ? 'put' : 'post'}">
                    <div class="input-group mb-3">
                        <input name="connectionName" type="text" class="form-control"
                               placeholder="Name of the connection"
                               aria-label="Name of the connection"
                               value="${fn:escapeXml(connection.connectionName)}">
                    </div>
                    <div class="input-group mb-3">
                        <input name="username" type="text" class="form-control" placeholder="Username"
                               aria-label="Username" value="${connection.username ? fn:escapeXml(connection.username) : fn:escapeXml(username)}">
                        <span class="input-group-text">@</span>
                        <input name="host" type="text" class="form-control" placeholder="Host address"
                               aria-label="Host address" value="${fn:escapeXml(connection.host)}">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text">Port</span>
                        <input name="port" type="number" class="form-control" aria-label="Port"
                               value="${connection.port ne null ? connection.port : 22}">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text">Password</span>
                        <input name="password" autocomplete="one-time-code" type="password" class="form-control"
                               placeholder="********" aria-label="Password" value="${fn:escapeXml(connection.password)}">
                    </div>
                    <div class="input-group">
                        <button class="btn btn-outline-success" type="submit">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
