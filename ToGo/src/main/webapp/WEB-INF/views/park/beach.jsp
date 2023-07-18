<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>해수욕장 정보</title>
</head>
<body>
    <h1>해수욕장</h1>
    <table>
        <tr>
            <th>해수욕장 이름</th>
            <th>개장일</th>
            <th>폐장일</th>
        </tr>
        <c:forEach var="data" items="${dataList}">
            <tr>
                <td>${data.name}</td>
                <td>${data.startDate}</td>
                <td>${data.endDate}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>