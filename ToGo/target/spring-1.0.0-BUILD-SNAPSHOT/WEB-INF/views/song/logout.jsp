<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	Kakao.init('bcc9d1aa7486b562e019afcd9ad3839b'); // 사용하려는 앱의 JavaScript 키 입력
</script>

<script>
	
	kakaoLogout();
	
	// 로그아웃
	function kakaoLogout() {
		Kakao.API.request({
			url: '/v1/user/unlink',
			success: function(res) {
		  		alert('로그아웃되었습니다.')
		  		location.href = "/ToGo/trip/main";
			},
			fail: function(err) {
		  		alert('fail: ' + JSON.stringify(err))
			},
		})
	}

</script>

<!--
<script>
  // Replace these variables with your actual REST API key and Logout Redirect URI
  const YOUR_REST_API_KEY = 'f8ad76ca44538cce53ddd7af32a8a93a';
  const YOUR_LOGOUT_REDIRECT_URI = '/trip/main';

  function kakaoLogout() {
    const logoutURL = "https://kauth.kakao.com/oauth/logout?client_id=${YOUR_REST_API_KEY}&logout_redirect_uri=${YOUR_LOGOUT_REDIRECT_URI}";
    
    // Redirect to the Kakao logout URL to initiate the logout process
    window.location.href = logoutURL;
  }

  // Call kakaoLogout() function directly when the page is accessed
  kakaoLogout();
</script>
-->