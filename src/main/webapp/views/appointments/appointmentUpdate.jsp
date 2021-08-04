<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>IPolyclinic</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../resources/theme1/css/blog.css" rel="stylesheet" type="text/css"/>
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
            <p>Welcome,<a class="p-2 link-secondary" href="${pageContext.request.contextPath}/profile">${currentUser.login}</a></p>
        </div>
      </div>
    </header>

  <div class="nav-scroller py-1 mb-2">
    <nav class="nav d-flex justify-content-between">
      <a class="p-2 link-secondary" href="show">Appointments info</a>
      <a class="p-2 link-secondary" href="updatePage">Appointment update</a>
      <a class="p-2 link-secondary" href="createPage">Appointment creation</a>
      <a class="p-2 link-secondary" href="deletePage">Appointments deletion</a>
    </nav>
  </div>
</div>

<div class="container light-style flex-grow-1 container-p-y">

    <h4 class="font-weight-bold py-3 mb-4">
      Appointment update page
    </h4>

    <div class="SuccessAnswer" ><span style='color: green;' id="response"></span></div>
    <div class="ErrorAnswer"><span style='color: red;' id="responseError"></span></div>

    <div class="card overflow-hidden">
      <div class="row no-gutters row-bordered row-border-light">
        <div class="col-md-3 pt-0">
          <div class="list-group list-group-flush account-settings-links">
            <a class="list-group-item list-group-item-action active" data-toggle="list" href="#account-select">Appointment update</a>
          </div>
        </div>

        <div class="col-md-9">
          <div class="tab-content">

            <div class="tab-pane fade active show" id="account-select">
              <div class="card-body">

                <div class="form-group">
                  <label class="form-label">Username</label>
                  <input type="text" class="form-control" id ="username" name ="username">
                </div>

                <div class="form-group">
                  <label class="form-label">Visit time in format dd/MM/yyyy HH:mm</label>
                  <input type="text" class="form-control" name ="date" id ="date">
                </div>

              </div>
            </div>

          </div>
        </div>

      </div>
    </div>

    <div class="text-right mt-3">
      <button type="button" class="btn btn-primary" onclick = "sendForm();">Update appointment</button>&nbsp;
    </div>
</div>

<script type="text/javascript"></script>
    <script>
    function sendForm() {
    var sendData = new Object();
    $('#response').text("");
    $('#responseError').text("");

    sendData.username = $("#username").val();
    sendData.dateTime = $("#date").val();
        $.ajax({
          url: "${pageContext.request.contextPath}/doctorDashboard/appointments/update",
          type: "put",
          dataType: "json",
          data: JSON.stringify(sendData),
          contentType: 'application/json',
          statusCode: {
            200: function() {
                $('#response').text("Appointment updated!");
                },
            400: function() {
                $('#responseError').text("Error while updating. Check your inputs!");
                }
            }
        })
    }
    </script>
</body>
</html>