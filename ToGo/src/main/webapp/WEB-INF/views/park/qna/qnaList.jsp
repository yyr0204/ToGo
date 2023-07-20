<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
	<title>Q&A 게시판</title>
</head>
<body>
<h3>Q&A</h3>
<form method="post" action="qnaList" id="list">
	<input type="hidden" name="curPage" value="1" />
	<c:if test="${memId != null}">
		<a href= "/ToGo/board/qnaWriteForm">글쓰기</a>
	</c:if>
	<div >검색창</div>
	<div>
		<div>
			<select name="option">
				<option value="all">전체</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="id">작성자</option>
			</select>
			<input name="keyword" type="text" placeholder="검색어를 입력하세요..." aria-describedby="button-search" />
			<button  type="submit">
				<i>검색</i>
			</button>
		</div>
	</div>
</form>
<h3>총 게시글 수 ${page.totalList }</h3>
<table>
	<tr>
		<th class="w-px60">번호</th>
		<th>제목</th>
		<th class="w-px100">작성자</th>
		<th class="w-px120">작성일자</th>
		<th class="w-px60">조회수</th>
<!-- 		<th class="w-px60">첨부파일</th> -->
	</tr>
	<c:forEach items="${page.list }" var="dto">
		<tr>
			<td>${dto.num }</td>
			<td class="left">
				<c:forEach var="i" begin="1" end="${dto.indent }">
					${i eq dto.indent ? "<img src='/resource/static/img/re.png' />" : "&nbsp;&nbsp;" }
				</c:forEach>
				<a href="/ToGo/board/qnaDetail?num=${dto.num }" >${dto.title }</a>
			</td>
			<td>${dto.writer }</td>
			<td><fmt:formatDate value="${dto.writedate}" pattern="yyyy/MM/dd" /></td>
			<td>${dto.readcnt }</td>
		</tr>
	</c:forEach>
</table>
<div class="btnSet">
	<jsp:include page="/WEB-INF/views/include/page.jsp"/>
</div>
</body>
</html>