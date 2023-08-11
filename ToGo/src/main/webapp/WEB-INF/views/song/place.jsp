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

/* 헤더 스타일링 */
h2 {
  font-size: 24px;
  color: #333;
  margin-bottom: 10px;
}

/* 여행일 수 스타일링 */
.h3-days {
  font-size: 18px;
  color: #666;
  margin-top: -10px;
  margin-bottom: 20px;
}

/* 스케줄 바 스타일링 */
.schedule_bar {
  width: 100%;
  overflow: auto;
}

/* 일차 스타일링 */
.day {
  float: right;
  clear: both;
  margin-bottom: 20px;
}

.day h1 {
  font-size: 28px;
  color: #e74c3c;
  margin-top: 0;
}

/* 스케줄 항목 스타일링 */
.schedule-item {
  float: left;
  width: 250px;
  margin-right: 20px;
  margin-bottom: 20px;
  padding: 10px;
  border: 1px solid #ddd;
  background-color: #f9f9f9;
}

.schedule-item img {
  max-width: 100%;
  height: auto;
}

.schedule-item h3 {
  font-size: 18px;
  color: #333;
  margin-top: 10px;
}

.schedule-item a {
  text-decoration: none;
  color: #3498db;
  transition: color 0.3s;
}

.schedule-item a:hover {
  color: #2980b9;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<div class="container">
		<h2>내 일정</h2>
		<form method="post" action="/ToGo/board/cmMain" id="list">
			<input type="hidden" name="curPage" value="1" />
		</form>
		<h2>(총 여행일 : ${userPlan.size()}일)</h2>
		<br />
		<div class="schedule_bar" >
			<c:forEach var = "dto" items = "${day}" varStatus = "vs">
				<div>
					<br />
					<h1> ${vs.count}일차 </h1>
					<c:forEach var = "dto2" items = "${dto}" varStatus = "vs">
						<div>
							<h3> ${dto2} </h3>
							<a href="">
								<img src = "" width = "250" height = "200" />
							</a><br />
							<h3> 9:00~10:00 </h3>
						</div>
					</c:forEach>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- Pagination-->
	<nav aria-label="Pagination">
		<hr class="my-0" />
		<ul class="pagination justify-content-center my-4">
			<c:if test="${pr.startPage > pr.pagePerBlock}">
				<li class="page-item"><a class="page-link"
					href="/ToGo/board/cmMain?pageNum=1&option=${option}&keyword=${keyword}">처음<i
						class="fs-3 bi bi-caret-left-fill"></i>
				</a></li>
				<li class="page-item"><a class="page-link"
					href="/ToGo/board/cmMain?pageNum=${pr.startPage - 1}&option=${option}&keyword=${keyword}">이전<i
						class="fs-3 bi bi-caret-left"></i>
				</a></li>
			</c:if>
			<c:forEach begin="${pr.startPage}" end="${pr.endPage}" var="pNum">
				<li class="page-item ${pr.page == pNum ? 'active' : ''}"
					aria-current="${pr.page == pNum ? 'page' : ''}"><a
					class="page-link"
					href="/ToGo/board/cmMain?pageNum=${pNum}&option=${option}&keyword=${keyword}"
					name="pageNum">${pNum}</a></li>
			</c:forEach>
			<c:if test="${pr.endPage < pr.totalPage}">
				<li class="page-item"><a class="page-link"
					href="/ToGo/board/cmMain?pageNum=${pr.endPage + 1}&option=${option}&keyword=${keyword}">다음<i
						class="fs-3 bi bi-caret-right"></i>
				</a></li>
				<li class="page-item"><a class="page-link"
					href="/ToGo/board/cmMain?pageNum=${pr.totalPage}&option=${option}&keyword=${keyword}">맨끝<i
						class="fs-3 bi bi-caret-right-fill"></i>
				</a></li>
			</c:if>
		</ul>
	</nav>
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