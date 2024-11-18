<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/15/2024
  Time: 12:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:url var="path" value="/Lab4" />
    <h1>Bai 1 -Bai 4</h1>
    <table border="1" style="width: 100%">
        <thead>
        <tr>
            <th>Video Title</th>
            <th>SL Chia Sẽ</th>
            <th>Ngày đầu tiên</th>
            <th>Ngày cuối cùng</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="t" items="${titleDates}" >
            <tr>
                <td>${t[0]}</td>
                <td>${t[1]}</td>
                <td>${t[2]}</td>
                <td>${t[3]}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <hr>
    <h1>Bai 2</h1>
    <form method="post">
        <h2>login</h2>
        <input name="id" placeholder="idUser or email">
        <input name="password" placeholder="password">
        <button formaction="${path}/login">dang nhap</button>
    </form>
    <h2>
        ${user.email} <br>
        ${user.password} <br>
        ${message}
    </h2>

    <br>
    <hr>
    <h1>Bai 3</h1>
    <form method="get">
        <h2>Tìm kiếm là truy vấn
            các video có title chứa từ khóa.</h2>
        <input name="title" placeholder="Tim kiem title chứa từ khóa ">
        <button formaction="${path}/bai3">Tim kiem</button>
    </form>
    <table border="1" style="width: 100%">
        <thead>
        <tr>
            <th>Tieu de video</th>
            <th>Số lượng thích</th>
            <th>Còn hiệu lực</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="f" items="${findTitleKeyCountSLLink}" >
            <tr>
                <td>${f[0]}</td>
                <td>${f[1]}</td>
                <td>${f[2]}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
