<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="width: 100%; overflow: auto;">
	<table border="1" style="white-space: nowrap;">
		<tr>
			<c:forEach var = "dto" items = "${plan}" varStatus = "vs">
	<!--	<td colspan="${list3.get(vs.index)}">	-->
			<td colspan="${main.size()/plan.size()}">
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

<div>
	<c:forEach var = "sub" items = "${daySub}" varStatus = "vs">
		<c:forEach var = "dto" items = "${sub}" varStatus = "vs2">
			<h1> ${vs.count}일차 서브일정 </h1>
			<h2> ${dto.name} </h2>
			<h3> ${dto.adress} </h3>
			<h4> ${dto.getLat()} , ${dto.getLon()} </h4>
			<br />
		</c:forEach>
	</c:forEach>
</div>

<div style="width: 100%; overflow: auto;">
	<table border="1" style="white-space: nowrap;">
		<tr>
			<c:forEach var = "dto" items = "${plan}" varStatus = "vs">
	<!--	<td colspan="${list3.get(vs.index)}">	-->
			<td colspan="${finalList.size()/plan.size()}">
				<b> ${dto} ${vs.count}일차 </b>
			</td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach var = "dto" items = "${dayMap}" varStatus = "vs">
			<c:forEach var = "dto2" items = "${dto}" varStatus = "vs">
			<td>
				<h3> ${dto2.name} </h3>
				<a href="">
					<img src = "" width = "250" height = "200" />
				</a><br />
				<h3> 9:00~11:00 </h3>
			</td>
			</c:forEach>
			</c:forEach>
		</tr>
	</table>
</div>
