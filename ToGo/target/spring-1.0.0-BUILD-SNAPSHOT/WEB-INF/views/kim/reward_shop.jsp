<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reward-shop</title>
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<div>
	내 포인트 : ${cash}
	<div>
		<c:forEach var="goods" items="${goods}">
			<div style="display: inline-block; margin-right: 20px;">
				<img src="${goods.imageUrl}" alt="${goods.name}" style="width: 300px; height: 300px;"/>
				<p>${goods.name} - ${goods.points}pt</p>
			</div>
		</c:forEach>
	</div>
</div>
</body>
</html>