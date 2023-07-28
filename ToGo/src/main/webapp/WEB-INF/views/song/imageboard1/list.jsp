<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>이미지 게시판</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/board.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<div class="container">
		<h3>글목록(전체 글:${count})</h3>
		<tr>
			<td class="right">
				<c:if test="${memId == null}">
					<a href="/ToGo/login/loginMain">로그인</a>
				</c:if>
				<c:if test="${memId != null}">
					<a class="btn btn-secondary" href="/ToGo/imageboard1/mylist">내가쓴 글</a>
					<a class="btn btn-primary" href="/ToGo/imageboard1/writeForm">글쓰기</a>
				</c:if>
			</td>
		</tr>
		<table class="board-table">
			<c:if test="${empty boardList}">
				<h2 class="my-5 text-center">게시글이 없습니다.</h2>
			</c:if>
			<c:if test="${count != 0}">
				<tr>
					<th class="w-px60">썸네일</th>
					<th>제목</th>
					<th class="w-px100">작성자</th>
					<th class="w-px120">작성일자</th>
					<th class="w-px60">조회수</th>
				</tr>
				<c:forEach var="dto" items="${boardList}" >
					<tr>
						<td><a href="/ToGo/imageboard1/contentForm?num=${dto.num}&pageNum=${currentPage}&pr_pageNum=1">
								<img src="/ToGo/resources/static/song/upload/${dto.thumbnail}" />
							</a></td>
						<td>${dto.subject}</td>
						<td>${dto.writer}</td>
						<td><fmt:formatDate value="${dto.reg_date}" pattern="yyyy/MM/dd" /></td>
						<td>${dto.readcount}</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<c:if test="${count > 0}" >
			<c:if test="${startPage > 10}" >
				<a href = "/ToGo/imageboard1/list?pageNum=${startPage - 10}">[이전]</a>
			</c:if>	
			<c:forEach var = "i" items = "${page}" >
				<a href = "/ToGo/imageboard1/list?pageNum=${i}">[${i}]</a>
			</c:forEach>
			<c:if test="${endPage < pageCount}" >
				<a href = "/ToGo/imageboard1/list?pageNum=${startPage + 10}">[다음]</a>
			</c:if>
		</c:if>
	</div>
</body>
</html>
