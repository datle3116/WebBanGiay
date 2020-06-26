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
						</c:otherwise>
					</c:choose>
				</div>
			</nav>
		</div>
		<div class="container d-flex p-2">
			<div class="row">
				<div style="position: absolute; left: 150px; top: 160px">
					<img src="resources/images/policy-icon1.png" />
				</div>
				<div class="col medium-3 small-6 large-3 col-divided">
					<div class="col medium-5 small-6 large-5 d-flex p-2">
						<div>
							<h3>
								<strong>GIAO HÀNG</strong>
							</h3>
							<p style="text-align: left;">Giao hàng COD toàn quốc</p>
						</div>
					</div>
				</div>
				<div style="position: absolute; left: 450px; top: 160px">
					<img src="resources/images/policy-icon2.png" />
				</div>
				<div class="col medium-3 small-6 large-3 col-divided">
					<div class="col medium-5 small-6 large-5 d-flex p-2">
						<div>
							<h3>
								<strong>BẢO HÀNH</strong>
							</h3>
							<p>Bảo hành 2 tháng, đổi hàng trong vòng 7 ngày</p>
						</div>
					</div>
				</div>
				<div style="position: absolute; left: 730px; top: 160px">
					<img src="resources/images/policy-icon6.png" />
				</div>
				<div class="col medium-3 small-6 large-3 col-divided">
					<div class="col medium-5 small-6 large-5 d-flex p-2">
						<div>
							<h3>
								<strong>QUÀ TẶNG</strong>
							</h3>
							<p>Tất ngắn hoặc Túi rút</p>
						</div>
					</div>
				</div>
				<div style="position: absolute; left: 1020px; top: 160px">
					<img src="resources/images/policy-icon7.png" />
				</div>
				<div class="col medium-3 small-6 large-3 col-divided">
					<div class="col medium-5 small-6 large-5 d-flex p-2">
						<div>
							<h3>
								<strong>TƯ VẤN</strong>
							</h3>
							<p>Hotline: 0339.106.622 (FB:
								https://www.facebook.com/le.dat.165)</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<!-- Links -->
			<ul class="navbar-nav">
				<li class="nav-item d-flex p-2">
					<div class="dropdown">
						<button class="dropbtn btn btn-dark"
							onclick="window.location.href = '/WebBanGiayBongDa';">
							TRANG CHỦ <i class="fa fa-caret-down"></i>
						</button>
					</div>
				</li>
				<li class="nav-item d-flex p-2">
					<div class="dropdown">
						<button class="dropbtn btn btn-dark">
							GIÀY ĐÁ BANH <i class="fa fa-caret-down"></i>
						</button>
					</div>
				</li>
				<li class="nav-item d-flex p-2">
					<div class="dropdown">
						<button class="dropbtn btn btn-dark">
							PHỤ KIỆN <i class="fa fa-caret-down"></i>
						</button>
					</div>
				</li>
				<li class="nav-item d-flex p-2">
					<div class="dropdown">
						<button class="dropbtn btn btn-dark"
							onclick="window.location.href = 'https://www.facebook.com/le.dat.165';">
							LIÊN HỆ, TƯ VẤN <i class="fa fa-caret-down"></i>
						</button>
					</div>
				</li>
				<li class="nav-item d-flex p-2"><a class="btn bg-warning"
					href="${pageContext.servletContext.contextPath}/cart.htm">GIỎ
						HÀNG<img src="resources/images/cart.png" />
				</a></li>

			</ul>
		</nav>
	</div>
	<div style="margin-top: 40px">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Code:</th>
					<th>TênSP</th>
					<th>Loại hàng</th>
					<th>Giá</th>
					<th>Số lượng</th>
					<th>Chức năng</th>
				</tr>
			</thead>
			<tbody>
				<!-- Chạy vòn lặp lấy ra thông tin từng sản phẩm hiển thị lên giao diện -->
				<c:forEach var="item" items="${listCartProd}" varStatus="varStatus">
					<tr>
						<td>${item.code}</td>
						<td>${item.name}</td>
						<td>${item.type}</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="3"
								value="${item.amount}" /> đồng</td>
						<td>${item.quantity}</td>
						<td><a class="btn btn-warning"
							href="<c:url value="/cart/edit/${item.code}.htm"/>">Sửa (số
								lượng)</a> <a class="btn btn-danger"
							href="<c:url value="/cart/delete/${item.code}.htm"/>">Hủy
								hàng</a></td>
					</tr>
				</c:forEach>
				<tr>
					<th>Tổng tiền: <fmt:formatNumber type="number" maxFractionDigits="3"
								value="${cartOrder.amount}" /> đồng</th>
				</tr>
			</tbody>
		</table>
	</div>
	<c:if test="${cartOrder.customerName != null}">
		<div style="margin-top: 40px; margin-left: 60px; margin-bottom: 40px"
			class="row">
			<div class="col-sm-4">
				<div>Tên khách hàng: ${cartOrder.customerName}</div>
				<br />
				<div>Email: ${cartOrder.customerEmail}</div>
				<br />
				<div>Số Điện Thoại: ${cartOrder.customerPhone}</div>
				<br />
				<div>Địa Chỉ: ${cartOrder.customerAddress}</div>
				<br />
			</div>
		</div>
		<div style="margin-top: 40px; margin-left: 550px; margin-bottom: 40px">
			<a class="btn btn-warning" href="<c:url value="/cart/editOrd.htm"/>">Sửa
				thông tin khách hàng</a>
		</div>
		<div style="margin-top: 40px; margin-left: 550px; margin-bottom: 40px">
			<a class="btn btn-warning"
				href="<c:url value="/cart/deleteOrd.htm"/>">Xóa thông tin khách
				hàng</a>
		</div>
	</c:if>
	<c:choose>
		<c:when test="${listCartProd.size() >0}">
			<c:choose>
				<c:when test="${cartOrder.customerName == null}">
					<div
						style="margin-top: 40px; margin-left: 550px; margin-bottom: 40px">
						<a class="btn btn-warning" href="<c:url value="/cart/order.htm"/>">Xác
							nhận mua hàng</a>
					</div>
				</c:when>
				<c:otherwise>
					<div
						style="margin-top: 40px; margin-left: 550px; margin-bottom: 40px">
						<a class="btn btn-warning" href="<c:url value="/cart/success.htm"/>">Xác
							nhận đơn hàng</a>
					</div>

				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<div style="margin-top: 40px; margin-left: 550px; margin-bottom: 40px">Giỏ hàng đang
				rỗng!</div>
		</c:otherwise>
	</c:choose>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body>
</html>