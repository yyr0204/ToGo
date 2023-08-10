<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>리워드 받기</title>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<div id="scheduleTitles">
		<c:forEach var="schedule" items="${schedules}">
    		<p data-plan-num="${schedule.plan_num}" data-endday="${schedule.endday}">
        	${schedule.title}
    		</p>
		</c:forEach>
	</div>
    <div id="map"></div>
</body>

<script>
var selectedPlanNum = null; // 선택된 plan_num을 저장할 변수
var selectedEndDay = null; // 선택된 endday를 저장할 변수
var memId = '<%= session.getAttribute("memId") %>'; // 세션에서 memId를 가져옵니다.

$(document).ready(function() {
    // title 클릭 이벤트 추가
    $("#scheduleTitles p").on("click", function() {
        selectedPlanNum = $(this).data("plan-num");
        selectedEndDay = $(this).data("endday");  // endday 값 저장
        console.log("Selected plan_num:", selectedPlanNum);
        console.log("Selected endday:", selectedEndDay);  // endday 값 출력
        var titleName = $(this).text();
        alert(titleName + "이(가) 선택되었습니다.");  
    });
});

let map, infoWindow, pos, cityCircle;

function initMap() {
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 37.5665, lng: 126.9779 },
        zoom: 15,
        mapTypeControl: false,
        styles: [
            {
                featureType: "poi",
                stylers: [{ visibility: "off" }]
            }
        ]
    });

    infoWindow = new google.maps.InfoWindow();

    const locationButton = document.createElement("button");
    locationButton.textContent = "리워드 받기";
    locationButton.classList.add("custom-map-control-button");
    map.controls[google.maps.ControlPosition.TOP_CENTER].push(locationButton);
  
    locationButton.addEventListener("click", () => {
        if (selectedPlanNum === null) {
            alert("제목을 먼저 선택해주세요.");
            return;
        }
      
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    pos = {
                        lat: position.coords.latitude,
                        lng: position.coords.longitude,
                    };

                    if (!cityCircle) {
                        cityCircle = new google.maps.Circle({
                            strokeColor: "#0000FF",
                            strokeOpacity: 0.8,
                            strokeWeight: 2,
                            fillColor: "#0000FF",
                            fillOpacity: 0.35,
                            map,
                            center: pos,
                            radius: 500,
                        });
                    } else {
                        cityCircle.setCenter(pos);
                    }

                    $.ajax({
                        url: '/ToGo/User/reward_ip',
                        type: 'POST',
                        data: JSON.stringify({
                            plan_num: selectedPlanNum,
                            endday: selectedEndDay,  // endday도 함께 전송
                            lat: pos.lat,
                            lng: pos.lng,
                            memId: memId
                        }),
                        contentType: 'application/json',
                        success: function (data) {
                            if (data === "success") {
                                alert("성공!");
                                window.location.href = "/ToGo/trip/main"; 
                            } else if (data === "time_limit") {
                                alert("리워드를 받은 날을 기준으로 일주일 뒤에 가능합니다.");
                            } else {
                                alert("실패!");
                                window.location.href = "/ToGo/trip/main"; 
                            }
                        },
                        error: function(){
                            alert('요청이 실패했습니다.');
                        }
                    });

                    infoWindow.setPosition(pos);
                    infoWindow.setContent("여기있어요!");
                    infoWindow.open(map);
                    map.setCenter(pos);
                },
                () => {
                    handleLocationError(true, infoWindow, map.getCenter());
                }
            );
        } else {
            handleLocationError(false, infoWindow, map.getCenter());
        }
    });
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(
        browserHasGeolocation
        ? "Error: The Geolocation service failed."
        : "Error: Your browser doesn't support geolocation."
    );
    infoWindow.open(map);
}

window.initMap = initMap;
</script>

<script
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB41DRUbKWJHPxaFjMAwdrzWzbVKartNGg&libraries=geometry&callback=initMap&v=weekly"
    defer>
</script>

<style>
#map {
    float: right;
    width: 80%;
    height: 100%;
}

#scheduleTitles {
    float: left;
    width: 20%;
    height: 100%;
    overflow: auto; 
    padding: 10px;
    border-right: 1px solid #ccc;
}

#scheduleTitles p {
    border-bottom: 1px solid #ddd;
    padding-bottom: 5px;
    margin-bottom: 10px;
}

html, body {
    height: 100%;
    margin: 0;
    padding: 0;
}

.custom-map-control-button {
    background-color: #fff;
    border: 0;
    border-radius: 2px;
    box-shadow: 0 1px 4px -1px rgba(0, 0, 0, 0.3);
    margin: 10px;
    padding: 0 0.5em;
    font: 400 18px Roboto, Arial, sans-serif;
    overflow: hidden;
    height: 40px;
    cursor: pointer;
}

.custom-map-control-button:hover {
    background: rgb(235, 235, 235);
}
</style>
</html>
