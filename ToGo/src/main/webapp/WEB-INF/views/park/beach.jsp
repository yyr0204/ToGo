<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Result</title>
</head>
<body>
	<h1>Result</h1>
	<table>
		<thead>
			<tr>
				<th>개장일</th>
				<th>시도1</th>
				<th>시도2</th>
				<th>주소</th>
				<th>폐장일</th>
				<th>해수욕장명</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${resultData.data}" var="item">
				<tr>
					<td>${item.openDate}</td>
					<td>${item.sido1}</td>
					<td>${item.sido2}</td>
					<td>${item.address}</td>
					<td>${item.closingDate}</td>
					<c:choose>
						<c:when
							test="${not empty item.beachName and not item.beachName.matches('.*해수욕장$|.*해변$')}">
							<td>${item.beachName}해수욕장</td>
						</c:when>
						<c:otherwise>
							<td>${item.beachName}</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
