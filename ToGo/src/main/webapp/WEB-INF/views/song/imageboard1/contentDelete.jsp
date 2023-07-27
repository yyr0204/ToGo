<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<h3>글삭제</h3>
	<br>
	<form method="post" name="contentDelete" action="contentDeletePro">
		<table>
			<tr>
				<td>
					<b>비밀번호를 입력해 주세요.</b>
				</td>
			</tr>
			<tr>
				<td>
					비밀번호 :
					<input type = "password" name = "passwd" size = "8" maxlength = "12">
					<input type = "hidden" name ="num" value = "${dto.num}">
				</td>
				<input type = "hidden" name ="pageNum" value = "${pageNum}">
			</tr>
			<tr>
				<td>
					<input type = "submit" value = "글삭제">
					<input type = "button" value = "글목록"
					onclick = "document.location.href='list.jsp?pageNum=${pageNum}'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>