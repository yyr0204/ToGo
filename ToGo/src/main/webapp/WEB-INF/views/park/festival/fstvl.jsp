<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Festival View</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
</head>
<body>
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
</body>
</html>