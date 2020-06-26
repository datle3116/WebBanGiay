<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN SUCCESS!</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<h1>You have succeed logging the Website!</h1>
	<form:form modelAttribute="user">
		<ul>
			<li>Id: ${user.id}</li>
			<li>Password: ${user.password}</li>
		</ul>
	</form:form>
</body>
</html>