<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>email 등록</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
body {
	margin: 20px; /* 페이지 전체 margin 조정 */
}

form {
	max-width: 400px; /* 폼 너비를 조정 */
	margin: 0 auto; /* 가운데 정렬 */
	padding: 20px; /* 내용과 폼 요소들 간의 간격 */
	border: 1px solid #ddd; /* 폼 주위에 테두리 추가 */
	border-radius: 8px; /* 테두리 모서리를 둥글게 만들기 */
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
}

input[type="submit"], input[type="button"] {
	margin-top: 10px; /* 버튼 위쪽 간격 */
}
</style>
</head>

<c:if test="${re == 1}" >
	<script>
		alert("이미 있는 email입니다.");
	</script>
</c:if>

<script>
	alert("email을 설정해주세요.");
</script>

<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<div style="margin-top: 30px;"></div>
	<form method="post" name="email" action="/ToGo/login/emailPro">
		<div class="form-group">
			<label for="pw">email :</label> 
			<input type="text" name="email" class="form-control" required/>
		</div>
		<input type="hidden" name="id" value="${id}" />
		<input type="submit" value="확인" class="btn btn-primary">
	</form>
	<%-- 오류 메시지가 있을 경우에만 표시 --%>
	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger" role="alert">${errorMessage}</div>
	</c:if>
</body>
</html>