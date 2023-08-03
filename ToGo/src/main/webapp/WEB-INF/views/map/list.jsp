<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<head>
    <link href="${pageContext.request.contextPath}/resources/static/css/map_css.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/static/css/plan_css.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/static/css/jquery-ui.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery-ui.js"></script>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <script defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC63MWSfMneMDT-oW0JIm_cZkKB1p9nmtI&libraries=drawing,geometry,maps,places&v=beta&callback=initMap"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
</head>
<style>
    html, body {
        margin: 0px;
        padding: 0px;
        font-family: 'Nanum Gothic', sans-serif;
        font-size: 15px;
    }

    .side {
        position: absolute;
        left: 0;
        background-color: #FFFFFF;
        display: grid;
        grid-template-rows: repeat(auto-fill, minmax(10%, auto));
        width: 10vh;
        height: 100%;
        justify-items: center;
        align-items: center;
        z-index: 1;
    }

    #map {
        width: 70vw !important;
        height: 100vh !important;
        margin-left: -100px;
        position: absolute;
        right: 0;
    }

    img {
        width: 100%;
        height: 100% !important;
    }

    a{
        text-align: center;
        opacity: 0.4;
        transition: 0.2s;
    }
    .side_bt {
        font-size: 0.8em;
        z-index: 2;
    }
</style>
<body>
<div class="side">
    <div>
        <img src="${pageContext.request.contextPath}/resources/static/img/ToGo_logo-remove.png">
    </div>
    <div class="side_bt">
        <a href="#">
            STEP 1
            <br>
            날짜 확인
        </a>
    </div>
    <div class="side_bt">
        <a href="#">
            STEP 2
            <br>
            장소 선택
        </a>
    </div>
    <div class="side_bt">
        <a href="#">
            STEP 3
            <br>
            최종 확인
        </a>
    </div>
</div>
<div id="map"></div>
<script>
    /////////////////맵파트////////////////////////
    let map
    /////////////////제이쿼리 이벤트 부분///////////////////
    $('.side_bt>a').on('click', () => {
        $('a').css('opacity','').css('font-size','').css('color','').css('font-weight','')
        $(event.target).css('opacity','1.0').css('font-size','1.2em').css('color','rgb(81, 212, 229)').css('font-weight','600')
    })
    function initMap() {
        var lat = 37.5512
        var lng = 126.9933
        map = new google.maps.Map(document.getElementById("map"), {
            center: {lat, lng},
            zoom: 12,
        });
    }
    window.initMap = initMap
</script>
</body>