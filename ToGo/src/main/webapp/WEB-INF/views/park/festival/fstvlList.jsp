<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Festival List</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<form action="/ToGo/board/fstvlList">
		<div>
			<select name="option">
				<option value="all">전체</option>
				<option value="title">제목</option>
				<option value="location">지역</option>
			</select> 
			<input class="form-control mx-1" name="keyword" type="text"
				placeholder="검색어를 입력하세요..." aria-describedby="button-search" />
			<button class="btn btn-primary" type="submit">
				<i class="bi bi-search">검색</i>
			</button>
		</div>
	</form>
	<div class="row row-cols-1 row-cols-md-3 g-4">
	      <c:forEach var="festival" items="${fstvlList}">
		    <div class="card" style="max-width: 20%">
				<a href="${festival.website}" target="_blank"> <img
					src="${festival.image_url}" class="card-img-top" alt="${festival.title}">
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
						href="/ToGo/board/fstvlList?pageNum=1"> <i
							class="fs-3 bi bi-caret-left-fill">처음</i>
					</a></li>
					<li class="page-item"><a class="page-link"
						href="/ToGo/board/fstvlList?pageNum=${pr.startPage - 1}">이전<i
							class="fs-3 bi bi-caret-left"></i>
					</a></li>
				</c:if>
				<c:forEach begin="${pr.startPage}" end="${pr.endPage}" var="pNum">
					<li
						class="page-item ${pr.page == pNum ? 'active-btn' : 'non-active-btn'}">
						<a class="page-link" href="/ToGo/board/fstvlList?pageNum=${pNum}" name="pageNum">${pNum}</a>
					</li>
				</c:forEach>
				<c:if test="${pr.endPage < pr.totalPage}">
					<li class="page-item"><a class="page-link"
						href="/ToGo/board/fstvlList?pageNum=${pr.endPage + 1}">다음<i
							class="fs-3 bi bi-caret-right"></i>
					</a></li>
					<li class="page-item"><a class="page-link"
						href="/ToGo/board/fstvlList?pageNum=${pr.totalPage}">맨끝<i
							class="fs-3 bi bi-caret-right-fill"></i>
					</a></li>
				</c:if>
			</ul>
		</nav>
		</div>
		
</body>
</html>