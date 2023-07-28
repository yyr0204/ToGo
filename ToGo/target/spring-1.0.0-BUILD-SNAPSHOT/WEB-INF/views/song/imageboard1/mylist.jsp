<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title> 게시판 </title>
</head>

<body>
<b>글목록(내가 쓴 글:${count})</b>
<table width = "700">
	<tr>
		<td align = "left">
			<a href = "/ToGo/trip/main">홈으로</a>
		</td>
		<td align = "right">
			<c:if test="${memId == null}">
				<a href = "/ToGo/login/loginMain">로그인</a>
			</c:if>
			<c:if test="${memId != null}">
				<a href = "/ToGo/imageboard1/list">전체 글</a>
				<a href = "/ToGo/imageboard1/writeForm">글쓰기</a>
			</c:if>
		</td>
	</tr>
</table>

<c:if test="${count == 0}">
	<table width = "700" border = "1" cellpadding = "0" cellspacing = "0">
		<tr>
			<td align = "center">
			게시판에 저장된 글이 없습니다.
			</td>
		</tr>
	</table>
</c:if>
<c:if test="${count != 0}">
	<table border = "1" width ="700" cellpadding = "0" cellspacing = "0" align = "center">
		<c:forEach var = "dto" items = "${boardList}" varStatus = "vs">
			<tr height = "500" >
				<td align = "center" width = "500" >
					<a href="/ToGo/imageboard1/contentForm?num=${dto.num}&pageNum=${currentPage}">
						<img src = "/ToGo/resources/static/song/upload/${dto.thumbnail}" width = "500" height = "400" />
					</a><br />
					<h3> ${dto.subject} </h3>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
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
</body>
</html>