<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>내 주변 정보 찾기</title>

<script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>

</head>

<body>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<div class="contain">
    <div id="userinput" class="item">
        <script>
        	var places = [];
        </script>
    </div>
    <div id="map" class="item"></div>
</div>
<script type="text/javascript">
let map, infoWindow, cityCircle, pos;
let list = {}
function initMap() {
  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: 37.5665, lng: 126.9779 },
    zoom: 15,
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

  // 체크박스 생성
  var checkBox1 = document.createElement('div');
  checkBox1.className = "form-check";
  checkBox1.innerHTML = '<input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"><label class="form-check-label" for="flexCheckDefault">식당 / 카페</label>';
  controlDiv.appendChild(checkBox1);

  var checkBox2 = document.createElement('div');
  checkBox2.className = "form-check";
  checkBox2.innerHTML = '<input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked><label class="form-check-label" for="flexCheckChecked">관광지</label>';
  controlDiv.appendChild(checkBox2);

  infoWindow = new google.maps.InfoWindow();

  var markers = [];
  
  // 위치 찾기 버튼 생성
  const locationButton = document.createElement("button");
  locationButton.textContent = "내 위치 찾기";
  locationButton.classList.add("custom-map-control-button");
  map.controls[google.maps.ControlPosition.TOP_CENTER].push(locationButton);
  
  locationButton.addEventListener("click", () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          pos = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          };

          $.ajax({
              url: '/ToGo/User/user_ip',
              type: 'POST',
              data: JSON.stringify({
                  lat: pos.lat,
                  lng: pos.lng,
                  isMain: $('#flexCheckChecked').prop('checked'), // 관광지 체크박스 상태
                  isSub: $('#flexCheckDefault').prop('checked'), // 식당/카페 체크박스 상태
              }),
              contentType: 'application/json', // 서버로 보내는 데이터의 타입
              dataType: 'json', // 서버에서 받아오는 데이터의 타입
              success: function (data) {
            	  for(var i = 0 ; i < data.length; i++){
            	  	places = data[i].places;
            	  	markers.forEach(marker => marker.setMap(null));  // 기존 마커를 삭제합니다.
	                markers = [];  // 마커 배열을 비웁니다.
						
                  	add_mk(places);
            	  }


               // 리스트 엘리먼트 생성 및 페이지에 추가
                  var userinput = document.getElementById('userinput');
                  userinput.innerHTML = '';  // 기존 리스트를 지웁니다.
                  places.forEach((place, i) => {
                      var placeDiv = document.createElement('div');
                      placeDiv.id = 'place_' + i;
                      placeDiv.className = 'place';

                      var placeSpan = document.createElement('span');
                      placeSpan.textContent = place.name;
                      
                      var placeAddress = document.createElement('p');
                      placeAddress.textContent = '주소: ' + place.adress;

                      var placePhone = document.createElement('p');
                      placePhone.textContent = place.phonnum !== 'Null' ? ('전화번호: ' + place.phonnum) : '전화번호 정보가 없습니다';

                      placeDiv.appendChild(placeSpan);
                      placeDiv.appendChild(placeAddress);
                      placeDiv.appendChild(placePhone);
                      userinput.appendChild(placeDiv);
                  });
                  
                  // 각각의 장소 이름에 이벤트 리스너 추가
                  places.forEach((place, i) => {
                      document.getElementById('place_' + i).addEventListener('click', function() {
                          map.setCenter(markers[i].getPosition());
                          infoWindow.setContent(place.name);  // 클릭한 장소의 이름을 표시합니다.
                          infoWindow.open(map, markers[i]);  // 정보창을 엽니다.
                      });
                  });
              },
              error: function(){
                  alert('?')
              }
          })
          function add_mk(list){
        	    for(let num in list){
        	        if(list[num].lat && list[num].lon) { // Check if lat and lon are not null
        	            let mk = new google.maps.Marker({
        	                position:{lat:list[num].lat,lng:list[num].lon},
        	                map,
        	            })
        	            mk.setMap(map)
        	            markers.push(mk);
        	        }
        	    }
        	    if(list.length > 0 && list[2] && list[2].lat && list[2].lon) { // Check if list[2] exists and has lat and lon
        	        map.panTo({lat:list[2].lat,lng:list[2].lon})
        	    }
        	}
          markers.forEach(marker => marker.setMap(null));  // 기존 마커를 삭제합니다.
          markers = [];  // 마커 배열을 비웁니다.
  
          for (var i = 0; i < places.length; i++) {
            var placePosition = new google.maps.LatLng(places[i].lat, places[i].lng);
            var distanceInKm = google.maps.geometry.spherical.computeDistanceBetween(
              placePosition, 
              new google.maps.LatLng(pos)
            ) / 1000;
    
            if (distanceInKm <= 1) {
              var marker = new google.maps.Marker({
                position: placePosition,
                map: map,
                title: places[i].name
              });
      
              google.maps.event.addListener(marker, 'click', (function(marker, i) {
                  return function() {
                    infoWindow.setContent(places[i].name);
                    infoWindow.open(map, marker);
                  }
              })(marker, i));

              markers.push(marker);
            }
          }

          if (!cityCircle) {
        	  // 사용자 위치를 중심으로 원 생성
              cityCircle = new google.maps.Circle({
                strokeColor: "#0000FF",
                strokeOpacity: 0.8,
                strokeWeight: 2,
                fillColor: "#0000FF",
                fillOpacity: 0.35,
                map,
                center: pos,
                radius: 1000,
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

</body>
<script
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB41DRUbKWJHPxaFjMAwdrzWzbVKartNGg&libraries=geometry&callback=initMap&v=weekly"
    defer>
</script>

<style>
#map {
    height: 745px;
}

html, body {
    height: 100vh;
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
    height: 745px;
    overflow: scroll;
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
