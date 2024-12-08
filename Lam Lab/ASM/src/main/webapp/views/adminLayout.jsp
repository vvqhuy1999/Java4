<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN LAYOUT</title>
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
		<!-- Header -->
		<header class="row bg-primary text-white p-3 justify-content-center">${namepage}</header>
		<!-- Navigation -->
		<nav class="bg-dark">
			<c:url var="path" value="/admin" />
			<div class="container-fluid">
				<ul class="nav justify-content-center me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="/Lab2_Test_war_exploded/user/home">
							<i class="fa-solid fa-globe"></i> Online Entertainment
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${path}/videoManager"> <i class="fa-solid fa-film"></i>
							Video Manager
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${path}/userManager"> <i class="fa-regular fa-user"></i>
							User Manager
					</a></li>
					<li class="nav-item"><a class="nav-link" href="${path}/report">
							<i class="fa-solid fa-circle-info"></i> Report
					</a></li>
				</ul>
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