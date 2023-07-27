<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>

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
		<form method="post" action="/ToGo/admin/userManagement" id="list">
			<input type="hidden" name="curPage" value="1" />
			<div class="search-box">
				<div>
					<select name="option">
						<option value="all">전체</option>
						<option value="id">아이디</option>
						<option value="email">이메일</option>
						<option value="nick">닉네임</option>
						<option value="status">상태</option>
					</select> <input name="keyword" type="text" placeholder="검색어를 입력하세요..."
						aria-describedby="button-search" />
					<button type="submit">
						<i>검색</i>
					</button>
				</div>
			</div>
		</form>
		<h3>총 회원 수 : ${pr.total}</h3>
		<form method="post" action="/ToGo/admin/chStatus">
		<table class="board-table">
			<c:if test="${empty userlist}">
				<h2 class="my-5 text-center">회원이 없습니다.</h2>
			</c:if>
			<tr>
				<th class="w-px60">번호</th>
				<th>아이디</th>
				<th class="w-px100">이메일</th>
				<th class="w-px100">닉네임</th>
				<th class="w-px60">성별</th>
				<th class="w-px60">생일</th>
				<th class="w-px60">성향</th>
				<th class="w-px60">캐시</th>
<!-- 				<th class="w-px60">프로필 사진</th> -->
				<th class="w-px60">상태</th>
			</tr>
			<c:forEach items="${userlist }" var="dto">
				<tr>
					<td></td>
					<td>${dto.id}</td>
					<td>${dto.email}</td>
					<td>${dto.nickname}</td>
					<td>${dto.gender}</td>
					<td>${dto.birthday}</td>
					<td>${dto.mbti}</td>
					<td>${dto.cash}</td>
<%-- 					<td><img src='${dto.profile_img}'></td> --%>
					<td>${dto.status} 
		                <input class="btn btn-success" type="submit" value="${dto.status=='Y' ? '비활성화' : '활성화' }" />
		            </td>
				</tr>
			</c:forEach>
		<input type="hidden" name="id" value="${dto.id}" />
		<input type="hidden" name="status" value="${dto.status}" />
		</table>
		</form>
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
<c:if test="chStatus==1">
	<script>
		alert("상태가 변경되었습니다.")
	</script>
</c:if>
</body>
</html>