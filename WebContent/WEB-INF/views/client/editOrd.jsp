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
<title>Thông tin khách hàng - Giày bóng đá phủi</title>
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
	<form:form action="${pageContext.servletContext.contextPath}/cart/editOrd.htm"
		modelAttribute="cartOrder" method="POST">
		<div class="container w-25 p-3">
		<div class="form-group">
				<label> <span style="font-size: 28px"
					class="badge badge-info">Thông Tin Khách Hàng</span></label>
			</div>
			<div class="form-group">
				<label> <span
					class="badge badge-pill badge-warning">Họ và tên:</span></label>
				<form:input type="text" class="form-control" path="customerName" value="${cartOrder.customerName }"/>
				<form:errors path="customerName" class="badge badge-danger"/>
			</div>
			<div class="form-group">
				<label><span
					class="badge badge-pill badge-warning">Email:</span></label>
				<form:input type="email" class="form-control" path="customerEmail" value="${cartOrder.customerEmail }"/>
				<form:errors path="customerEmail" class="badge badge-danger"/>
			</div>
			<div class="form-group">
				<label><span
					class="badge badge-pill badge-warning">Số Điện Thoại:</span></label>
				<form:input type="number" class="form-control" path="customerPhone" value="${cartOrder.customerPhone }"/>
				<form:errors path="customerPhone" class="badge badge-danger"/>
			</div>
			<div class="form-group">
				<label><span
					class="badge badge-pill badge-warning">Địa Chỉ:</span></label>
				<form:input type="text" class="form-control" path="customerAddress" value="${cartOrder.customerAddress }"/>
				<form:errors path="customerAddress" class="badge badge-danger"/>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-success">Xác Nhận</button>
			</div>
			<div class="form-group">
				<a href="${pageContext.servletContext.contextPath}/cart.htm" class="btn btn-danger">Hủy</a>
			</div>
		</div>
	</form:form>
</body>
</html>