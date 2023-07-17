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

<!-- 
파일 첨부 시 form 태그의  필요 속성
1. 반드시 method가 post
2. enctype을 지정 ▶ enctype='multipart/form-data'
 -->
<form id="qnaForm" action="qnaInsert" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<th class="w-px160">제목</th>
			<td><input type="text" name="title" class="need" /></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${sessionScope }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content" class="need"></textarea></td>
		</tr>
		<tr>
			<th>파일 첨부</th>
			<td class="left">
				<label>
					<input type="file" name="file" id="attach-file" />
<!-- 					<img src="img/select.png" class="file-img" /> -->
				</label>
				<span id="file-name"></span>
				<span id="delete-file" style="color: red; margin-lefT: 20px;"><i class="fas fa-times font-img" ></i></span>
 			</td>
		</tr>
	</table>
<div class="btnSet">
	<a class="btn-fill" onclick="if(necessary()) $('#qnaForm').submit()">저장</a>
	<a class="btn-empty" href="qnaList">취소</a>
</div>
</form>

</body>
</html>