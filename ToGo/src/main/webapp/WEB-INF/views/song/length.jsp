<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
.left-box {
  float: left;
}
.right-box {
  float: right;
}
</style>

<div class='left-box'>
<c:forEach var="dto" items="${main}" varStatus = "vs">
	<h1> ${vs.count}. ${dto.name} </h1>
	<h2> &nbsp; ${dto.adress} </h2>
	<h3> &nbsp;&nbsp; Lat : ${dto.getLat()} ,&nbsp; Lon : ${dto.getLon()} </h3>
	<br />
</c:forEach>
</div>

<div class='right-box'>
<c:forEach var="dto" items="${length}" varStatus = "vs">
	<br />
	<br />
	<h1> ${main.get(vs.index).name} -> ${main.get(vs.count).name} </h1>
	<h1> ${dto} </h1>
	<br />
	<br />
</c:forEach>
</div>