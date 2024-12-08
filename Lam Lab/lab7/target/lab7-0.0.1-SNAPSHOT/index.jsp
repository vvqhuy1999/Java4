<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fetch JSON từ Servlet</title>
</head>
<body>
    <h1>Demo Fetch API</h1>
    <button id="fetchDataBtn">Fetch JSON</button>

    <script>
        // Lấy nút bấm từ DOM
        const fetchDataBtn = document.getElementById("fetchDataBtn");

        // Gắn sự kiện click vào nút
        fetchDataBtn.addEventListener("click", async () => {
            try {
                // Sử dụng Fetch API để gửi request tới servlet
                const response = await fetch("http://localhost:8080/YOUR_APP_CONTEXT/getEmployeeData");
                
                // Kiểm tra phản hồi
                if (!response.ok) {
                    throw new Error("Lỗi khi fetch dữ liệu: " + response.status);
                }

                // Đọc JSON từ response
                const data = await response.json();

                // Xuất JSON ra console
                console.log("Dữ liệu JSON nhận được:", data);
            } catch (error) {
                console.error("Có lỗi xảy ra:", error);
            }
        });
    </script>
</body>
</html>
