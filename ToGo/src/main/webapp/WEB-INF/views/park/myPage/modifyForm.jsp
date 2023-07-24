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
		<form action="/myPage/modifyPro" method="post">
			<table>
				<tr>
					<td>* 아이디</td>
					<td><input type="text" name="id" value="${dto.id}" readonly>
					</td>
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<td><input type="password" name="memberPw" value=""></td>
				</tr>
				<tr>
					<td>* 이메일</td>
					<td><input type="text" name="memberName" value="${dto.email}" readonly></td>
				</tr>

				<tr>
					<td>* 성별</td>
					<td><input type="text" name="memberEmail" value="${dto.gender}" readonly></td>
				</tr>
				<tr>
					<td>* 성향</td>
					<td><input type="text" name="mbti" value="${dto.mbti}" readonly></td>
				</tr>
				
				<tr>
				<td colspan="2" align="center">
				<input type="submit" value="수정하기">
				</td>
				</tr>
			</table>
			
		</form>
	</div>
	
</body>
</html>