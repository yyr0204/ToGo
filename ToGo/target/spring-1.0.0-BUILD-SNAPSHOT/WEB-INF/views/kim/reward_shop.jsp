<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reward-shop</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<div>
    내 포인트 : <span id="memId">${cash}</span>
    <div>
        <c:forEach var="goods" items="${goods}">
            <div style="display: inline-block; margin-right: 20px;" data-points="${goods.points}">
                <img src="${goods.imageUrl}" alt="${goods.name}" style="width: 200px; height: 200px;"/>
                <p>${goods.name} - ${goods.points}point</p>
                <button onclick="getAddress('${goods.name}', '${memId}', ${goods.points});">상품 교환</button>
            </div>
        </c:forEach>
    </div>
</div>
<div id="addressInput" style="display: none;">
    <input type="text" id="detailAddress" placeholder="상세주소를 입력하세요.">
    <button onclick="exchangeGoods();">주소 완료</button>
</div>

<script>
var goodsId;
var memId;
var roadAddr;
var jibunAddr;
var points;

function getAddress(gId, mId, pts) {
    var cash = parseInt(document.getElementById('memId').textContent);
    if(cash < pts){
        alert("상품 교환하기에 포인트가 부족합니다");
        return;
    }
    
    goodsId = gId;
    memId = mId;
    points = pts; // Save points value
    new daum.Postcode({
        oncomplete: function(data) {
            roadAddr = data.roadAddress;
            jibunAddr = data.jibunAddress;
            alert("상세주소를 추가로 적어주세요");
            document.getElementById("addressInput").style.display = "block";
        }
    }).open();
}

function exchangeGoods() {
    var detailAddr = document.getElementById('detailAddress').value;
    $.ajax({
        url: '/ToGo/User/exchange',
        type: 'POST',
        data: JSON.stringify({
            goodsId: goodsId,
            memId: memId,
            address: roadAddr + ' ' + jibunAddr + ' ' + detailAddr,
            points: points
        }),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(data) {
            if (data.success) {
                alert('상품 교환에 성공하였습니다.');
                location.reload();
            } else {
                alert('상품 교환에 실패하였습니다.');
                document.getElementById("addressInput").style.display = "none";
            }
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            document.getElementById("addressInput").style.display = "none";
        }
    });
}
</script>

</body>
</html>
