<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 설정</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Bootstrap icons-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="${pageContext.request.contextPath}/resources/static/song/css/styles.css" rel="stylesheet" />	
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
<a class="navbar-brand" href="/ToGo/trip/main" ><h1>ToGo</h1></a>
	<h1>비밀번호를 설정해주세요.</h1>
	<form action="/ToGo/pwSettingPro" method="post" name="userinput" onsubmit="return checkIt()">
		비밀번호 : <input type="password" name="pw"/> <br />
		비밀번호 재입력: <input type="password" name="pw2"/> <br />
			<input type="submit" value="전송"/> <br />
	</form>
</body>
</html>