<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 작성</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link
	href="${pageContext.request.contextPath}/resources/static/song/css/styles.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- Custom CSS -->
<style>
form {
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

form label {
	font-weight: bold;
}

form input[type="text"] {
	width: 100%;
	padding: 8px;
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

form input[type="submit"], form input[type="button"] {
	padding: 10px 20px;
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

form input[type="submit"]:hover, form input[type="button"]:hover {
	background-color: #0056b3;
}

form input[type="submit"] {
	margin-right: 10px;
}

form input[type="button"] {
	background-color: #6c757d;
}

.writer-info {
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<c:choose>
		<c:when test="${(adminId != null) && (level == '3')}">
			<form action="/ToGo/board/faqWritePro" method="post">
				<div class="writer-info">
					<label for="faq_title">제목 :</label> <input type="text"
						name="faq_title" id="faq_title" required> 작성자 : ${adminId}
				</div>
				<div class="form-group">
					<label for="faq_content">내용 :</label> <textarea wrap="hard" id="faq_content" name="faq_content" class="form-control mb-3" placeholder="내용을 입력해 주세요." required style="width: 100%; height: 400px;"></textarea>
				</div>
				<div class="btn-container">
					<input type="submit" value="등록"> <input type="button"
						value="목록" onclick="location.href='/ToGo/board/faqList'">
				</div>
			</form>
		</c:when>
		<c:otherwise>
			<script>
				alert("권한이 없습니다.");
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>