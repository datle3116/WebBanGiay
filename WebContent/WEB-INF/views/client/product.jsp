<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
							<p class="text-white">${account.username }</p>
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
						<div class="dropdown-content">
							<a href="#">SÂN CỎ NHÂN TẠO</a> <a href="#">SÂN CỎ TỰ NHIÊN</a>
						</div>
					</div>
				</li>
				<li class="nav-item d-flex p-2">
					<div class="dropdown">
						<button class="dropbtn btn btn-dark">
							PHỤ KIỆN <i class="fa fa-caret-down"></i>
						</button>
						<div class="dropdown-content">
							<a href="#">BÓNG</a> <a href="#">BĂNG KEO THỂ THAO</a> <a
								href="#">VỚ-TẤT</a> <a href="#">BÓ GỐI</a>
						</div>
					</div>
				</li>
				<li class="nav-item d-flex p-2">
					<div class="dropdown">
						<button class="dropbtn btn btn-dark"
							onclick="window.location.href = '${pageContext.servletContext.contextPath}/contact.htm';">
							LIÊN HỆ, TƯ VẤN <i class="fa fa-caret-down"></i>
						</button>
					</div>
				</li>
				<li class="nav-item d-flex p-2"><a class="btn bg-warning"
					href="#">GIỎ HÀNG<img src="resources/images/cart.png" /></a></li>

			</ul>
		</nav>
	</div>
	<div>
		
	</div>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body>
</html>