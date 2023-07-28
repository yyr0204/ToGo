<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>게시판</title>

<script>

	function deleteSave() {
		if(document.contentDelete.passwd.value == '') {
			alert("비밀번호를 입력하시요.");
			document.contentDelete.passwd.facus();
			return false;
		}
	}
</script>
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
h3 {
	text-align: center;
    margin-bottom: 20px;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<h3>글삭제</h3>
	<br>
	<form method="post" name="contentDelete" action="contentDeletePro">
		<div class="form-group">
			<label for="passwd">비밀번호:</label> <input type="password" name="passwd"
				id="passwd" class="form-control" required>
		</div>
		<input type = "hidden" name ="num" value = "${dto.num}">
		<input type = "hidden" name ="pageNum" value = "${pageNum}">
		<input type="submit" value="확인" class="btn btn-primary">
		<input type = "button" value = "글목록" onclick = "document.location.href='list.jsp?pageNum=${pageNum}'" class="btn btn-secondary">
	</form>
		
</body>
</html>