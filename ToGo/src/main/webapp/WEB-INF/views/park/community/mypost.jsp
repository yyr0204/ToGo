<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 글목록</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${pageContext.request.contextPath}/resources/static/song/css/styles.css" rel="stylesheet" />
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<!-- Page content-->
	<div class="container">
		<div class="row">
			<!-- Search widget-->
			<div class="card mb-5">
				<form action="/ToGo/board/cmMain">
					<div class="card-header">검색창</div>
					<a href= "/ToGo/board/cmWriteForm">글쓰기</a>
					<div class="card-body">
						<div class="input-group">
							<select class="mx-1" name="option">
								<option value="all">전체</option>
								<option value="title">제목</option>
								<option value="content">내용</option>
							</select>
							<input class="form-control mx-1" name="keyword" type="text" placeholder="검색어를 입력하세요..." aria-describedby="button-search" />
							<button class="btn btn-primary" type="submit">
								<i class="bi bi-search">검색</i>
							</button>
						</div>
					</div>
				</form>
			</div>

			<!-- Blog entries-->
			<div class="col-lg-8">
				<!-- 게시물 리스트 -->
				<c:if test="${not empty boardList}">
					<c:forEach var="row" items="${boardList}">
						<a href="/ToGo/board/cmView?cm_no=${row.cm_no}">
							<div class="card mb-4">
								<div class="card-body p-3">
									<div class="card-title fs-1">제목 : ${row.cm_title}</div>
									<div class="card-content mt-4 mb-3"></div>
									<div class="card-id fs-5 mb-3">작성자 : ${row.cm_writer}</div>
									<div class="card-id fs-5 mb-3">조회수 : ${row.readcount}</div>
									<span class="small text-muted">작성일 : <fmt:formatDate value="${row.reg_date}" pattern="yyyy년 MM월 dd일 a hh시 mm분 "/></span>
								</div>
							</div>
						</a>
					</c:forEach>
				</c:if>
				<c:if test="${empty boardList}">
					<h2 class="my-5 text-center">게시글이 없습니다.</h2>
				</c:if>

				<!-- Pagination-->
				<nav aria-label="Pagination">
					<hr class="my-0" />
					<ul class="pagination justify-content-center my-4">
						<c:if test="${pr.startPage > pr.pagePerBlock}">
							<li class="page-item">
								<a class="page-link" href="/ToGo/board/cmMypost?pageNum=1">
									<i class="fs-3 bi bi-caret-left-fill"></i>
								</a>
							</li>
							<li class="page-item">
								<a class="page-link" href="/ToGo/board/cmMypost?pageNum=${pr.startPage - 1}&option=${option}&keyword=${keyword}">
									<i class="fs-3 bi bi-caret-left"></i>
								</a>
							</li>
						</c:if>
						<c:forEach begin="${pr.startPage}" end="${pr.endPage}" var="pNum">
							<li class="page-item ${pr.page == pNum ? 'active-btn' : 'non-active-btn'}">
								<a class="page-link" href="/ToGo/board/cmMypost?pageNum=${pNum}&option=${option}&keyword=${keyword}" name="pageNum">${pNum}</a>
							</li>
						</c:forEach>
						<c:if test="${pr.endPage < pr.totalPage}">
							<li class="page-item">
								<a class="page-link" href="/ToGo/board/cmMypost?pageNum=${pr.endPage + 1}&option=${option}&keyword=${keyword}">
									<i class="fs-3 bi bi-caret-right"></i>
								</a>
							</li>
							<li class="page-item">
								<a class="page-link" href="/ToGo/board/cmMypost?pageNum=${pr.totalPage}&option=${option}&keyword=${keyword}">
									<i class="fs-3 bi bi-caret-right-fill"></i>
								</a>
							</li>
						</c:if>
					</ul>
				</nav>
			</div>

		</div>
	</div>
</body>
</html>