<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*, java.text.*, model.User, java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>IPolyclinic</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/theme1/css/blog.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Playfair&#43;Display:700,900&amp;display=swap" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
    function createVariable() {
    var doctorId;
    }
    </script>

    <script>
    function sendForm() {
    $('#response').text("");
    var methodType;
    var $activeTab = $('.tab-content .tab-pane.active');
    var activeId = $activeTab.attr('id');
    var sendData = new Object();
    sendData.specialization = $("#specialization :selected").val();
    sendData.fio = $("#fio :selected").val();
    sendData.date = $("#date :selected").val();
    sendData.time = $("#time :selected").val();
    if ( activeId != "specialization") {
        sendData.doctorId = $("#fio :selected").attr('doctorId');
    }
        $.ajax({
          url: "${pageContext.request.contextPath}/talon/" + activeId,
          type: "post",
          dataType: "json",
          data: JSON.stringify(sendData),
          contentType: 'application/json',
          statusCode: {
            201: function() {
                $('#response').text("Talon saved!");
                }
            },
            success: function(json, textStatus) {

                if (activeId == "specialization" || activeId == "doctor" || activeId == "date") {
                    $('#response').text("Info received!");
                }

                if (activeId == "specialization") {
                    $.each(json, function(i, value) {
                        $('#fio').append($('<option>').text(value.fio).attr('doctorId', value.doctorId));
                    });
                }

                if (activeId == "doctor") {
                    $.each(json, function(i, value) {
                        $('#dateSelect').append($('<option>').text(value));
                    });
                }

                if (activeId == "date") {
                    $.each(json, function(i, value) {
                        $('#timeSelect').append($('<option>').text(value));
                    });
                }
            },

            error: function(json, textStatus) {
                $('#response').text("Error " + json.status + " occurred!");
            }
        })
    }
    </script>

    <title>IPolyclinic</title>
  </head>
  <body>

  <div class="container">
    <header class="blog-header py-3">
      <div class="row flex-nowrap justify-content-between align-items-center">
        <div class="col-4 pt-1">
          <a class="blog-header-logo text-dark" href="home">IPolyclinic</a>
        </div>
        <div class="col-4 d-flex justify-content-end align-items-center">
            <c:choose>
                <c:when test="${currentUser ne null}">
                    <p>Welcome,<a class="p-2 link-secondary" href="profile">${currentUser.login}</a></p>
                </c:when>
                <c:otherwise>
                    <span class="primary-btns">
                        <a class="btn btn-sm btn-outline-secondary" href="signUp">Sign up</a>
                        <a class="btn btn-sm btn-outline-secondary" href="signIn">Sign in</a>
                    </span>
                </c:otherwise>
            </c:choose>
        </div>
      </div>
    </header>

  <div class="nav-scroller py-1 mb-2">
    <nav class="nav d-flex justify-content-between">
      <a class="p-2 link-secondary" href="#">Home</a>
      <a class="p-2 link-secondary" href="#">About US</a>
      <a class="p-2 link-secondary" href="#">Departments</a>
      <a class="p-2 link-secondary" href="talon">Get ticket</a>
      <c:if test="${currentUser ne null}">
        <a class="p-2 link-secondary" href="logout">Logout</a>
      </c:if>
    </nav>
  </div>
</div>

<div class="container light-style flex-grow-1 container-p-y">

    <h4 class="font-weight-bold py-3 mb-4">
      Ticket settings
    </h4>

    <div class="isa_info" id="response"></div>

    <div class="card overflow-hidden">
      <div class="row no-gutters row-bordered row-border-light">
        <div class="col-md-3 pt-0">
          <div class="list-group list-group-flush account-settings-links">
            <a class="list-group-item list-group-item-action active" data-toggle="list" href="#specialization">Specialization</a>
            <a class="list-group-item list-group-item-action" data-toggle="list" href="#doctor">Doctor name</a>
            <a class="list-group-item list-group-item-action" data-toggle="list" href="#date">Ticket date</a>
            <a class="list-group-item list-group-item-action" data-toggle="list" href="#time">Ticket time</a>
          </div>
        </div>

        <div class="col-md-9">
          <div class="tab-content">

            <div class="tab-pane fade active show" id="specialization">
              <div class="card-body">

                <div class="form-group">
                  <label class="form-label">Specialization</label>
                        <select class="custom-select" name ="specialization" id ="specialization">
                                <c:forEach items="${positionsList}" var="position">
                                    <option value="${position}"> ${position} </option>
                                </c:forEach>
                        </select>
                </div>
              </div>
            </div>

            <div class="tab-pane fade" id="doctor">
              <div class="card-body">

                <div class="form-group">
                  <label class="form-label">Doctor name</label>
                        <select class="custom-select" name ="fio" id ="fio" doctorId="">
                        </select>
                </div>
              </div>
            </div>

            <div class="tab-pane fade" id="date">
              <div class="card-body pb-2">

                <div class="form-group">
                  <label class="form-label">Ticket date</label>
                  <select class="custom-select" name = "dateSelect" id = "dateSelect">
                  </select>
                </div>
              </div>
            </div>

            <div class="tab-pane fade" id="time">
              <div class="card-body pb-2">

                <div class="form-group">
                  <label class="form-label">Ticket time</label>
                        <select class="custom-select" value="" name ="timeSelect" id ="timeSelect">
                        </select>
                </div>

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="text-right mt-3">
      <button type="button" class="btn btn-primary" onclick = "sendForm();">Select</button>&nbsp;
    </div>
</div>


  </body>
</html>