<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 설정</title>
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link
	href="${pageContext.request.contextPath}/resources/static/song/css/styles.css"
	rel="stylesheet" />
<style>
.container {
	max-width: 600px;
	margin: 0 auto;
}

body {
	text-align: center;
	font-family: Arial, sans-serif;
}

h1 {
	margin-top: 20px;
}

form {
	margin-top: 20px;
}

input[type="password"] {
	width: 200px;
	padding: 8px;
	margin: 5px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type="submit"] {
	padding: 10px 20px;
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #0056b3;
}

.form-container {
	max-width: 300px;
	margin: 0 auto;
}
</style>
</head>
<body>
	<script>
		function checkIt() {
			if (!userinput.pw.value) {
				alert("비밀번호를 입력하세요");
				return false;
			}
			if (userinput.pw.value != userinput.pw2.value) {
				alert("비밀번호를 동일하게 입력하세요");
				return false;
			}
		}
	</script>
	<img class="mb-4"
		src="${pageContext.request.contextPath}/resources/static/img/ToGo_logo.jpg"
		alt="" width="200" height="200">
	<h1>비밀번호를 설정해주세요.</h1>
	<div class="container">
		<form action="/ToGo/pwSettingPro" method="post" name="userinput"
			onsubmit="return checkIt()">
			<table align="center">
				<tr>
					<td>비밀번호 :</td>
					<td><input type="password" name="pw" /></td>
				</tr>
				<tr>
					<td>비밀번호 재입력:</td>
					<td><input type="password" name="pw2" /></td>
				</tr>
				<tr>
		          <td colspan="2" align="center"><input type="submit" value="전송"></td>
		        </tr>
			</table>
		</form>
	</div>
</body>
</html>