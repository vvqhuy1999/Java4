<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div id="login" class="container tab-pane" role="tabpanel">
        <h2>Form Đăng nhập</h2>
        <form class="border border-danger-subtle p-3">
            <div class="mb-3">
                <label for="loginEmail" class="form-label">Email </label>
                <input type="email" class="form-control" id="loginEmail" placeholder="Nhập Email">
            </div>
            <div class="mb-3">
                <label for="loginPassword" class="form-label">Mật Khẩu</label>
                <input type="password" class="form-control" id="loginPassword" placeholder="Nhập mật khẩu">
            </div>

            <button type="submit" class="btn btn-primary">Đăng Nhập</button>
        </form>
    </div>
</body>
</html>