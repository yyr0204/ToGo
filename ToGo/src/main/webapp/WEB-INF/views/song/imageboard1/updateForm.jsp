<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시판</title>
</head>

<c:if test="${memId == null}" >
	<script>
		alert("로그인후 글수정 가능");
		location = "ToGo/login/loginMain";
	</script>
</c:if>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
    <!-- Page content-->
    <div class="container mt-5">
    <h3>여행기 수정</h3>
        <div class="row">
            <div class="editable offset-3 col-6">
                <form method = "post" class="form-horizontal" name = "updateForm" action = "updatePro" enctype="multipart/form-data" onsubmit = "return writeSave()">
                    <article>
                    	<input type = "hidden" name = "num" value = "${numContent.num}" >
						<input type = "hidden" name = "writer" value = "${numContent.writer}" >
                        <div class="mb-4">
                            <!-- 제목 -->
                            <div class="mx-3 mb-2">제목</div>
                            <input id="cm_title" name="subject" type="text" class="form-control mb-3" placeholder="제목을 입력해 주세요." value ="${numContent.subject}" required />
                            <!-- 작성자 -->
                            <div class="mx-3 mb-2">작성자</div>
                            <div class="form-control mb-3">${numContent.writer}
                            <input type="hidden" name="writer" value="${numContent.writer}" ></div>
							<!-- 썸네일 -->
                            <div class="mx-3 mb-2">썸네일<input type="file" name="save" />
                            	<input type = "hidden" name = "thumbnail" value = "${numContent.thumbnail}" >
                            </div>
                            <!-- 이미지 -->
                            <div class="mx-3 mb-2">이미지<input type="file" name="save" />
                          	<input type = "hidden" name = "image" value = "${numContent.image}" ></div>
                            <!-- 내용 -->
                            <div class="mx-3 mb-2">내용</div>
                            <textarea id="cm_content" name="content" class="form-control mb-3" placeholder="내용을 입력해 주세요." required style="width: 100%; height: 400px;">${numContent.content}</textarea>
                            <input type="hidden" name="passwd" value="${dto.pw}">
                            <div class="btn_wrap text-end mb-5">
                                <button class="btn btn-success" type="submit" value="글수정">글수정</button>
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