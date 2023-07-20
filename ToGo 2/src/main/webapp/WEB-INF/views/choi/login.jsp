<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>kakaoLogin</title>
</head>
<body>
<form action="usercheck" method="post">
<table>
	<tr>
		<td>
		이메일
		</td>
	</tr>
	<tr>
		<td>
			<input type="text" maxlength="20" name="email"/>
			<input type="button" value="확인"/>
		</td>
	</tr>
	<tr>
		<td>
		비밀번호
		</td>
	</tr>
	<tr>
		<td>
			<input type="password" name="pw"/>
		</td>
	</tr>
	<tr>
		<td>
		<!-- <a class="p-2" href="/choi/loginMain">
			<img src="/spring/resources/icon/loginbutton.png" style="height:50px">
		</a> -->
		<input type="submit" value="로그인" onclick="location='loginMain'">
		</td>
	</tr>
	<tr align="center">
		<td>
	<!-- 카카오 로그인 -->
		<a class="p-2" href="https://kauth.kakao.com/oauth/authorize?client_id=f8ad76ca44538cce53ddd7af32a8a93a&redirect_uri=http://localhost:8080/spring/login/kakaologin&response_type=code">
			<img src="/spring/resources/icon/kakao_login_medium_narrow.png" style="height:50px">
		</a>
		</td>
	</tr>
</table>
</form>
</body>
</html>
 