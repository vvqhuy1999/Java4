<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/12/2024
  Time: 8:28 PM
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
    <c:url var="path" value="/showPersonLikeVideo" />
    <h2>Tim kiem Hien thi video yeu thich</h2>
    <form method="get">
        <h2>Tìm kiếm</h2>
        <input name="id" placeholder="idUser">

        <button formaction="${path}/search">Search</button>
    </form>
    <h3>
        ${user.fullname}
        <c:forEach var="d" items="${videos}">
                <p>${d.video.id} | ${d.video.title}</p>
        </c:forEach>
    </h3>
    <hr>
    <table border="1" style="width: 100%">
        <thead>
        <tr>
            <th>Video Title</th>
            <th>Người thích</th>
            <th>Ngày thích</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="f" items="${favorites}" >
            <tr>
                <td>${f.video.title}</td>
                <td>${f.user.fullname}</td>
                <td><a href="${f.video.link}"><img src="${f.video.poster}" style="width: 100px; height: 100px;"></a>
                </td>
                <td>${f.likedate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</body>
</html>
