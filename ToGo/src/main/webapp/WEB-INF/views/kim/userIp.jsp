<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>내 주변 정보 찾기</title>

<script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>

</head>

<body>
<div class="contain">
	<div id="userinput" class="item">
		<script>
		
  			var places = [
    			<c:forEach var="place" items="${place_list}" varStatus="loop">
      				{lat: ${place.lat}, lng: ${place.lon}, name: "${place.name}"}<c:if test="${!loop.last}">,</c:if>
    			</c:forEach>
  			];
		</script>
		<c:forEach var="place" items="${place_list}" varStatus="status">
            <div id="place_${status.index}" class="place"><span>${place.name}</span></div>
        </c:forEach>
	</div>
	<div id="map" class="item"></div>
</div>
</body>
<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB41DRUbKWJHPxaFjMAwdrzWzbVKartNGg&callback=initMap&v=weekly"
		defer>
</script>
<script type="text/javascript">
let map, infoWindow, cityCircle;

function initMap() {
  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: 37.5665, lng: 126.9780 },
    zoom: 6,
 	// 지도, 위성 버튼 비활성화
    mapTypeControl: false,
    // api에서 제공하는 모든 핀 비활성화
    styles: [
        {
            featureType: "poi",
            stylers: [{ visibility: "off" }]
        }
    ]
  });
  
  infoWindow = new google.maps.InfoWindow();
  
  var markers = [];
  
  // 위치 데이터에서 마커 생성
  for (var i = 0; i < places.length; i++) {
    var position = new google.maps.LatLng(places[i].lat, places[i].lng);
    var marker = new google.maps.Marker({
        position: position,
        map: map,
        title: places[i].name  // 마커의 타이틀 설정
    });
    
 	// 클릭 이벤트 리스너 추가
    google.maps.event.addListener(marker, 'click', (function(marker, i) {
        return function() {
          infoWindow.setContent(places[i].name);
          infoWindow.open(map, marker);
        }
    })(marker, i));

    markers.push(marker);
  }
  
  window.addEventListener('load', (event) => {
	  places.forEach((place, i) => {
	    document.getElementById('place_' + i).addEventListener('click', function() {
	      map.setCenter(markers[i].getPosition());
	    });
	  });
	});
  
	//체크박스 컨테이너 생성
  var controlDiv = document.createElement('div');
  controlDiv.style.backgroundColor = '#fff';
  controlDiv.style.border = '2px solid #fff';
  controlDiv.style.borderRadius = '3px';
  controlDiv.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
  controlDiv.style.cursor = 'pointer';
  controlDiv.style.marginTop = '10px';
  controlDiv.style.marginBottom = '22px';
  controlDiv.style.textAlign = 'center';
  map.controls[google.maps.ControlPosition.TOP_LEFT].push(controlDiv);

	//체크박스 생성
  var checkBox1 = document.createElement('div');
  checkBox1.className = "form-check";
  checkBox1.innerHTML = '<input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"><label class="form-check-label" for="flexCheckDefault">식당 / 카페</label>';
  controlDiv.appendChild(checkBox1);

  var checkBox2 = document.createElement('div');
  checkBox2.className = "form-check";
  checkBox2.innerHTML = '<input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked><label class="form-check-label" for="flexCheckChecked">관광지</label>';
  controlDiv.appendChild(checkBox2);
  
  //위치 찾기 버튼 생성
  const locationButton = document.createElement("button");
  locationButton.textContent = "내 위치 찾기";
  locationButton.classList.add("custom-map-control-button");
  map.controls[google.maps.ControlPosition.TOP_CENTER].push(locationButton);
  
  //이벤트 리스너 추가
  locationButton.addEventListener("click", () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const pos = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          };
          
          if (!cityCircle) {
              // 사용자의 위치를 중심으로 원을 생성
              cityCircle = new google.maps.Circle({
                strokeColor: "#0000FF",
                strokeOpacity: 0.8,
                strokeWeight: 2,
                fillColor: "#0000FF",
                fillOpacity: 0.35,
                map,
                center: pos,
                radius: 1000, // 반경 1km
              });
            } else {
              // 원이 이미 존재하면 중심 위치를 새로고침
              cityCircle.setCenter(pos);
            }

          infoWindow.setPosition(pos);
          infoWindow.setContent("여기있어요!");
          infoWindow.open(map);
          map.setCenter(pos);
        },
        () => {
          handleLocationError(true, infoWindow, map.getCenter());
        },
      );
    } else {
      // Browser doesn't support Geolocation
      handleLocationError(false, infoWindow, map.getCenter());
    }
  });
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
  infoWindow.setPosition(pos);
  infoWindow.setContent(
    browserHasGeolocation
      ? "Error: The Geolocation service failed."
      : "Error: Your browser doesn't support geolocation.",
  );
  infoWindow.open(map);
}

window.initMap = initMap;
</script>

<style>
#map {
	height: 800px;
}

html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}
.contain{
display:grid;
grid-template-columns:2fr 8fr;
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

#userinput{
	height: 800px;
	overflow: scroll; /* 스크롤 가능하게 설정 */
}

#userinput {
    display: flex;
    flex-direction: column;
    gap: 10px;
}
.place {
    border: 1px solid #ddd;
    padding: 10px;
    box-sizing: border-box;
}
</style>

</html>