<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>

<html>
<head>
<title>Forms</title>
</head>
<body>
<h2>News form</h2>
<form method="POST" action="${pageContext.request.contextPath}/forms/news">
			<table style="with: 50%">
				<tr>
					<td>Header</td>
					<td><input type="text" name="header" /></td>
				</tr>
				<tr>
					<td>Text</td>
					<td><input type="text" name="text" /></td>
				</tr></table>
			<p><input type="submit" value="Add news" /></form></p>

<h2>Address form</h2>
<form method="POST" action="${pageContext.request.contextPath}/forms/address">
			<table style="with: 50%">
				<tr>
					<td>User id</td>
					<td><input type="text" name="id" /></td>
				</tr>
				<tr>
					<td>City</td>
					<td><input type="text" name="city" /></td>
				</tr>
				<tr>
					<td>Street</td>
					<td><input type="text" name="street" /></td>
				</tr>
				<tr>
					<td>House</td>
					<td><input type="text" name="house" /></td>
				</tr>
				<tr>
					<td>Flat</td>
					<td><input type="text" name="flat" /></td>
				</tr>
				<tr>
					<td>Country</td>
					<td><input type="text" name="country" /></td>
				</tr></table>
			<p><input type="submit" value="Add address" /></form></p>

<h2>Passport form</h2>
<form method="POST" action="${pageContext.request.contextPath}/forms/passport">
			<table style="with: 50%">
				<tr>
					<td>User id</td>
					<td><input type="text" name="id" /></td>
				</tr>
				<tr>
					<td>Passport id</td>
					<td><input type="text" name="passportId" /></td>
				</tr>
				<tr>
					<td>Passport number</td>
					<td><input type="text" name="passportNum" /></td>
				</tr>
				<tr>
					<td>Country of living</td>
					<td><input type="text" name="country" /></td>
				</tr>
				<tr>
					<td>Nationality</td>
					<td><input type="text" name="nationality" /></td>
				</tr>
				<tr>
					<td>Birth date</td>
					<td><input type="text" name="birthDate" /></td>
				</tr>
				<tr>
					<td>Sex</td>
					<td><input type="text" name="sex" /></td>
				</tr>
				<tr>
					<td>Issue date</td>
					<td><input type="text" name="issueDate" /></td>
				</tr>
				<tr>
					<td>Expire date</td>
					<td><input type="text" name="expireDate" /></td>
				</tr>
				<tr>
					<td>Birth country</td>
					<td><input type="text" name="birthCountry" /></td>
				</tr>
				</table>
			<p><input type="submit" value="Add passport" /></form></p>

<h2>Departments form</h2>
<form method="POST" action="${pageContext.request.contextPath}/forms/department">
			<table style="with: 50%">
				<tr>
					<td>Department name</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>Department info</td>
					<td><input type="text" name="info" /></td>
				</tr></table>
			<p><input type="submit" value="Add department" /></form></p>

<h2>Disease form</h2>
<form method="POST" action="${pageContext.request.contextPath}/forms/disease">
			<table style="with: 50%">
				<tr>
					<td>Disease name</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>Disease code</td>
					<td><input type="text" name="code" /></td>
				</tr></table>
			<p><input type="submit" value="Add disease" /></form></p>

<h2>Symptom form</h2>
<form method="POST" action="${pageContext.request.contextPath}/forms/symptom">
			<table style="with: 50%">
				<tr>
					<td>Symptom name</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>Symptom code</td>
					<td><input type="text" name="code" /></td>
				</tr></table>
			<p><input type="submit" value="Add symptom" /></form></p>

<h2>Medcard form</h2>
<form method="POST" action="${pageContext.request.contextPath}/forms/medcard">
			<table style="with: 50%">
				<tr>
					<td>Card id</td>
					<td><input type="text" name="id" /></td>
				</tr>
				<tr>
					<td>Doctor id</td>
					<td><input type="text" name="doctorId" /></td>
				</tr>
				<tr>
					<td>Disease name</td>
					<td><input type="text" name="diseaseName" /></td>
				</tr>
				<tr>
					<td>Diagnose date</td>
					<td><input type="text" name="diagnoseDate" /></td>
				</tr>
				<tr>
					<td>Discharge date</td>
					<td><input type="text" name="dischargeDate" /></td>
				</tr>
				</table>
			<p><input type="submit" value="Add medcard" /></form></p>

<h2>Talon form</h2>
<form method="POST" action="${pageContext.request.contextPath}/forms/talon">
			<table style="with: 50%">
				<tr>
					<td>User id</td>
					<td><input type="text" name="id" /></td>
				</tr>
				<tr>
					<td>Time</td>
					<td><input type="text" name="time" /></td>
				</tr>
				<tr>
					<td>Doctor id</td>
					<td><input type="text" name="doctorId" /></td>
				</tr>
				</table>
			<p><input type="submit" value="Add talon" /></form></p>
</body>
</html>