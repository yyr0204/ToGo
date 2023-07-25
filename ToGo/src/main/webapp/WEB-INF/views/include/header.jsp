<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="${pageContext.request.contextPath}/resources/static/song/css/styles.css" rel="stylesheet" />
	
	<!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="${pageContext.request.contextPath}/resources/static/song/js/scripts.js"></script>
</head>
<boay>
<!-- Navigation-->
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container px-5">
                    <a class="navbar-brand" href="/ToGo/trip/main" ><h1>ToGo</h1></a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        	<a class="btn btn-primary btn-xl text-uppercase" href="/ToGo/trip/plan" style="width:250px;height:50px"><h3>일 정 생 성</h3></a>
                        </ul>
                        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        	
                            <li class="nav-item"><a class="nav-link" href="">리워드Shop</a></li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" id="navbarDropdownBlog" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">게시판</a>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownBlog">
                                    <li><a class="dropdown-item" href="">공유 일정</a></li>
                                    <li><a class="dropdown-item" href="/ToGo/board/cmMain">커뮤니티</a></li>
                                    <li><a class="dropdown-item" href="/ToGo/imageboard1/list">여행기</a></li>
                                    <li><a class="dropdown-item" href="/ToGo/board/fstvlList">축제 모아모아</a></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" id="navbarDropdownBlog" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">FAQ</a>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownBlog">
                                    <li><a class="dropdown-item" href="/ToGo/board/faqList">자주묻는 질문</a></li>
                                    <li><a class="dropdown-item" href="/ToGo/board/qnaList">1:1문의</a></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" id="navbarDropdownPortfolio" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">${memId == null ? '로그인' : '내 정보'}</a>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownPortfolio">
                                    
                                    <c:if test="${memId != null}">
                                    	<li><a class="dropdown-item" href="/ToGo/myPage/user_check">내 정보</a></li>
	                                    <li><a class="dropdown-item" href="">내 일정</a></li>
	                                    <li><a class="dropdown-item" href="">나의 여행기</a></li>
	                                    <li><a class="dropdown-item" href="">활동 내역</a></li>
	                                    <li><a class="dropdown-item" href="/ToGo/login/logout">로그아웃</a></li>
									</c:if>
									<c:if test="${memId == null}">
                                    	<li><a class="dropdown-item" href="/ToGo/login/loginMain">로그인</a></li>
	                                    <li><a class="dropdown-item" href="">아이디 찾기</a></li>
	                                    <li><a class="dropdown-item" href="">비밀번호 찾기</a></li>
	                                    <li><a class="dropdown-item" href="">회원가입</a></li>
									</c:if>
                     				
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
</boay>
</html>