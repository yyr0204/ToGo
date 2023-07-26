<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호를 입력해주세요</title>
</head>
<body>
	<form action="/ToGo/myPage/modifyForm" method="post">
		비밀번호 : <input type="password" name="pw"/> <br />
		<input type="submit" value="확인"/> <br />
		<input type="button" value="메인" onclick="location.href='/ToGo/trip/main'"/>
	</form>
</body>
</html>