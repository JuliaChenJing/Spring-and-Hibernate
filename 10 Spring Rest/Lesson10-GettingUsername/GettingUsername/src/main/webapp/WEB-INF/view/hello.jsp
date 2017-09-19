<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@page session="false"%>
<html>
<body>
	<h1>Title : ${title}</h1>	
	<h1>Message : ${message}</h1>
	<p>
	User Name = user <br>
	Password  = password 
	<p>
	<c:url value="/admin" var="adminUrl" />
		<a href="${adminUrl}">Click to enter the Administrator Area</a>	
</body>
</html>