<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>community</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="${pageContext.request.contextPath}/resources/static/song/css/styles.css" rel="stylesheet" />
    <!-- HEAD CONTENT -->
</head>
<body>
    <%@ include file="/WEB-INF/views/include/header.jsp" %>
    <!-- Page Content -->
    <div class="container mt-5">
        <div class="row">
            <div class="col-lg-8">
                <form name="modifyBoard" method="post" action="/ToGo/board/cmModifyPro">
                    <!-- Post Content -->
                    <article>
                        <!-- Post Header -->
                        <header class="mb-4">
                            <input type="hidden" name="cm_no" value="${dto.cm_no}" />
                            <!-- 제목 -->
                            <div class="mx-3 mb-2">제목</div>
                            <textarea id="cm_title" name="cm_title" rows="1" class="form-control mb-3" required>${dto.cm_title}</textarea>
                            <!-- 작성자 -->
                            <div class="mx-3 mb-2">작성자</div>
                            <div class="form-control mb-3">${memId}</div>
                            <!-- 내용 -->
                            <div class="mx-3 mb-2">내용</div>
                            <textarea id="cm_content" name="cm_content" class="form-control" required style="width: 100%; height: 400px;">${dto.cm_content}</textarea>
                        </header>
                        <div class="btn_wrap text-end mb-5">
                            <button class="btn btn-success" type="submit" id="write" value="등록">등록</button>
                            <a class="btn btn-danger waves-effect waves-light" href="/ToGO/board/cmView?cm_no=${dto.cm_no}" style="color: white;">취소</a>
                        </div>
                    </article>
                </form>
            </div>
        </div>
    </div>
    
    <footer>
        <!-- FOOTER CONTENT -->
    </footer>
</body>
</html>
