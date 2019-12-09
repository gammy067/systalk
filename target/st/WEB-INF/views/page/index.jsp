<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>min</title>
</head>
<body>
	<h1>Spring MVC 5 + Spring Security 5 + Hibernate 5  + JPA 輕輕鬆鬆入門</h1>
	<h2>${message}(<spring:message code="app.page.header"/>)</h2>
	
	<form action='<spring:url value="/logout"/>' method="post">
		<input value="Logout" type="submit">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
	
		<form action='<spring:url value="/content/1"/>' method="post">
		<input value="content" type="submit">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
	
	 <p>
	<a href="?lang=en">en</a>
	<P>
	<a href="?lang=tw">tw</a>
  <br/>
  
</body>
</html>