<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="${pageContext.request.contextPath}/resources/static/css/map_css.css" rel="stylesheet" type="text/css">
<head>
    <%--    <script defer src="<c:url value="/resources/static/js/index.js"/>"></script>--%>
    <script defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC63MWSfMneMDT-oW0JIm_cZkKB1p9nmtI&callback=initMap"></script>
    <script>
        window.initMap = function () {
            var lat = 35.0906115
            var lng = 129.0466149
            const map = new google.maps.Map(document.getElementById("map"), {
                center: {lat, lng},
                zoom: 13,
            });
            /////////////////마커///////////////////
            const malls = [
                // { label: "C", name: "닥밭골 벽화마을", lat: 35.114225, lng:129.0238337 },
                // { label: "D", name: "자갈치 시장", lat: 35.0966559,lng: 129.0306748 },
                // { label: "B", name: "태종대", lat: 35.0597537,lng:129.0802753 },
                // { label: "E", name: "송도", lat: 35.0753891,lng:129.016761 },
                <c:forEach var="item" items="${places}" varStatus="str">
                {label: "A", name: "${item.name}", lat: parseFloat(${item.lat}), lng: parseFloat(${item.lon})},
                </c:forEach>
            ];

            /////////////////다중선///////////////////
            const flightPlanCoordinates = [
                <c:forEach var="item" items="${places}" varStatus="str">
                {lat: parseFloat(${item.lat}), lng: parseFloat(${item.lon})},
                </c:forEach>
            ];
            var colorCode="#"+Math.round(Math.random()*0xffffff).toString(16);
            console.log(colorCode)
            const flightPath = new google.maps.Polyline({
                path: flightPlanCoordinates,
                geodesic: true,
                strokeColor: colorCode,
                strokeOpacity: 1.0,
                strokeWeight: 2,
            });

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
            flightPath.setMap(map);
        };
    </script>
</head>

<body>
<div class="banner"></div>
<div class="container">
    <div class="item" id="map" style="height: 600px;"></div>
    <div class="item" id="place_box">
        <c:forEach var="item" items="${places}" varStatus="str">
            <div class="place">
                <div class="item"><img src="${pageContext.request.contextPath}/resources/static/img/5745739.png"></div>
                <div class="item"><p>${item.name}</p></div>
                <div>
                    <x></x>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>