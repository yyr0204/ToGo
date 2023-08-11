<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>ToGo</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${pageContext.request.contextPath}/resources/static/song/css/styles.css" rel="stylesheet" />
        
        <!-- //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
        <!-- 이벤트 정보 슬라이드 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
			integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
			crossorigin="anonymous">
		<!-- //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
		
		
		<!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles2.css" rel="stylesheet" />
		<c:if test="${uPTrue==1}">
        	<script>
        		alert("수정되었습니다.");
        	</script>
        </c:if>
		
    </head>
    <body class="d-flex flex-column h-100">
        <main class="flex-shrink-0">
            <!-- Navigation-->
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark" >
                <div class="container px-5">
                    <a class="navbar-brand" href="/ToGo/trip/main" ><h1>ToGo</h1></a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        	<a class="btn btn-primary btn-xl text-uppercase" href="/ToGo/trip/plan" style="width:250px;height:50px"><h3>일 정 생 성</h3></a>
				<!--		&nbsp;&nbsp;&nbsp; <a class="btn btn-primary btn-xl text-uppercase" href="/ToGo/trip/plan" style="width:250px;height:50px"><h3>주변 검색</h3></a>	-->
                        </ul>
                        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        	
                        	<li class="nav-item"><a class="nav-link" href="/ToGo/User/reco_place">내 주변엔 뭐가 있지?</a></li>
                            <li class="nav-item dropdown">
                            	<c:if test="${memId != null}">
                            		<a class="nav-link dropdown-toggle" id="navbarDropdownBlog" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">리워드 샵</a>
                                	<ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownBlog">
                                		<li><a class="dropdown-item" href="/ToGo/User/reward">리워드 받기</a></li>
                                		<li><a class="dropdown-item" href="/ToGo/User/reward_shop">리워드 샵</a></li>
                                	</ul>
                            	</c:if>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" id="navbarDropdownBlog" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">게시판</a>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownBlog">
                                    <li><a class="dropdown-item" href="/ToGo/board/cmMain">커뮤니티</a></li>
                                    <li><a class="dropdown-item" href="/ToGo/imageboard1/list">여행기</a></li>
                                    <li><a class="dropdown-item" href="/ToGo/board/fstvlList">축제 모아모아</a></li>
                                    <li><a class="dropdown-item" href="/ToGo/board/beach">해수욕장 개폐장일</a></li>
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
                                <a class="nav-link dropdown-toggle" id="navbarDropdownPortfolio" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">${(memId == null)&&(adminId==null) ? '로그인' : level=='3' ? '관리' : '내정보'}</a>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownPortfolio">
                                    
                                    <c:if test="${memId != null}">
                                    	<li><a class="dropdown-item" href="/ToGo/myPage/user_check">내 정보</a></li>
	                                    <li><a class="dropdown-item" href="/ToGo/trip/myPlan">내 일정</a></li>
	                                    <li><a class="dropdown-item" href="/ToGo/imageboard1/mylist">나의 여행기</a></li>
	                                    <li><a class="dropdown-item" href="/ToGo/User/activeList">활동 내역</a></li>
	                                    <li><a class="dropdown-item" href="/ToGo/login/logout">로그아웃</a></li>
									</c:if>
									<c:if test="${(memId == null) && (adminId==null)}">
                                    	<li><a class="dropdown-item" href="/ToGo/login/loginMain">로그인</a></li>
	                                    <li><a class="dropdown-item" href="">아이디 찾기</a></li>
	                                    <li><a class="dropdown-item" href="">비밀번호 찾기</a></li>
	                                    <li><a class="dropdown-item" href="">회원가입</a></li>
									</c:if>
                     				<c:if test="${adminId != null}">
	                                    <li><a class="dropdown-item" href="/ToGo/admin/userManagement">회원관리</a></li>
	                                    <li><a class="dropdown-item" href="/ToGo/User/Admin_reward">리워드 관리</a></li>
	                                    <li><a class="dropdown-item" href="/ToGo/login/logout">로그아웃</a></li>
									</c:if>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <!-- Header-->
            <header class="bg-dark py-5">
                <div class="container px-5">
                
                
                	<!-- //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
					<!-- 이벤트 정보 슬라이드 -->
					<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
					  <ol class="carousel-indicators">
					    <c:forEach items="${fstvlList}" var="fstvl" varStatus="status">
					      <li data-bs-target="#carouselExampleCaptions" data-bs-slide-to="${status.index}"${status.index == 0 ? ' class="active"' : ''}></li>
					    </c:forEach>
					  </ol>
					  <div class="carousel-inner">
					    <c:forEach items="${fstvlList}" var="fstvl" varStatus="status">
					      <div class="carousel-item${status.index == 0 ? ' active' : ''}">
					        <a href="${fstvl.website}" target="_blank">
					          <img src="${fstvl.image_url}" class="d-block w-100" alt="${fstvl.title}" style="object-fit: cover;">
					        </a>
					        <div class="carousel-caption d-none d-md-block" style="text-shadow: -1px -1px 0 black, 1px -1px 0 black, -1px 1px 0 black, 1px 1px 0 black; color: white;">
					          <h5>${fstvl.title}</h5>
					          <p>${fstvl.period}</p>
					        </div>
					      </div>
					    </c:forEach>
					  </div>
					  <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-bs-slide="prev">
					    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
					    <span class="visually-hidden">Previous</span>
					  </a>
					  <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-bs-slide="next">
					    <span class="carousel-control-next-icon" aria-hidden="true"></span>
					    <span class="visually-hidden">Next</span>
					  </a>
					</div>
					<!-- //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
                    
                    
                </div>
            </header>
			<br/>
			<br/>
			
	
            <!-- Features section-->
			<section class="page-section bg-light" id="portfolio">
	            <div class="container">
	                <div class="text-center">
	                    <h2 class="section-heading text-uppercase">우리나라 관광지</h2>
	                    <h3 class="section-subheading text-muted">Travel destinations in Korea</h3>
	                </div>
	                <div class="row">
	                    <div class="col-lg-4 col-sm-6 mb-4">
	                        <!-- Portfolio item 1-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" href="/ToGo/trip/popularPlace?area=${cityList.get(0).text}" >
	                                <div class="portfolio-hover">
	                                <br />
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/city_img/${cityList.get(0).img}.jpg" />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-subheading text-muted"><h3>${cityList.get(0).text}</h3></div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-4 col-sm-6 mb-4">
	                        <!-- Portfolio item 2-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" href="/ToGo/trip/popularPlace?area=${cityList.get(1).text}">
	                                <div class="portfolio-hover">
	                                <br />
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/city_img/${cityList.get(1).img}.jpg" />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-subheading text-muted"><h3>${cityList.get(1).text}</h3></div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-4 col-sm-6 mb-4">
	                        <!-- Portfolio item 3-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" href="/ToGo/trip/popularPlace?area=${cityList.get(2).text}">
	                                <div class="portfolio-hover">
	                                <br />
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/city_img/${cityList.get(2).img}.jpg" />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-subheading text-muted"><h3>${cityList.get(2).text}</h3></div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-4 col-sm-6 mb-4 mb-lg-0">
	                        <!-- Portfolio item 4-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" href="/ToGo/trip/popularPlace?area=${cityList.get(3).text}">
	                                <div class="portfolio-hover">
	                                <br />
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/city_img/${cityList.get(3).img}.jpg" />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-subheading text-muted"><h3>${cityList.get(3).text}</h3></div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-4 col-sm-6 mb-4 mb-sm-0">
	                        <!-- Portfolio item 5-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" href="/ToGo/trip/popularPlace?area=${cityList.get(4).text}">
	                                <div class="portfolio-hover">
	                                <br />
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/city_img/${cityList.get(4).img}.jpg" />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-subheading text-muted"><h3>${cityList.get(4).text}</h3></div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-4 col-sm-6">
	                        <!-- Portfolio item 6-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" href="/ToGo/trip/popularPlace?area=${cityList.get(5).text}">
	                                <div class="portfolio-hover">
	                                <br />
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/city_img/${cityList.get(5).img}.jpg" />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-subheading text-muted"><h3>${cityList.get(5).text}</h3></div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="col-lg-4 col-sm-6 mb-4">
	                        <!-- Portfolio item 1-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" href="/ToGo/trip/popularPlace?area=${cityList.get(6).text}">
	                                <div class="portfolio-hover">
	                                <br />
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/city_img/${cityList.get(6).img}.jpg" />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-subheading text-muted"><h3>${cityList.get(6).text}</h3></div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-4 col-sm-6 mb-4">
	                        <!-- Portfolio item 2-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" href="/ToGo/trip/popularPlace?area=${cityList.get(7).text}">
	                                <div class="portfolio-hover">
	                                <br />
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/city_img/${cityList.get(7).img}.jpg" />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-subheading text-muted"><h3>${cityList.get(7).text}</h3></div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-4 col-sm-6 mb-4">
	                        <!-- Portfolio item 3-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" href="/ToGo/trip/popularPlace?area=${cityList.get(8).text}">
	                                <div class="portfolio-hover">
	                                <br />
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/city_img/${cityList.get(8).img}.jpg" />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-subheading text-muted"><h3>${cityList.get(8).text}</h3></div>
	                            </div>
	                        </div>
	                    </div>
		            </div>
		        </section>
            
            
            

            
            <!-- Blog preview section-->
            <section class="py-5">
                <div class="container px-5 my-5">
                    <div class="row gx-5 justify-content-center">
                        <div class="col-lg-8 col-xl-6">
                            <div class="text-center">
                                <h2 class="fw-bolder">인기 여행</h2>
                                <p class="lead fw-normal text-muted mb-5">여행 기록 Best3</p>
                            </div>
                        </div>
                    </div>
                    <div class="row gx-5">
                        <div class="col-lg-4 mb-5" >
                            <div class="card h-100 shadow border-0" >
                                <img class="card-img-top" src="/ToGo/resources/static/song/upload/${wePlan.get(0).thumbnail}" onerror="this.src='https://dummyimage.com/600x350/adb5bd/495057'" style="width:375px; height:220px;"/>
                                <div class="card-body p-4">
                                    <div class="badge bg-primary bg-gradient rounded-pill mb-2">Hot</div>
                                    <a class="text-decoration-none link-dark stretched-link" href="/ToGo/imageboard1/contentForm?num=${wePlan.get(0).num}&pageNum=1&pr_pageNum=1"><h5 class="card-title mb-3">${wePlan.get(0).subject}</h5></a>
                                    <p class="card-text mb-0">${wePlan.get(0).content}</p>
                                </div>
                                <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                                    <div class="d-flex align-items-end justify-content-between">
                                        <div class="d-flex align-items-center">
                                            <img class="rounded-circle me-3" src="${wePlan.get(0).thumbnail}" />
                                            <div class="small">
                                                <div class="fw-bold">${wePlan.get(0).writer}</div>
                                                <div class="text-muted">${wePlan.get(0).reg_date}, 조회수 : ${wePlan.get(0).readcount}</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 mb-5">
                            <div class="card h-100 shadow border-0">
                                <img class="card-img-top" src="/ToGo/resources/static/song/upload/${wePlan.get(1).thumbnail}" onerror="this.src='https://dummyimage.com/600x350/adb5bd/495057'" style="width:375px; height:220px;"/>
                                <div class="card-body p-4">
                                    <div class="badge bg-primary bg-gradient rounded-pill mb-2">Hot</div>
                                    <a class="text-decoration-none link-dark stretched-link" href="/ToGo/imageboard1/contentForm?num=${wePlan.get(1).num}&pageNum=1&pr_pageNum=1"><h5 class="card-title mb-3">${wePlan.get(1).subject}</h5></a>
                                    <p class="card-text mb-0">${wePlan.get(1).content}</p>
                                </div>
                                <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                                    <div class="d-flex align-items-end justify-content-between">
                                        <div class="d-flex align-items-center">
                                            <img class="rounded-circle me-3" src="${wePlan.get(1).thumbnail}" />
                                            <div class="small">
                                                <div class="fw-bold">${wePlan.get(1).writer}</div>
                                                <div class="text-muted">${wePlan.get(1).reg_date}, 조회수 : ${wePlan.get(1).readcount}</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 mb-5">
                            <div class="card h-100 shadow border-0">
                                <img class="card-img-top" src="/ToGo/resources/static/song/upload/${wePlan.get(2).thumbnail}" onerror="this.src='https://dummyimage.com/600x350/adb5bd/495057'" style="width:375.99px; height:220px;"/>
                                <div class="card-body p-4">
                                    <div class="badge bg-primary bg-gradient rounded-pill mb-2">Hot</div>
                                    <a class="text-decoration-none link-dark stretched-link" href="/ToGo/imageboard1/contentForm?num=${wePlan.get(2).num}&pageNum=1&pr_pageNum=1"><h5 class="card-title mb-3">${wePlan.get(2).subject}</h5></a>
                                    <p class="card-text mb-0">${wePlan.get(2).content}</p>
                                </div>
                                <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                                    <div class="d-flex align-items-end justify-content-between">
                                        <div class="d-flex align-items-center">
                                            <img class="rounded-circle me-3" src="${wePlan.get(2).thumbnail}" />
                                            <div class="small">
                                                <div class="fw-bold">${wePlan.get(2).writer}</div>
                                                <div class="text-muted">${wePlan.get(2).reg_date}, 조회수 : ${wePlan.get(2).readcount}</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <!-- Footer-->
        <footer class="bg-dark py-4 mt-auto">
            <div class="container px-5">
                <div class="row align-items-center justify-content-between flex-column flex-sm-row">
                    
                </div>
            </div>
        </footer>
        
        
      
      
		<script type="text/javascript">
		// 로그아웃
		  function unlinkApp() {
		    Kakao.API.request({
		      url: '/v1/user/unlink',
		      success: function(res) {
		        alert('로그아웃되었습니다.')
		        window.location.href = '/WG/user/kakaologout';
		      },
		      fail: function(err) {
		        alert('fail: ' + JSON.stringify(err))
		      },
		    })
		  }
		</script>
       	
       	<!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="${pageContext.request.contextPath}/resources/static/song/js/scripts.js"></script>
        
        
        <!-- //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
        <!-- 이벤트 정보 슬라이드 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
			crossorigin="anonymous"></script>
		<script>
		  $(document).ready(function() {
		    $('.carousel').carousel({
		      interval: 10000
		    });
		  });
		</script>
		<style>
		  .carousel-item img {
		    object-fit: cover;
		    max-height: 400px;
		  }
		</style>
		<!-- //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
		
		
		<!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts2.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
		
			
    </body>
</html>
