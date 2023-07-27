<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 설정</title>
</head>
<body>
<script>
function checkIt() {
	if(!userinput.pw.value ) {
    	alert("비밀번호를 입력하세요");
        return false;
	}
	if(userinput.pw.value != userinput.pw2.value){
		alert("비밀번호를 동일하게 입력하세요");
		return false;
	}
}
</script>
	<h1>비밀번호를 설정해주세요.</h1>
	<form action="/ToGo/pwSettingPro" method="post" name="userinput" onsubmit="return checkIt()">
		비밀번호 : <input type="password" name="pw"/> <br />
		비밀번호 재입력: <input type="password" name="pw2"/> <br />
			<input type="submit" value="전송"/> <br />
	</form>
</body>
</html>