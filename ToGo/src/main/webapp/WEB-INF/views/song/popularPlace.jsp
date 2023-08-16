<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>내 일정</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/board.css">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="${pageContext.request.contextPath}/resources/static/js/jquery.js"></script>
	
	<style>
		.popularMain{
			margin:10px 300px;
			display:flex;
			flex-direction: row;
    		flex-wrap: wrap;
		}
		.popularSub{
			width:40%;
			border: 2px solid #eee;
		    border-radius: 0.3rem;
		    margin: 0px 10px;
		}
		span{
			display: inline-block;
			width:20px;
		}
		.popularP{
			margin:5px 0px;
			padding:0px;
		}
		.popularUpperP{
			padding:0px 20px 20px 20px;
		}
		p>a{
			color:#666;
			text-decoration: none;
		}
		
	</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<div class="popularMain">
		<div class="popularSub">
			<h1 style="font-weight: 700; padding:20px;">관광지 인기 TOP</h1>
			<div class="popularUpperP">
				<c:forEach var="dto" items="${main}" varStatus="vs">
					<p class="popularP">
						<c:if test="${vs.count< 4}">
							<span class="badge bg-primary" style="display:inline-block; width:50px; text-align: center;">${vs.count}</span>
						</c:if>
						<c:if test="${vs.count > 3}">
							<span class="badge bg-secondary" style="display:inline-block; width:50px; text-align: center;">${vs.count}</span>
						</c:if>
						<a href="https://map.naver.com/v5/search/${dto.name}" target="_blank">${dto.name} </a>
					</p>
				</c:forEach>
			</div>
		</div>
		
		<div class="popularSub">	
			<h1 style="font-weight: 700; padding:20px;">음식점 인기 TOP</h1>
			<div class="popularUpperP">
				<c:forEach var="dto" items="${sub}" varStatus="vs">
					<p class="popularP">
						<c:if test="${vs.count< 4}">
							<span class="badge bg-primary" style="display:inline-block; width:50px; text-align: center;">${vs.count}</span>
						</c:if>
						<c:if test="${vs.count > 3}">
							<span class="badge bg-secondary" style="display:inline-block; width:50px; text-align: center;">${vs.count}</span>
						</c:if>
						<a href="https://map.naver.com/v5/search/${dto.name}" target="_blank">${dto.name} </a>
					</p>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>