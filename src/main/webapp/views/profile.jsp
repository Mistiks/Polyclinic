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
    <link href="resources/theme1/css/blog.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Playfair&#43;Display:700,900&amp;display=swap" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
    function sendForm() {
    $('#response').text("");
    $('#responseError').text("");
    var sendData = new Object();
    sendData.username = $("#username").val();
    sendData.firstName = $("#name").val();
    sendData.surname = $("#surname").val();
    sendData.patronymic = $("#patronymic").val();
    sendData.mail = $("#mail").val();
    sendData.number = $("#number").val();
    sendData.currentPassword = $("#currentPassword").val();
    sendData.newPassword = $("#newPassword").val();
    sendData.repeatPassword = $("#repeatPassword").val();
    sendData.residenceCountry = $("#residenceCountry").val();
    sendData.city = $("#city").val();
    sendData.street = $("#street").val();
    sendData.house = $("#house").val();
    sendData.passportId = $("#passportId").val();
    sendData.passportNumber = $("#passportNumber").val();
    sendData.passportCountry = $("#passportCountry").val();
    sendData.nationality = $("#nationality").val();
    sendData.sex = $("#sex").val();
    sendData.issueDate = $("#issueDate").val();
    sendData.expireDate = $("#expireDate").val();
    sendData.birthCountry = $("#birthCountry").val();
    sendData.birthDate = $("#birthDate").val();
        $.ajax({
          url: "${pageContext.request.contextPath}/profile",
          type: "post",
          dataType: "json",
          data: JSON.stringify(sendData),
          contentType: 'application/json',
          statusCode: {
            200: function() {
                $('#response').text("User updated!");
                },
            400: function() {
                $('#responseError').text("Error while updating. Check your inputs!");
                }
            }
        })
    }
    </script>

</head>
<body>

<div class="container">
    <header class="blog-header py-3">
      <div class="row flex-nowrap justify-content-between align-items-center">
        <div class="col-4 pt-1">
          <a class="blog-header-logo text-dark" href="home">IPolyclinic</a>
        </div>
        <div class="col-4 d-flex justify-content-end align-items-center">
            <p>Welcome,<a class="p-2 link-secondary" href="profile">${currentUser.login}</a></p>
        </div>
      </div>
    </header>

  <div class="nav-scroller py-1 mb-2">
    <nav class="nav d-flex justify-content-between">
      <a class="p-2 link-secondary" href="#">Home</a>
      <a class="p-2 link-secondary" href="#">About US</a>
      <a class="p-2 link-secondary" href="#">Departments</a>
      <a class="p-2 link-secondary" href="talon">Get ticket</a>
      <a class="p-2 link-secondary" href="logout">Logout</a>
    </nav>
  </div>
</div>

<div class="container light-style flex-grow-1 container-p-y">

    <h4 class="font-weight-bold py-3 mb-4">
      Account settings
    </h4>

    <div class="SuccessAnswer" ><span style='color: green;' id="response"></span></div>
    <div class="ErrorAnswer"><span style='color: red;' id="responseError"></span></div>

    <div class="card overflow-hidden">
      <div class="row no-gutters row-bordered row-border-light">
        <div class="col-md-3 pt-0">
          <div class="list-group list-group-flush account-settings-links">
            <a class="list-group-item list-group-item-action active" data-toggle="list" href="#account-general">General</a>
            <a class="list-group-item list-group-item-action" data-toggle="list" href="#account-password">Change password</a>
            <a class="list-group-item list-group-item-action" data-toggle="list" href="#account-address">Address</a>
            <a class="list-group-item list-group-item-action" data-toggle="list" href="#account-passport">Passport</a>
          </div>
        </div>

        <div class="col-md-9">
          <div class="tab-content">

            <div class="tab-pane fade active show" id="account-general">
              <div class="card-body">

                <div class="form-group">
                  <label class="form-label">Username</label>
                  <input type="text" class="form-control" id ="username" name ="username">
                </div>

                <div class="form-group">
                  <label class="form-label">Name</label>
                  <input type="text" class="form-control" name ="name" id ="name">
                </div>

                <div class="form-group">
                  <label class="form-label">Surname</label>
                  <input type="text" class="form-control" name ="surname" id ="surname">
                </div>

                <div class="form-group">
                  <label class="form-label">Patronymic</label>
                  <input type="text" class="form-control" name ="patronymic" id ="patronymic">
                </div>

                <div class="form-group">
                  <label class="form-label">E-mail</label>
                  <input type="text" class="form-control" name ="mail" id ="mail">
                </div>

                <div class="form-group">
                  <label class="form-label">Phone number</label>
                  <input type="text" class="form-control" name ="number" id ="number">
                </div>

              </div>
            </div>

            <div class="tab-pane fade" id="account-password">
              <div class="card-body">

                <div class="form-group">
                  <label class="form-label">Current password</label>
                  <input type="password" class="form-control" name ="currentPassword" id ="currentPassword">
                </div>

                <div class="form-group">
                  <label class="form-label">New password</label>
                  <input type="password" class="form-control" name ="newPassword" id ="newPassword">
                </div>

                <div class="form-group">
                  <label class="form-label">Repeat new password</label>
                  <input type="password" class="form-control" name ="repeatPassword" id ="repeatPassword">
                </div>

              </div>
            </div>

            <div class="tab-pane fade" id="account-address">
              <div class="card-body pb-2">

                <div class="form-group">
                  <label class="form-label">Country</label>
                        <select class="custom-select" name ="residenceCountry" id ="residenceCountry" value = "">
                                <c:forEach items="${countries}" var="country">
                                    <option value="${country.name}">${country.name}</option>
                                </c:forEach>
                        </select>
                </div>

                <div class="form-group">
                  <label class="form-label">City</label>
                  <input type="text" class="form-control" name ="city" id ="city">
                </div>

                <div class="form-group">
                  <label class="form-label">Street</label>
                  <input type="text" class="form-control" name ="street" id ="street">
                </div>

                <div class="form-group">
                  <label class="form-label">House</label>
                  <input type="text" class="form-control" name ="house" id ="house">
                </div>

                <div class="form-group">
                  <label class="form-label">Flat</label>
                  <input type="text" class="form-control" name ="flat" id ="flat">
                </div>
              </div>
            </div>



            <div class="tab-pane fade" id="account-passport">
              <div class="card-body pb-2">

                <div class="form-group">
                  <label class="form-label">Passport Id</label>
                  <input type="text" class="form-control" name ="passportId" id ="passportId">
                </div>

                <div class="form-group">
                  <label class="form-label">Passport Number</label>
                  <input type="text" class="form-control" name ="passportNumber" id ="passportNumber">
                </div>

                <div class="form-group">
                  <label class="form-label">Passport Country</label>
                        <select class="custom-select" name ="passportCountry" id ="passportCountry" value = "">
                                <c:forEach items="${countries}" var="country">
                                    <option value="${country.name}">${country.name}</option>
                                </c:forEach>
                        </select>
                </div>

                <div class="form-group">
                  <label class="form-label">Nationality</label>
                  <input type="text" class="form-control" value="" name ="nationality" id ="nationality">
                </div>

                <div class="form-group">
                  <label class="form-label">Gender</label>
                        <select class="custom-select" name ="sex" id ="sex">
                            <option>UNKNOWN</option>
                            <option>MALE</option>
                            <option>FEMALE</option>
                        </select>
                </div>

                <div class="form-group">
                  <label class="form-label">Issue date in format dd/MM/yyyy</label>
                  <input type="text" class="form-control" value="" name ="issueDate" id ="issueDate">
                </div>

                <div class="form-group">
                  <label class="form-label">Expire date in format dd/MM/yyyy</label>
                  <input type="text" class="form-control" value="" name ="expireDate" id ="expireDate">
                </div>

                <div class="form-group">
                  <label class="form-label">Birth Date in format dd/MM/yyyy</label>
                  <input type="text" class="form-control" value="" name ="birthDate" id ="birthDate">
                </div>

                <div class="form-group">
                  <label class="form-label">Country of birth</label>
                        <select class="custom-select" name ="birthCountry" id ="birthCountry" value = "">
                                <c:forEach items="${countries}" var="country">
                                    <option value="${country.name}">${country.name}</option>
                                </c:forEach>
                        </select>
                </div>

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="text-right mt-3">
      <button type="button" class="btn btn-primary" onclick = "sendForm();">Save changes</button>&nbsp;
    </div>
</div>

<script type="text/javascript"></script>
</body>
</html>