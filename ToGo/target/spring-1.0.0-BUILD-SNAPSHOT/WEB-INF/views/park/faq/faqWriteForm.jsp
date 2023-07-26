<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 작성</title>
</head>
<body>
	<form action="/ToGo/board/faqWritePro" method="post" >
		제목 : <input type="text" name="faq_title"/> 
		작성자 : ${memId }<br />
		내용 : <input type="text" name="faq_content"/> <br />
			<input type="submit" value="등록"/> <br />
			<input type="button" value="목록" onclick="location.href='/ToGo/board/faqList'"/>
	</form>
</body>
</html>