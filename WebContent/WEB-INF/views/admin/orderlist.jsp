<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Giỏ Hàng</title>
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
	<c:forEach var="listOrder" items="${listOrderSql}"
		varStatus="varStatus">
		<div style="margin: 20px">
			<div style="margin-top: 40px">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Code:</th>
							<!-- Product_id -->
							<th>TênSP</th>
							<!-- Product_name -->
							<th>Loại hàng</th>
							<!-- Product_type -->
							<th>Giá</th>
							<!-- OrderDetail_amount -->
							<th>Số lượng</th>
							<!-- OrderDetail_quantity -->
						</tr>
					</thead>
					<tbody>
						<!-- Chạy vòn lặp lấy ra thông tin từng sản phẩm hiển thị lên giao diện -->
						<c:forEach var="listOrdDe" items="${listOrdDeSql}">
							<c:if test="${listOrder.id == listOrdDe.order.id}">
								<c:forEach var="listProd" items="${listProdSql}">
									<c:if test="${listOrdDe.product.code == listProd.code}">
										<tr>
											<td>${listProd.code}</td>
											<td>${listProd.name}</td>
											<td>${listProd.type}</td>
											<td><fmt:formatNumber type="number"
													maxFractionDigits="3" value="${listOrdDe.amount}" /> đồng</td>
											<td>${listOrdDe.quantity}</td>

										</tr>
									</c:if>
								</c:forEach>
							</c:if>
						</c:forEach>
						<tr>
							<th>Tổng tiền: <fmt:formatNumber type="number" maxFractionDigits="3"
								value="${cartOrder.amount}" /> đồng</th>
							<th>Trạng thái: ${listOrder.status}</th>
						</tr>
					</tbody>
				</table>
			</div>
			<c:if test="${listOrder.customerName != null}">
				<div
					style="margin-top: 40px; margin-left: 60px; margin-bottom: 40px"
					class="row">
					<div class="col-sm-4">
						<div>Tên khách hàng: ${listOrder.customerName}</div>
						<br />
						<div>Email: ${listOrder.customerEmail}</div>
						<br />
						<div>Số Điện Thoại: ${listOrder.customerPhone}</div>
						<br />
						<div>Địa Chỉ: ${listOrder.customerAddress}</div>
						<br />
					</div>
				</div>
				<div
					style="margin-top: 40px; margin-left: 550px; margin-bottom: 40px">
					<a class="btn btn-success"
						href="<c:url value="/admin/takeOrder/${listOrder.id}/${account.username}.htm"/>">Xong
						Đơn Đặt Hàng</a>
				</div>
				<div
					style="margin-top: 40px; margin-left: 550px; margin-bottom: 40px">
					<a class="btn btn-success"
						href="<c:url value="/admin/confirm/${listOrder.id}.htm"/>">Xác
						nhận đơn cho Khách Hàng</a>
				</div>
			</c:if>
		</div>
	</c:forEach>
	<c:if test="${ listOrderSql.size() <=0 }">
		<div style="margin-top: 40px; margin-left: 550px; margin-bottom: 40px">Danh sách đơn hàng đang rỗng!</div>
	</c:if>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body>
</html>