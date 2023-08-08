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
        <a href="/ToGo/trip/main">TOGO</a>
        <i style="cursor: pointer" class="user_info"><img src="${pageContext.request.contextPath}/resources/static/img/person-circle.svg"
                style="width: 25px;height: 25px"></i>
    </nav>
    <div style="position: relative;overflow-y: auto;">
        <div>
            <div id="map" style="width: 100%;height:100%;position:relative;overflow: hidden;"></div>
        </div>
    </div>
    <%--    왼쪽 사이드 바--%>
    <div class="mySidebar">
        <div>
            <ul style="list-style: none;text-align: center">
                <c:if test="${tourInfo!=null}">
                    <li style="padding-top: 10px" class="area_name">
                        <a href="#"> <span style="font-size: 25px;font-weight: 500"> ${tourInfo.area}</span></a><br>
                        <span style="opacity: 0.5">${latlon.name}</span>
                    </li>
                    <li style="font-size: 30px;font-weight: 800;" class="total_days"></li>
                    <li style="font-size: 18px;font-weight: 300;"><span class="startDay">${startDay}</span><input
                            type="date" name="startDay" style="width: 15px;border: none"/>~<span
                            class="endDay">${endDay}</span><input type="date" name="endDay"
                                                                  style="width: 15px;border: none"/></li>
                </c:if>
                <c:if test="${tourInfo==null}">
                    <li style="padding-top: 10px" class="area_name">
                        <a href="#"><span style="font-size: 25px;font-weight: 500;cursor: pointer">여행지를 골라주세요</span></a><br>
                        <span style="opacity: 0.5">---</span>
                    </li>
                    <li style="font-size: 30px;font-weight: 800;">0</li>
                    <li style="font-size: 18px;font-weight: 300;"><a class="tourDays">여행일을 골라주세요</a><input type="date"
                                                                                                           name="startDay"
                                                                                                           style="display:none"/>
                    </li>
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
    <%--오른쪽 선택구역 리스트--%>
    <div id="recommend_list" class="recommend_area">
        <div class="search_select" style="bottom:85vh !important;">
            <a href="">호텔</a>
            <a href="">관광지</a>
        </div>
        <div class="search_bar">
            <input type="text" placeholder="관광지 검색..">
            <i>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search"
                     viewBox="0 0 16 16">
                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                </svg>
            </i>
        </div>
        <div class="cityListDiv" id="cityList">
        </div>
        <div class="lodgingListDiv" id="lodgingList" style="display: none">
        </div>
    </div>
    <%--    <div style="display: grid;grid-template-rows: 3fr 2fr;z-index: 2;right: 0px;position: absolute;top:75px;height: 150px;background-color: #FFFFFF;align-items: center;text-align: center">--%>
    <%--        <div style="height: 80%;padding-top: 30px">--%>
    <%--            <input type="text" style="width: 90%;height: 50%;border: none;background-color: aliceblue">--%>
    <%--        </div>--%>
    <%--        <div style="background-color: #FFFFFF;;width:266px;padding: 2px 2px 2px 2px;display: grid;grid-template-columns: 1fr 1fr;">--%>
    <%--            <a href="#" id="hotel_add" class="float_button">호텔</a>--%>
    <%--            <a href="#" id="place_add" class="float_button">관광지</a>--%>
    <%--        </div>--%>
    <%--    </div>--%>

    <div class="buttons" style="position: absolute;left: 300px;top:90px">
        <a id="recommend_list_add" class="float_button" href="#">추천경로</a>
        <a id="ex_line_add" class="float_button" href="#">동선생성</a>
        <a id="schedule_save" class="float_button" href="#">일정저장</a>
        <a id="place_save" class="float_button" href="#">장바구니</a>
        <div class="place_bag" style="display: none;transition: 0.5s;">
            <div style="display:flex;flex-flow: row;justify-content: space-between">
                <input type="button" value="선택삭제">
                <input type="button" value="전체삭제">
            </div>
            <ul>

            </ul>
        </div>
        <a id="shuffle2" class="float_button" href="#">아이콘 셔플</a>
        <a id="auto_move_bt" class="float_button" href="#" style="opacity: 0.5">자동이동</a>
    </div>
    <div>
        <a id="side_popup" class="float_button" href="#">동선삭제</a>
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
    }

    let map = {}
    let infoWindow
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
    let user_schedule
    let login
    let tourInfo = {
        area: '${tourInfo.area}',
        days: {
            start: '<fmt:formatDate value="${tourInfo.startDay}" pattern="yyyy-MM-dd"/>',
            end: '<fmt:formatDate value="${tourInfo.endDay}" pattern="yyyy-MM-dd"/>'
        },
        totalDay:${end-str+1},
        center: {lat:${latlon.lat}, lng:${latlon.lon}},
        select_place:[],
        name:'${latlon.name}'
    }
    $(document).ready(() => {
        $('.total_days').html(tourInfo.totalDay + 'DAY')
        $('.startDay').val(tourInfo.days['start'])
        $('.endDay').val(tourInfo.days['end'])
        for (let num = 1; num <= tourInfo.totalDay; num++) {
            var newDiv1 = '<div><div class="day_info_box">' + num + '일차' + '</div>' +
                '<ul class="day_info_list" id="' + num + 'day_list"></ul><div>'
            $('#select_place_list').append(newDiv1)
        }
        resetList()
    })

    $('.user_info').off().on('click',()=>{
        <c:if test="${memId==null}">
        login = window.open("/ToGo/login/loginMain","login",'width=600,height=600','resizable=no')
        </c:if>
    })
    function set_child(){
    }
    function resetList() {
        try {
            $('.cityListDiv').empty()
            $.ajax({
                data: {area: tourInfo.area, str: 1, end: 20},
                type: "POST",
                url: '/ToGo/map/place_list',
                success: function (data) {
                    console.log(data)
                    attrList = data;
                    for (let num in data) {
                        let div =
                            "<div class='recommendPlaceDiv' id='placeDiv_" + num + '_' + data[num].name + "'>\n" +
                            " <div class='item' title='img_area'><img \n" +
                            "src=\"${pageContext.request.contextPath}/resources/static/img/attr.png\"></div>\n" +
                            "<div class=\"item recommendPlace_name\">\n" +
                            "<div class=\"name_area\">\n" +
                            "<span class=\"place_name\" title=\"" + data[num].name + "\"><h7>" + data[num].name + "</h7></span> </div>\n" +
                            "<div class=\"address_area\">\n" +
                            "<span class=\"place_name\" title=\"" + data[num].adress + "\"><h7>" + data[num].adress + "</h7></span>\n" +
                            "</div> </div> <div> <input type=\"radio\" value=\"" + data[num].name + "\" class=\"city_add_button\">\n </div> </div>"
                        $('#cityList').append(div)
                    }
                }, error: function () {
                    alert('에러')
                }
            })
        } catch (e) {
            console.log(e)
        }
    }
    //////////////////////일정 저장하기/////////////////
    $('#schedule_save').off('click').on('click',()=>{
        <c:if test="${memId!=null}">
        let title = prompt("여행의 제목을 입력해주세요")
        let form = {user_schedule:user_schedule,area:tourInfo.area,title:title,id:'${memId}',day:tourInfo.totalDay}
        console.log(JSON.stringify(form))
        $.ajax({
            type:"POST",
            url:"/ToGo/map/test2",
            data:JSON.stringify(form),
            contentType:'application/json',
            success:function (){
                let result = confirm("저장이 완료되었습니다 메인화면으로 이동하시겠습니까?")
                if(result){
                    location.href='/ToGo/trip/main'
                }
            },
            error:function (){
                alert('실패!')
            }
        })
        </c:if>
        <c:if test="${memId==null}">
        alert('로그인을 해주세요!')
        </c:if>
    })
    ///////////////날짜바꾸기//////////////////////////////////날짜바꾸기//////////////////////////////////날짜바꾸기///////////////////
    $('input[type=date]').change(() => {
        let date = $(event.target).val().split('-')
        let year = date[0]
        let month = date[1]
        let day = date[2]
        let str = new Date(tourInfo.days['start'])
        let end = new Date(tourInfo.days['end'])
        let endDiv = $('.endDay')
        let strDiv = $('.startDay')
        if ($(event.target).attr('name').includes('start')) {
            str = new Date($(event.target).val())
            console.log('start')
            strDiv.html(null)
            $('.total_days').html(null)
            if (str < end) {
                tourInfo.days['start'] = $(event.target).val()
                strDiv.html(year + '.' + month + '.' + day).val(year + '.' + month + '.' + day)
            } else if (str > end) {
                tourInfo.days['start'] = tourInfo.days['end']
                tourInfo.days['end'] = $(event.target).val()
                strDiv.html(endDiv.html()).val(endDiv.html())
                endDiv.html(null)
                endDiv.html(year + '.' + month + '.' + day).val(year + '.' + month + '.' + day)
                str = new Date(tourInfo.days['start'])
                end = new Date(tourInfo.days['end'])
            }
            $('.total_days').html(((end - str) / 86400000 + 1) + 'DAY')
            tourInfo.totalDay = ((end - str) / 86400000 + 1)
        } else {
            end = new Date($(event.target).val())
            endDiv.html(null)
            $('.total_days').html(null)
            if (end < str) {
                endDiv.html(strDiv.html()).val(strDiv.html())
                tourInfo.days['end'] = tourInfo.days['start']
                tourInfo.days['start'] = $(event.target).val()
                console.log('endDiv.html=' + endDiv.html())
                strDiv.html(year + '.' + month + '.' + day).val(year + '.' + month + '.' + day)
                str = new Date(tourInfo.days['start'])
                end = new Date(tourInfo.days['end'])
            } else if (end > str) {
                tourInfo.days['end'] = $(event.target).val()
                endDiv.html(year + '.' + month + '.' + day).val(year + '.' + month + '.' + day)
            }
            $('.total_days').html(((end - str) / 86400000 + 1) + 'DAY')
            tourInfo.totalDay = ((end - str) / 86400000 + 1)
        }
        initMap()
    })
    //////////////////////장바구니 삭제/////////////////////////
    $('#place_save').off().on('click', () => {
        var place_bag = $('.place_bag')
        if(place_bag.css('display')==='none') {
            place_bag.show('fast')
            place_bag.css('background-color','white')
        }else{
            console.log('hide')
            place_bag.hide('normal')
        }
        event.stopPropagation()
    })
    $('.place_bag').find('input[type=button]','a').click(() => {
        if ($(event.target).val() === '선택삭제') {
            for (var num = 0; num < $('.place_bag').find('li').length; num++) {
                let target = $('.place_bag').find('ul').children(":eq(" + num + ")")
                if (target.children('input[type=radio]').is(':checked')) {
                    $('div[id*="' + target.text() + '"]').show()
                    $('div[id*="' + target.text() + '"]').find('.city_add_button').prop('checked', false)
                    target.remove()
                    tourInfo.select_place.splice(tourInfo.select_place.indexOf(target.text()),1)
                    num--
                }
            }
        }else{
            for (var num = 0; num < $('.place_bag').find('li').length; num++) {
                let target = $('.place_bag').find('ul').children(":eq(" + num + ")")
                $('div[id*="' + target.text() + '"]').show()
                $('div[id*="' + target.text() + '"]').find('.city_add_button').prop('checked', false)
                tourInfo.select_place.length=0;
            }
            $('.place_bag').find('ul').empty()
        }
    })
    function initMap() {
        let opacity
        let result = true
        let listName = 'place'
        $('.area_name>a').off().on('click', select_open)
        $(document).off().on('click', '.close', select_close)
        $(document).off().on('click', '.cityName', () => {
            if (result) {
                $('.area_name>a>span').text(null)
                $('.area_name>span').text(null)
                $('.area_name>a>span').text($(event.target).text())
                let name = $(event.target).text()
                let form = {name: $(event.target).text()}
                $.ajax({
                    type: "POST",
                    url: "/ToGo/map/mapInfo",
                    data: form,
                    success: function (data) {
                        resetMap(data)
                        resetList()
                    },
                    error: function () {
                        alert('에러')
                    }
                })

                function resetMap(data) {
                    $('.area_name>span').text(data.name)
                    tourInfo.name=data.name;
                    tourInfo.area = data.city
                    tourInfo['center'] = {lat: data.lat, lng: data.lon}
                    console.log(tourInfo)
                    initMap()
                    result = false;
                }

                select_close()
            }
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
        var lat = tourInfo["center"].lat
        var lng = tourInfo["center"].lng
        ${latlon.lon}
        </c:if>
        <c:if test="${tourInfo==null}">
        var lat = 37.5512
        var lng = 126.9933
        </c:if>
        map = new google.maps.Map(document.getElementById("map"), {
            center: {lat, lng},
            zoom: 12,
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
        attrList = {}
        var drawingManager = new google.maps.drawing.DrawingManager();
        drawingManager.setMap(map);


        const infowindow = new google.maps.InfoWindow();


        //////////////////////아이콘 부분////////////////////////////
        const numList = ["one", "two", "three", "four", "five", 'six', 'seven', 'eight', 'nine', 'ten']
        myIcons = []
        for (let num in numList) {
            myIcons.push(new google.maps.MarkerImage("${pageContext.request.contextPath}/resources/static/img/" + numList[num] + ".png", null, null, null, new google.maps.Size(20, 20)))
        }
        //////////////추천일정만들기//////////////////////////////추천일정만들기//////////////////////////////추천일정만들기////////////////
        $('#recommend_list_add').off('click').on('click', function (e) {
            event.preventDefault()
            console.log("추천일정시작")
            openLoading()
            let form = {
                area: tourInfo.area,
                startDay: tourInfo.days['start'],
                endDay: tourInfo.days['end'],
                totalDay: tourInfo.totalDay,
                mainList: [tourInfo.select_place],
                name : tourInfo.name,
            }
            console.log(form)
            $.ajax({
                type: "POST",
                url: "/ToGo/trip/place",
                data: form,
                traditional : true,
                success: function (data) {
                    try {
                        user_schedule = data
                        let count = 0;
                        if (re_mks.length !== 0) {
                            for (let num in re_mks) {
                                re_mks[num].setMap(null)
                            }
                            re_mks.length = 0
                        }
                        if (re_polys.length !== 0) {
                            for (let num in re_polys) {
                                re_polys[num].setMap(null)
                            }
                            re_polys.length = 0
                        }
                        $('#select_place_list').children().empty()
                        infoWindow = new google.maps.InfoWindow();
                        let num3 = 0;
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
                                re_marker.addListener("click", () => {
                                    map.setCenter(re_marker.getPosition())
                                });
                                var newDiv = '<li>\n<div class="placeDiv">\n<div>\n<img src="${pageContext.request.contextPath}/resources/static/img2/20201230173806551_JRT8E1VC.png">\n' +
                                    '</div>\n<div style="display: grid;grid-template-rows: 2fr 3fr">\n' +
                                    '<div>\n<span>' + result[num2].name + '</span>\n</div>\n<div></div>\n</div>\n</div>\n</li>'
                                let id = num + 'day_list'
                                console.log(id)
                                $('ul[id=' + id + ']').append(newDiv)
                                num3++;
                            }
                            let re_poly = new google.maps.Polyline({
                                path: day,
                                strokeColor: colorCode(),
                                strokeOpacity: 1.0,
                                strokeWeight: 3,
                            })
                            re_poly.setMap(map)
                            re_polys[num] = re_poly
                            num3++;
                        }
                        closeLoading()
                    } catch (e) {
                        alert(e)
                        closeLoading()
                    }
                },
                error: function (e) {
                    alert(e);
                    closeLoading()
                }
            })
        })

        ///////////////////////////////hover이벤트 부분///////////////////////////////////
        $(document).on('mouseenter', 'div[class=recommend_area]>div>div[class*=recommend]', function (e) {
            if (listName === 'place') {
                let name = $(event.target).find('.place_name', 'span').attr('title')
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


        ////////////////////일차별 접고 펴기///////////////////
        $(document).on("mouseenter", ".day_info_box", function () {
            $(this).css('background-color', '#fbd9dc')
            $(this).mouseleave(() => {
                $(this).css('background-color', '#FBEAEB')
            })
        })
        $(document).on("click", ".day_info_box", function () {
            try {
                let name = $(event.target).parent().find('ul').attr('id').slice(0, 1)
                let rannum = Math.round(Math.random() * 1 * 6 + ((parseInt(name) - 1)) * 6)
                let target = $(event.target)
                if ($(event.target).css('opacity') !== '1') {
                    $('.active').attr('class', 'day_info_box')
                    target.attr("class", target.attr('class') + ' active')
                    $('.day_info_box').css('opacity', '0.2').css('box-shadow', '')
                    target.css('opacity', '1.0').css('box-shadow', '5px 3px 3px gray')
                    if ($('.day_info_list').children().length !== 0) {
                        map['zoom']=13;
                        target.parent().parent().find('ul').hide(100)
                        console.log(target.parent().find('ul').css('display'))
                        target.parent().find('ul').show(100)
                        for (let num in re_polys) {
                            re_polys[num].setMap(null)
                        }
                        re_polys[parseInt(name)].setMap(map)
                        map.panTo(re_mks[rannum].position)
                    }
                } else {
                    map['zoom']=12;
                    map.panTo(tourInfo.center)
                    $('.active').attr('class', 'day_info_box')
                    target.parent().parent().find('ul').show()
                    for (let num in re_polys) {
                        re_polys[num].setMap(map)
                    }
                    $('.day_info_box').css('opacity', '0.9')
                    target.css('box-shadow', '')
                }

            } catch (e) {
                console.log(e)
            }
        })
        ////////////////////////장바구니 추가 //////////////////////////////////
        $(document).off('change').on('change', '.city_add_button', function () {
            let target = $(event.target)
            target.parent().parent().hide(100)
            let li = '<li><input type="radio"/>' + target.val() + '<input type="hidden" class="div_num" value="' + target.parent().parent().attr('id').split('_')[1] + '"><a href="#"></a></li>'
            $('.place_bag>ul').append(li)
            tourInfo.select_place.push(target.val())
        })
        $(document).on('change', '.lodging_add_button', function () {
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
        $('#select_hotel_button').off('click').on('click', function () {
            $(event.target).css('border-bottom', '2px solid orangered')
            $('#select_place_button').css('border-bottom', 'none')
            $('#select_place_list').hide()
            $('#select_hotel_list').show()
        })
        $('#select_place_button').off('click').on('click', function () {
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
    }

    window.initMap = initMap;
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

    <%--$('#add').click(recommend_course)--%>

    <%--function recommend_course() {--%>
    <%--    for (let num in malls) {--%>
    <%--        // Add the circle for this city to the map.--%>
    <%--        let numString = numList[malls[num].label - 1]--%>
    <%--        var myIcon = new google.maps.MarkerImage("${pageContext.request.contextPath}/resources/static/img/" + numString + ".png", null, null, null, new google.maps.Size(20, 20));--%>
    <%--        const marker = new google.maps.Marker({--%>
    <%--            position: malls[num].center,--%>
    <%--            map,--%>
    <%--            icon: myIcon,--%>
    <%--        });--%>
    <%--        $('#remove').click(function () {--%>
    <%--            marker.setMap(null)--%>
    <%--        })--%>
    <%--        marker.addListener("click", () => {--%>
    <%--            map.panTo(marker.position);--%>
    <%--            const contentString =--%>
    <%--                '<div id="content">' +--%>
    <%--                '<p><h2>' + malls[num].name + '</h2></p>' +--%>
    <%--                '<p>주소:' + malls[num].address + '</p>' +--%>
    <%--                '</div>'--%>
    <%--            let infowindow = new google.maps.InfoWindow({--%>
    <%--                content: contentString,--%>
    <%--                ariaLabel: "Uluru",--%>
    <%--            });--%>
    <%--            infowindow.open({--%>
    <%--                anchor: marker,--%>
    <%--                map,--%>
    <%--            });--%>
    <%--        });--%>
    <%--    }--%>
    <%--}--%>

</script>
</body>