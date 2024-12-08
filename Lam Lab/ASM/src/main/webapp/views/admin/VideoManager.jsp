<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>VIDEO MANAGER</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div class="container my-5">
		<c:url var="path" value="/videoManager" />
		<form method="post">
			<div class="mb-3">
				<label for="id" class="form-label">Video ID</label> <input
					type="text" class="form-control" id="id" name="id" required
					value="${item.id}">
			</div>
			<div class="mb-3">
				<label for="title" class="form-label">Title</label> <input
					type="text" class="form-control" id="title" name="title" required
					value="${item.title}">
			</div>
			<div class="mb-3">
				<label for="poster" class="form-label">Poster URL</label> <input
					type="text" class="form-control" id="poster" name="poster" required
					value="${item.poster}">
			</div>
			<div class="mb-3">
				<label for="link" class="form-label">Video Link</label> <input
					type="text" class="form-control" id="link" name="link" required
					value="${item.link}">
			</div>
			<div class="mb-3">
				<label for="views" class="form-label">Views</label> <input
					type="number" class="form-control" id="views" name="views" required
					value="${item.views}" min="0">
			</div>
			<div class="mb-3">
				<label for="description" class="form-label">Description</label>
				<textarea class="form-control" id="description" name="description"
					rows="3" required>${item.description}</textarea>
			</div>
			<div class="mb-3 form-check">
				<label class="form-label mb-2"> <input type="radio"
					name="active" value="1" ${item.active == true ? 'checked' : ''}
					required> Active
				</label> <label class="form-label mb-2"> <input type="radio"
					name="active" value="0" ${item.active == false ? 'checked' : ''}
					required> Inactive
				</label>
			</div>
			<hr>
			<button class="btn btn-info mt-2" formaction="${path}/create">Create</button>
			<button class="btn btn-success mt-2" formaction="${path}/update">Update</button>
			<button class="btn btn-warning mt-2" formaction="${path}/delete">Delete</button>
			<button class="btn btn-danger mt-2" formaction="${path}/reset">Reset</button>

		</form>
		<hr>
		<!-- TABLE -->
		<table class="table table-bordered table-hover my-2">
			<thead>
				<tr>
					<th>ID</th>
					<th>Title</th>
					<th>Poster</th>
					<th>Link</th>
					<th>Views</th>
					<th>Description</th>
					<th>Active</th>
					<th>Trạng thái</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="d" items="${list}" varStatus="vs">
					<tr>
						<%--                <td>${vs.count}</td>--%>
						<td>${d.id}</td>
						<td>${d.title}</td>
						<td><img src="${d.poster}" alt="Poster" width="100"></td>
						<td><a href="#" class="video-link" data-bs-toggle="modal"
							data-bs-target="#videoModal"
							data-link="${d.link}">${d.link}</a></td>
						<td>${d.views}</td>
						<td>${d.description}</td>
						<td>${d.active == true ? 'On' : 'Off'}</td>
						<td><a href="${path}/edit/${d.id}">Edit</a></td>
					</tr>
				</c:forEach>
			</tbody>

			<!-- Modal -->
			<div class="modal fade" id="videoModal" tabindex="-1"
				aria-labelledby="videoModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="videoModalLabel">Video</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body d-flex justify-content-center">
							<div id="videoPlaceholder"></div>
						</div>
					</div>
				</div>
			</div>
		</table>
		<%--    <h1>${so}</h1> --%>
		<form method="post">
			<c:forEach begin="1" end="${sopage}" var="i">
				<button class="btn btn-primary mt-2 mb-2"
					formaction="${path}/page?page=${i}">${i}</button>
			</c:forEach>
		</form>
	</div>
</body>
<script>
	$(document).ready(function() {
		$(".video-link").click(function() {
			var videoLink = $(this).data("link");
			showVideo(videoLink);
			$("#videoModal").modal("show");
		});
	});

	function showVideo(videoLink) {
		// Kiểm tra và thay đổi đường dẫn video từ dạng watch sang embed
		if (videoLink.includes("https://youtu.be/")) {
			videoLink = videoLink.replace("https://youtu.be/",
					"https://www.youtube.com/embed/");
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
</script>
</html>
