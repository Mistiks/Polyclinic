<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sign Up</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"

    <!-- Custom styles for this template -->
    <link href="resources/theme1/css/signin.css" rel="stylesheet" />

  </head>
  <body class="text-center">

<script>
function sendForm() {
var registerData = new Object();
registerData.username = $("#floatingInput").val();
registerData.password = $("#floatingPassword").val();
    $.ajax({
      url: "${pageContext.request.contextPath}/signUp",
      type: "post",
      dataType: "json",
      data: JSON.stringify(registerData),
      contentType: 'application/json',
          statusCode: {
            301: function() {
                window.location.href = "${pageContext.request.contextPath}/home";
                }
            },
          error: function(data) {
            $('#error').text(data.responseText);
          }
    })
}
</script>

<main class="form-signin">
  <form id="register" name = "register" >
  <div class = "form_space">
    <a class="h2 text-dark"  href="home">IPolyclinic</a>
  </div>
    <h1 class="h3 mb-3 fw-normal">Please sign up</h1>

    <div class="form-floating">
      <input type="text" class="form-control" id="floatingInput" name ="username" placeholder="Username">
      <label for="floatingInput">Username</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword" name ="password" placeholder="Password">
      <label for="floatingPassword">Password</label>
    </div>

    <div class = "form_space">
    <button class="w-100 btn btn-lg btn-primary" type="button" onclick = "sendForm();">Sign up</button>
    </div>
  </form>
  <h2 class="h5 mb-3 fw-normal"><span style='color: red;' id = "error"></span></h2>
</main>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  </body>
</html>
