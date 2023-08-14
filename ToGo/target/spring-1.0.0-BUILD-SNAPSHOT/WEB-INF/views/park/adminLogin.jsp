<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/resources/static/song/css/bootstrap.min.css" rel="stylesheet">
<link rel="canonical" href="${pageContext.request.contextPath}/resources/static/song/css/signin.css">
<link href="${pageContext.request.contextPath}/resources/static/song/css/signin.css" rel="stylesheet">

</head>
<body class="text-center">
	<main class="form-signin">
	<form method="post" action="/ToGo/login/adminLoginPro" >
		<a href="/ToGo/trip/main" >
	    	<img class="mb-4" src="${pageContext.request.contextPath}/resources/static/img/ToGo_logo.jpg" alt="" width="200" height="200" >
	    </a>
	    <h1 class="h3 mb-3 fw-normal">관리자 로그인</h1>
	
	    <div class="form-floating">
			<input type="email" class="form-control" id="floatingInput" name="id" placeholder="name@example.com">
			<label for="floatingInput">아이디</label>
	    </div>
	    <div class="form-floating">
			<input type="password" class="form-control" id="floatingPassword" name="pw" placeholder="Password">
			<label for="floatingPassword">비밀번호</label>
	    </div>
	    <button class="w-100 btn btn-lg btn-primary" type="submit">관리자 로그인</button>
	    <hr/>
	</form>
	</main>
	<c:if test="${result == 0 }">
		<script>
			alert("아이디와 비밀번호를 확인해주세요");
		</script>
	</c:if>
</body>
</html>