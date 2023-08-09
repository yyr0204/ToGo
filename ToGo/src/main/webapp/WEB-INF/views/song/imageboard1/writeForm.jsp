<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시판</title>
<style>
/* Add your custom CSS styles here */
body {
    font-family: Arial, sans-serif;
}

h3 {
	text-align: center;
    margin-bottom: 20px;
}

table {
    width: 100%;
    border-collapse: collapse;
    border: 1px solid black;
}

th, td {
    border: 1px solid black;
    padding: 10px;
}

th {
    background-color: #f2f2f2;
}

td {
    vertical-align: middle;
}

a {
    color: #007bff;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}

form {
    max-width: 800px;
    margin: 0 auto;
    background-color: #fff;
    padding: 20px;
    border-radius: 5px;
}

form table {
    width: 100%;
}

form td {
    padding: 8px;
}

form input[type="text"],
form textarea,
form select {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

form input[type="file"] {
    display: block;
    margin-top: 5px;
}

form input[type="submit"],
form input[type="reset"],
form input[type="button"] {
    background-color: #007bff;
    color: #fff;
    padding: 8px 15px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    margin-right: 10px;
}

form input[type="submit"]:hover,
form input[type="reset"]:hover,
form input[type="button"]:hover {
    background-color: #0056b3;
}

.error-msg {
    color: red;
    font-weight: bold;
    margin-bottom: 10px;
}

/* Center-align buttons */
form td:last-child {
    text-align: center;
}
</style>
</head>
<c:if test="${memId == null}" >
    <script>
        alert("로그인 후 글쓰기 가능");
        location = "ToGo/login/loginMain";
    </script>
</c:if>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
    <!-- Page content-->
    <h3>여행기 작성</h3>
    <div class="container mt-5">
        <div class="row">
            <div class="editable offset-3 col-6">
                <form name="writeform"  class="form-horizontal" action="writePro" enctype="multipart/form-data" method="post">
                    <article>
                        <div class="mb-4">
                            <!-- 제목 -->
                            <div class="mx-3 mb-2">제목</div>
                            <input id="cm_title" name="subject" type="text" class="form-control mb-3" placeholder="제목을 입력해 주세요." required />
                            <!-- 작성자 -->
                            <div class="mx-3 mb-2">작성자</div>
                            <div class="form-control mb-3">${memId}
                            <input type="hidden" name="writer" value="${memId}" ></div>
							<!-- 내 일정 -->
 							<c:if test="${userPlan.size() > 0}" >
	 							<div class="mx-3 mb-2">TripPlan</div>
	 							<select name="TripPlan" size="40" maxlength="30">
	 								<c:forEach var="dto" items="${userPlan}" varStatus="vs">
	 									<option value="${dto.plan_num}">${dto.name}</option>
	 								</c:forEach>
	 							</select>
 							</c:if>
 							
							<!-- 썸네일 -->
                            <div class="mx-3 mb-2">썸네일<input type="file" name="save" /></div>
                            <!-- 이미지 -->
                            <div class="mx-3 mb-2">이미지<input type="file" name="save" /></div>
                            <!-- 내용 -->
                            <div class="mx-3 mb-2">내용</div>
                            <textarea id="cm_content" name="content" class="form-control mb-3" placeholder="내용을 입력해 주세요." required style="width: 100%; height: 400px;"></textarea>
                            <input type="hidden" name="passwd" value="${dto.pw}">
                            <div class="btn_wrap text-end mb-5">
                                <button class="btn btn-success" type="submit" value="등록">등록</button>
                                <button class="btn btn-success" type="reset">다시 작성</button>
                                <a class="btn btn-danger waves-effect waves-light" href="/ToGo/imageboard1/list" style="color: white;">취소</a>
                            </div>
                        </div>
                    </article>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
