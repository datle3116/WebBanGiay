<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Giày bóng đá phủi | Giá rẻ, chất lượng tốt</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-navbar.css" />"
	rel="stylesheet">
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<div class="bg-warning">
		<div>
			<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
				<a class="navbar-brand" href="/WebBanGiayBongDa">
					<ul class="navbar-nav d-flex p-2">
						<li class="nav-item active d-flex p-2"><img
							src="resources/images/logo.png" alt="Logo" style="width: 80px;" /></li>
						<li class="nav-item active">
							<h1>Giày bóng đá phủi</h1>
							<p>Website bán lẻ giày bóng đá Online</p>
						</li>
					</ul>
				</a>
				<div class="nav-link active "
					style="position: absolute; right: 80px; top: 20px">
					<c:choose>
						<c:when test="${account == null}">
							<a href="${pageContext.servletContext.contextPath}/login.htm"><p
									class="text-light btn btn-primary btn-info">Đăng nhập</p></a>
						</c:when>
						<c:otherwise>
							<p class="text-white">${account.username}</p>
							<a href="${pageContext.servletContext.contextPath}/login.htm"><p
									class="text-light btn btn-primary btn-danger">Đăng xuất</p></a>
						</c:otherwise>
					</c:choose>
				</div>
			</nav>
		</div>
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<!-- Links -->
			<ul class="navbar-nav">
				<li class="nav-item d-flex p-2">
					<div class="dropdown">
						<button class="dropbtn btn btn-dark"
							onclick="window.location.href = '${pageContext.servletContext.contextPath}/admin/management.htm';">
							TRANG CHỦ <i class="fa fa-caret-down"></i>
						</button>
					</div>
				</li>
				<li class="nav-item d-flex p-2">
					<div class="dropdown">
						<button class="dropbtn btn btn-dark"
							onclick="window.location.href = '${pageContext.servletContext.contextPath}/admin/orderlist/${account.username}.htm';">
							DANH SÁCH ĐƠN HÀNG <i class="fa fa-caret-down"></i>
						</button>
					</div>
				</li>
				<li class="nav-item d-flex p-2">
					<div class="dropdown">
						<button class="dropbtn btn btn-dark"
							onclick="window.location.href = '${pageContext.servletContext.contextPath}/admin/insert.htm';">
							THÊM SẢN PHẨM <i class="fa fa-caret-down"></i>
						</button>
					</div>
				</li>

			</ul>
		</nav>
	</div>
	<div class="container-fluid"><p class="text-danger">${ message }</p></div>
	<div class="container-fluid" style="margin: 20px">
		<div style="margin-top: 40px" class="row">
			<c:forEach var="item" items="${list}" varStatus="varStatus">
				<div class="col-sm-4">
					<div>
						<img src="resources/images/${item.image}" width="300px" />
					</div>
					<div>Code: ${item.code}</div>
					<br />
					<div>Ngày tạo: ${item.createdate}</div>
					<br />
					<div>TênSP: ${item.name}</div>
					<br />
					<div>
						Giá:
						<fmt:formatNumber type="number" maxFractionDigits="3"
							value="${item.price}" />
						đồng
					</div>
					<br />
					<div>Loại hàng: ${item.type}</div>
					<br /> <a class="btn btn-warning"
						href="<c:url value="admin/adminEdit/${item.code}.htm"/>">Chỉnh
						Sửa</a>
					<br /> <a class="btn btn-danger"
						href="<c:url value="admin/delete/${item.code}.htm"/>">Xóa</a>
				</div>
			</c:forEach>
		</div>
	</div>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body>
</html>