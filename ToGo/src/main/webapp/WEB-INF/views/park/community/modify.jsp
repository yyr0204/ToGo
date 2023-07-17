<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>community</title>
    <!-- HEAD CONTENT -->
</head>
<body>
    <!-- NAV -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a href="/board/home">
                <h3 class="navbar-brand">Board</h3>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <c:choose>
                        <c:when test="${loginId == null}">
                            <li class="nav-item"><a class="btn btn-secondary" href="/board/home">홈</a></li>
                            <li class="nav-item"><a class="btn btn-secondary mx-1" href="/board/register">회원가입</a></li>
                            <li class="nav-item"><a class="btn btn-secondary" href="/board/login">로그인</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item"><a class="btn btn-secondary" href="/board/home">홈</a></li>
                            <li class="nav-item"><a class="btn btn-secondary mx-1" href="/board/write">글쓰기</a></li>
                            <li class="nav-item"><a class="btn btn-secondary" href="/board/logout">로그아웃</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </nav>
    <!-- NAV 끝 -->
    
    <!-- Page Content -->
    <div class="container mt-5">
        <div class="row">
            <div class="col-lg-8">
                <form name="modifyBoard" method="post" action="/board/update">
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
                            <div class="form-control mb-3">${loginId}</div>
                            <!-- 내용 -->
                            <div class="mx-3 mb-2">내용</div>
                            <textarea id="cm_content" name="cm_content" class="form-control" required style="width: 100%; height: 400px;">${dto.cm_content}</textarea>
                        </header>
                        <div class="btn_wrap text-end mb-5">
                            <button class="btn btn-success" type="submit" id="write" value="등록">등록</button>
                            <a class="btn btn-danger waves-effect waves-light" href="/board/view?cm_no=${dto.cm_no}" style="color: white;">취소</a>
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
