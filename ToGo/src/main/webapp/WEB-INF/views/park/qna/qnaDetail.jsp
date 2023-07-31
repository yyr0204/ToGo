<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 내역</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- Custom CSS -->
<style>
.container {
	max-width: 600px;
	margin: 0 auto;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

th, td {
	border: 1px solid black;
	padding: 8px;
	text-align: left;
}
th {
	background-color: #f2f2f2;
}
.left {
	text-align: left;
}
.btnSet {
	margin-top: 20px;
	text-align: center;
}
.btn-fill {
	padding: 10px 20px;
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 5px;
	text-decoration: none;
	margin-right: 10px;
}
.btn-fill:hover {
	background-color: #0056b3;
}
.btn-fill:last-child {
	margin-right: 0;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<div class="container">
		<h3>QNA글 조회</h3>
		<table style="border-collapse: separate;">
			<tr>
				<th class="w-px160">제목</th>
				<td colspan="5" class="left" style="font-weight: bold">${dto.title}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${dto.writer}</td>
				<th class="w-px120">작성일자</th>
				<td class="w-px120"><fmt:formatDate value="${dto.writedate}"
						pattern="yyyy년 MM월 dd일 a hh시 mm분 " /></td>
				<th class="w-px80">조회수</th>
				<td class="w-px80">${dto.readcnt}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="5" class="left" style="font-weight: bold">${fn:replace(dto.content, crlf, '<br>')}</td>
			</tr>
		</table>

		<div class="btnSet">
			<a class="btn-fill" href="javascript:history.back();">목록으로</a>
			<c:if test="${(adminId != null) && (level == '3')}">
				<a class="btn-fill" href="qnaModifyForm?num=${dto.num}">수정</a>
				<a class="btn-fill"
					onclick="if(confirm('정말 삭제하시겠습니까?')) { href='qnaDelete?num=${dto.num}' }">삭제</a>
				<a class="btn-fill" href="qnaReplyForm?num=${dto.num}">답글 쓰기</a>
			</c:if>
			<c:if test="${memId != null}">
				<a class="btn-fill" href="qnaModifyForm?num=${dto.num}">수정</a>
				<a class="btn-fill"
					onclick="if(confirm('정말 삭제하시겠습니까?')) { href='qnaDelete?num=${dto.num}' }">삭제</a>
			</c:if>
		</div>
	</div>
</body>
</html>
