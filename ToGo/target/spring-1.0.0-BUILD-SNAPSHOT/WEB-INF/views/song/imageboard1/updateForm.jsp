<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시판</title>
</head>

	<c:if test="${memId == null}" >
		<script>
			alert("로그인후 글수정 가능");
			location = "ToGo/login/loginMain";
		</script>
	</c:if>

<center><b>글수정</b>
<br>
<form method = "post" name = "updateForm" action = "updatePro" enctype="multipart/form-data" onsubmit = "return writeSave()">
	<%-- writePro로 이동하는 form생성 (writeform) --%>	
	<table width = "400" border = "1" cellspacing = "0" cellpadding = "0" align = "center">
		<tr>
			<td align = "right" colspan = "2" >
				<a href = "list">글목록</a>
			</td>
		</tr>
		<tr>
			<td width ="70" align = "center"> 글쓴이 </td>
			<td width = "330">
				${numContent.writer}
			</td>
			<input type = "hidden" name = "num" value = "${numContent.num}" >
			<input type = "hidden" name = "writer" value = "${numContent.writer}" >
		</tr>
		<tr>
			<td width ="70" align = "center"> 제 목 </td>
			<td width = "330">
				<input type = "text" size = "40" maxlength = "50" name = "subject" value = "${numContent.writer}">
			</td>
		</tr>		
		<tr>
			<td width = "70" align = "center">Email</td>
			<td width = "330">
				<input type = "text" size = "40" maxlength = "30" name = "email" value = "${numContent.email}" >
			</td>
		</tr>
		<tr>
			<td width = "70" align = "center">thumbnail</td>
			<td width = "330">
				<input type = "file" name="save" />
				<input type = "hidden" name = "thumbnail" value = "${numContent.thumbnail}" >
			</td>
		</tr>
		<tr>
			<td width = "70" align = "center">image</td>
			<td width = "330">
				<input type = "file" name="save" />
				<input type = "hidden" name = "image" value = "${numContent.image}" >
			</td>
		</tr>
		<tr>
			<td width = "70" align = "center" > 내 용 </td>
			<td width = "330" >
				<textarea name = "content" rows = "13" cols = "40" >${numContent.content}</textarea>
			</td>
		</tr>
			<input type = "hidden" name = "passwd" value = "${numContent.passwd}">
		<tr>
			<td colspan = "2" align = "center" >
				<input type = "submit" value = "글수정" >
				<input type = "reset" value = "다시작성" >
				<input type = "button" value = "목록보기" onclick = "location = 'list'">
			</td>
		</tr>
	</table>
</form>
</body>
</html>