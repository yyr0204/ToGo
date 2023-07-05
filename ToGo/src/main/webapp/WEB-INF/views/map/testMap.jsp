<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<%--    <script defer src="<c:url value="/resources/static/js/index.js"/>"></script>--%>
    <script defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC63MWSfMneMDT-oW0JIm_cZkKB1p9nmtI&callback=initMap"></script>
    <script>
        window.initMap = function () {
            var lat = 35.0906115
            var lng = 129.0466149
            const map = new google.maps.Map(document.getElementById("map"), {
                center: { lat, lng },
                zoom: 13,
            });

            const malls = [
                // { label: "C", name: "닥밭골 벽화마을", lat: 35.114225, lng:129.0238337 },
                // { label: "A", name: "자갈치 시장", lat: 35.0966559,lng: 129.0306748 },
                // { label: "A", name: "태종대", lat: 35.0597537,lng:129.0802753},
                // { label: "I", name: "송도", lat: 35.0753891,lng:129.016761 },
                <c:forEach var="item" items="${places}" varStatus="str">
                { label: "A", name: "${item.name}", lat: parseFloat(${item.lat}), lng: parseFloat(${item.lon}) },
                </c:forEach>
            ];
            const infowindow = new google.maps.InfoWindow();
            malls.forEach(({ label, name, lat, lng }) => {
                const marker = new google.maps.Marker({
                    position: { lat, lng },
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
<div id="map" style="height: 600px;"></div>
<select id="marker">
<c:forEach var="item" items="${places}" varStatus="str">
    <option name="${item.lat}">${item.name}</option>
</c:forEach>
</select>
</body>