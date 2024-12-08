<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>USER LAYOUT</title>
<!-- Font awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap JS -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<header class="row bg-dark text-light p-2 justify-content-center">HEADER</header>
		<nav class="navbar navbar-expand-lg text-bg-info">
			<div class="container">
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="collapsibleNavbar">
					<c:url var="path" value="/user" />
					<ul class="navbar-nav me-auto">
						<li class="nav-item"><a class="nav-link" href="${path}/home"><i
								class="fa-solid fa-globe"></i> Online Entertainment</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${path}/myFavorites"><i class="fa-solid fa-star"></i>
								My Favorites</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown"> <i class="fa-solid fa-user"></i>
								My Account
						</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="${path}/editProfile">Edit Profile</a></li>
								<li><a class="dropdown-item" href="${path}/changePassword">Change Password</a></li>
								<li><a class="dropdown-item" href="${path}/forgotPassword">Forgot Password</a></li>
								<li><a class="dropdown-item" href="${path}/logoff">Logoff</a></li>
							</ul></li>
					</ul>
					<div class="navbar-nav d-flex">
						<a class="nav-item nav-link" href="${path}/login"><button
								class="btn btn-outline-light" type="button">Login</button></a> <a
							class="nav-item nav-link" href="${path}/registration"><button
								class="btn btn-outline-light" type="button">Registration</button></a>
					</div>
				</div>
			</div>
		</nav>
		<div class="container-lg mt-4">
			<section class="row">
				<jsp:include page="/views/${page}"></jsp:include>
			</section>
		</div>
		<footer class="row p-2 text-light bg-dark justify-content-center">©
			Copyright by PFT Polytechnic</footer>
	</div>
</body>
</html>