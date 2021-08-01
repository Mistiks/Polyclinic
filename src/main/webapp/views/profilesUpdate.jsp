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
        <a class="p-2 link-secondary" href="showPage">Profile info</a>
        <a class="p-2 link-secondary" href="updatePage">Profile update</a>
    </nav>
  </div>
</div>

<div class="container light-style flex-grow-1 container-p-y">

    <h4 class="font-weight-bold py-3 mb-4">
      User update
    </h4>

    <div class="SuccessAnswer" ><span style='color: green;' id="response"></span></div>
    <div class="ErrorAnswer"><span style='color: red;' id="responseError"></span></div>

    <div class="card overflow-hidden">
      <div class="row no-gutters row-bordered row-border-light">
        <div class="col-md-3 pt-0">
          <div class="list-group list-group-flush account-settings-links">
            <a class="list-group-item list-group-item-action active" data-toggle="list" href="#account-select">Profile select</a>
            <a class="list-group-item list-group-item-action" data-toggle="list" href="#account-general">General</a>
            <a class="list-group-item list-group-item-action" data-toggle="list" href="#account-address">Address</a>
            <a class="list-group-item list-group-item-action" data-toggle="list" href="#account-passport">Passport</a>
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

              </div>
            </div>

            <div class="tab-pane fade" id="account-general">
              <div class="card-body">

                <div class="form-group">
                  <label class="form-label">Username</label>
                  <input type="text" class="form-control" id ="usernameShow" name ="usernameShow">
                </div>

                <div class="form-group">
                  <label class="form-label">Name</label>
                  <output type="text" class="form-control" name ="name" id ="name">
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

                <div class="form-group">
                  <label class="form-label">Status</label>
                        <select class="custom-select" name ="status" id ="status" value = "">
                            <option>UNVERIFIED</option>
                            <option>VERIFIED</option>
                        </select>
                </div>

                <div class="form-group">
                  <label class="form-label">Role</label>
                        <select class="custom-select" name ="role" id ="role" value = "">
                            <option>USER</option>
                            <option>DOCTOR</option>
                            <option>ADMIN</option>
                        </select>
                </div>

                <div class="form-group">
                  <label class="form-label">Position</label>
                  <input type="text" class="form-control" name ="position" id ="position">
                </div>

                <div class="form-group">
                  <label class="form-label">Past position</label>
                  <input type="text" class="form-control" name ="pastPosition" id ="pastPosition">
                </div>

                <div class="form-group">
                  <label class="form-label">Cabinet number</label>
                  <input type="text" class="form-control" name ="cabinetNum" id ="cabinetNum">
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
                  <input type="text" class="form-control" name ="nationality" id ="nationality">
                </div>

                <div class="form-group">
                  <label class="form-label">Gender</label>
                        <select class="custom-select" name ="sex" id ="sex">
                            <option>MALE</option>
                            <option>FEMALE</option>
                        </select>
                </div>

                <div class="form-group">
                  <label class="form-label">Issue date</label>
                  <input type="text" class="form-control" name ="issueDate" id ="issueDate">
                </div>

                <div class="form-group">
                  <label class="form-label">Expire date</label>
                  <input type="text" class="form-control" name ="expireDate" id ="expireDate">
                </div>

                <div class="form-group">
                  <label class="form-label">Birth Date</label>
                  <input type="text" class="form-control" name ="birthDate" id ="birthDate">
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
      <button type="button" class="btn btn-primary" onclick = "sendForm();">Edit profile</button>&nbsp;
    </div>
</div>

<script type="text/javascript"></script>
    <script>
    function sendForm() {
    var sendData = new Object();
    var methodSelect;
    $('#response').text("");
    $('#responseError').text("");
    var $activeTab = $('.tab-content .tab-pane.active');
    var activeId = $activeTab.attr('id');
    if (activeId != "account-select") {
        methodSelect = "PUT";
        sendData.name = $("#name").val();
        sendData.surname = $("#surname").val();
        sendData.patronymic = $("#patronymic").val();
        sendData.mail = $("#mail").val();
        sendData.number = $("#number").val();
        sendData.residenceCountry = $("#residenceCountry :selected").val();
        sendData.city = $("#city").val();
        sendData.street = $("#street").val();
        sendData.house = $("#house").val();
        sendData.passportId = $("#passportId").val();
        sendData.passportNumber = $("#passportNumber").val();
        sendData.passportCountry = $("#passportCountry :selected").val();
        sendData.nationality = $("#nationality").val();
        sendData.sex = $("#sex :selected").val();
        sendData.issueDate = $("#issueDate").val();
        sendData.expireDate = $("#expireDate").val();
        sendData.birthCountry = $("#birthCountry :selected").val();
        sendData.birthDate = $("#birthDate").val();
        sendData.status = $("#status :selected").val();
        sendData.role = $("#role :selected").val();
        sendData.position = $("#position").val();
        sendData.pastPosition = $("#pastPosition").val();
        sendData.cabinetNum = $("#cabinetNum").val();
    } else {
        methodSelect = "GET";
    }
    sendData.username = $("#username").val();
        $.ajax({
          url: "${pageContext.request.contextPath}/dashboard/profiles/update/" + sendData.username,
          type: methodSelect,
          dataType: "json",
          data: JSON.stringify(sendData),
          contentType: 'application/json',
          statusCode: {
            200: function(receivedData) {
                $('#response').text("User found or updated!");
                document.getElementById("usernameShow").value = receivedData.username;
                document.getElementById("name").value = receivedData.firstName;
                document.getElementById("surname").value = receivedData.surname;
                document.getElementById("patronymic").value = receivedData.patronymic;
                document.getElementById("mail").value = receivedData.mail;
                document.getElementById("number").value = receivedData.number;
                document.getElementById("status").value = receivedData.status;
                document.getElementById("role").value = receivedData.role;
                document.getElementById("position").value = receivedData.position;
                document.getElementById("pastPosition").value = receivedData.pastPosition;
                document.getElementById("cabinetNum").value = receivedData.cabinetNum;
                document.getElementById("residenceCountry").value = receivedData.residenceCountry;
                document.getElementById("city").value = receivedData.city;
                document.getElementById("street").value = receivedData.street;
                document.getElementById("house").value = receivedData.house;
                document.getElementById("flat").value = receivedData.flat;
                document.getElementById("passportId").value = receivedData.passportId;
                document.getElementById("passportNumber").value = receivedData.passportNum;
                document.getElementById("passportCountry").value = receivedData.country;
                document.getElementById("nationality").value = receivedData.nationality;
                document.getElementById("sex").value = receivedData.sex;
                document.getElementById("issueDate").value = receivedData.issueDate;
                document.getElementById("expireDate").value = receivedData.expireDate;
                document.getElementById("birthDate").value = receivedData.birthDate;
                document.getElementById("birthCountry").value = receivedData.birthCountry;
                },
            204: function() {
                $('#responseError').text("User not found!");
                },
            404: function() {
                $('#responseError').text("User not found!");
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