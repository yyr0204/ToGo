<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="width: 100%; overflow: auto;">
	<table border="1" style="white-space: nowrap;">
		<tr>
			<c:forEach var = "dto" items = "${list2}" varStatus = "vs">
			<td colspan="${list3.get(vs.index)}">
				<b> ${dto} ${vs.count}일차 </b>
			</td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach var = "dto" items = "${list}" varStatus = "vs">
			<td>
				<h3> 장소 이름 </h3>
				<a href="">
							<img src = "" width = "250" height = "200" />
						</a><br />
				<h3> 9:00~11:00 </h3>
			</td>
			</c:forEach>
		</tr>
	</table>
</div>