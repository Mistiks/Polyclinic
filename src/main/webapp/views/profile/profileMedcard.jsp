<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*, java.text.*, java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>IPolyclinic</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../resources/theme1/css/blog.css" rel="stylesheet" type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Playfair&#43;Display:700,900&amp;display=swap" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>

<div class="container">
    <header class="blog-header py-3">
      <div class="row flex-nowrap justify-content-between align-items-center">
        <div class="col-4 pt-1">
          <a class="blog-header-logo text-dark" href="${pageContext.request.contextPath}/home">IPolyclinic</a>
        </div>
        <div class="col-4 d-flex justify-content-end align-items-center">
            <p>Welcome,<a class="p-2 link-secondary" href="${pageContext.request.contextPath}/profile/show">${currentUser.login}</a></p>
        </div>
      </div>
    </header>

  <div class="nav-scroller py-1 mb-2">
    <nav class="nav d-flex justify-content-between">
      <a class="p-2 link-secondary" href="show">Profile</a>
      <a class="p-2 link-secondary" href="update">Update</a>
      <a class="p-2 link-secondary" href="medcard">Medcard</a>
      <a class="p-2 link-secondary" href="ticket">Tickets</a>
      <a class="p-2 link-secondary" href="talon">Get ticket</a>
      <a class="p-2 link-secondary" href="${pageContext.request.contextPath}/logout">Logout</a>
    </nav>
  </div>
</div>

<div class="container light-style flex-grow-1 container-p-y">

    <h4 class="font-weight-bold py-3 mb-4">
      Medcard records information page
    </h4>

    <div class="card overflow-hidden">

        <c:choose>
            <c:when test="${medcard.size() > 0}">
                <table border="1" style = "text-align:center">
                    <tbody>
                            <tr>
                                <td width="15%" style="font-size:20px;"><strong>Doctor</strong></td>
                                <td width="15%" style="font-size:20px;"><strong>Disease</strong></td>
                                <td width="15%" style="font-size:20px;"><strong>Diagnose date</strong></td>
                                <td width="15%" style="font-size:20px;"><strong>Discharge date</strong></td>
                                <td width="40%" style="font-size:20px;"><strong>Notes</strong></td>
                            </tr>

                        <c:forEach items="${medcard}" var="item" varStatus="status">
                            <tr>
                                <td width="15%">${item.username}</td>
                                <td width="15%">${item.diseaseName}</td>
                                <td width="15%">${item.diagnoseDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}</td>
                                <td width="15%">${item.dischargeDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}</td>
                                <td width="40%">${item.notes}</td>
                            </tr>
                        </c:forEach>
                    </body>
                </table>
            </c:when>
            <c:otherwise>
                <p>You don't have any medcard records.</p>
            </c:otherwise>
        </c:choose>

    </div>
</div>

</body>
</html>