<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REPORTS</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
	<div class="container-fluid my-2">
		<c:url var="path" value="/report" />
		<form method="get">
			<div class="input-group mb-3 mt-3">
				<label class="input-group-text" for="inputGroupSelect01">Title</label>
				<select class="form-select" name="title" id="inputGroupSelect01  ">

					<%-- JSTL c:forEach to populate video titles --%>
					<c:forEach var="title" items="${videoTitles}">
						<option value="${title}">${title}</option>
					</c:forEach>
				</select>

				<!-- Search button with Font Awesome icon -->
				<button class="btn btn-primary ms-1" formaction="${path}/search">
					<i class="fas fa-search"></i>
				</button>
			</div>
		</form>
		
		<!-- tab panes -->
		<ul class="nav nav-tabs" id="myTab" role="tablist">
			<li class="nav-item" role="presentation">
				<button class="nav-link active" id="favorites-tab"
					data-bs-toggle="tab" data-bs-target="#favorites" type="button"
					role="tab" aria-controls="favorites" aria-selected="true">Favorites</button>
			</li>
			<li class="nav-item" role="presentation">
				<button class="nav-link" id="favorite-users-tab"
					data-bs-toggle="tab" data-bs-target="#favorite-users" type="button"
					role="tab" aria-controls="favorite-users" aria-selected="false">Favorite
					Users</button>
			</li>
			<li class="nav-item" role="presentation">
				<button class="nav-link" id="shared-friends-tab"
					data-bs-toggle="tab" data-bs-target="#shared-friends" type="button"
					role="tab" aria-controls="shared-friends" aria-selected="false">Shared
					Friends</button>
			</li>
		</ul>

		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade show active" id="favorites" role="tabpanel"
				aria-labelledby="favorites-tab">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Video Title</th>
							<th>Favorite Count</th>
							<th>Latest Date</th>
							<th>Oldest Date</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="t" items="${titleDates}">
							<tr>
								<td>${t[0]}</td>
								<td>${t[1]}</td>
								<td>${t[2]}</td>
								<td>${t[3]}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="tab-pane fade" id="favorite-users" role="tabpanel"
				aria-labelledby="favorite-users-tab">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Username</th>
							<th>Fullname</th>
							<th>Email</th>
							<th>Favorite Date</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="f" items="${favoriteUsers}">
							<tr>
								<td>${f.user.id}</td>
								<td>${f.user.fullname}</td>
								<td>${f.user.email}</td>
								<td>${f.likeDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="tab-pane fade" id="shared-friends" role="tabpanel"
				aria-labelledby="shared-friends-tab">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Sender Name</th>
							<th>Sender Email</th>
							<th>Receiver Email</th>
							<th>Sent Date</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="s" items="${sharedFriends}">
							<tr>
								<td>${s.user.id}</td>
								<td>${s.user.email}</td>
								<td>${s.email}</td>
								<td>${s.sharedate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>