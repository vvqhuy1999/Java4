<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý nhân viên</title>
</head>
<body>
    <h1>Quản lý Nhân Viên</h1>
    <button onclick="getEmployees()">Lấy danh sách nhân viên</button>
    <div id="employee-list"></div>

    <script>
        async function getEmployees() {
            try {
                // Gửi yêu cầu đến REST API
                const response = await fetch('<%= request.getContextPath() %>/employees');
                const data = await response.json();

                // Hiển thị danh sách nhân viên
                const employeeList = document.getElementById('employee-list');
                employeeList.innerHTML = '<h2>Danh sách nhân viên</h2>';

                if (Array.isArray(data)) {
                    const list = document.createElement('ul');
                    data.forEach(employee => {
                        const item = document.createElement('li');
                        item.textContent = `${employee.id} - ${employee.name} - ${employee.gender ? 'Nam' : 'Nữ'} - ${employee.salary} VND`;
                        list.appendChild(item);
                    });
                    employeeList.appendChild(list);
                } else {
                    employeeList.innerHTML += '<p>Không có dữ liệu nhân viên</p>';
                }
            } catch (error) {
                console.error('Lỗi khi lấy danh sách nhân viên:', error);
            }
        }
    </script>
</body>
</html>
