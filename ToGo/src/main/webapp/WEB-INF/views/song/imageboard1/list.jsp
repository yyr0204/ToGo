<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>게시판</title>
<style>
/* Add your custom CSS styles here */
body {
	font-family: Arial, sans-serif;
	margin: 20px;
	background-color: #f2f2f2;
}

h3 {
	text-align: center;
	margin-bottom: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #ccc;
	padding: 10px;
}

th {
	background-color: #f2f2f2;
}

td {
	vertical-align: middle;
}

td.left {
	text-align: left;
}

td.right {
	text-align: right;
}

a {
	color: #007bff;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

.no-content-msg {
	text-align: center;
	font-weight: bold;
	font-size: 16px;
	padding: 20px;
}

/* Added Styles */
.container {
	max-width: 800px;
	margin: 0 auto;
}

.search-box {
	margin-bottom: 20px;
	padding: 10px;
	background-color: #f2f2f2;
	border-radius: 5px;
	display: flex;
	justify-content: space-between;
}

.search-box select, .search-box input[type="text"], .search-box button[type="submit"] {
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
	<div class="container">
		<h3>글목록(전체 글:${count})</h3>
		<table>
			<tr>
				<td class="left"><a class="btn btn-success" href="/ToGo/trip/main">홈으로</a></td>
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
		</table>

		<c:if test="${count == 0}">
			<div class="no-content-msg">게시판에 저장된 글이 없습니다.</div>
		</c:if>

		<c:if test="${count != 0}">
			<table>
				<c:forEach var="dto" items="${boardList}" varStatus="vs">
					<tr height="500">
						<td align="center" width="500">
							<a href="/ToGo/imageboard1/contentForm?num=${dto.num}&pageNum=${currentPage}&pr_pageNum=1">
								<img src="/ToGo/resources/static/song/upload/${dto.thumbnail}" width="500" height="400" />
							</a><br />
							<h3>${dto.subject}</h3>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>
