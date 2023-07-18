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
        <title>Modern Business - Start Bootstrap Template</title>
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
		
		
    </head>
    <body class="d-flex flex-column h-100">
        <main class="flex-shrink-0">
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
                        	
                            <li class="nav-item"><a class="nav-link" href="index.html">Home</a></li>
                            <li class="nav-item"><a class="nav-link" href="about.html">About</a></li>
                            <li class="nav-item"><a class="nav-link" href="contact.html">Contact</a></li>
                            <li class="nav-item"><a class="nav-link" href="pricing.html">Pricing</a></li>
                            <li class="nav-item"><a class="nav-link" href="faq.html">FAQ</a></li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" id="navbarDropdownBlog" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Blog</a>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownBlog">
                                    <li><a class="dropdown-item" href="blog-home.html">Blog Home</a></li>
                                    <li><a class="dropdown-item" href="blog-post.html">Blog Post</a></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" id="navbarDropdownPortfolio" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Portfolio</a>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownPortfolio">
                                    <li><a class="dropdown-item" href="portfolio-overview.html">Portfolio Overview</a></li>
                                    <li><a class="dropdown-item" href="portfolio-item.html">Portfolio Item</a></li>
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
	                    <h2 class="section-heading text-uppercase">Portfolio</h2>
	                    <h3 class="section-subheading text-muted">Lorem ipsum dolor sit amet consectetur.</h3>
	                </div>
	                <div class="row">
	                    <div class="col-lg-4 col-sm-6 mb-4">
	                        <!-- Portfolio item 1-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal1">
	                                <div class="portfolio-hover">
	                                    <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/assets2/img/portfolio/1.jpg" alt="..." />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-heading">Threads</div>
	                                <div class="portfolio-caption-subheading text-muted">Illustration</div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-4 col-sm-6 mb-4">
	                        <!-- Portfolio item 2-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal2">
	                                <div class="portfolio-hover">
	                                    <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/assets2/img/portfolio/2.jpg" alt="..." />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-heading">Explore</div>
	                                <div class="portfolio-caption-subheading text-muted">Graphic Design</div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-4 col-sm-6 mb-4">
	                        <!-- Portfolio item 3-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal3">
	                                <div class="portfolio-hover">
	                                    <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/assets2/img/portfolio/3.jpg" alt="..." />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-heading">Finish</div>
	                                <div class="portfolio-caption-subheading text-muted">Identity</div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-4 col-sm-6 mb-4 mb-lg-0">
	                        <!-- Portfolio item 4-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal4">
	                                <div class="portfolio-hover">
	                                    <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/assets2/img/portfolio/4.jpg" alt="..." />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-heading">Lines</div>
	                                <div class="portfolio-caption-subheading text-muted">Branding</div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-4 col-sm-6 mb-4 mb-sm-0">
	                        <!-- Portfolio item 5-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal5">
	                                <div class="portfolio-hover">
	                                    <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/assets2/img/portfolio/5.jpg" alt="..." />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-heading">Southwest</div>
	                                <div class="portfolio-caption-subheading text-muted">Website Design</div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-4 col-sm-6">
	                        <!-- Portfolio item 6-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal6">
	                                <div class="portfolio-hover">
	                                    <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/assets2/img/portfolio/6.jpg" alt="..." />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-heading">Window</div>
	                                <div class="portfolio-caption-subheading text-muted">Photography</div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="col-lg-4 col-sm-6 mb-4">
	                        <!-- Portfolio item 1-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal1">
	                                <div class="portfolio-hover">
	                                    <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/assets2/img/portfolio/1.jpg" alt="..." />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-heading">Threads</div>
	                                <div class="portfolio-caption-subheading text-muted">Illustration</div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-4 col-sm-6 mb-4">
	                        <!-- Portfolio item 2-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal2">
	                                <div class="portfolio-hover">
	                                    <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/assets2/img/portfolio/2.jpg" alt="..." />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-heading">Explore</div>
	                                <div class="portfolio-caption-subheading text-muted">Graphic Design</div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-4 col-sm-6 mb-4">
	                        <!-- Portfolio item 3-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal3">
	                                <div class="portfolio-hover">
	                                    <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/assets2/img/portfolio/3.jpg" alt="..." />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-heading">Finish</div>
	                                <div class="portfolio-caption-subheading text-muted">Identity</div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-4 col-sm-6 mb-4 mb-lg-0">
	                        <!-- Portfolio item 4-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal4">
	                                <div class="portfolio-hover">
	                                    <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/assets2/img/portfolio/4.jpg" alt="..." />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-heading">Lines</div>
	                                <div class="portfolio-caption-subheading text-muted">Branding</div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-4 col-sm-6 mb-4 mb-sm-0">
	                        <!-- Portfolio item 5-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal5">
	                                <div class="portfolio-hover">
	                                    <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/assets2/img/portfolio/5.jpg" alt="..." />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-heading">Southwest</div>
	                                <div class="portfolio-caption-subheading text-muted">Website Design</div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-lg-4 col-sm-6">
	                        <!-- Portfolio item 6-->
	                        <div class="portfolio-item">
	                            <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal6">
	                                <div class="portfolio-hover">
	                                    <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
	                                </div>
	                                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/static/song/assets2/img/portfolio/6.jpg" alt="..." />
	                            </a>
	                            <div class="portfolio-caption">
	                                <div class="portfolio-caption-heading">Window</div>
	                                <div class="portfolio-caption-subheading text-muted">Photography</div>
	                            </div>
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
                                <h2 class="fw-bolder">From our blog</h2>
                                <p class="lead fw-normal text-muted mb-5">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Eaque fugit ratione dicta mollitia. Officiis ad.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row gx-5">
                        <div class="col-lg-4 mb-5">
                            <div class="card h-100 shadow border-0">
                                <img class="card-img-top" src="https://dummyimage.com/600x350/ced4da/6c757d" alt="..." />
                                <div class="card-body p-4">
                                    <div class="badge bg-primary bg-gradient rounded-pill mb-2">News</div>
                                    <a class="text-decoration-none link-dark stretched-link" href="#!"><h5 class="card-title mb-3">Blog post title</h5></a>
                                    <p class="card-text mb-0">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                </div>
                                <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                                    <div class="d-flex align-items-end justify-content-between">
                                        <div class="d-flex align-items-center">
                                            <img class="rounded-circle me-3" src="https://dummyimage.com/40x40/ced4da/6c757d" alt="..." />
                                            <div class="small">
                                                <div class="fw-bold">Kelly Rowan</div>
                                                <div class="text-muted">March 12, 2023 &middot; 6 min read</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 mb-5">
                            <div class="card h-100 shadow border-0">
                                <img class="card-img-top" src="https://dummyimage.com/600x350/adb5bd/495057" alt="..." />
                                <div class="card-body p-4">
                                    <div class="badge bg-primary bg-gradient rounded-pill mb-2">Media</div>
                                    <a class="text-decoration-none link-dark stretched-link" href="#!"><h5 class="card-title mb-3">Another blog post title</h5></a>
                                    <p class="card-text mb-0">This text is a bit longer to illustrate the adaptive height of each card. Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                </div>
                                <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                                    <div class="d-flex align-items-end justify-content-between">
                                        <div class="d-flex align-items-center">
                                            <img class="rounded-circle me-3" src="https://dummyimage.com/40x40/ced4da/6c757d" alt="..." />
                                            <div class="small">
                                                <div class="fw-bold">Josiah Barclay</div>
                                                <div class="text-muted">March 23, 2023 &middot; 4 min read</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 mb-5">
                            <div class="card h-100 shadow border-0">
                                <img class="card-img-top" src="https://dummyimage.com/600x350/6c757d/343a40" alt="..." />
                                <div class="card-body p-4">
                                    <div class="badge bg-primary bg-gradient rounded-pill mb-2">News</div>
                                    <a class="text-decoration-none link-dark stretched-link" href="#!"><h5 class="card-title mb-3">The last blog post title is a little bit longer than the others</h5></a>
                                    <p class="card-text mb-0">Some more quick example text to build on the card title and make up the bulk of the card's content.</p>
                                </div>
                                <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                                    <div class="d-flex align-items-end justify-content-between">
                                        <div class="d-flex align-items-center">
                                            <img class="rounded-circle me-3" src="https://dummyimage.com/40x40/ced4da/6c757d" alt="..." />
                                            <div class="small">
                                                <div class="fw-bold">Evelyn Martinez</div>
                                                <div class="text-muted">April 2, 2023 &middot; 10 min read</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Call to action-->
                    <aside class="bg-primary bg-gradient rounded-3 p-4 p-sm-5 mt-5">
                        <div class="d-flex align-items-center justify-content-between flex-column flex-xl-row text-center text-xl-start">
                            <div class="mb-4 mb-xl-0">
                                <div class="fs-3 fw-bold text-white">New products, delivered to you.</div>
                                <div class="text-white-50">Sign up for our newsletter for the latest updates.</div>
                            </div>
                            <div class="ms-xl-4">
                                <div class="input-group mb-2">
                                    <input class="form-control" type="text" placeholder="Email address..." aria-label="Email address..." aria-describedby="button-newsletter" />
                                    <button class="btn btn-outline-light" id="button-newsletter" type="button">Sign up</button>
                                </div>
                                <div class="small text-white-50">We care about privacy, and will never share your data.</div>
                            </div>
                        </div>
                    </aside>
                </div>
            </section>
        </main>
        <!-- Footer-->
        <footer class="bg-dark py-4 mt-auto">
            <div class="container px-5">
                <div class="row align-items-center justify-content-between flex-column flex-sm-row">
                    <div class="col-auto"><div class="small m-0 text-white">Copyright &copy; Your Website 2023</div></div>
                    <div class="col-auto">
                        <a class="link-light small" href="#!">Privacy</a>
                        <span class="text-white mx-1">&middot;</span>
                        <a class="link-light small" href="#!">Terms</a>
                        <span class="text-white mx-1">&middot;</span>
                        <a class="link-light small" href="#!">Contact</a>
                    </div>
                </div>
            </div>
        </footer>
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
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
