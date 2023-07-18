<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>community</title>
    <h2>세션스코프 : ${sessionScope.memId}</h2>
    <h2>그냥 : ${memId}</h2>
</head>
<body>
    <!-- NAV -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a href="/ToGo/board/cmMain">
                <h3 class="navbar-brand">ToGo</h3>
            </a>
<!--             <button class="navbar-toggler" type="button" data-bs-toggle="collapse" -->
<!--                 data-bs-target="#navbarSupportedContent" -->
<!--                 aria-controls="navbarSupportedContent" aria-expanded="false" -->
<!--                 aria-label="Toggle navigation"> -->
<!--                 <span class="navbar-toggler-icon"></span> -->
<!--             </button> -->
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <c:choose>
                        <c:when test="${sessionScope.memId} == null}">
                            <li class="nav-item"><a class="btn btn-secondary"
                                href="/ToGo/board/cmMain">홈</a></li>
<!--                             <li class="nav-item"><a class="btn btn-secondary mx-1" -->
<!--                                 href="/ToGo/login/loginMain">회원가입</a></li> -->
                            <li class="nav-item"><a class="btn btn-secondary"
                                href="/ToGo/login/loginMain">로그인</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item"><a class="btn btn-secondary"
                                href="/ToGo/board/cmMain">홈</a></li>
<!--                             <li class="nav-item"><a class="btn btn-secondary mx-1" -->
<!--                                 href="/ToGo/board/cmWrite">글쓰기</a></li> -->
                            <li class="nav-item"><a class="btn btn-secondary"
                                href="/ToGo/login/loginMain">로그아웃</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </nav>
    <!-- NAV 끝 -->
    <!-- Page content-->
    <div class="container mt-5">
        <div class="row">
            <div class="editable offset-3 col-6">
                <form name="addBoard" class="form-horizontal" action="/ToGo/board/cmWritePro" method="post">
                    <article>
                        <div class="mb-4">
                            <!-- 제목 -->
                            <div class="mx-3 mb-2">제목</div>
                            <input id="cm_title" name="cm_title" type="text" class="form-control mb-3" placeholder="제목을 입력해 주세요." required />
                            <!-- 작성자 -->
                            <div class="mx-3 mb-2">작성자</div>
                            <div class="form-control mb-3">${sessionScope.memId}</div>
                            <!-- 내용 -->
                            <div class="mx-3 mb-2">내용</div>
                            <textarea id="cm_content" name="cm_content" class="form-control mb-3" placeholder="내용을 입력해 주세요." required style="width: 100%; height: 400px;"></textarea>
                            <div class="btn_wrap text-end mb-5">
                                <button class="btn btn-success" type="submit" id="write" value="등록">등록</button>
                                <a class="btn btn-danger waves-effect waves-light" href="/ToGo/board/home" style="color: white;">취소</a>
                            </div>
                        </div>
                    </article>
                </form>
            </div>
        </div>
    </div>
    <footer></footer>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <script type="text/javascript">
        
    </script>
</body>
</html>
