<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 수정</title>
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
.profile img {
    max-width: 200px; /* 이미지의 최대 너비를 원하는 크기로 설정하세요. */
    height: auto; /* 이렇게 함으로써 이미지의 가로 세로 비율이 유지됩니다. */
  }
table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #ccc;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

input[type="text"], input[type="password"], select {
	width: 100%;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type="submit"], input[type="button"] {
	padding: 10px 20px;
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 5px;
	text-decoration: none;
	margin-right: 10px;
	cursor: pointer;
}

.btn-success {
	padding: 10px 20px;
	height: 44px; /* 원하는 높이로 변경해주세요 */
	line-height: 44px; /* 버튼의 텍스트를 수직으로 가운데 정렬하는데 사용됩니다 */
	margin-right: 10px;
}

input[type="submit"]:hover, input[type="button"]:hover {
	background-color: #0056b3;
}

/* Center the form */
div {
	display: flex;
	justify-content: center;
	align-items: center;
}
/* 스타일 추가 부분 */
input[readonly] {
	background-color: #f2f2f2;
	color: #555;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<div class="container">
		<div style="margin-top: 30px;"></div>
			<div class="profile">
				<c:if test="${dto.profile_img.startsWith('http://') || dto.profile_img.startsWith('https://')}">
				  <!-- 외부 서버의 이미지일 경우 -->
				  <img src="${dto.profile_img}" alt="프로필 이미지">
				</c:if>	
					<img src="/ToGo/resources/static/profile/${dto.profile_img}"/>
			</div>
		<div class="profile-details">
			<form action="/ToGo/myPage/modifyPro" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<td>* 아이디</td>
						<td><input type="text" name="id" value="${dto.id}" readonly></td>
					</tr>
					<tr>
						<td>* 이메일</td>
						<td><input type="text" name="email" value="${dto.email}"
							readonly></td>
					</tr>
					<tr>
						<td>* 비밀번호</td>
						<td><input type="password" name="pw" value="${dto.pw}"></td>
					</tr>
					<tr>
						<td>* 이름</td>
						<td><input type="text" name="nickname"
							value="${dto.nickname}"></td>
					</tr>
					<tr>
						<td>* 생일</td>
						<td><input type="text" name="birthday"
							value="${dto.birthday}" readonly></td>
					</tr>
					<tr>
						<td>* 성별</td>
						<td><select name="k_gender">
								<option value="male" ${dto.gender == 'male' ? 'selected' : ''}>남자</option>
								<option value="female"
									${dto.gender == 'female' ? 'selected' : ''}>여자</option>
						</select></td>
					</tr>

					<tr>
						<td>* 성향</td>
						<td><input type="text" name="mbti" value="${dto.mbti}"
							readonly></td>
					</tr>
					<tr>
						<td>* 포인트</td>
						<td>
						  <fmt:formatNumber value="${dto.cash}" type="currency" currencySymbol="pt " /> 
						</td>
					</tr>

					<tr>
						<td colspan="3" align="center">
							<input type="file" name="save" value="이미지 변경"/>
							<input type="submit" value="수정하기">
							<a class="btn btn-success" href="/ToGo/question">성향 검사</a>
							<input type="button" onclick="location.href='/ToGo/trip/main'" value="취소" /> <br /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>