<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>FAQ 작성</title>
	<!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="${pageContext.request.contextPath}/resources/static/song/css/styles.css" rel="stylesheet" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<form action="/ToGo/board/faqWritePro" method="post" >
		제목 : <input type="text" name="faq_title"/> 
		작성자 : ${memId }<br />
		내용 : <input type="text" name="faq_content"/> <br />
			<input type="submit" value="등록"/> <br />
			<input type="button" value="목록" onclick="location.href='/ToGo/board/faqList'"/>
	</form>
</body>
</html>