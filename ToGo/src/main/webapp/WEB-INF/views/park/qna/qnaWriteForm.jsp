<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/resources/js/need_check.js"></script>
<script type="text/javascript" src="/resources/js/file_attach.js"></script>
<title>qna 작성하기</title>
</head>
<body>
<h3>신규 QNA</h3>

<form id="qnaForm" action="qnaInsert" method="post" >
	<table>
		<tr>
			<th class="w-px160">제목</th>
			<td><input type="text" name="title" class="need" /></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${memId }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content" class="need"></textarea></td>
		</tr>
	</table>
<div class="btnSet">
	<a class="btn-fill" href="#" onclick="submitForm()">저장</a>
	<a class="btn-empty" href="qnaList">취소</a>
</div>
</form>
<script>
    function submitForm() {
        document.getElementById('qnaForm').submit();
    }
</script>
</body>
</html>