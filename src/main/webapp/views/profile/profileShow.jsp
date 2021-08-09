<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*, java.text.*, model.enums.Gender" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>IPolyclinic</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../resources/theme1/css/blog.css" rel="stylesheet" />
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
            <p>Welcome,<a class="p-2 link-secondary" href="show">${currentUser.login}</a></p>
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
      Account information
    </h4>

    <div class="card overflow-hidden">
      <div class="row no-gutters row-bordered row-border-light">
        <div class="col-md-3 pt-0">
          <div class="list-group list-group-flush account-settings-links">
            <a class="list-group-item list-group-item-action active" data-toggle="list" href="#account-general">General</a>
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
                  <output type="text" class="form-control" id ="username" name ="username">
                </div>

                <div class="form-group">
                  <label class="form-label">Name</label>
                  <output type="text" class="form-control" name ="name" id ="name" >
                </div>

                <div class="form-group">
                  <label class="form-label">Surname</label>
                  <output type="text" class="form-control" name ="surname" id ="surname">
                </div>

                <div class="form-group">
                  <label class="form-label">Patronymic</label>
                  <output type="text" class="form-control" name ="patronymic" id ="patronymic">
                </div>

                <div class="form-group">
                  <label class="form-label">E-mail</label>
                  <output type="text" class="form-control" name ="mail" id ="mail">
                </div>

                <div class="form-group">
                  <label class="form-label">Phone number</label>
                  <output type="text" class="form-control" name ="number" id ="number">
                </div>

              </div>
            </div>

            <div class="tab-pane fade" id="account-address">
              <div class="card-body pb-2">

                <div class="form-group">
                  <label class="form-label">Country</label>
                    <output type="text" class="form-control" name ="residenceCountry" id ="residenceCountry">
                </div>

                <div class="form-group">
                  <label class="form-label">City</label>
                  <output type="text" class="form-control" name ="city" id ="city">
                </div>

                <div class="form-group">
                  <label class="form-label">Street</label>
                  <output type="text" class="form-control" name ="street" id ="street">
                </div>

                <div class="form-group">
                  <label class="form-label">House</label>
                  <output type="text" class="form-control" name ="house" id ="house">
                </div>

                <div class="form-group">
                  <label class="form-label">Flat</label>
                  <output type="text" class="form-control" name ="flat" id ="flat">
                </div>
              </div>
            </div>


            <div class="tab-pane fade" id="account-passport">
              <div class="card-body pb-2">

                <div class="form-group">
                  <label class="form-label">Passport Id</label>
                  <output type="text" class="form-control" name ="passportId" id ="passportId">
                </div>

                <div class="form-group">
                  <label class="form-label">Passport Number</label>
                  <output type="text" class="form-control" name ="passportNumber" id ="passportNumber">
                </div>

                <div class="form-group">
                  <label class="form-label">Country</label>
                    <output type="text" class="form-control" name ="country" id ="country">
                </div>

                <div class="form-group">
                  <label class="form-label">Nationality</label>
                  <output type="text" class="form-control" name ="nationality" id ="nationality">
                </div>

                <div class="form-group">
                  <label class="form-label">Country</label>
                    <output type="text" class="form-control" name ="sex" id ="sex">
                </div>

                <div class="form-group">
                  <label class="form-label">Issue date</label>
                  <output type="text" class="form-control" name ="issueDate" id ="issueDate">
                </div>

                <div class="form-group">
                  <label class="form-label">Expire date</label>
                  <output type="text" class="form-control" name ="expireDate" id ="expireDate">
                </div>

                <div class="form-group">
                  <label class="form-label">Birth Date</label>
                  <output type="text" class="form-control" name ="birthDate" id ="birthDate">
                </div>

                <div class="form-group">
                  <label class="form-label">Birth country</label>
                    <output type="text" class="form-control" name ="birthCountry" id ="birthCountry">
                </div>

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
</div>

<script type="text/javascript"></script>
<script>
document.getElementById("username").value = "${profile.username}";
document.getElementById("name").value = value = "${profile.firstName}";
document.getElementById("surname").value = "${profile.surname}";
document.getElementById("patronymic").value = "${profile.patronymic}";
document.getElementById("mail").value = "${profile.mail}";
document.getElementById("number").value = "${profile.number}";
document.getElementById("residenceCountry").value = "${profile.residenceCountry}";
document.getElementById("city").value = "${profile.city}";
document.getElementById("street").value = "${profile.street}";
document.getElementById("house").value = "${profile.house}";
document.getElementById("flat").value = "${profile.flat}";
document.getElementById("passportId").value = "${profile.passportId}";
document.getElementById("passportNumber").value = "${profile.passportNum}";
document.getElementById("country").value = "${profile.country}";
document.getElementById("nationality").value = "${profile.nationality}";
document.getElementById("sex").value = "${profile.sex}";
document.getElementById("issueDate").value = "${profile.issueDate}";
document.getElementById("expireDate").value = "${profile.expireDate}";
document.getElementById("birthDate").value = "${profile.birthDate}";
document.getElementById("birthCountry").value = "${profile.birthCountry}";
</script>
</body>
</html>