<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Festival List</title>

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

<style>
/* 이미지를 일정한 크기로 조정 */
.card-img-top {
	width: 100%;
	height: 200px; /* 원하는 높이 값으로 조정 */
	object-fit: cover; /* 이미지가 카드에 꽉 차도록 조정 */
}

/* 카드 전체 크기를 조정 (선택 사항) */
.card {
	max-height: 40%; /* 원하는 너비 값으로 조정 */
}
/* 페이지 버튼들의 높이를 동일하게 설정 */
.pagination .page-link {
	height: 40px; /* 원하는 높이 값으로 조정 */
	line-height: 40px; /* 높이와 동일한 값으로 설정하여 텍스트를 중앙 정렬 */
	justify-content: center; /* 수평 가운데 정렬 설정 */
}

.search-box {
	margin-bottom: 20px;
	padding: 10px;
	background-color: #f2f2f2;
	border-radius: 5px;
	display: flex;
	justify-content: space-between;
}

.search-box select, .search-box input[type="text"], .search-box button[type="submit"]
	{
	padding: 5px 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.search-box select {
	min-width: 80px;
}

.search-box input[type="text"] {
	flex: 1;
	margin-right: 10px;
}

.search-box button[type="submit"] {
	background-color: #007bff;
	color: #fff;
	cursor: pointer;
}

.search-box button[type="submit"]:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<form action="/ToGo/board/fstvlList" align="center">
		<input type="hidden" name="curPage" value="1" />
		<div class="search-box">
			<div>
				<select name="option">
					<option value="all">전체</option>
					<option value="title">제목</option>
					<option value="location">지역</option>
				</select> <input name="keyword" type="text" placeholder="검색어를 입력하세요..."
					aria-describedby="button-search" />
				<button class="btn btn-primary" type="submit">
					<i class="bi bi-search">검색</i>
				</button>
			</div>
		</div>
		<c:if test="${memId != null}">
			<a href="/ToGo/board/qnaWriteForm">글쓰기</a>
		</c:if>
	</form>
	<div class="row row-cols-1 row-cols-md-3 g-4">
		<c:forEach var="festival" items="${fstvlList}">
			<div class="card" style="max-width: 20%">
				<a href="${festival.website}" target="_blank"> <img
					src="${festival.image_url}" class="card-img-top"
					alt="${festival.title}">
				</a>
				<div class="card-body">
					<h5 class="card-title">${festival.title}</h5>
					<p class="card-text">기간: ${festival.period}</p>
				</div>
			</div>
		</c:forEach>
	</div>
	<!-- Pagination-->
	<nav aria-label="Pagination">
		<hr class="my-0" />
		<ul class="pagination justify-content-center my-4">
			<c:if test="${pr.startPage > pr.pagePerBlock}">
				<li class="page-item"><a class="page-link"
					href="/ToGo/board/fstvlList?pageNum=1&option=${option}&keyword=${keyword}">처음<i
						class="fs-3 bi bi-caret-left-fill"></i>
				</a></li>
				<li class="page-item"><a class="page-link"
					href="/ToGo/board/fstvlList?pageNum=${pr.startPage - 1}&option=${option}&keyword=${keyword}">이전<i
						class="fs-3 bi bi-caret-left"></i>
				</a></li>
			</c:if>
			<c:forEach begin="${pr.startPage}" end="${pr.endPage}" var="pNum">
				<li class="page-item ${pr.page == pNum ? 'active' : ''}"
					aria-current="${pr.page == pNum ? 'page' : ''}"><a
					class="page-link"
					href="/ToGo/board/fstvlList?pageNum=${pNum}&option=${option}&keyword=${keyword}"
					name="pageNum">${pNum}</a></li>
			</c:forEach>
			<c:if test="${pr.endPage < pr.totalPage}">
				<li class="page-item"><a class="page-link"
					href="/ToGo/board/fstvlList?pageNum=${pr.endPage + 1}&option=${option}&keyword=${keyword}">다음<i
						class="fs-3 bi bi-caret-right"></i>
				</a></li>
				<li class="page-item"><a class="page-link"
					href="/ToGo/board/fstvlList?pageNum=${pr.totalPage}&option=${option}&keyword=${keyword}">맨끝<i
						class="fs-3 bi bi-caret-right-fill"></i>
				</a></li>
			</c:if>
		</ul>
	</nav>


</body>
</html>