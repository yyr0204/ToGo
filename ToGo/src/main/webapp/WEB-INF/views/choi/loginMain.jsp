<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginMain</title>
</head>
<body>
<head>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<c:if test="${memId != null}" >
	<script>
		location = "/ToGo/trip/main";
	</script>
</c:if>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">

    <link rel="canonical" href="${pageContext.request.contextPath}/resources/static/song/css/signin.css">

    

    <!-- Bootstrap core CSS -->
	<link href="${pageContext.request.contextPath}/resources/static/song/css/bootstrap.min.css" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/static/song/css/signin.css" rel="stylesheet">
</head>

	<body class="text-center">
	<main class="form-signin">
		<form method="post" action="/ToGo/login/admlogin" >
			<a href="/ToGo/trip/main" >
		    	<img class="mb-4" src="${pageContext.request.contextPath}/resources/static/img/ToGo_logo.jpg" alt="" width="200" height="200" >
		    </a>
		    <h1 class="h3 mb-3 fw-normal">로그인 페이지</h1>
		
		    <div class="form-floating">
				<input type="email" class="form-control" id="floatingInput" name="id" placeholder="name@example.com">
				<label for="floatingInput">아이디</label>
		    </div>
		    <div class="form-floating">
				<input type="password" class="form-control" id="floatingPassword" name="pw" placeholder="Password">
				<label for="floatingPassword">비밀번호</label>
		    </div>
		
		    <div class="checkbox mb-3">
				<label>
					<input type="checkbox" value="remember-me"> 아이디 저장
				</label>
		    </div>
		    <button class="w-100 btn btn-lg btn-primary" type="submit">로그인</button>
		    <hr/>
		</form>
		
		<div>
			<a id="kakao-login-btn" >
			</a>	
		</div>
	</main>
	</body>

<div id="result">
</div>
 
<script type="text/javascript">
    // 로그인
    Kakao.init('bcc9d1aa7486b562e019afcd9ad3839b');
    console.log(Kakao.isInitialized());
    Kakao.Auth.createLoginButton({
        container: '#kakao-login-btn',
        success: function(authObj) {
            Kakao.API.request({
                url: '/v2/user/me',
                success: function(result) {
                    $('#result').append(result);
                    id = result.id;
                    connected_at = result.connected_at;
                    kakao_account = result.kakao_account;
                    $('#result').append(kakao_account);
                    email = kakao_account.email;
                    nickname = kakao_account.profile.nickname;
                    gender = kakao_account.gender;
                    birthday = kakao_account.birthday;
                    profile = kakao_account.profile;

                    $.ajax({
                        type: "POST",
                        url: "/ToGo/login/login",
                        data: {
                            id: id,
                            email: email,
                            nickname: nickname,
                            gender: gender,
                            birthday: birthday,
                        },
                        cache: false,
                        success: function(result) {
                        	if(result=='main'){
                        		location.href = "/ToGo/trip/main";
                        	}else{
                        		location.href = "/ToGo/pwSetting";
                        	}
                            
                        }
                    });
                },
                
                fail: function(error) {
                   
                },
            });
        },
        fail: function(err) {
            alert('로그인 실패')
        },
    });
    
</script>
<c:if test="${result == 0 }">
	<script>
		alert("아이디와 비밀번호를 확인해주세요");
	</script>
</c:if>
</body>
</html>


