<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Festival List</title>
</head>
<body>
    <h1>Festival List</h1>
    
    <%-- 에러 메시지 출력 --%>
    <c:if test="${not empty errorMessage}">
        <p>${errorMessage}</p>
    </c:if>

    <table>
        <tr>
            <th>Festival Name</th>
            <th>Start Date</th>
            <th>End Date</th>
        </tr>
        <c:forEach var="festival" items="${festivals}">
            <tr>
                <td>${festival.fstvlNm}</td>
                <td>${festival.fstvlStartDate}</td>
                <td>${festival.fstvlEndDate}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>