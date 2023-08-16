<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>활동내역</title>
<!-- Bootstrap JS (and other scripts) -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
	<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<!-- Your custom CSS (styles.css) -->
<link
	href="${pageContext.request.contextPath}/resources/static/song/css/styles.css"
	rel="stylesheet" />
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<div class="container mt-5">
			<div class="accordion" id="accordionExample">
				<c:if test="${list.size() > 0}" >
					<div>
						<table class="board-table">
							<tr>
								<th class="w-px60">신청자 이메일</th>
								<th class="w-px100">주 소</th>
								<th class="w-px120">상품명</th>
								<th class="w-px60">배송 상태</th>
							</tr>
							<c:forEach items="${list}" var="list" varStatus="status">
								<tr>
									<td style="display:none;"><input type="hidden" id="id" name="id" value="${list.id}" /></td>
									<td>${list.k_email}</td>
									<td>${list.address}</td>
									<td>${list.goods}</td>
									<td>${list.status}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:if>
				<c:if test="${list.size() <= 0}" >
					<div>
						<h1> 활동 내역이 없습니다..! </h1>
					</div>
				</c:if>
			</div>
		</div>
</body>
</html>