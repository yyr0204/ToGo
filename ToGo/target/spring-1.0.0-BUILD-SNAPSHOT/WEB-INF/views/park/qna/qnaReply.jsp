<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 답글</title>
</head>
<body>
<h3>답글 쓰기</h3>

<form id ="form" action="qnaReplyPro" method="post">
	<input type="hidden" name="root" value="${dto.root }" />
	<input type="hidden" name="step" value="${dto.step }" />
	<input type="hidden" name="indent" value="${dto.indent }" />
	
	<table>
		<tr>
			<th class="w-px160">제목</th>
			<td><input type="text" name="title" class="need" /></td>
		</tr>
		<tr>
			<th>작성자</th>
<!-- 			관리자로 바꿔야함 -->
			<td>${memId}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content" class="need"></textarea></td>
		</tr>
		
	</table>
</form>
<div class="btnSet">
	<a class="btn-fill" href="#" onclick="submitForm()">저장</a>
	<a class="btn-empty" href="qnaList">취소</a>
</div>

<!-- 실시간 갱신을 위해 getTime을 붙여준다 -->
<script type="text/javascript" src="/ToGo/resources/js/need_check.js?v=<%=new java.util.Date().getTime() %>"></script>
<script type="text/javascript" src="/ToGo/resources/js/file_attach.js"></script>
<script>
    function submitForm() {
        document.getElementById('form').submit();
    }
</script>
</body>
</html>