<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Data Result</title>
</head>
<body>
    <h1>Data Result</h1>
    <table>
        <tr>
            <th>Name</th>
            <th>Start Date</th>
            <th>End Date</th>
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