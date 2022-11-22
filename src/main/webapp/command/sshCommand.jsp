<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SSH Command</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%@ include file="../common/header.jsp" %>
<div class="container container-md" style="padding-top: 10px">
    <div class="row" style="padding-top: 20px">
        <div class="col">
            <div class="alert alert-success" role="alert">
                <h5 style="margin-bottom: 0">Execution configuration</h5>
            </div>
            <c:set value="${connection.id ne null}" var="existingConnection"/>
            <div class="card" style="padding: 10px">
                <c:if test="${existingConnection}">
                    <div class="card-header">
                            ${fn:escapeXml(connection.connectionName)}
                    </div>
                </c:if>
                <div class="card-body">
                    <form action="ssh-command" method="POST" style="margin-bottom: 0">
                        <input type="hidden" name="id" value="${connection.id}">
                        <input type="hidden" name="connectionName" value="${fn:escapeXml(connection.connectionName)}">
                        <div class="input-group mb-3">
                            <input ${existingConnection ? 'readonly' : ''} name="username" type="text"
                                                                           class="form-control" placeholder="Username"
                                                                           aria-label="Username"
                                                                           value="${fn:escapeXml(connection.username)}">
                            <span class="input-group-text">@</span>
                            <input ${existingConnection ? 'readonly' : ''} name="host" type="text" class="form-control"
                                                                           placeholder="Host address"
                                                                           aria-label="Host address"
                                                                           value="${fn:escapeXml(connection.host)}">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">Port</span>
                            <input ${existingConnection ? 'readonly' : ''} name="port" type="number"
                                                                           class="form-control" aria-label="Port"
                                                                           value="${connection.port == null ? 22 : connection.port}">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">Password</span>
                            <input ${existingConnection ? 'readonly' : ''} name="password" autocomplete="one-time-code"
                                                                           type="password" class="form-control"
                                                                           placeholder="********" aria-label="Password"
                                                                           value="${fn:escapeXml(connection.password)}">
                        </div>
                        <div class="input-group">
                            <span class="input-group-text">Command</span>
                            <input name="command" type="text" class="form-control" placeholder="ls -l /"
                                   aria-label="Command">
                            <button class="btn btn-outline-success" type="submit">Execute!</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="alert alert-success" role="alert">
                <h5 style="margin-bottom: 0">Execution result</h5>
            </div>
            <div class="card bg-dark mb-3" style="min-height: 74.5%; color: #15af15; font-family: monospace">
                <div class="card-body">
                    <p class="card-text">${result == null ? 'No results yet...' : result}</p>
                </div>
            </div>
        </div>
    </div>
</div>
<br/>
</div>
</body>
</html>
