<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/3/2024
  Time: 9:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
<%--         pageEncoding="UTF-8"%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Title</title>--%>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">--%>
<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="container">--%>
<%--    <c:url var="path" value="/UserUI" />--%>
<%--    <!-- FORM -->--%>
<%--    <form method="post">--%>
<%--        <label>ID:</label><br>--%>
<%--        <input name="id" value="${item.id}"><br>--%>
<%--        <label>Password:</label><br>--%>
<%--        <input name="password" value="${item.password}"><br>--%>
<%--        <label>Fullname:</label><br>--%>
<%--        <input name="fullname" value="${item.fullname}"><br>--%>
<%--        <label>Email:</label><br>--%>
<%--        <input name="email" value="${item.email}"><br>--%>
<%--        <label>Trạng thái:</label><br>--%>
<%--&lt;%&ndash;        <input type="checkbox" name="enabled" ${item.enabled == 1 ? 'checked' : ''}><br>&ndash;%&gt;--%>
<%--        <label><input type="radio" name="admin" value="1" ${item.admin == true ? 'checked' : ''} required> Admin</label>--%>
<%--        <label><input type="radio" name="admin" value="0" ${item.admin == false ? 'checked' : ''} required> User</label>--%>
<%--        <hr>--%>
<%--        <button formaction="${path}/create">Create</button>--%>
<%--        <button formaction="${path}/update">Update</button>--%>
<%--        <button formaction="${path}/delete">Delete</button>--%>
<%--        <button formaction="${path}/reset">Reset</button>--%>
<%--    </form>--%>
<%--    <hr>--%>
<%--    <!-- TABLE -->--%>
<%--    <h2>${message}</h2>--%>
<%--    <form method="get">--%>
<%--        <h2>Tìm kiếm</h2>--%>
<%--        <input name="fullname" placeholder="fullname">--%>
<%--        <label><input type="radio" name="admin" value="1" ${item.admin == true ? 'checked' : ''} required> Admin</label>--%>
<%--        <label><input type="radio" name="admin" value="0" ${item.admin == false ? 'checked' : ''} required> User</label>--%>
<%--        <button formaction="${path}/search">Search</button>--%>
<%--    </form>--%>
<%--&lt;%&ndash;    <h2>&ndash;%&gt;--%>
<%--&lt;%&ndash;        ${searchadmin}, ${searchname}&ndash;%&gt;--%>
<%--&lt;%&ndash;    </h2>&ndash;%&gt;--%>
<%--    <table border="1" style="width: 100%">--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th>ID</th>--%>
<%--            <th>Password</th>--%>
<%--            <th>Fullname</th>--%>
<%--            <th>Email</th>--%>
<%--            <th>Trạng thái</th>--%>
<%--            <th></th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <c:forEach var="d" items="${list}" varStatus="vs">--%>
<%--            <tr>--%>
<%--&lt;%&ndash;                <td>${vs.count}</td>&ndash;%&gt;--%>
<%--                <td>${d.id}</td>--%>
<%--                <td>${d.password}</td>--%>
<%--                <td>${d.fullname}</td>--%>
<%--                <td>${d.email}</td>--%>
<%--                <td>${d.admin == true ? 'Admin' : 'User'}</td>--%>
<%--                <td><a href="${path}/edit/${d.id}">Edit</a></td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--        </tbody>--%>
<%--    </table>--%>
<%--&lt;%&ndash;    <h1>${pageParam}</h1>&ndash;%&gt;--%>
<%--    <form method="post">--%>
<%--        <c:forEach begin="1" end="${sopage}" var="i">--%>
<%--            <button class="btn btn-primary" formaction="${path}/page?page=${i}">${i}</button>--%>
<%--        </c:forEach>--%>
<%--    </form>--%>

<%--</div>--%>
<%--</body>--%>
<%--</html>--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>User Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<div class="container my-5">
    <h1 class="mb-4">User Management</h1>

    <c:url var="path" value="/UserUI" />

    <!-- FORM -->
    <form method="post" class="row g-3 mb-4">
        <div class="col-md-6">
            <label for="id" class="form-label">ID:</label>
            <input type="text" class="form-control" name="id" value="${item.id}">
        </div>
        <div class="col-md-6">
            <label for="password" class="form-label">Password:</label>
            <input type="password" class="form-control" name="password" value="${item.password}">
        </div>
        <div class="col-md-6">
            <label for="fullname" class="form-label">Fullname:</label>
            <input type="text" class="form-control" name="fullname" value="${item.fullname}">
        </div>
        <div class="col-md-6">
            <label for="email" class="form-label">Email:</label>
            <input type="email" class="form-control" name="email" value="${item.email}">
        </div>

        <!-- Role Selection -->
        <div class="col-md-12">
            <label class="form-label">Role:</label><br>
            <div class="form-check form-check-inline">
                <input type="radio" class="form-check-input" name="admin" value="1" ${item.admin == true ? 'checked' : ''} required>
                <label class="form-check-label">Admin</label>
            </div>
            <div class="form-check form-check-inline">
                <input type="radio" class="form-check-input" name="admin" value="0" ${item.admin == false ? 'checked' : ''} required>
                <label class="form-check-label">User</label>
            </div>
        </div>

        <!-- Action Buttons -->
        <div class="col-md-12">
            <button formaction="${path}/create" class="btn btn-success me-2">Create</button>
            <button formaction="${path}/update" class="btn btn-primary me-2">Update</button>
            <button formaction="${path}/delete" class="btn btn-danger me-2">Delete</button>
            <button formaction="${path}/reset" class="btn btn-secondary">Reset</button>
        </div>
    </form>

    <!-- SEARCH FORM -->
    <form method="get" class="mb-4">
        <h2>Search</h2>
        <div class="input-group">
            <input type="text" class="form-control" name="fullname" placeholder="Fullname">
            <div class="input-group-text">
                <label class="form-check-label me-2">Role:</label>
                <input type="radio" name="admin" value="1" ${item.admin == true ? 'checked' : ''} required> Admin
                <input type="radio" name="admin" value="0" ${item.admin == false ? 'checked' : ''} required> User
            </div>
            <button formaction="${path}/search" class="btn btn-info">Search</button>
        </div>
    </form>

    <!-- TABLE -->
    <h2>${message}</h2>
    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Password</th>
            <th>Fullname</th>
            <th>Email</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="d" items="${list}">
            <tr>
                <td>${d.id}</td>
                <td>${d.password}</td>
                <td>${d.fullname}</td>
                <td>${d.email}</td>
                <td>${d.admin == true ? 'Admin' : 'User'}</td>
                <td><a href="${path}/edit/${d.id}" class="btn btn-warning btn-sm">Edit</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Pagination -->
    <form method="post" class="d-flex justify-content-center mt-4">
        <c:forEach begin="1" end="${sopage}" var="i">
            <button class="btn btn-outline-primary mx-1" formaction="${path}/page?page=${i}">${i}</button>
        </c:forEach>
    </form>

</div>
</body>
</html>

