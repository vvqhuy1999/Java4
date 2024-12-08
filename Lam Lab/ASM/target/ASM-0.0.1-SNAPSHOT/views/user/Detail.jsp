<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Auto Play Video</title>
    <style>
        .embed-responsive-item {
            width: 100%;
            height: 100%;
        }
        .video-container {
            position: relative;
            padding-bottom: 56.25%; /* 16:9 aspect ratio */
            height: 0;
            overflow: hidden;
        }
        .video-container iframe {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
        }
        #shareVideo {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.3);
            z-index: 1000;
        }

        .overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0,0,0,0.5);
            z-index: 999;
        }
    </style>
</head>
<body>
<c:url var="path" value="/user" />
<div class="container mt-5">
    <div class="card">
        <div class="video-container">
            <div id="videoPlaceholder" onclick="showVideo('${OneVideo.link}')">
                <img src="${OneVideo.poster}" alt="Hinh anh" class="img-fluid">
            </div>
        </div>
        <div class="card-body">
            <h5 class="card-title">${OneVideo.title}</h5>
            <p class="card-text">${OneVideo.description}</p>
            <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                    <form method="post" style="display: inline;">
                        <input type="hidden" name="action" value="like">
                        <button type="submit" class="btn btn-sm btn-outline-secondary  ${isLiked ? 'active' : ''}" formaction="${path}/detail/${OneVideo.id}">
                            <i class="fas fa-thumbs-up"></i> Like
                        </button>
                    </form>
                    <button type="button" class="btn btn-sm btn-outline-secondary" onclick="toggleShareForm()">
                        <i class="fas fa-share"></i> Share
                    </button>
                </div>
                <small class="text-muted">${views}</small>
            </div>
        </div>
        <div class="overlay" id="overlay"></div>

        <c:if test="${not empty message}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                    ${message}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${error}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>

        <div id="shareVideo">
            <h3>Send Video to Your Friend</h3>
            <div>
                <form method="post" >
                    <div class="form-group">
                        <input type="email"
                               class="form-control"
                               required
                               pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                               name="email"
                               title="Invalid email address"
                               placeholder="Enter friend's email">
                    </div>
                    <div class="mt-3">
                        <button type="submit" class="btn btn-primary">Share</button>
                        <button type="button" class="btn btn-secondary" onclick="toggleShareForm()">Cancel</button>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>

<script>
    function showVideo(videoLink) {
        // Kiểm tra và thay đổi đường dẫn video từ dạng watch sang embed
        if (videoLink.includes("https://youtu.be/")) {
            videoLink = videoLink.replace("https://youtu.be/", "https://www.youtube.com/embed/");
        } else if (videoLink.includes("youtube.com/watch?v=")) {
            videoLink = videoLink.replace("watch?v=", "embed/");
        }

        // Thêm các tham số cần thiết vào URL
        if (videoLink.includes("?")) {
            videoLink += "&autoplay=1&mute=1";
        } else {
            videoLink += "?autoplay=1&mute=1";
        }

        // Tạo iframe với nguồn video
        const iframe = document.createElement('iframe');
        iframe.id = 'videoFrame';
        iframe.className = 'embed-responsive-item';
        iframe.src = videoLink;
        iframe.allow = "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture";
        iframe.setAttribute('allowfullscreen', '');
        iframe.style.width = '100%';
        iframe.style.height = '100%';

        // Lấy phần tử container và thay thế nội dung
        const videoPlaceholder = document.getElementById('videoPlaceholder');
        if (videoPlaceholder) {
            videoPlaceholder.innerHTML = ''; // Xóa nội dung cũ
            videoPlaceholder.appendChild(iframe);
        }
    }

    // Function để toggle form share
    function toggleShareForm() {
        const shareForm = document.getElementById('shareVideo');
        const overlay = document.getElementById('overlay');

        if (shareForm.style.display === 'none' || shareForm.style.display === '') {
            shareForm.style.display = 'block';
            overlay.style.display = 'block';
        } else {
            shareForm.style.display = 'none';
            overlay.style.display = 'none';
        }
    }


    // Đóng form khi click outside
    document.getElementById('overlay').addEventListener('click', function() {
        toggleShareForm();
    });

    // Đóng form khi nhấn ESC
    document.addEventListener('keydown', function(event) {
        if (event.key === 'Escape') {
            const shareForm = document.getElementById('shareVideo');
            if (shareForm.style.display === 'block') {
                toggleShareForm();
            }
        }
    });
</script>
</body>
</html>
