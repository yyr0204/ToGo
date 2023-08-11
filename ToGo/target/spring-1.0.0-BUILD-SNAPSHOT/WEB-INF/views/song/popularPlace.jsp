<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>메인</h1>
<div style=float:left;>
<c:forEach var="dto" items="${main}" varStatus="vs">
	<h3> ${dto.name} </h3>
	<a href="">
		<img src = "" width = "250" height = "200" />
	</a><br />
</c:forEach>
</div>

<br />

<h1>서브</h1>
<div style=float:left;>
<c:forEach var="dto" items="${sub}" varStatus="vs">
	<h3> ${dto.name} </h3>
	<a href="">
		<img src = "" width = "250" height = "200" />
	</a><br />
</c:forEach>
</div>