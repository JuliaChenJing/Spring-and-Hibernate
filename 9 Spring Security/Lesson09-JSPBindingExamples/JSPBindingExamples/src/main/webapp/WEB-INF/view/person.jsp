<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">

<title>Person with dates and enum</title>

<style type="text/css">
span.label {
	display: inline-block;
	width: 100px;
	text-align: right;
	margin-right: 6px;
}

td {
	margin: 4px;
}

.field {
	width: 250px;
}
</style>

<script>
	$(function() {
		$("#datepicker").datepicker();
	});

</script>


</head>
<body>
	<form:errors path="person.*" />

	<form:form method="POST" action="updatePerson" modelAttribute="person">
		<table>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="dateOfBirth">Date of Birth</form:label></td>
				<fmt:formatDate pattern="MM/dd/yyyy" value="${person.dateOfBirth}"
					var="fmtdateOfBirth" />
				<td><form:input path="dateOfBirth" value="${fmtdateOfBirth}" /></td>
			</tr>
			<tr>
				<td><form:label path="timeOfBirth">Time of Birth</form:label></td>
				<fmt:formatDate pattern="MM/dd/yyyy h:mm:ss aaa" value="${person.timeOfBirth}"
					var="fmtTimeOfBirth" />
				<td><form:input path="timeOfBirth" value="${fmtTimeOfBirth}" /></td>
			</tr>
			<tr>
				<td><form:label path="retireDate">Retire Date</form:label></td>
				<fmt:formatDate pattern="MM/dd/yyyy" value="${person.retireDate}"
					var="fmtRetireDate" />
				<td><form:input path="retireDate" id="datepicker" value="${fmtRetireDate}"/></td>
			</tr>
			<tr>
				<td><form:label path="gender">Gender</form:label></td>
				<td><form:radiobutton path="gender" value="MALE" />Male</td>
				<td><form:radiobutton path="gender" value="FEMALE" />Female</td>
				<td><form:radiobutton path="gender" value="UNKNOWN" />Unknown</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>

	<br>
	<table>
        <tr>
            <th>First Name</th>
            <th>Sex</th>
            <th>Date Of Birth</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${people}" var="p">
            <tr>
                <td>${p.name}</td>
                <td>${p.gender}</td>
                <td>${p.dateOfBirth}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>