<%--
  Created by IntelliJ IDEA.
  User: windows
  Date: 05.06.22
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Home page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<div class="container container-md" style="padding-top: 30px">
    <div class="alert alert-success" role="alert" style="margin-bottom: 0">
        History entries of "${fn:escapeXml(connection.connectionName)}"
    </div>

    <div class="row" style="padding-top: 20px">
        <div class="col">
            <div class="card" style="padding: 10px;">
                <div class="accordion" id="accordion" style="padding-top: 5px;">
                    <c:forEach items="${entries}" var="entry">
                        <div class="accordion-item">
                            <h5 class="accordion-header" id="heading-${entry.id}">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#collapse-${entry.id}" aria-expanded="false"
                                        aria-controls="collapse-${entry.id}">
                                    <fmt:formatDate type="both" value="${entry.timestamp}"/>
                                    <span style="font-size: medium; margin-left: 10px" class="badge bg-dark">${fn:escapeXml(entry.command)}</span>
                                </button>
                            </h5>
                            <div id="collapse-${entry.id}" class="accordion-collapse collapse"
                                 aria-labelledby="heading-${entry.id}"
                                 data-bs-parent="#accordion">
                                <div class="accordion-body">
                                    <div class="card bg-dark mb-3" style="color: #15af15; font-family: monospace">
                                        <div class="card-body">
                                            <p class="card-text">${entry.result}</p>
                                        </div>
                                    </div>
                                    <form method="post" action="history" style="margin: 0">
                                        <input type="hidden" name="id" value="${connection.id}">
                                        <input type="hidden" name="historyEntryId" value="${entry.id}">
                                        <input type="hidden" name="method" value="delete">
                                        <button class="btn btn-danger" type="submit" style="margin-right: 10px">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                 fill="currentColor"
                                                 class="bi bi-trash" viewBox="0 0 16 16">
                                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                                <path fill-rule="evenodd"
                                                      d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                            </svg>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
