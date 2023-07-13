<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>해수욕장 정보</title>
<script src="//code.jquery.com/jquery-3.7.0.min.js"></script>

<script>
function filterByRegion(region) {
    $('tbody tr').hide(); // 모든 행 숨기기

    if (region === '전체') {
        $('tbody tr').show(); // 모든 행 보이기
        $('thead th:nth-child(2), tbody td:nth-child(2)').show(); // "지역" 컬럼 보이기
    } else {
        $('tbody tr').each(function() {
            var sido1 = $(this).find('td:eq(1)').text(); // 현재 행의 "지역" 데이터 가져오기
            if (sido1 === region) {
                $(this).show(); // 선택한 지역과 일치하는 행 보이기
                $('thead th:nth-child(2), tbody td:nth-child(2)').hide(); // "지역" 컬럼 숨기기
            }
        });
    }
}

function showSigunguColumn() {
    $('thead th:nth-child(2), tbody td:nth-child(2)').show(); // "지역" 컬럼 보이기
}

$(document).ready(function() {
    showSigunguColumn(); // 페이지 로드 시 "지역" 컬럼 보이기 함수 호출
});
</script>

</head>
<body>
    <h1>해수욕장</h1>
    <div class="btn-group" role="group" aria-label="Basic outlined example">
    <button onclick="filterByRegion('전체')">전체</button>
    <button onclick="filterByRegion('인천시')">인천시</button>
    <button onclick="filterByRegion('강원도')">강원도</button>
    <button onclick="filterByRegion('충청남도')">충청남도</button>
    <button onclick="filterByRegion('경상남도')">경상남도</button>
    <button onclick="filterByRegion('경상북도')">경상북도</button>
    <button onclick="filterByRegion('울산시')">울산시</button>
    <button onclick="filterByRegion('전라남도')">전라남도</button>
    <button onclick="filterByRegion('전라북도')">전라북도</button>
    <button onclick="filterByRegion('부산시')">부산시</button>
    <button onclick="filterByRegion('제주특별자치도')">제주특별자치도</button>
</div>
    <table>
        <thead>
            <tr>
                <th>해수욕장명</th>
                <th style="display: none;">지역</th> <!-- "시군구" 컬럼을 "지역" 컬럼으로 변경 -->
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
                    <td style="display: none;">${item.sido1}</td>
                    <td>${item.address}</td>
                    <td>${item.openDate}</td>
                    <td>${item.closingDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
