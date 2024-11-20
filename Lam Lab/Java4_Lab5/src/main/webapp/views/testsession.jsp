<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/19/2024
  Time: 8:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="visitor-counter">
        <div class="counter-container">
            <i class="fas fa-users"></i>
            <span class="counter-label">Số lượt truy cập:</span>
            <span class="counter-value">${applicationScope.visitors}</span>
        </div>
    </div>
</body>
</html>
