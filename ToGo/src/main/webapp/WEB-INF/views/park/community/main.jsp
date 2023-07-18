<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>community</title>
</head>
<body>

	<header class="py-5 bg-light border-bottom mb-4">
		<div class="container">
			<div class="text-center my-5">
				<h1 class="fw-bolder">커뮤니티 게시판</h1>
			</div>
		</div>
	</header>
	<!-- Page content-->
	<div class="container">
		<div class="row">
			<!-- Side widgets-->
			<div class="col-lg-4">
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
									<option value="id">작성자</option>
								</select>
								<input class="form-control mx-1" name="keyword" type="text" placeholder="검색어를 입력하세요..." aria-describedby="button-search" />
								<button class="btn btn-primary" type="submit">
									<i class="bi bi-search">검색</i>
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>

			<!-- Blog entries-->
			<div class="col-lg-8">
				<!-- 게시물 리스트 -->
				<c:if test="${not empty boardList}">
					<c:forEach var="row" items="${boardList}">
						<a href="/ToGo/board/cmView?cm_no=${row.cm_no}">
							<div class="card mb-4">
								<div class="card-body p-3">
									<div class="card-title fs-1">${row.cm_title}</div>
									<div class="card-content mt-4 mb-3"></div>
									<div class="card-id fs-5 mb-3">${row.cm_writer}</div>
									<span class="small text-muted"><fmt:formatDate value="${row.reg_date}" pattern="yyyy년 MM월 dd일 a hh시 mm분 "/></span>
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
								<a class="page-link" href="/ToGo/board/cmMain?pageNum=1">
									<i class="fs-3 bi bi-caret-left-fill"></i>
								</a>
							</li>
							<li class="page-item">
								<a class="page-link" href="/board/cmMain?pageNum=${pr.startPage - 1}">
									<i class="fs-3 bi bi-caret-left"></i>
								</a>
							</li>
						</c:if>
						<c:forEach begin="${pr.startPage}" end="${pr.endPage}" var="pNum">
							<li class="page-item ${pr.page == pNum ? 'active-btn' : 'non-active-btn'}">
								<a class="page-link" href="/ToGo/board/cmMain?pageNum=${pNum}" name="pageNum">${pNum}</a>
							</li>
						</c:forEach>
						<c:if test="${pr.endPage < pr.totalPage}">
							<li class="page-item">
								<a class="page-link" href="/ToGo/board/cmMain?pageNum=${pr.endPage + 1}">
									<i class="fs-3 bi bi-caret-right"></i>
								</a>
							</li>
							<li class="page-item">
								<a class="page-link" href="/ToGo/board/cmMain?pageNum=${pr.totalPage}">
									<i class="fs-3 bi bi-caret-right-fill"></i>
								</a>
							</li>
						</c:if>
					</ul>
				</nav>
			</div>

		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
