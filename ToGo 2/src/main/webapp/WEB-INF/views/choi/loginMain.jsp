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
	<%--  <c:if test="${userId != null}">
       	 <h1>로그인 성공입니다</h1>
        <input type="button" value="로그아웃" onclick="location='/spring/login/logout'">
    </c:if>
    <c:if test="${userId == null}">
        <input type="button" value="로그인" onclick="location='/spring/login/login'">
    </c:if> --%>
    
<head>



<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

	<a id="kakao-login-btn"
		>
	</a>
	<button class="api-btn" onclick="unlinkApp()">로그아웃</button>
 	<div id="result"></div>
	
<script type="text/javascript">
// 로그아웃
  function unlinkApp() {
    Kakao.API.request({
      url: '/v1/user/unlink',
      success: function(res) {
        alert('로그아웃성공: ' + JSON.stringify(res))
      },
      fail: function(err) {
        alert('fail: ' + JSON.stringify(err))
      },
    })
  }
</script>
 
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
          			profile = kakao_account.profile_image;
          			
          			
          			resultdiv="<h4>로그인";
          			resultdiv += '<h4>id: '+id+'<h4>';
          			resultdiv +='<h4>email :'+email+'<h4>';        	
          			resultdiv += '<h4>nick: '+nickname+'<h4>';
          			resultdiv += '<h4>gender: '+gender+'<h4>';
          			resultdiv +='<h4>생일 :'+birthday+'<h4>';         			
          			resultdiv += '<h4>connected_at: '+connected_at+'<h4>';
          			resultdiv += '<h4>profile : '+profile+'<h4>';
          			
          			$('#result').append(resultdiv);			
          			/* location.href="/spring/login/kakaologin" */
          			
          			$.ajax({
						  type:"POST",
						  url:"/spring/question",
						  data: {id:id,
							  	email:email,
							  	nickname:nickname,
							  	gender:gender,
							  	birthday:birthday,
							  	},
						  cache: false,
						  success: function(){
							  
						  }
					  })
        		},
        		fail: function(error) {
          			alert('login success, but failed to request user information: ' +JSON.stringify(error))
        	},
		})
    },
    fail: function(err) {
      alert('failed to login: ' + JSON.stringify(err))
    },	
});

  
  
</script>
  <!--   <ul>
	<li onclick="kakaoLogin();">
      <a href="javascript:void(0)">
          <span>카카오 로그인</span>
      </a>
	</li>
	<li onclick="kakaoLogout();">
      <a href="javascript:void(0)">
          <span>카카오 로그아웃</span>
      </a>
	</li>
</ul>
카카오 스크립트
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
Kakao.init('bcc9d1aa7486b562e019afcd9ad3839b'); 
console.log(Kakao.isInitialized()); 

function kakaoLogin() {
    Kakao.Auth.login({
      success: function (response) {
        Kakao.API.request({
          url: '/v2/user/me',
          success: function (response) {
        	  console.log(response)
          },
          fail: function (error) {
            console.log(error)
          },
        })
      },
      fail: function (error) {
        console.log(error)
      },
    })
  }

function kakaoLogout() {
    if (Kakao.Auth.getAccessToken()) {
      Kakao.API.request({
        url: '/v1/user/unlink',
        success: function (response) {
        	console.log(response)
        },
        fail: function (error) {
          console.log(error)
        },
      })
      Kakao.Auth.setAccessToken(undefined)
    }
  }  
  <input type="hidden" id="tocken" name="tocken" value="0">
</script> -->
  
</body>
</html>
