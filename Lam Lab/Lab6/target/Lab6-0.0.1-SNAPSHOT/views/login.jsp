<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <c:if test="${not empty errorMessage}">
        <div style="color: red;">${errorMessage}</div>
    </c:if>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="username">Username:</label>
        <input id="username" name="username" type="text" required><br>
        
        <label for="password">Password:</label>
        <input id="password" name="password" type="password" required><br>
        
        <button type="submit">Login</button>
    </form>
</body>
</html>