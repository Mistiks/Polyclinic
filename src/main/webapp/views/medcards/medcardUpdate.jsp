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
      <a class="p-2 link-secondary" href="show">Medcards info</a>
      <a class="p-2 link-secondary" href="updatePage">Medcards update</a>
      <a class="p-2 link-secondary" href="createPage">Medcards creation</a>
      <a class="p-2 link-secondary" href="deletePage">Medcards deletion</a>
    </nav>
  </div>
</div>

<div class="container light-style flex-grow-1 container-p-y">

    <h4 class="font-weight-bold py-3 mb-4">
      Medcard record update page
    </h4>

    <div class="SuccessAnswer" ><span style='color: green;' id="response"></span></div>
    <div class="ErrorAnswer"><span style='color: red;' id="responseError"></span></div>

    <div class="card overflow-hidden">
      <div class="row no-gutters row-bordered row-border-light">
        <div class="col-md-3 pt-0">
          <div class="list-group list-group-flush account-settings-links">
            <a class="list-group-item list-group-item-action active" data-toggle="list" href="#account-select">Record select</a>
            <a class="list-group-item list-group-item-action" data-toggle="list" href="#account-update">Record update</a>
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
                  <label class="form-label">Diagnose date</label>
                  <input type="text" class="form-control" name ="diagnoseDate" id ="diagnoseDate">
                </div>

              </div>
            </div>

            <div class="tab-pane fade" id="account-update">
              <div class="card-body pb-2">

                <div class="form-group">
                  <label class="form-label">Username</label>
                  <input type="text" class="form-control" id ="usernameUpdate" name ="usernameUpdate" value="">
                </div>

                <div class="form-group">
                  <label class="form-label">Disease</label>
                  <input type="text" class="form-control" name ="diseaseUpdate" id ="diseaseUpdate" value="">
                </div>

                <div class="form-group">
                  <label class="form-label">Diagnose date</label>
                  <input type="text" class="form-control" name ="diagnoseDateUpdate" id ="diagnoseDateUpdate" value="">
                </div>

                <div class="form-group">
                  <label class="form-label">Discharge date</label>
                  <input type="text" class="form-control" name ="dischargeDateUpdate" id ="dischargeDateUpdate" value="">
                </div>

                <div class="form-group">
                  <label class="form-label">Notes</label>
                   <textarea class="form-control" rows="5" name ="notes" id ="notes"></textarea>
                </div>

              </div>
            </div>

          </div>
        </div>

      </div>
    </div>

    <div class="text-right mt-3">
      <button type="button" class="btn btn-primary" onclick = "sendForm();">Update record</button>&nbsp;
    </div>
</div>

<script type="text/javascript"></script>
    <script>
    function sendForm() {
    var sendData = new Object();
    var methodSelect;
    var $activeTab = $('.tab-content .tab-pane.active');
    var activeId = $activeTab.attr('id');
    $('#response').text("");
    $('#responseError').text("");
    if (activeId != "account-select") {
        methodSelect = "PUT";
        sendData.username = $("#usernameUpdate").val();
        sendData.notes = $("#notes").val();
        sendData.diseaseName = $("#diseaseUpdate").val();
        sendData.diagnoseDate = $("#diagnoseDateUpdate").val();
        sendData.dischargeDate = $("#dischargeDateUpdate").val();
        sendData.medcardId = $("#account-update").attr('name')
    } else {
        methodSelect = "POST";
        sendData.username = $("#username").val();
        sendData.diagnoseDate = $("#diagnoseDate").val();
    }
        $.ajax({
          url: "${pageContext.request.contextPath}/doctorDashboard/medcards/update",
          type: methodSelect,
          dataType: "json",
          data: JSON.stringify(sendData),
          contentType: 'application/json',
          statusCode: {
            200: function(receivedData) {
                $('#response').text("Method succeeded!");
                document.getElementById("usernameUpdate").value = receivedData.username;
                document.getElementById("diseaseUpdate").value = receivedData.diseaseName;
                document.getElementById("diagnoseDateUpdate").value = receivedData.diagnoseDate;
                document.getElementById("dischargeDateUpdate").value = receivedData.dischargeDate;
                document.getElementById("notes").value = receivedData.notes;
                document.getElementById("account-update").setAttribute("name", receivedData.medcardId);
                },
            204: function() {
                $('#responseError').text("Record doesn't exist!");
                },
            400: function() {
                $('#responseError').text("Method failed. Check your inputs!");
                }
            }
        })
    }
    </script>
</body>
</html>