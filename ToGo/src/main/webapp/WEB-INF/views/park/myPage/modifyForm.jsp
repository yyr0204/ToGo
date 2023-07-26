<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 수정</title>
</head>
<body>
	<div class="">
		<form action="/ToGo/myPage/modifyPro" method="post">
			<table>
				<tr>
					<td>* 아이디</td>
					<td><input type="text" name="id"value="${dto.id}" readonly></td>
				</tr>
				<tr>
					<td>* 이메일</td>
					<td><input type="text" name="email" value="${dto.email}" readonly></td>
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<td><input type="password" name="pw" value="${dto.pw}"></td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="nickname" value="${dto.nickname}" ></td>
				</tr>

				<tr>
					<td>* 생일</td>
					<td><input type="text" name="birthday" value="${dto.birthday}" readonly></td>
				</tr>
				<tr>
				    <td>* 성별</td>
			        <td>
			            <select name="k_gender">
			                <option value="male" ${dto.gender == 'male' ? 'selected' : ''}>남자</option>
			                <option value="female" ${dto.gender == 'female' ? 'selected' : ''}>여자</option>
			            </select>
			        </td>
				</tr>

				<tr>
					<td>* 성향</td>
					<td><input type="text" name="mbti" value="${dto.mbti}" readonly></td>
				</tr>
				
				<tr>
				<td colspan="2" align="center">
				<input type="submit" value="수정하기">
				<input type="button" onclick="location.href='/ToGo/trip/main'" value="취소"/> <br />
				</td>
				</tr>
			</table>
			
		</form>
	</div>
	
</body>
</html>