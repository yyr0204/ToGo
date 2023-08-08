<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script src="${pageContext.request.contextPath}/resources/static/js/jquery.js"></script>
<meta charset="UTF-8">
<title>Reward-shop</title>
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<div>
	내 포인트 : ${cash}
	<div style="display: grid; grid-template-columns: repeat(5, 250px);grid-template-rows: repeat(5,30vh)">
		<c:forEach var="goods" items="${goods}">
			<div style="display:grid; justify-items: center;align-items: center; margin-right: 20px;">
				<img src="${goods.imageUrl}" alt="${goods.name}" style="width: 125px; height: 125px;"/>
				<p>${goods.name} - ${goods.points}pt</p>
				<input class="ex_bt" type="button" value="교환하기" style="border: none;box-shadow: 3px 3px 2px rgb(0, 0, 0, 0.2)">
			</div>
		</c:forEach>
	</div>
</div>

</body>
</html>