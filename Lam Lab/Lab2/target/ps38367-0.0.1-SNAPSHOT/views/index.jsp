<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/3/2024
  Time: 9:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <c:url var="path" value="/UserUI" />
    <!-- FORM -->
    <form method="post">
        <label>ID:</label><br>
        <input name="id" value="${item.id}"><br>
        <label>Password:</label><br>
        <input name="password" value="${item.password}"><br>
        <label>Fullname:</label><br>
        <input name="fullname" value="${item.fullname}"><br>
        <label>Email:</label><br>
        <input name="email" value="${item.email}"><br>
        <label>Trạng thái:</label><br>
<%--        <input type="checkbox" name="enabled" ${item.enabled == 1 ? 'checked' : ''}><br>--%>
        <label><input type="radio" name="admin" value="1" ${item.admin == true ? 'checked' : ''} required> Admin</label>
        <label><input type="radio" name="admin" value="0" ${item.admin == false ? 'checked' : ''} required> User</label>
        <hr>
        <button formaction="${path}/create">Create</button>
        <button formaction="${path}/update">Update</button>
        <button formaction="${path}/delete">Delete</button>
        <button formaction="${path}/reset">Reset</button>
    </form>
    <hr>
    <!-- TABLE -->
    <h2>${message}</h2>
    <form method="get">
        <h2>Tìm kiếm</h2>
        <input name="fullname" placeholder="fullname">
        <label><input type="radio" name="admin" value="1" ${item.admin == true ? 'checked' : ''} required> Admin</label>
        <label><input type="radio" name="admin" value="0" ${item.admin == false ? 'checked' : ''} required> User</label>
        <button formaction="${path}/search">Search</button>
    </form>
<%--    <h2>--%>
<%--        ${searchadmin}, ${searchname}--%>
<%--    </h2>--%>
    <table border="1" style="width: 100%">
        <thead>
        <tr>
            <th>ID</th>
            <th>Password</th>
            <th>Fullname</th>
            <th>Email</th>
            <th>Trạng thái</th>
            <th></th>
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
<%--    <h1>${pageParam}</h1>--%>
    <form method="post">
        <c:forEach begin="1" end="${sopage}" var="i">
            <button class="btn btn-primary" formaction="${path}/page?page=${i}">${i}</button>
        </c:forEach>
    </form>

</div>
</body>
</html>
