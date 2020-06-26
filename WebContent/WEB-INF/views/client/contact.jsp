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
<title>Liên hệ, tư vấn - Giày bóng đá phủi</title>
</head>
<body style="background-color: grey">
	<div>
		<h1>Giày bóng đá phủi</h1>
	</div>
	<div>
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<div>
				<a class="navbar-brand justify-content-center"
					href="/WebBanGiayBongDa">
					<ul class="navbar-nav d-flex p-2">
						<li class="nav-item active d-flex p-2"><img
							src="resources/images/logo.png" alt="Logo"
							style="width: 80px;"></li>
						<li class="nav-item active"><h1>Giày bóng đá phủi</h1>
							<p>Website bán lẻ giày bóng đá Online</p></li>
					</ul>
				</a>
			</div>
		</nav>
	</div>
	<form:form action="client/login.htm" modelAttribute="user" method="post">
		<div class="container w-25 p-3">
			<div class="form-group">
				<label for="usr"><span
					class="badge badge-pill badge-warning">Tên đăng nhập:</span></label> <input
					type="text" class="form-control" id="usr">
			</div>
			<div class="form-group">
				<label for="pwd"><span
					class="badge badge-pill badge-warning">Mật khẩu:</span></label> <input
					type="password" class="form-control" id="pwd">
			</div>
			<div class="form-group">
				<button type="button" class="btn btn-primary">Đăng nhập</button>
			</div>
		</div>
	</form:form>
</body>
</html>