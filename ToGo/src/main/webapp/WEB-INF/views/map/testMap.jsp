<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<head>
    <link href="${pageContext.request.contextPath}/resources/static/css/map_css.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/static/css/plan_css.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/move.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/loading.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/city_select.js"></script>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <script defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC63MWSfMneMDT-oW0JIm_cZkKB1p9nmtI&libraries=drawing,geometry,maps,places&v=beta&callback=initMap"></script>
    <fmt:formatDate value="${tourInfo.startDay}" pattern="yyyy.MM.dd" type="date" var="startDay"/>
    <fmt:formatDate value="${tourInfo.endDay}" pattern="yyyy.MM.dd" type="date" var="endDay"/>
    <fmt:parseNumber value="${tourInfo.startDay.time/(1000*60*60*24)}" integerOnly="true" var="str" scope="request"/>
    <fmt:parseNumber value="${tourInfo.endDay.time/(1000*60*60*24)}" integerOnly="true" var="end" scope="request"/>
</head>

<body style="margin: 0px;overflow-y: hidden">

<div class="container">
    <nav id="navcc">
        <a href="#">TOGO${lat}</a>
    </nav>
    <div style="position: relative;overflow-y: auto;">
        <div>
            <div id="map" style="width: 100%;height:100%;position:relative;overflow: hidden;"></div>
        </div>
    </div>
    <%--오른쪽 선택구역 리스트--%>
    <div id="recommend_list" class="recommend_area">
        <div class="cityListDiv" id="cityList">
            <c:forEach var="item" items="${allList}" varStatus="str">
                <div class="recommendPlaceDiv" id="placeDiv${str.count}">
                    <div class="item" title="img_area"><img
                            src="${pageContext.request.contextPath}/resources/static/img/attr.png"></div>
                    <div class="item recommendPlace_name">
                        <div class="name_area">
                            <span class="place_name" title="${item.name}"><h7>${item.name}</h7></span>
                        </div>
                        <div class="address_area">
                            <span class="place_name" title="${item.adress}"><h7>${item.adress}</h7></span>
                        </div>
                    </div>
                    <div>
                        <input type="radio" value="${item.name}" class="city_add_button">
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="lodgingListDiv" id="lodgingList" style="display: none">
            <c:forEach var="item" items="${lodgingList}" varStatus="str">
                <div class="recommendLodgingDiv" id="placeDiv${str.count}">
                    <div class="item" title="img_area"><img
                            src="${pageContext.request.contextPath}/resources/static/img/hotel.png"></div>
                    <div class="item recommendLodging_name">
                        <div class="name_area">
                            <span class="lodging_name" title="${item.name}"><h7>${item.name}</h7></span>
                        </div>
                        <div class="address_area">
                            <span class="lodging_name" title="${item.adress}"><h7>${item.adress}</h7></span>
                        </div>
                    </div>
                    <div>
                        <input type="radio" value="${item.name}" class="lodging_add_button">
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <%--    왼쪽 사이드 바--%>
    <div class="mySidebar">
        <div>
            <ul style="list-style: none;text-align: center">
                <c:if test="${tourInfo!=null}">
                <li style="padding-top: 10px" class="area_name">
                    <a href="#"> <span style="font-size: 25px;font-weight: 500"> ${tourInfo.area}</span></a><br>
                    <span style="opacity: 0.5">seoul</span>
                </li>
                <li style="font-size: 30px;font-weight: 800;">${end-str}DAY</li>
                <li style="font-size: 18px;font-weight: 300;">${startDay}~${endDay}</li>
                </c:if>
                <c:if test="${tourInfo==null}">
                <li style="padding-top: 10px" class="area_name">
                    <a href="#"><span style="font-size: 25px;font-weight: 500;cursor: pointer">여행지를 골라주세요</span></a><br>
                    <span style="opacity: 0.5">seoul</span>
                </li>
                <li style="font-size: 30px;font-weight: 800;">여행일을</li>
                <li style="font-size: 18px;font-weight: 300;">정해주세요</li>
                </c:if>
            </ul>
        </div>

        <div id="select_list" style="margin: 0 5px 0 5px !important; padding-bottom: 1px !important">
            <div style="margin: 10px 0 10px 0;text-align: center">
                <span style="font-weight: 500;font-size: 22px">선택목록</span>
            </div>
            <div class="select_list">
                <a href="#" id="select_hotel_button" class="button"><span>호텔</span></a>
                <a href="#" id="select_place_button" class="button"
                   style="border-bottom: 2px solid orangered"><span>장소</span></a>
            </div>
            <div>
                <button id="all_delete"
                        style="margin:3px auto;width: 100%;height: 35px; line-height: 0; box-shadow: none; background-color: #ff1744 !important;border: none;color: white;">
                    <h7>장소전체삭제</h7>
                </button>
            </div>
            <div id="select_place_list" style="display: block">
            </div>
            <div id="select_hotel_list">
                <ul style="list-style: none; padding: 0;overflow-y: auto;height: 60vh">
                </ul>
            </div>
        </div>
        <div></div>
    </div>
    <div style="display: grid;grid-template-rows: 3fr 2fr;z-index: 2;right: 0px;position: absolute;top:75px;height: 150px;background-color: #FFFFFF;align-items: center;text-align: center">
        <div style="height: 80%;padding-top: 30px">
            <input type="text" style="width: 90%;height: 50%;border: none;background-color: aliceblue">
        </div>
        <div style="background-color: #FFFFFF;;width:266px;padding: 2px 2px 2px 2px;display: grid;grid-template-columns: 1fr 1fr;">
            <a href="#" id="hotel_add" class="float_button">호텔</a>
            <a href="#" id="place_add" class="float_button">관광지</a>
        </div>
    </div>

    <div class="buttons" style="position: absolute;left: 300px;top:90px">
        <a id="add" class="float_button" href="#" data-tooltip-text="경로표시">경로표시</a>
        <a id="remove" class="float_button" href="#">경로끄기</a>
        <a id="circle_add" class="float_button" href="#">추천경로</a>
        <a id="mk_reset" class="float_button" href="#">마커리셋</a>
        <a id="mk_add" class="float_button" href="#">마커경로</a>
        <a id="ex_line_add" class="float_button" href="#">동선생성</a>
        <a id="ex_line_remove" class="float_button" href="#">동선삭제</a>
        <a id="shuffle" class="float_button" href="#">셔플</a>
        <a id="shuffle2" class="float_button" href="#">아이콘 셔플</a>
        <a id="auto_move_bt" class="float_button" href="#" style="opacity: 0.5">자동이동</a>
    </div>
    <div>
        <a href="#">식당</a>
        <a href="#"></a>
        <a href="#">식당</a>
        <a href="#">식당</a>
    </div>
    <%--    <div style="">--%>
    <%--        <div id="adList">--%>

    <%--        </div>--%>
    <%--    </div>--%>
</div>
<script>
    ////////////////////////////a태그, 버튼 이동 막기///////////////////////////
    function on_submit(event) {
        event.preventDefault()
        console.log(event)
    }

    let map;
    let cityName
    let malls
    let attrList
    let cityLatlng = [];
    let cityLatlng2 = [];
    let city_marker = null
    let add_markers = {}
    const divList = []
    const attrLines = []
    let names = []
    let adList = []
    let myIcons = []
    let lodgings_list = []
    let lodgings = []
    let reList = {}
    let recommend = {}
    let re_mks = [];
    let re_polys = []
    let re_mk = {}

    window.initMap = function maps() {
        let opacity
        let listName = 'place'
        $('.area_name>a').click(select_open)
        $(document).on('click','.close',select_close)
        $(document).on('click','.cityName',()=>{
            $('.area_name>a>span').text(null)
            $('.area_name>a>span').text($(event.target).text())
            let name = $(event.target).text()
            let form = {name:$(event.target).text()}
            $.ajax({
                type: "POST",
                url: "/map/mapInfo",
                data: form,
                success: function (data) {
                    console.log(data)
                    maps()
                    map.panTo({lat:data.lat,lng:data.lon})
                },
                error: function (){
                    alert('에러')
                }
            })
            select_close()
        })
        function colorCode() {
            let colorCode = "#" + Math.round(Math.random() * 0xffffff).toString(16);
            return colorCode
        }

        ///////////////자동이동 버튼 이벤트//////////////////////////
        $('#auto_move_bt').click(function () {
            opacity = parseFloat($(event.target).css('opacity'))
            if (opacity < 1) {
                $(event.target).css('opacity', '1')
            } else if (opacity === 1 || opacity === 0.8) {
                $(event.target).css('opacity', '0.5')
            }
        })
        <c:if test="${latlon!=null}">
        var lat = ${latlon.lat}
        var lng = ${latlon.lon}
        ${latlon.lon}
        </c:if>
        <c:if test="${tourInfo==null}">
        var lat = 37.5512;
        var lng = 126.9933
        </c:if>
        const map = new google.maps.Map(document.getElementById("map"), {
            center: {lat, lng},
            zoom: 13,
        });
        //////////////////////////////리스트업 배열 부분//////////////////////

        lodgings_list = {
            <c:forEach var="dto" items="${lodgingList}" varStatus="str">
            ['${dto.name}']: {
                center: {lat:${dto.lat}, lng:${dto.lon}},
                name: '${dto.name}',
                label:${str.count},
                address: '${dto.adress}'
            },
            </c:forEach>
        }
        attrList = {
            <c:forEach var="dto" items="${allList}" varStatus="str">
            ['${dto.name}']: {center: {lat:${dto.lat}, lng:${dto.lon}}, name: '${dto.name}', address: '${dto.adress}'},
            </c:forEach>
        }
        var drawingManager = new google.maps.drawing.DrawingManager();
        drawingManager.setMap(map);


        const infowindow = new google.maps.InfoWindow();
        //////////////////////아이콘 부분////////////////////////////
        const numList = ["one", "two", "three", "four", "five", 'six', 'seven', 'eight', 'nine', 'ten']
        myIcons = []
        for (let num in numList) {
            myIcons.push(new google.maps.MarkerImage("${pageContext.request.contextPath}/resources/static/img/" + numList[num] + ".png", null, null, null, new google.maps.Size(40, 40)))
        }
        $('#add').click(recommend_course)

        function recommend_course() {
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
        }

        ///////////////////////////////hover이벤트 부분///////////////////////////////////
        $(document).on('mouseenter', 'div[class=recommend_area]>div>div[class*=recommend]', function (e) {
            if (listName === 'place') {
                let name = $(event.target).find('.place_name', 'span').attr('title')
                console.log($(event.target).find('.place_name', 'span'))
                var over_mk = add_marker(attrList[name].center)
                const contentString =
                    '<div id="content">' +
                    '<p><h2>' + attrList[name].name + '</h2></p>' +
                    '<p>주소:' + attrList[name].address + '</p>' +
                    '</div>'
                info_window(contentString, attrList[name].center, over_mk)
                $(event.target).mouseleave(function () {
                    over_mk.setMap(null)
                })
            } else {
                let name = $(event.target).find('.lodging_name', 'span').attr('title')
                console.log($(event.target))
                var over_mk = add_marker(lodgings_list[name].center)
                const contentString =
                    '<div id="content">' +
                    '<p><h2>' + lodgings_list[name].name + '</h2></p>' +
                    '<p>주소:' + lodgings_list[name].address + '</p>' +
                    '</div>'
                info_window(contentString, lodgings_list[name].center, over_mk)
                $(event.target).mouseleave(function () {
                    over_mk.setMap(null)
                })
            }

            function add_marker(position) {
                const over_mk = new google.maps.Marker({
                    position: position,
                    map,
                })
                map.panTo(position)
                over_mk.setMap(map)
                return over_mk
            }
        })

        $('#circle_add').click(function () {
            openLoading()

            let re_poly
            let start = new Date().getTime()
            let form = {area: "서울", startDay: "2023-07-16", endDay: "2023-07-18"}
            re_polys.push(re_poly = new google.maps.Polyline({
                geodesic: true,
                strokeColor: colorCode(),
                strokeOpacity: 1.0,
                strokeWeight: 4,
            }))
            re_poly.setMap(map)
            $.ajax({
                type: "POST",
                url: "/trip/place2",
                data: form,
                success: function (data) {
                    try {
                        const infoWindow = new google.maps.InfoWindow();
                        console.log(data)
                        console.log(Object.keys(data).length)
                        for (let num = 1; num <= Object.keys(data).length; num++) {
                            let result = data[num + '일차']
                            let day = []
                            var newDiv1 = '<div><div class="day_info_box">' + num + '일차' + '</div>' +
                                '<ul class="day_info_list" id="' + num + 'day_list"></ul><div>'
                            $('#select_place_list').append(newDiv1)
                            for (let num2 in result) {

                                let re_lnglat = {lat: result[num2].lat, lng: result[num2].lon}
                                var re_marker = new google.maps.Marker({
                                    position: re_lnglat,
                                    title: result[num2].name,
                                    optimized: false,
                                    icon: myIcons[num - 1],
                                    map,
                                })
                                re_marker.setMap(map)
                                day.push(re_lnglat)
                                re_mks.push(re_marker)
                                re_mks[num2].addListener('click', (e) => {
                                    console.log(e)
                                    infoWindow.close();
                                    infoWindow.setContent(re_mks[num2].getTitle());
                                    infoWindow.open(re_mks[num2].getMap(), e);
                                    map.panTo(re_mks[num2])
                                })
                                var newDiv = '<li>\n<div class="placeDiv">\n<div>\n<img src="${pageContext.request.contextPath}/resources/static/img2/20201230173806551_JRT8E1VC.png">\n' +
                                    '</div>\n<div style="display: grid;grid-template-rows: 2fr 3fr">\n' +
                                    '<div>\n<span>' + result[num2].name + '</span>\n</div>\n<div></div>\n</div>\n</div>\n</li>'
                                let id = num + 'day_list'
                                console.log(id)
                                $('ul[id=' + id + ']').append(newDiv)
                            }

                            let re_poly = new google.maps.Polyline({
                                path: day,
                                strokeColor: colorCode(),
                                strokeOpacity: 1.0,
                                strokeWeight: 3,
                            })
                            re_poly.setMap(map)
                            re_polys[num] = re_poly
                        }
                        console.log(re_mk)
                        console.log(re_polys)
                        closeLoading()
                    } catch (e) {
                        alert(e)
                        closeLoading()
                    }
                },
                error: function () {
                    alert("에러 발생");
                    closeLoading()
                }
            })

        })
        ////////////////////일차별 접고 펴기///////////////////
        $(document).on("click", ".day_info_box", function () {
            try {
                console.log($(event.target).css('opacity'))
                let name = $(event.target).parent().find('ul').attr('id').slice(0, 1)
                if ($(event.target).css('opacity') !== '1') {
                    $(event.target).parent().parent().find('ul').hide()
                    for (let num in re_polys) {
                        re_polys[num].setMap(null)
                    }
                    $(event.target).parent().find('ul').show()
                    re_polys[parseInt(name)].setMap(map)
                    $('.day_info_box').css('opacity', '0.6')
                    $(event.target).css('opacity', '1')
                } else {
                    $(event.target).parent().parent().find('ul').show()
                    for (let num in re_polys) {
                        re_polys[num].setMap(map)
                    }
                    $('.day_info_box').css('opacity', '0.8')
                }
            } catch (e) {
                console.log(e)
            }
        })
        ////////////////////////동선생성//////////////////////////////////
        $('.city_add_button').change(function () {
            $(event.target).parent().parent().hide()
            let name = $(event.target).attr('value')
            var newDiv = '<li>\n<div class="placeDiv">\n<div>\n<img src="${pageContext.request.contextPath}/resources/static/img2/20201230173806551_JRT8E1VC.png">\n' +
                '</div>\n<div style="display: grid;grid-template-rows: 2fr 3fr">\n' +
                '<div>\n<span>' + name + '</span>\n</div>\n<div></div>\n</div>\n</div>\n</li>'
            $('#select_place_list>ul').append(newDiv)
            add_marker(name, attrList[name].center)
        })
        $('.lodging_add_button').change(function () {
            $(event.target).parent().parent().hide()
            let name = $(event.target).attr('value')
            var newDiv = '<li>\n<div class="hotelDiv">\n<div>\n<img src="${pageContext.request.contextPath}/resources/static/img2/20201230173806551_JRT8E1VC.png">\n' +
                '</div>\n<div style="display: grid;grid-template-rows: 2fr 3fr">\n' +
                '<div>\n<span>' + name + '</span>\n</div>\n<div></div>\n</div>\n</div>\n</li>'
            $('#select_hotel_list>ul').append(newDiv)
            add_marker(name, lodgings_list[name].center)
            console.log(name, lodgings_list[name].center)
        })


        function course_add() {
            let type = event.target.className.split('_')[0]
            divList.push($(event.target).parent().parent())
            $(event.target).parent().parent().hide()
            let name = event.target.value
            if (type === 'city') {
                names.push(name)
            } else {
                lodgings.push(name)
            }
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
            newSpan.title = name
            newDiv.className = 'adList_box'
            newDiv.id = 'adListDiv' + names.length.toString()
            newDiv1.className = 'item'
            newDiv2.className = 'item'
            newDiv3.className = 'item'
            newDiv2.style.paddingTop = '15px'
            newDiv2.style.margin = '0 auto'
            document.getElementById('adList').appendChild(newDiv)
            newDiv.appendChild(document.createElement('div'))
            newDiv.appendChild(newDiv2).appendChild(newSpan)
            newDiv.appendChild(newDiv3).appendChild(newBt)
            $('#adList').css('grid-template-columns', 'repeat(+' + divList.length + ', 200px)');
            add_marker(name, type)
        }


        function add_marker(name, type) {
            let add_icon = new google.maps.MarkerImage("${pageContext.request.contextPath}/resources/static/img/add_marker.png", null, null, null, new google.maps.Size(40, 40));
            let hotel_icon = new google.maps.MarkerImage("${pageContext.request.contextPath}/resources/static/img/hotel.png", null, null, null, new google.maps.Size(40, 40));
            if (type === 'city') {
                const contentString =
                    '<div id="content">' +
                    '<p><h2>' + attrList[name].name + '</h2></p>' +
                    '<p>주소:' + attrList[name].address + '</p>' +
                    '</div>'

                add_markers[name] = add_marker(contentString, myIcons[names.length - 1], attrList[name].center);
                add_markers[name].setMap(map)
                info_window(contentString, attrList[name].center, add_markers[name])

            }
            if (type === 'hotel') {
                let hotel_marker = new google.maps.Marker({
                    icon: hotel_icon,
                    position: lodgings_list[name].center,
                    map: map,
                })
                const contentString =
                    '<div id="content">' +
                    '<p><h2>' + lodgings_list[name].name + '</h2></p>' +
                    '<p>주소:' + lodgings_list[name].address + '</p>' +
                    '</div>'
                info_window(contentString, lodgings_list[name].center, hotel_marker)
                add_markers[name] = add_marker(contentString, hotel_icon, lodgings_list[name].center)
                add_markers[name].setMap(map)
            }

            function add_marker(contentString, icon, position) {
                let add_marker = new google.maps.Marker({
                    icon: icon,
                    position: position,
                    map: map,
                })
                info_window(contentString, position, add_marker)
                return add_marker;
            }
        }

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
            divList.splice(num3, 1)
            console.log(num3)
            names.splice(num3, 1)
            line_remove()
            line_add()
        })
        $('#ex_line_add').click(line_add)
        $('#ex_line_remove').click(line_remove)
        //////////////////////호텔목록////////////////////
        $('#hotel_add').click(load_lodging)

        function load_lodging() {
            listName = 'lodging'
            $('.cityListDiv').hide()
            $('.lodgingListDiv').show()
        }


        $('#place_add').click(load_place)

        function load_place() {
            console.log('loadPlace')
            listName = 'place'
            $('.lodgingListDiv').hide()
            $('.cityListDiv').show()
        }

        function line_add() {
            for (num = 0; num < names.length - 1; num++) {

                attrLines.push(attrLine = new google.maps.Polyline({
                    path: [add_markers[names[num]].position, add_markers[names[num + 1]].position],
                    geodesic: true,
                    strokeColor: colorCode(),
                    strokeOpacity: 1.0,
                    strokeWeight: 4,
                }))
                attrLine.setMap(map)
            }
        }

        function line_remove() {
            for (let num in attrLines) {
                attrLines[num].setMap(null)
            }
            attrLines.length = 0
        }

        ///////////////////////지도 중심 위치//////////////////////
        //////////////////////선택한 장소 목록//////////////////
        $('#select_hotel_button').click(function () {
            $(event.target).css('border-bottom', '2px solid orangered')
            $('#select_place_button').css('border-bottom', 'none')
            $('#select_place_list').hide()
            $('#select_hotel_list').show()
        })
        $('#select_place_button').click(function () {
            $(event.target).css('border-bottom', '2px solid orangered')
            $('#select_hotel_button').css('border-bottom', 'none')
            $('#select_hotel_list').hide()
            $('#select_place_list').show()
        })
        $('#all_delete').click(function () {
            $('#select_hotel_list').find('li').remove()
            $('#select_place_list').find('li').remove()
        })
        //////////////////////셔플///////////////////////////
        $('#shuffle').click(function () {
            let names2 = []
            line_remove()
            let num = $('#adList').children().length
            adList = Array.from({length: num}, (v, i) => i + 1)
            adList.sort(() => Math.random() - 0.5);
            console.log(adList)
            for (let n2 = 0; n2 < 1; n2++) {
                let name = '#adListDiv' + adList[n2].toString()
                const item = names[adList[n2] - 1]
                console.log(item)
                names2.push(item)
                $(name).insertAfter("#adListDiv" + num.toString());
                for (let n = 1; n < adList.length; n++) {
                    const item = names[adList[n] - 1]
                    names2.push(item)
                    console.log(item)
                    let name = '#adListDiv' + adList[n].toString()
                    $(name).insertAfter("#adListDiv" + adList[n - 1]);
                }
            }
            console.log(names2)
            names = names2
            line_add()
            shuffle2()
        })

        function shuffle2() {
            let numlist = Array.from({length: names.length}, (v, i) => i + 1)
            numlist.sort(() => Math.random() - 0.5);
            for (let num in names) {
                add_markers[names[num]].icon = myIcons[num]
                // let name = names[num]
                // add_markers[name].icon = myIcons[numlist[num]]
                add_markers[names[num]].setMap(null)
                add_markers[names[num]].setMap(map)
            }
        }

        $('#shuffle2').click()
        //////////////////////클릭이벤트 부분//////////////////////
        $('#list_name_area>a').click(function () {
            const contentString =
                '<div id="content">' +
                '<p><h2>' + attrList[event.target.id].name + '</h2></p>' +
                '<p>주소:' + attrList[event.target.id].address + '</p>'
            '</div>'
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
            info_window(contentString, city, city_marker)
        })

        function info_window(contentString, city, city_marker) {
            const city_info = new google.maps.InfoWindow({
                content: contentString,
                ariaLabel: "Uluru",
            });
            city_info.open({
                anchor: city_marker,
                map,
            })
            map.panTo(city)
        }
    };
</script>
<script>
    ////////////////안쓰는 기능//////////////////
    ////////////////클릭 마커/////////////////
    // poly = new google.maps.Polyline({
    //     strokeColor: colorCode,
    //     strokeOpacity: 1.0,
    //     strokeWeight: 3,
    // });
    // map.addListener("click", addLatLng);
    // let num = 0;
    //
    // function addLatLng(event) {
    //     let chr = String.fromCharCode(65 + num)
    //     num++
    //     const path = poly.getPath();
    //     path.push(event.latLng);
    //     // Add a new marker at the new plotted point on the polyline.
    //     let marker2 = new google.maps.Marker({
    //         label: chr,
    //         position: event.latLng,
    //         title: "#" + path.getLength(),
    //         map: map,
    //     });
    //     document.getElementById('mk_reset').addEventListener("click", mk_remove)
    //     document.getElementById('mk_add').addEventListener("click", mk_add)
    //
    //     function mk_remove() {
    //         marker2.setMap(null)
    //         poly.setMap(null)
    //         poly = new google.maps.Polyline({
    //             strokeColor: colorCode,
    //             strokeOpacity: 1.0,
    //             strokeWeight: 3,
    //         });
    //         num = 0
    //     }
    //
    //     document.getElementsByClassName('')
    //
    //     function mk_add() {
    //         poly.setMap(map)
    //     }
    // }
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

    <%--    function valina_add() {--%>

    <%--        <c:forEach var="item" items="${places2}" varStatus="str">--%>
    <%--        colorCode = "#" + Math.round(Math.random() * 0xffffff).toString(16);--%>
    <%--        latlng3 = [--%>
    <%--            <c:forEach var="dto" items="${item.value}">--%>
    <%--            {lat: parseFloat(${dto.lat}), lng: parseFloat(${dto.lon})},--%>
    <%--            </c:forEach>--%>
    <%--        ]--%>
    <%--        // if (flightPath2 != null) {--%>
    <%--        //     flightPath2.setMap(null)--%>
    <%--        // }--%>
    <%--        for (let step = 0; step < malls.length - 1; step++) {--%>
    <%--            const flightPath4 = new google.maps.Polyline({--%>
    <%--                path: [latlng3[step], latlng3[step + 1]],--%>
    <%--                geodesic: true,--%>
    <%--                strokeColor: colorCode,--%>
    <%--                strokeOpacity: 1.0,--%>
    <%--                strokeWeight: 8,--%>
    <%--            });--%>
    <%--            flightPath4.setMap(map)--%>
    <%--            document.getElementById('reset').addEventListener('click', valina_remove);--%>

    <%--            function valina_remove() {--%>
    <%--                flightPath4.setMap(null)--%>
    <%--            }--%>
    <%--        }--%>
    <%--        </c:forEach>--%>
    <%--    }--%>
    // $('#place_name_area>a').mouseenter(function () {
    //     let move = malls[event.target.id].center
    //     map.panTo(move)
    //     let hv_mk = new google.maps.Marker({
    //         label: malls[event.target.id].name,
    //         position: move,
    //         map,
    //     })
    //     hv_mk.setMap(map)
    //     $(event.target).mouseleave(function () {
    //         hv_mk.setMap(null)
    //     })
    // })
    // for (let num = 0; num < malls.length - 1; num++) {
    //     let name = num + "_city"
    //     let circle_lat = (malls[num].lat + malls[num + 1].lat) / 2
    //     let circle_lng = (malls[num].lng + malls[num + 1].lng) / 2
    //     let dist = Math.sqrt(Math.pow((malls[num].lat - malls[num + 1].lat), 2) + Math.pow((malls[num].lng - malls[num + 1].lng), 2))
    //     cityLatlng.push({city_center: {lat: circle_lat, lng: circle_lng}, population: dist})
    // }
    //
    // for (let num = 1; num < malls.length - 2; num++) {
    //     if ((num - 1) % 6 === 0) {
    //         let circle_lat2 = (malls[num].lat + malls[num + 3].lat) / 2
    //         let circle_lng2 = (malls[num].lng + malls[num + 3].lng) / 2
    //         let dist2 = Math.sqrt(Math.pow((malls[num].lat - malls[num + 3].lat), 2) + Math.pow((malls[num].lng - malls[num + 3].lng), 2))
    //         cityLatlng2.push({city_center: {lat: circle_lat2, lng: circle_lng2}, population: dist2})
    //     }
    // }
</script>
</body>