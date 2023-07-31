<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/resources/static/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/city_select.js"></script>
<link href="${pageContext.request.contextPath}/resources/static/css/plan_css.css" type="text/css" rel="stylesheet">
<style>
    body {
        background-color: ivory;
    }

    .main {
        width: 100%;
        height: 100%;

    }

    .select_bar {
        display: none;
    }

    .select_cityList_div {
        width: 400px;
        height: 600px;
        position: absolute;
        top: 50%;
        left: 50%;
        background-color: #FFFFFF;
        border-radius: 15px;
        display: grid;
        grid-template-rows: 1fr 8fr;
        margin: -400px 0 0 -200px;
        justify-items: center;
    }

    .item {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .city_list {
        display: grid;
        grid-template-columns: 150px 150px;
        grid-auto-rows: 1fr;
    }

    .city_list > div {
        height: 80%;
        margin: 10px 10px 10px 10px;
        cursor: pointer;
        text-align: center;
        background-color: ghostwhite;
    }

    .close:after {
        display: inline-block;
        content: "\00d7";
        font-size: 20pt;
        position: absolute;
        right: 10px;
        top: 0;
        cursor: pointer;
    }
</style>
<body>
<img src='${pageContext.request.contextPath}/resources/static/img/Spinner-1s-200px.gif'
     style='position: relative; display: block; margin: 0px auto;width: 50px;height: 50px'/>
<img src='${pageContext.request.contextPath}/resources/static/img/Spinner-1s-200px.gif'
     style='position: relative; display: block; margin: 0px auto;width: 50px;height: 50px'/>
<div>
    <a class="test_a" href="#">test</a>
</div>
<div class="main">
    <input type="button" value="지역선택">
</div>
<div class="select_bar">
    <div class="select_cityList_div">

        <div class="item info_area"><span>지역 선택</span><a href="#" class="close"></a></div>
        <div class="item city_list">
            <div class="item">서울</div>
            <div class="item">부산</div>
            <div class="item">광주</div>
            <div class="item">경기도</div>
            <div class="item">충청도</div>
            <div class="item">대구</div>
            <div class="item">강원도</div>
            <div class="item">경상도</div>
            <div class="item">인천</div>
            <div class="item">전라도</div>
            <div class="item">세종</div>
            <div class="item">울산</div>
        </div>
    </div>
</div>
<script>
    $('input[type=button]').click(select_open)
    $(document).on('click', '.close', select_close)
    // ()=>{
    //     $('.select_bar').show()
    //     $('.main').css('opacity','0.3')
    // }
    $(document).ready(function () {
        $(document).on({
            mouseenter: function () {
                console.log('fds')
                $(event.target).css('opacity', '0.5')
            },
            mouseleave: function () {
                $(event.target).css('opacity', '1.0')
            }
        }, '.teat_a');
    });
</script>
</body>