<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="${pageContext.request.contextPath}/resources/static/css/map_css.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/resources/static/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/move.js"></script>
<head>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <script defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC63MWSfMneMDT-oW0JIm_cZkKB1p9nmtI&libraries=drawing,geometry,maps&v=beta&callback=initMap"></script>

</head>

<body>
<div class="banner"></div>
<div class="container">
    <div class="item" id="cityList">
        <form action="/map/search" method="post" onsubmit="return false;">
            <input type="text" name="search"/>
            <input type="button" name="전송">
        </form>
        <c:forEach var="item" items="${allList}" varStatus="str">
            <div class="place" id="placeDiv${str.count}">
                <div class="item" title="img_area"><img src="${pageContext.request.contextPath}/resources/static/img/attr.png"></div>
                <div class="item" title="name_area" style="padding-top: 15px">
                    <span class="place_name" title="${item.name}"><h7>${item.name}</h7></span>
                </div>
                <div>
                    <button value="${item.name}" class="city_add">add</button>
                </div>
            </div>
        </c:forEach>
    </div>
    <div id="map" class="item" style="height: 700px"></div>
    <div class="item" id="place_box">
        <c:forEach var="item" items="${places2}" varStatus="str">
            <div style="background: aquamarine;height: 40px;margin: 0 auto;text-align: center">
                <div style="opacity: 1.0;font-weight:500;padding-top: 10px;text-align: center;">
                        ${str.count}일차
                </div>
            </div>
            <c:forEach var="dto" items="${item.value}">
                <br>
                <div class="place2" id="place_bar${str.count}">
                    <div class="item2"><img src="${pageContext.request.contextPath}/resources/static/img/5745739.png">
                    </div>
                    <div class="item2" id="place_name_area">
                        <a href="#" id="${dto.name}" data-aa="A">${dto.name}</a>
                    </div>
                </div>
            </c:forEach>
        </c:forEach>
    </div>
</div>
<button id="add" type="button">경로표시</button>
<button id="remove" type="button">경로끄기</button>
<button id="circle_add" type="button">원표시</button>
<button id="mk_reset" type="button">마커리셋</button>
<button id="mk_add" type="button">마커경로</button>
<button id="ex_line_add" type="button">동선생성</button>
<button id="ex_line_remove" type="button">동선삭제</button>
<button id="shuffle" type="button">셔플</button>
<div style="width: 100%;overflow: auto;white-space: nowrap;">
    <div id="adList">

    </div>
</div>
<script>
    let poly;
    let map;
    let cityName
    let colorCode = "#" + Math.round(Math.random() * 0xffffff).toString(16);
    let malls
    let attrList
    let cityLatlng = [];
    let cityLatlng2 = [];
    let city_marker = null
    let add_markers = {}
    const divList = []
    const attrLines=[]
    let names = []

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
        //////////////////////////////리스트업 배열 부분//////////////////////
        malls = {
            <c:forEach var="item" items="${places2}" varStatus="str">
            <c:forEach var="dto" items="${item.value}">
            ['${dto.name}']: {
                center: {lat:${dto.lat}, lng:${dto.lon}},
                name: '${dto.name}',
                label:${str.count},
                address: '${dto.adress}'
            },
            <%--malls.push({lat:${dto.lat},lng:${dto.lon},name:'${dto.name}',label:${str.count}})--%>
            </c:forEach>
            </c:forEach>
        }
        attrList = {
            <c:forEach var="dto" items="${allList}" varStatus="str">
            ['${dto.name}']: {center: {lat:${dto.lat}, lng:${dto.lon}}, name: '${dto.name}', address: '${dto.adress}'},
            </c:forEach>
        }
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
        $('#add').click(function () {
            for (let num in malls) {
                // Add the circle for this city to the map.
                let numString = numList[malls[num].label - 1]
                var myIcon = new google.maps.MarkerImage("${pageContext.request.contextPath}/resources/static/img/" + numString + ".png", null, null, null, new google.maps.Size(40, 40));
                const marker = new google.maps.Marker({
                    position: malls[num].center,
                    map,
                    icon: myIcon,
                });
                $('#remove').click(function () {
                    marker.setMap(null)
                })
                marker.addListener("click", () => {
                    map.panTo(marker.position);
                    const contentString =
                        '<div id="content">' +
                        '<p><h2>' + malls[num].name + '</h2></p>' +
                        '<p>주소:' + malls[num].address + '</p>' +
                        '</div>'
                    let infowindow = new google.maps.InfoWindow({
                        content: contentString,
                        ariaLabel: "Uluru",
                    });
                    infowindow.open({
                        anchor: marker,
                        map,
                    });
                });
            }
        })

        document.getElementById('add').addEventListener('click', valina_add);
        ///////////////////////////////hover이벤트 부분///////////////////////////////////
        $('#place_name_area>a').mouseenter(function () {
            let move = malls[event.target.id].center
            map.panTo(move)
            let hv_mk=new google.maps.Marker({
                label:malls[event.target.id].name,
                position:move,
                map,
            })
            hv_mk.setMap(map)
            $(event.target).mouseleave(function (){
                hv_mk.setMap(null)
            })
        })
        $('div[class=place]').mouseenter(function (){
            console.log($(event.target).children('.place_name'))
            let name= $(event.target).children('.place_name').attr('title')
            map.panTo(attrList[name].center)
            const over_mk=new google.maps.Marker({
                label:attrList[name].name,
                position:attrList[name].center,
                map,
            })
            over_mk.setMap(map)
            $(event.target).mouseleave(function (){
                over_mk.setMap(null)
            })
        })
        ////////////////////////동선생선//////////////
        $(document).ready(function () {
            $('button[class=city_add]').click(function () {
                divList.push($(event.target).parent().parent())
                $(event.target).parent().parent().hide()
                let name = event.target.value
                names.push(name)
                const newDiv = document.createElement('div');
                const newDiv1 = document.createElement('div');
                const newDiv2 = document.createElement('div');
                const newDiv3 = document.createElement('div');
                const newSpan = document.createElement('span')
                const newBt = document.createElement('button')
                const newText = document.createTextNode('X')
                const newText2 = document.createTextNode(name)
                newBt.appendChild(newText)
                newBt.className = 'add_remove'
                newSpan.appendChild(newText2)
                newSpan.title=name
                newDiv.className = 'adList_box'
                newDiv.id='adListDiv'+names.length.toString()
                newDiv1.className = 'item'
                newDiv2.className = 'item'
                newDiv3.className = 'item'
                newDiv2.style.paddingTop = '15px'
                newDiv2.style.margin = '0 auto'
                document.getElementById('adList').appendChild(newDiv)
                newDiv.appendChild(document.createElement('div'))
                newDiv.appendChild(newDiv2).appendChild(newSpan)
                newDiv.appendChild(newDiv3).appendChild(newBt)
                newDiv.appendChild(newDiv3).appendChild(newBt)
                $('#adList').css('grid-template-columns', 'repeat(+' + divList.length + ', 200px)');
                let add_icon = new google.maps.MarkerImage("${pageContext.request.contextPath}/resources/static/img/add_marker.png", null, null, null, new google.maps.Size(40, 40));
                let add_marker
                add_marker = new google.maps.Marker({
                    icon: add_icon,
                    position: attrList[name].center,
                    map: map,
                })
                add_markers[name]=add_marker;
                const contentString =
                    '<div id="content">' +
                    '<p><h2>' + attrList[name].name + '</h2></p>' +
                    '<p>주소:' + attrList[name].address + '</p>' +
                    '</div>'
                let infowindow = new google.maps.InfoWindow({
                    content: contentString,
                    ariaLabel: "Uluru",
                });

                add_marker.addListener("click", () => {
                    infowindow.open({
                        anchor: add_marker,
                        map,
                    });
                });
                add_marker.setMap(map)
                map.panTo(attrList[name].center)
            });
            /////////////////////추가경로삭제/////////////////
            $(document).on('click', ".add_remove", function () {
                let adList_box = $(event.target).parent().parent()
                let name = adList_box.find('span').attr('title')
                adList_box.remove()
                map.panTo(add_markers[name].position)
                add_markers[name].setMap(null)
                delete add_markers[name]
                let num3 = names.indexOf(name)
                divList[num3].show()
                divList.splice(num3,1)
                console.log(num3)
                names.splice(num3,1)
                attrLines[num3-1].setMap(null)
            })
            $('#ex_line_add').click(function () {
                for (num = 0; num < names.length - 1; num++) {
                    colorCode = "#" + Math.round(Math.random() * 0xffffff).toString(16);
                    let attrLine
                    attrLines.push(attrLine = new google.maps.Polyline({
                        path: [add_markers[names[num]].position, add_markers[names[num+1]].position],
                        geodesic: true,
                        strokeColor: colorCode,
                        strokeOpacity: 1.0,
                        strokeWeight: 4,
                    }));
                    attrLine.setMap(map)
                    $('#ex_line_remove').click(function () {
                        attrLine.setMap(null)
                    })
                }
            })
        })
        //////////////////////셔플///////////////////////////
        $('#shuffle').click(function (){
            let num=$('#adList').childElementCount
        })
        //////////////////////클릭이벤트 부분///////////////////
        $('#list_name_area>a').click(function () {
            const contentString =
                '<div id="content">' +
                '<p><h2>' + attrList[event.target.id].name + '</h2></p>' +
                '<p>주소:' + attrList[event.target.id].address + '</p>'
            '</div>'
            const city_info = new google.maps.InfoWindow({
                content: contentString,
                ariaLabel: "Uluru",
            });
            if (city_marker != null) {
                city_marker.setMap(null)
            }
            var myIcon = new google.maps.MarkerImage("${pageContext.request.contextPath}/resources/static/img/attr.png", null, null, null, new google.maps.Size(40, 40));
            let city = attrList[event.target.id].center
            city_marker = new google.maps.Marker({
                position: city,
                map,
                icon: myIcon,
            });
            city_marker.setMap(map)
            map.panTo(city)
            city_info.open({
                anchor: city_marker,
                map,
            })
        })
        for (let num = 0; num < malls.length - 1; num++) {
            let name = num + "_city"
            let circle_lat = (malls[num].lat + malls[num + 1].lat) / 2
            let circle_lng = (malls[num].lng + malls[num + 1].lng) / 2
            let dist = Math.sqrt(Math.pow((malls[num].lat - malls[num + 1].lat), 2) + Math.pow((malls[num].lng - malls[num + 1].lng), 2))
            cityLatlng.push({city_center: {lat: circle_lat, lng: circle_lng}, population: dist})
        }

        for (let num = 1; num < malls.length - 2; num++) {
            if ((num - 1) % 6 === 0) {
                let circle_lat2 = (malls[num].lat + malls[num + 3].lat) / 2
                let circle_lng2 = (malls[num].lng + malls[num + 3].lng) / 2
                let dist2 = Math.sqrt(Math.pow((malls[num].lat - malls[num + 3].lat), 2) + Math.pow((malls[num].lng - malls[num + 3].lng), 2))
                cityLatlng2.push({city_center: {lat: circle_lat2, lng: circle_lng2}, population: dist2})
            }
        }
        // for(const city in cityLatlng2){
        //         const cityCircle = new google.maps.Circle({
        //             strokeColor: "#FF0000",
        //             strokeOpacity: 0.8,
        //             strokeWeight: 2,
        //             fillColor: "#FF0000",
        //             fillOpacity: 0.35,
        //             map,
        //             center: cityLatlng2[city].city_center,
        //             radius: Math.sqrt(cityLatlng2[city].population*10000) * 125,
        //         });
        // }
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