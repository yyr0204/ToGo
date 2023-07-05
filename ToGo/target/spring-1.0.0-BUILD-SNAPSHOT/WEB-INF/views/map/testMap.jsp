<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="${pageContext.request.contextPath}/resources/static/css/map_css.css" rel="stylesheet" type="text/css">
<head>
    <%--    <script defer src="<c:url value="/resources/static/js/index.js"/>"></script>--%>
    <script defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC63MWSfMneMDT-oW0JIm_cZkKB1p9nmtI&callback=initMap"></script>
    <script>
        let poly;
        let map;
        let flightPath = null;
        window.initMap = function (index) {
            var lat = 35.0906115
            var lng = 129.0466149
            const map = new google.maps.Map(document.getElementById("map"), {
                center: {lat, lng},
                zoom: 13,
            });
            /////////////////마커///////////////////
            const malls = [
                {label: "C", name: "닥밭골 벽화마을", lat: 35.114225, lng: 129.0238337},
                {label: "D", name: "자갈치 시장", lat: 35.0966559, lng: 129.0306748},
                {label: "B", name: "태종대", lat: 35.0597537, lng: 129.0802753},
                {label: "E", name: "송도", lat: 35.0753891, lng: 129.016761},
                <%--                <c:forEach var="item" items="${places}" varStatus="str">--%>
                <%--                {label: "A", name: "${item.name}", lat: parseFloat(${item.lat}), lng: parseFloat(${item.lon})},--%>
                <%--                </c:forEach>--%>
            ];
            /////////////////다중선///////////////////
            document.getElementById('reset').addEventListener("click", remove)
            document.getElementById('add').addEventListener("click", add)
            var colorCode = "#" + Math.round(Math.random() * 0xffffff).toString(16);

            function add() {
                colorCode = "#" + Math.round(Math.random() * 0xffffff).toString(16);
                if (flightPath != null) {
                    flightPath.setMap(null)
                }
                flightPath = new google.maps.Polyline({
                    path: malls,
                    geodesic: true,
                    strokeColor: colorCode,
                    strokeOpacity: 1.0,
                    strokeWeight: 2,
                });
                flightPath.setMap(map)
            }

            function remove() {
                flightPath.setMap(null)
            }

            ////////////////클릭 마커/////////////////
            poly = new google.maps.Polyline({
                strokeColor: colorCode,
                strokeOpacity: 1.0,
                strokeWeight: 3,
            });
            map.addListener("click", addLatLng);

            function addLatLng(event) {
                const path = poly.getPath();

                path.push(event.latLng);
                // Add a new marker at the new plotted point on the polyline.
                let marker2 = new google.maps.Marker({
                    position: event.latLng,
                    title: "#" + path.getLength(),
                    map: map,
                });
                document.getElementById('mk_reset').addEventListener("click", mk_remove)
                document.getElementById('mk_add').addEventListener("click", mk_add)

                function mk_remove() {
                    marker2.setMap(null)
                    poly.setMap(null)
                    poly = new google.maps.Polyline({
                        strokeColor: colorCode,
                        strokeOpacity: 1.0,
                        strokeWeight: 3,
                    });
                }

                function mk_add() {
                    poly.setMap(map)
                }
            }

            // let lat2 = document.getElementsByClassName('lat').item(index).value;
            // document.getElementById('place_name').addEventListener("click",lat_alert)
            // function lat_alert() {
            //     alert(lat2)
            // }

            // marker2 = new google.maps.Marker({
            //     position: ,
            //     title: "#" + path.getLength(),
            //     map: map,
            // });

            ///////////////////////////////////////////////////////
            const infowindow = new google.maps.InfoWindow();
            malls.forEach(({label, name, lat, lng}) => {
                const marker = new google.maps.Marker({
                    position: {lat, lng},
                    label,
                    map,
                });
                marker.addListener("click", () => {
                    map.panTo(marker.position);
                    infowindow.setContent(name);
                    infowindow.open({
                        anchor: marker,
                        map,
                    });
                });
            });
        };


    </script>
</head>

<body>
<div class="banner"></div>
<div class="container">
    <div class="item" id="map" style="height: 600px;"></div>
    <div class="item" id="place_box">
        <c:forEach var="item" items="${places}" varStatus="str">
            <div class="place" id="place_bar${str.count}">
                <div class="item"><img src="${pageContext.request.contextPath}/resources/static/img/5745739.png"></div>
                <div class="item">
                    <a href="#" id="place_name${str.count}">${item.name}</a>
                </div>
                <input type="hidden" class="lat" value="${item.lat}">
                <input type="hidden" class="lon" value="${item.lon}">
                <div>
                    <x></x>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<button id="reset" type="button">리셋</button>
<button id="add" type="button">경로표시</button>
<button id="mk_reset" type="button">마커리셋</button>
<button id="mk_add" type="button">마커경로</button>
</body>