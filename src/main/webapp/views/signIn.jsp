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
    <link href="webjars/bootstrap/5.0.1/css/bootstrap.min.css" rel="stylesheet">

    <style>
    .form_space {
        margin-bottom:15px;
    }
    </style>

    <!-- Custom styles for this template -->
    <link href="resources/theme1/css/signin.css" rel="stylesheet" />

  </head>
  <body class="text-center">

<main class="form-signin">
  <form method="POST" action="${pageContext.request.contextPath}/signIn">
  <div class = "form_space">
    <a class="h2 text-dark"  href="home">IPolyclinic</a>
  </div>
    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

    <div class="form-floating">
      <input type="text" class="form-control" id="floatingInput" name ="username" placeholder="Username">
      <label for="floatingInput">Username</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword" name ="password" placeholder="Password">
      <label for="floatingPassword">Password</label>
    </div>

    <div class = "form_space">
    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
    </div>
  </form>
  <h2 class="h5 mb-3 fw-normal"><span style='color: red;'>${fail_sign_in}</span></h2>
</main>

<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script src="webjars/bootstrap/5.0.1/js/bootstrap.min.js"></script>
  </body>
</html>