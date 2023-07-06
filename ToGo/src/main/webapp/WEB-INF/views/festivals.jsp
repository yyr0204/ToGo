<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Festivals</title>
</head>
<body>
    <h1>Festivals</h1>
    <table>
        <tr>
            <th>Title</th>
            <th>First Image</th>
            <th>Event Start Date</th>
            <th>Event End Date</th>
        </tr>
        <c:forEach var="festival" items="${festivals}">
            <tr>
                <td>${festival.title}</td>
                <td>${festival.firstimage}</td>
                <td>${festival.eventstartdate}</td>
                <td>${festival.eventenddate}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>