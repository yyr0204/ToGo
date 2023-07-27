<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>문의글 수정</title>
</head>
<body>
<h3>QNA 수정</h3>
<form id="form" action="qnaModifyPro" method="post" >
	<input type="hidden" name="num" value="${dto.num }"/>
	<table>
		<tr>
			<th class="w-px160">제목</th>
			<td><input type="text"  name="title" value="${dto.title }"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content">${dto.content }</textarea></td>
		</tr>
	</table>
</form>
<div class="btnSet">
	<a class="btn-fill" href="#" onclick="submitForm()">저장</a>
	<a class="btn-empty" href="qnaDetail?num=${dto.num}">취소</a>
</div>
<script>
    function submitForm() {
        document.getElementById('form').submit();
    }
</script>
</body>
</html>