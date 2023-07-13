<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Festival List</title>
<!--     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> -->
</head>
<!-- <body> -->
<!--     <div class="container"> -->
<!--         <h1>Festival List</h1> -->
<!--         <div class="row"> -->
<%--             <c:forEach var="festival" items="${fstvlList}"> --%>
<!--                 <div class="col-md-4"> -->
<!--                     <div class="card" style="width: 18rem;"> -->
<!--                         <div class="card-body"> -->
<%--                        		<a href="${festival.website}" target="_blank"> --%>
<%--                         		<img src="${festival.image_url}" class="card-img-top w-100"> --%>
<!--                         	</a> -->
<!--                         	<div> -->
<%-- 	                            <h5 class="card-title">${festival.title}</h5> --%>
<%-- 	                            <h6 class="card-subtitle mb-2 text-muted">${festival.location}</h6> --%>
<%-- 	                            <p class="card-text">${festival.period}</p> --%>
<!--                         	</div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<%--             </c:forEach> --%>
<!--         </div> -->
<!--     </div> -->
<!-- </body> -->
<style>
    .image-grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
        grid-gap: var(--row-gutter);
        align-items: center;
        justify-items: center;
        margin: var(--space-3) 0;
    }

    .image-grid a {
        display: block;
        text-align: center;
        text-decoration: none;
        color: #767676;
        transition: color 0.1s ease-in-out, opacity 0.1s ease-in-out;
    }

    .image-grid a:hover {
        color: #111;
    }

    .image-grid img {
        vertical-align: middle;
        border: none;
        width: 100%;
        height: auto;
        cursor: zoom-in;
        object-fit: none; /* Prevents altering the original aspect ratio */
    }
</style>

<body>
    <h1>Festival List</h1>
    <div class="image-grid">
        <c:forEach var="festival" items="${fstvlList}">
            <a href="${festival.website}" target="_blank">
                <img src="${festival.image_url}" alt="${festival.title}">
            </a>
        </c:forEach>
    </div>
</body>

</html>