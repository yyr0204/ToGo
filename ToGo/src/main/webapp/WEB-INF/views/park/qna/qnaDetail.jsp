<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 내역</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Bootstrap icons-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="${pageContext.request.contextPath}/resources/static/song/css/styles.css" rel="stylesheet" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<h3>QNA글 조회</h3>
<table>
	<tr>
		<th class="w-px160">제목</th>
		<td colspan="5" class="left">${dto.title }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${dto.writer }</td>
		<th class="w-px120">작성일자</th>
		<td class="w-px120"><fmt:formatDate value="${dto.writedate}" pattern="yyyy년 MM월 dd일 a hh시 mm분 "/></td>
		<th class="w-px80">조회수</th>
		<td class="w-px80">${dto.readcnt }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="5" class="left">${fn:replace(dto.content, crlf, '<br>') }</td>
	</tr>
</table>

<div class="btnSet">
	<a class="btn-fill" href="qnaList">목록으로</a>
	<!-- 관리자인 경우 수정 삭제 답글 가능 -->
<%-- 	<core:if test="${login_info.admin eq 'Y' } || ${memId.equalsdto.writer }"> --%>
		<a class="btn-fill" href="qnaModifyForm?num=${dto.num }">수정</a>
		<a class="btn-fill" onclick="if(confirm('정말 삭제하시겠습니까?')) { href='qnaDelete?num=${dto.num }' }">삭제</a>
		<a class="btn-fill" href="qnaReplyForm?num=${dto.num }">답글 쓰기</a>
<%-- 	</core:if> --%>
</div>
</body>
</html>