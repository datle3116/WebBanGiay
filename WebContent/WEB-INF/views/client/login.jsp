<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<title>Đăng Nhập - Giày bóng đá phủi</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body style="background-color: grey">
	<div>
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<div>
				<a class="navbar-brand justify-content-center"
					href="/WebBanGiayBongDa">
					<ul class="navbar-nav d-flex p-2">
						<li class="nav-item active d-flex p-2"><img
							src="resources/images/logo.png" alt="Logo" style="width: 80px;"></li>
						<li class="nav-item active"><h1>Giày bóng đá phủi</h1>
							<p>Website bán lẻ giày bóng đá Online</p></li>
					</ul>
				</a>
			</div>
		</nav>
	</div>
	<form:form action="${pageContext.servletContext.contextPath}/login.htm"
		modelAttribute="account" method="POST">
		<div class="container w-25 p-3">
			<div class="form-group">
				<label for="id"> <span
					class="badge badge-pill badge-warning">Tên đăng nhập:</span></label>
				<form:input type="text" class="form-control" path="username" />
			</div>
			<div class="form-group">
				<label for="password"><span
					class="badge badge-pill badge-warning">Mật khẩu:</span></label>
				<form:input type="password" class="form-control" path="password" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary">Đăng nhập</button>
			</div>
			<div class="text-white">${message}</div>
		</div>
	</form:form>
</body>
</html>