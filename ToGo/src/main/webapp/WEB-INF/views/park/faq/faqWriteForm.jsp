<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 작성</title>
</head>
<body>
	<form action="/spring/faq/faqWritePro" method="post" >
		제목 : <input type="text" name="title"/> 
		작성자 : <input type="text" name="writer" values="${memId }"/> <br />
		내용 : <input type="text" name="content"/> <br />
			<input type="submit" value="등록"/> <br />
			<input type="button" value="목록" onclick="location.href='/spring/faq/faqList'"/>
	</form>
</body>
</html>