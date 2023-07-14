<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="${pageContext.request.contextPath}/resources/static/css/map_css.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/resources/static/js/jquery.js"></script>
<head>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <script defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC63MWSfMneMDT-oW0JIm_cZkKB1p9nmtI&libraries=drawing,geometry,maps&v=beta&callback=initMap"></script>

</head>

<body>
<div class="banner"></div>
<div class="container">
    <div class="item" id="cityList">
        <c:forEach var="item" items="${cityList}" varStatus="str">
            <div class="place" id="place_bar${str.count}">
                <div class="item2"><img src="${pageContext.request.contextPath}/resources/static/img/img.png"></div>
                <div class="item2" id="city_name_area">
                    <form onsubmit="on_submit(event)" class="city_info" method="get" id="${item.name}latlng">
                        <a href="#" id="city_name&${item.name}" onclick="on_submit(this.id)">${item.name}</a>
                        <input type="submit" name="test">
                        <input type="hidden" name="lat" value="${item.lat}">
                        <input type="hidden" name="lng" value="${item.lon}">
                    </form>
                </div>
                <div>
                    <x></x>
                </div>
            </div>
        </c:forEach>
    </div>
    <div id="map" class="item" style="height: 800px"></div>
    <div class="item" id="place_box">
        <c:forEach var="item" items="${places2}" varStatus="str">
            <div style="background: aquamarine;height: 40px;margin: 0 auto;text-align: center">
                <div style="opacity: 1.0;font-weight:500;padding-top: 10px;text-align: center;">
                        ${str.count}일차
                </div>
            </div>
            <c:forEach var="dto" items="${item.value}">
                <br>
                <div class="place" id="place_bar${str.count}">
                    <div class="item2"><img src="${pageContext.request.contextPath}/resources/static/img/5745739.png">
                    </div>
                    <div class="item2" id="place_name_area">
                        <a href="#" id="${dto.name}">${dto.name}</a>
                    </div>
                </div>
            </c:forEach>
        </c:forEach>
    </div>
</div>
<button id="reset" type="button">리셋</button>
<button id="add" type="button">경로표시</button>
<button id="circle_add" type="button">원표시</button>
<button id="mk_reset" type="button">마커리셋</button>
<button id="mk_add" type="button">마커경로</button>
<button id="uni_add" type="button">대학교표시</button>
<button id="uni_reset" type="button">대학교표시 삭제</button>
<button id="ex_add" type="button">일정표시</button>
<button id="ex_reset" type="button">일정삭제</button>
<button id="ex_line_add" type="button">동선생성</button>
<button id="ex_line_remove" type="button">동선삭제</button>

<div>
    <c:forEach var="item2" items="${finalList}" varStatus="str">
        ${item2.name}
        ${item2.getLat()}
        ${item2.getLon()}
    </c:forEach>
</div>
<script>
    let poly;
    let map;
    let cityName
    let colorCode = "#" + Math.round(Math.random() * 0xffffff).toString(16);
    let malls
    let cityLatlng=[];


    function on_submit(event) {
        event.preventDefault()
        console.log(event)
    }

    window.initMap = function () {

        var lat = 37.496547146
        var lng = 126.955071006
        const map = new google.maps.Map(document.getElementById("map"), {
            center: {lat, lng},
            zoom: 13,
        });
        malls = [
            <c:forEach var="item" items="${places2}" varStatus="str">
            <c:forEach var="dto" items="${item.value}">
            {
                label: "${str.count}",
                name: "${dto.name}",
                lat: parseFloat(${dto.lat}),
                lng: parseFloat(${dto.lon})
            },
            </c:forEach>
            </c:forEach>
        ]
        var drawingManager = new google.maps.drawing.DrawingManager();
        drawingManager.setMap(map);


        ////////////////클릭 마커/////////////////
        poly = new google.maps.Polyline({
            strokeColor: colorCode,
            strokeOpacity: 1.0,
            strokeWeight: 3,
        });
        map.addListener("click", addLatLng);
        let num = 0;

        function addLatLng(event) {
            let chr = String.fromCharCode(65 + num)
            num++
            const path = poly.getPath();
            path.push(event.latLng);
            // Add a new marker at the new plotted point on the polyline.
            let marker2 = new google.maps.Marker({
                label: chr,
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
                num = 0
            }

            document.getElementsByClassName('')

            function mk_add() {
                poly.setMap(map)
            }
        }

        const infowindow = new google.maps.InfoWindow();
        const numList = ["one", "two", "three", "four", "five"]
        malls.forEach(({label, name, lat, lng}) => {
            // Add the circle for this city to the map.
            let numString = numList[label - 1]
            var myIcon = new google.maps.MarkerImage("${pageContext.request.contextPath}/resources/static/img/" + numString + ".png", null, null, null, new google.maps.Size(40, 40));
            const marker = new google.maps.Marker({
                position: {lat, lng},
                map,
                icon: myIcon,
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
        document.getElementById('add').addEventListener('click', valina_add);
        /////////////////////////////////////////////////////////////////////////

        for(let num=0;num<malls.length-1;num++){
            let name = num+"_city"
            let circle_lat=(malls[num].lat+malls[num+1].lat)/2
            let circle_lng=(malls[num].lng+malls[num+1].lng)/2
            let dist = Math.sqrt(Math.pow((malls[num].lat-malls[num+1].lat),2) + Math.pow((malls[num].lng-malls[num+1].lng),2))
            cityLatlng.push({city_center:{lat:circle_lat,lng:circle_lng},population: dist})
        }
        for(const city in malls){
                const cityCircle = new google.maps.Circle({
                    strokeColor: "#FF0000",
                    strokeOpacity: 0.8,
                    strokeWeight: 2,
                    fillColor: "#FF0000",
                    fillOpacity: 0.35,
                    map,
                    center: cityLatlng[city].city_center,
                    radius: Math.sqrt(cityLatlng[city].population*10000) * 100,
                });
        }
        /////////////////////////////////////////////////////////////////////////

        function valina_add() {

            <c:forEach var="item" items="${places2}" varStatus="str">
            colorCode = "#" + Math.round(Math.random() * 0xffffff).toString(16);
            latlng3 = [
                <c:forEach var="dto" items="${item.value}">
                {lat: parseFloat(${dto.lat}), lng: parseFloat(${dto.lon})},
                </c:forEach>
            ]
            // if (flightPath2 != null) {
            //     flightPath2.setMap(null)
            // }
            for (let step = 0; step < malls.length - 1; step++) {
                const flightPath4 = new google.maps.Polyline({
                    path: [latlng3[step], latlng3[step + 1]],
                    geodesic: true,
                    strokeColor: colorCode,
                    strokeOpacity: 1.0,
                    strokeWeight: 8,
                });
                flightPath4.setMap(map)
                document.getElementById('reset').addEventListener('click', valina_remove);

                function valina_remove() {
                    flightPath4.setMap(null)
                }
            }


            </c:forEach>
        }

    };
</script>
</body>