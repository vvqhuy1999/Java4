<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    <div id="register" class="container tab-pane active show" role="tabpanel">
        <h2>Form Đăng Ký</h2>
        <form class="border border-secondary p-3">
            <div class="mb-3">
                <label for="fullName" class="form-label">Họ và Tên</label>
                <input type="text" class="form-control" id="fullName" placeholder="Nhập Họ và Tên">
            </div>
            <div class="mb-3">
                <label for="registerEmail" class="form-label">Email</label>
                <input type="email" class="form-control" id="registerEmail" placeholder="Nhập Email">
            </div>
            <div class="mb-3">
                <label for="phoneNumber" class="form-label">Số Điện Thoại</label>
                <input type="text" class="form-control" id="phoneNumber" placeholder="Nhập Số điện thoại">
            </div>
            <div class="mb-3">
                <label for="registerPassword" class="form-label">Mật Khẩu</label>
                <input type="password" class="form-control" id="registerPassword" placeholder="Nhập mật khẩu">
            </div>
            <div class="mb-3">
                <label for="confirmPassword" class="form-label">Xác Nhận Mật Khẩu</label>
                <input type="password" class="form-control" id="confirmPassword" placeholder="Xác nhận mật khẩu">
            </div>
            <div class="mb-3">
                <label class="form-label d-block">Giới Tính</label>
                <div class="form-check form-check-inline">
                    <input type="radio" name="gioitinh" id="male" value="Nam" class="form-check-input">
                    <label for="male" class="form-check-label">Nam</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" name="gioitinh" id="female" value="Nu" class="form-check-input">
                    <label for="female" class="form-check-label">Nữ</label>
                </div>
            </div>
            <div class="mb-3">
                <label for="language" class="form-label">Ngôn Ngữ</label>
                <select id="language" class="form-select">
                    <option>Tiếng Việt</option>
                    <option>Tiếng Anh</option>
                </select>
            </div>
            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="agreeTerms">
                <label class="form-check-label" for="agreeTerms">Tôi đồng ý với các điều khoản và điều kiện</label>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</body>
</html>