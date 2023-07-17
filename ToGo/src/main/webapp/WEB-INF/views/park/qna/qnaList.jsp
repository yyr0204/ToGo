<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 게시판</title>
</head>
<body>
<h3>Q&A</h3>
<form method="post" action="qnaList" id="list">
	<input type="hidden" name="curPage" value="1" />
	
	<div id="list-top">
		<div>
			<ul>
				<li>
					<select name="search" class="w-px80">
						<option value="all" ${page.search eq 'all' ? 'selected' : '' }>전체</option>
						<option value="title" ${page.search eq 'title' ? 'selected' : '' }>제목</option>
						<option value="content" ${page.search eq 'content' ? 'selected' : '' }>내용</option>
						<option value="writer" ${page.search eq 'writer' ? 'selected' : '' }>작성자</option>
					</select>
				</li>
				<li><input value="${page.keyword }" type="text" name="keyword" class="w-px300" /></li>
				<li><button class="btn-fill" onclick="$('form').submit()">검색</button></li>
			</ul>
			<ul>
				<c:if test="${login_info.admin eq 'Y' }">
					<li><a class="btn-fill" href="new.qna">글쓰기</a></li>
				</c:if>			
			</ul>
		</div>
	</div>
</form>

<table>
	<tr>
		<th class="w-px60">번호</th>
		<th>제목</th>
		<th class="w-px100">작성자</th>
		<th class="w-px120">작성일자</th>
		<th class="w-px60">첨부파일</th>
	</tr>
	<c:forEach items="${page.list }" var="dto">
		<tr>
			<td>${dto.num }</td>
			<td class="left">
				<c:forEach var="i" begin="1" end="${dto.indent }">
					${i eq dto.indent ? "<img src='img/re.gif' />" : "&nbsp;&nbsp;" }
				</c:forEach>
				<a href="/ToGo/board/qnaDetail?num=${dto.num }" >${dto.title }</a>
			</td>
			<td>${dto.writer }</td>
			<td><fmt:formatDate value="${dto.writedate}"
						pattern="yyyy/MM/dd" /></td>
			<td>
				<c:if test="${!empty dto.filename }">
					<a href="download.qna?num=${dto.num }">
						<img title="${dto.filename }" class="file-img" src="img/attach.png" />
					</a>
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table>
<div class="btnSet">
	<jsp:include page="/WEB-INF/views/include/page.jsp"/>
</div>
</body>
</html>