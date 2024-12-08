<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Upload File với AJAX</title>
</head>
<body>
   <h1>Upload File</h1>
   <form id="uploadForm" method="POST" enctype="multipart/form-data">
       <input type="file" id="fileInput" name="file" required>
       <button type="submit">Upload</button>
   </form>

   <div id="fileInfo" style="margin-top: 20px;">
    <h2>Thông tin file:</h2>
    <img id="uploadedImage" style="max-width: 300px; display: none;" alt="Uploaded Image">
    <p id="fileName"></p>
    <p id="fileType"></p>
    <p id="fileSize"></p>
</div>

<script>
    const uploadForm = document.getElementById("uploadForm");
    const fileInput = document.getElementById("fileInput");
    const fileInfo = document.getElementById("fileInfo");
    const uploadedImage = document.getElementById("uploadedImage");
    const fileName = document.getElementById("fileName");
    const fileType = document.getElementById("fileType");
    const fileSize = document.getElementById("fileSize");

    uploadForm.addEventListener("submit", async (event) => {
        event.preventDefault();

        const formData = new FormData();
        formData.append("file", fileInput.files[0]);

        try {
            const response = await fetch("http://localhost:8080/lab7_war_exploded/uploadFile", {
                method: "POST",
                body: formData,
            });

            if (!response.ok) {
                throw new Error("Lỗi khi upload file: " + response.status);
            }

            const jsonResponse = await response.json();
            console.log("Thông tin file upload:", jsonResponse);

            // Hiển thị thông tin file
            uploadedImage.src = jsonResponse.url; // Gán URL ảnh
            uploadedImage.style.display = "block"; // Hiển thị ảnh
            fileName.textContent = "Tên file: " + jsonResponse.name;
            fileType.textContent = "Loại file: " + jsonResponse.type;
            fileSize.textContent = "Kích thước: " + (jsonResponse.size / 1024).toFixed(2) + " KB";
        } catch (error) {
            console.error("Có lỗi xảy ra:", error);
        }
    });
</script>
</body>
</html>
