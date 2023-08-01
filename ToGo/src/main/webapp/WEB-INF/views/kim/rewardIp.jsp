<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>내 주변 정보 찾기</title>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
    <div id="map"></div>
</body>

<script>
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

          // Send the user location to the server
          $.ajax({
              url: '/ToGo/User/reward_ip',
              type: 'POST',
              data: JSON.stringify({
                  lat: pos.lat,
                  lng: pos.lng,
              }),
              contentType: 'application/json', // the type of data you're sending
              success: function (data) {
                  // the action to perform when the response is successful
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

<script
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB41DRUbKWJHPxaFjMAwdrzWzbVKartNGg&libraries=geometry&callback=initMap&v=weekly"
    defer>
</script>

<style>
#map {
    height: 100%;
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
