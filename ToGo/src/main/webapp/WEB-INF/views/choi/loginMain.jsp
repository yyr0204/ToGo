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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>


<div>
	<a href="/ToGo/mypageMain"> 마이페이지 </a>
	<a href="/ToGo/board/cmMain"> 커뮤니티 게시판 </a>
	  
            <button class="api-btn" onclick="unlinkApp()">로그아웃</button>
</div>

 	<div id="result">
 	</div>
	
<script type="text/javascript">
// 로그아웃
  function unlinkApp() {
    Kakao.API.request({
      url: '/v1/user/unlink',
      success: function(res) {
        alert(kakao_account.profile.nickname + "님이 로그아웃하셨습니다.");
        window.location.href = "/ToGo/login/loginMain";
      },
      fail: function(err) {
        alert('로그아웃에 실패하였습니다.')
        window.location.href = "/ToGo/login/loginMain";
      },
    })
  }
</script>

<div>
	<a id="kakao-login-btn">
	</a>	
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

                    resultdiv="<h4>로그인";
                    resultdiv += '<h4>id: '+id+'<h4>';
                    resultdiv +='<h4>email :'+email+'<h4>';
                    resultdiv += '<h4>nick: '+nickname+'<h4>';
                    resultdiv += '<h4>gender: '+gender+'<h4>';
                    resultdiv +='<h4>생일 :'+birthday+'<h4>';
                    resultdiv += '<h4>connected_at: '+connected_at+'<h4>';
                    resultdiv += '<h4>profile : '+profile+'<h4>';
                    kakao_account
                    $('#result').append(resultdiv);
                    
                        location.href="/ToGo/question?id="+id;                       

                    $.ajax({
                        type: "POST",
                        url: "/ToGo/login/login",
                        data: {
                            id: id,
                            email: email,
                            nickname: nickname,
                            gender: gender,
                            birthday: birthday
                        },
                        cache: false,
                        success: function(){  
                        	if (confirm("성향분석하실?")) {
                        	      location.href="/ToGo/question?id="+id;
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
</body>
</html>


