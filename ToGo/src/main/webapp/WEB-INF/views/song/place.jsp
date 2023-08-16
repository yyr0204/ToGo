<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
.container {
	max-width: 800px;
	margin: 0 auto;
}

.search-box {
	margin-bottom: 20px;
	padding: 10px;
	background-color: #f2f2f2;
	border-radius: 5px;
	display: flex;
	justify-content: space-between;
}

.search-box select, .search-box input[type="text"], .search-box button[type="submit"]
	{
	padding: 5px 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.search-box select {
	min-width: 80px;
}

.search-box input[type="text"] {
	flex: 1;
	margin-right: 10px;
}

.search-box button[type="submit"] {
	background-color: #007bff;
	color: #fff;
	cursor: pointer;
}

.search-box button[type="submit"]:hover {
	background-color: #0056b3;
}

.board-table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

.board-table th, .board-table td {
	border: 1px solid #ccc;
	padding: 8px;
	text-align: left;
}

.board-table th {
	background-color: #f2f2f2;
}

.board-table .left {
	text-align: left;
}

.board-table .image {
	width: 15px;
	height: 15px;
	vertical-align: middle;
}
.write-link a:hover {
    background-color: #007bff;
    color: #fff;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<div class="container">
		<h3>내 일정</h3>
		<form method="post" action="/ToGo/board/cmMain" id="list">
			<input type="hidden" name="curPage" value="1" />
		</form>
		<h3>(총 여행일 : ${userPlan.size()}일)</h3>
		<br />
		<c:forEach var = "dto" items = "${day}" varStatus = "vs">
			<h1> ${vs.count}일차 </h1>
			<div class="schedule_bar card-group" style="width: 100%; overflow: auto;">
					<c:forEach var = "dto2" items = "${dto}" varStatus = "vs">
						<div class="card">
							<img src="https://cdn.imweb.me/upload/S2017101359e025984d346/bff36a6d2ced4.jpg" class="card-img-top" onclick="" />
							<div class="card-body">
								<p class="card-text">${dto2} </p>
								<p class="card-text"><small class="text-muted">${time.get(vs.index)}</small></p>
						    </div>
						</div>
					</c:forEach>
					<br />
			</div>
		</c:forEach>
	</div>

<script>
	// $('.schedule_bar').click(()=>{
    //     var target = $(event.target)
    //     if(target.prev().css('display')!=='hide') {
    //         target.parent().children().hide()
    //         target.show()
    //     }else{
    //         target.parent().children().show()
    //     }
	// })
</script>
</body>
</html>