<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/19/2024
  Time: 8:21 PM
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
    <c:url var="path" value="/Lab5" />

<%--    <c:if test="${not empty sessionScope.user}"> Xin chào, ${sessionScope.user} !</c:if>--%>
    <c:if test="${not empty sessionScope.user}">
        Xin chào, ${sessionScope.user.fullname}!
    </c:if>
    <h1>Bai 1</h1>
    <form method="post">
        <h2>login</h2>
        <input name="id" placeholder="id">
        <input name="password" placeholder="password">
        <button formaction="${path}/login">dang nhap</button>
        <button formaction="${path}/admin/test">admin</button>
        <button formaction="${path}/test">test</button>
    </form>
    <h2>
        ${user.fullname} <br>
        ${message}
    </h2>
</body>
</html>
