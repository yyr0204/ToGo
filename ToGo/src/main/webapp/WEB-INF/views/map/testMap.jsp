<<<<<<< HEAD
=======
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
>>>>>>> develop_Song
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="${pageContext.request.contextPath}/resources/static/css/map_css.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/resources/static/js/jquery.js"></script>
<head>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <script defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC63MWSfMneMDT-oW0JIm_cZkKB1p9nmtI&callback=initMap"></script>
    <script>
        let poly;
        let map;
        let flightPath = null;
        let cityName
        let colorCode = "#" + Math.round(Math.random() * 0xffffff).toString(16);
        function on_submit(event){
            event.preventDefault()
            console.log(event)
        }

        window.initMap = function () {
            var lat = 37.5635694
            var lng = 126.5003
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
            ];
            const university = [
                <c:forEach var="item" items="${places}" varStatus="str">
                {
                    label: "${str.count}",
                    name: "${item.name}",
                    lat: parseFloat(${item.lat}),
                    lng: parseFloat(${item.lon})
                },
                </c:forEach>
            ]
            const ex01 = [
                <c:forEach var="item2" items="${finalList}" varStatus="str">
                {
                    label: String.fromCharCode(64 +${str.count}),
                    name: "${item2.name}",
                    lat:${item2.getLat()},
                    lng:${item2.getLon()}
                },
                </c:forEach>
            ]
            /////////////////대학 마크///////////////
            const beachFlagImg = document.createElement('img');
            beachFlagImg.src = 'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png';
            document.getElementById('uni_add').addEventListener('click', uni_add)

            function uni_add() {
                const infowindow = new google.maps.InfoWindow();
                university.forEach(({label, name, lat, lng}) => {
                    let marker3 = new google.maps.Marker({
                        position: {lat: lat, lng: lng},
                        content: beachFlagImg,
                        map,
                        title: name
                    });

                    function uni_remove() {
                        marker3.setMap(null)
                    }

                    document.getElementById('uni_reset').addEventListener('click', uni_remove)
                })
            }

            ////////////////////////////////////////////////
            document.getElementById('ex_add').addEventListener('click', ex_add)
            function ex_add() {
                ex01.forEach(({label, name, lat, lng}) => {
                    let marker4 = new google.maps.Marker({
                        position: {lat: lat, lng: lng},
                        label,
                        map,
                        title: name
                    });
                    function ex_remove() {
                        marker4.setMap(null)
                    }
                    document.getElementById('ex_reset').addEventListener('click', ex_remove)
                })
            }
            document.getElementById('ex_line_remove').addEventListener("click", ex_line_remove)
            document.getElementById('ex_line_add').addEventListener("click", ex_line_add)

            function ex_line_add() {

                for (let step = 0; step < ex01.length; step++) {
                    if (step % 2 === 0) {
                        colorCode = "#" + Math.round(Math.random() * 0xffffff).toString(16);
                    }
                    
                    const latlng = [ex01[step], ex01[step+1]]

                    // if (flightPath2 != null) {
                    //     flightPath2.setMap(null)
                    // }
                    const flightPath2 = new google.maps.Polyline({
                        path: latlng,
                        geodesic: true,
                        strokeColor: colorCode,
                        strokeOpacity: 1.0,
                        strokeWeight: 2,
                    });
                    flightPath2.setMap(map)
                }
            }

            function ex_line_remove() {
                flightPath2.setMap(null)
            }

            /////////////////다중선///////////////////
            document.getElementById('reset').addEventListener("click", remove)
            document.getElementById('add').addEventListener("click", add)

            function add() {
                colorCode = "#" + Math.round(Math.random() * 0xffffff).toString(16);
                if (flightPath != null) {
                    flightPath.setMap(null)
                }
                flightPath = new google.maps.Polyline({
                    path: ex01,
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

            // ///////////////////////////////////////////////////////
            // $("[id*=city_name]").click(function go() {
            //     const city = {position: {lat: cityLat, lng: cityLon}}
            //     map.panTo(city);
            //
            // })

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
        <c:forEach var="item" items="${finalList}" varStatus="str">
            <div class="place" id="place_bar${str.count}">
                <div class="item2"><img src="${pageContext.request.contextPath}/resources/static/img/5745739.png"></div>
                <div class="item2" id="place_name_area">
                    <a href="#" id="${item.name}">${str.count}${item.name}</a>
                </div>
                <input type="hidden" class="${item.name}lat" value="${item.lat}">
                <input type="hidden" class="${item.name}lon" value="${item.lon}">
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
</body>