<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>해수욕장 정보</title>
<script src="//code.jquery.com/jquery-3.7.0.min.js"></script>

<script>
function filterByRegion(region) {
    // Hide all rows
    $('tbody tr').hide();
    
    // Show rows matching the selected region
    if (region === '전체') {
        $('tbody tr').show();
    } else {
        $('tbody tr').each(function() {
            var sido1 = $(this).find('td:eq(1)').text();
            if (sido1 === region) {
                $(this).show();
            }
        });
    }
}
</script>

</head>
<body>
    <h1>해수욕장</h1>
    
    <button onclick="filterByRegion('전체')">전체</button>
    <button onclick="filterByRegion('강원도')">강원도</button>
    <button onclick="filterByRegion('경상남도')">경상남도</button>
    <button onclick="filterByRegion('경상북도')">경상북도</button>
    <button onclick="filterByRegion('부산시')">부산시</button>
    <button onclick="filterByRegion('울산시')">울산시</button>
    <button onclick="filterByRegion('인천시')">인천시</button>
    <button onclick="filterByRegion('전라남도')">전라남도</button>
    <button onclick="filterByRegion('전라북도')">전라북도</button>
    <button onclick="filterByRegion('제주특별자치도')">제주특별자치도</button>
    <button onclick="filterByRegion('충청남도')">충청남도</button>

    <table>
        <thead>
            <tr>
                <th>해수욕장명</th>
                <th>시도1</th>
                <th>시도2</th>
                <th>주소</th>
                <th>개장일</th>
                <th>폐장일</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${resultData.data}" var="item">
                <tr>
                    <c:choose>
                        <c:when test="${not empty item.beachName and not item.beachName.matches('.*해수욕장$|.*해변$')}">
                            <td>${item.beachName}해수욕장</td>
                        </c:when>
                        <c:otherwise>
                            <td>${item.beachName}</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${item.sido1}</td>
                    <td>${item.sido2}</td>
                    <td>${item.address}</td>
                    <td>${item.openDate}</td>
                    <td>${item.closingDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
