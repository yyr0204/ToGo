<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Festival View</title>
</head>
<body>
    <h1>Festival Information</h1>
    <table>
        <tr>
            <th>축제명</th>
            <th>개최장소</th>
            <th>축제 시작일</th>
            <th>축제 종료일</th>
            <th>축제 내용</th>
            <th>주관 기관</th>
<!--             <th>주최 기관</th> -->
<!--             <th>후원 기관</th> -->
<!--             <th>전화번호</th> -->
            <th>홈페이지 주소</th>
        </tr>
        <c:forEach items="${festivalData}" var="festival">
            <tr>
                <td>${festival.fstvlNm}</td>
                <td>${festival.opar}</td>
                <td>${festival.fstvlStartDate}</td>
                <td>${festival.fstvlEndDate}</td>
                <td>${festival.fstvlCo}</td>
                <td>${festival.mnnst}</td>
<%--                 <td>${festival.auspcInstt}</td> --%>
<%--                 <td>${festival.suprtInstt}</td> --%>
<%--                 <td>${festival.phoneNumber}</td> --%>
                <td>${festival.homepageUrl}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>