<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<div class="container">
		<div class="row">
			<c:url var="path" value="/user" />
			<c:forEach var="video" items="${videos}">
				<div class="col-sm-6 col-md-4">
					<a href="${path}/detail/${video.id}" style="text-decoration: none;">
						<div class="card m-2" style="width: 18rem;">
							<img src="${video.poster}" class="card-img-top" alt="...">
<%-- 							<c:set var="embedUrl" value="${fn:replace(video.link, 'watch?v=', 'embed/')}" /> 
							<iframe width="100%" height="200" src="${embedUrl}" frameborder="0" allowfullscreen></iframe> --%>
							<div class="card-body">
								<h5 class="card-title">${video.title}</h5>
								<blockquote class="blockquote mb-0">
									<p>${video.description}</p>
									<footer class="blockquote-footer">${video.views}
										<cite title="Source Title">Views</cite>
									</footer>
								</blockquote>
							</div>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>