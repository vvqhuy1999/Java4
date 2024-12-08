<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>USER MANAGER</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<!--     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
 -->
</head>
<body>
	<div class="container">
		<c:url var="path" value="/userManager" />
		<!-- FORM -->
		<form method="post" class="form mt-2 border-1">
			<div class="row">
				<div class="col-sm-6 m-1">
					<label class="form-label mt-1">ID:</label><br> 
					<input class="form-control" name="id" value="${item.id}"><br>
					<label class="form-label mt-1">Password:</label><br> 
					<input class="form-control" name="password" value="${item.password}"><br>
				</div>
				<div class="col m-1">
					<label class="form-label mt-1">Fullname:</label><br> 
					<input class="form-control" name="fullname" value="${item.fullname}"><br>
					<label class="form-label mt-1">Email:</label><br> 
					<input class="form-control" name="email" value="${item.email}"><br>
				</div>
			</div>
			<label class="form-label mt-1">Trạng thái:</label><br>
			<label class="form-label mt-1">
			<input type="radio" name="admin" value="1" ${item.admin == true ? 'checked' : ''} required> Admin</label> 
			<label class="form-label mt-1">
			<input type="radio" name="admin" value="0" ${item.admin == false ? 'checked' : ''} required> User</label>
			<hr>
			<button class="btn btn-info mt-2" formaction="${path}/create">Create</button>
			<button class="btn btn-success mt-2" formaction="${path}/update">Update</button>
			<button class="btn btn-warning mt-2" formaction="${path}/delete">Delete</button>
			<button class="btn btn-danger mt-2" formaction="${path}/reset">Reset</button>
		</form>
		<hr>

		<h2>${message}</h2>
		<!-- SEARCH -->
		<h2>Tìm kiếm</h2>
		<form method="get" class="d-flex">

			<input class="form-control" name="fullname" placeholder="fullname">
			<label class="form-label mt-1 ms-3" for="ad"><input
				type="radio" id="ad" name="admin" value="1"
				${item.admin == true ? 'checked' : ''} required> Admin</label> <label
				class="form-label mt-1 ms-3" for="us"><input type="radio"
				id="us" name="admin" value="0"
				${item.admin == false ? 'checked' : ''} required> User</label>
			<button class="btn btn-warning ms-3" formaction="${path}/search">Search</button>
		</form>
		<%--    <h2>--%>
		<%--        ${searchadmin}, ${searchname}--%>
		<%--    </h2>--%>
		<!-- TABLE -->
		<table class="table table-bordered table-hover my-2">
			<thead>
				<tr>
					<th>ID</th>
					<th>Password</th>
					<th>Fullname</th>
					<th>Email</th>
					<th>Chức vụ</th>
					<th>Trạng thái</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="d" items="${list}" varStatus="vs">
					<tr>
						<%--                <td>${vs.count}</td>--%>
						<td>${d.id}</td>
						<td>${d.password}</td>
						<td>${d.fullname}</td>
						<td>${d.email}</td>
						<td>${d.admin == true ? 'Admin' : 'User'}</td>
						<td><a href="${path}/edit/${d.id}">Edit</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%--    <h1>${so}</h1> --%>
		<form method="post">
			<c:forEach begin="1" end="${sopage}" var="i">
				<button class="btn btn-primary mt-2 mb-2"formaction="${path}/page?page=${i}">${i}</button>
			</c:forEach>
		</form>

	</div>
</body>
</html>