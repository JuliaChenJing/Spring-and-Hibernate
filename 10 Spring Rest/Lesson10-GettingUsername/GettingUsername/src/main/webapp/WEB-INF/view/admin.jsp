<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
	   <h2>Welcome </h2>
	   Your user name using &#36;&#123;pageContext.request.userPrincipal.name&#125; on JSP: <b>${pageContext.request.userPrincipal.name} </b>
	   <br>
	   The same name obtained on the controller : <b>${name}</b>
	   	| <a href="<c:url value="/logout" />" > Logout</a>
	   	  
	</c:if>
</body>
</html>