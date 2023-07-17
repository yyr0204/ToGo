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


<div style="width: 100%; overflow: auto;">
	<table border="1" style="white-space: nowrap;">
		<tr>
			<c:forEach var = "dto" items = "${plan}" varStatus = "vs">
	<!--	<td colspan="${list3.get(vs.index)}">	-->
			<td colspan="2">
				<b> ${dto} ${vs.count}일차 </b>
			</td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach var = "dto" items = "${main}" varStatus = "vs">
			<td>
				<h3> ${dto.name} </h3>
				<a href="">
							<img src = "" width = "250" height = "200" />
						</a><br />
				<h3> 9:00~11:00 </h3>
			</td>
			</c:forEach>
		</tr>
	</table>
</div>

<div>
	<h1> ${Lat} </h1>
	<h1> ${Lon} </h1>
	<br>
	<h1> ${Lat1} </h1>
	<h1> ${Lon1} </h1>
</div>

