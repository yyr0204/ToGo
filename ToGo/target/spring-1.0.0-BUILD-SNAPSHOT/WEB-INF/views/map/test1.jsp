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
</head>
<style>
    body {

    }

    .mainContainer {
        width: 60vw;
        height: 60vh;
        border: 1px solid;
    }

    .main {
        display: grid;
        width: 50vw;
        height: 40vh;
        justify-items: center;
        grid-template-rows:1fr 9fr;
    }

    .calendarContainer {
        display: grid;
        grid-template-columns: 1fr 1fr;
        width: 60vw;
        height: 50vh;
    }

    .calendar_box {
        display: grid;
        grid-template-rows: 1fr 1fr 1fr 5fr;
        justify-items: center;
        align-items: center;
        text-align: center;
        font-size: 1.3em;
    }

    .weekDay_div {
        width: 300px;
        display: grid;
        grid-template-columns: repeat(7, 1fr);
    }

    .days_div {
        display: grid;
        grid-template-rows: repeat(5, 50px);
        width: 300px;
        height: 100%;
        align-items: center;
    }

    .week_div {
        height: 100%;
        display: grid;
        width: 100%;
        align-items: center;
        grid-template-columns: repeat(7, 1fr);
    }

    .day_bt {
        padding-top: 13%;
        padding-bottom: 13%;
    }

    .day_bt > a:visited {
        color: black;
    }

    .day_bt > a:link {
        color: black;
        text-decoration: none;
    }

    .top span {
        margin-top: 20px;
        font-size: 30px;
        font-weight: 500;
    }
    a {
        background-image: url("https://cdn.icon-icons.com/icons2/2505/PNG/512/sunny_weather_icon_150663.png");
        width: 25px;
        height: 25px;
        background-size: contain;
    }
</style>
<body style="background-color: rgba(0,0,0,0.4);">
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<input type="button" id="bt" value="달력">
<img src="https://cdn.pixabay.com/animation/2022/12/26/19/49/19-49-19-662_512.gif"
     style='position: relative; display: block; margin: 0px auto;width: 50px;height: 50px'/>";
<div class="mainContainer" style="background-color:ghostwhite;">
    <div class="main">
        <div style="height: 50px" class="top"><span>여행을 언제 떠나시나요?</span></div>
        <div class="calendarContainer">
            <div class="calendar_box before">
                <div class="title_name item">
                </div>
                <div class="item"></div>
                <div class="calendar_div item">
                    <div class="weekDay_div item">
                        <div><span>일</span></div>
                        <div><span>월</span></div>
                        <div><span>화</span></div>
                        <div><span>수</span></div>
                        <div><span>목</span></div>
                        <div><span>금</span></div>
                        <div><span>토</span></div>
                    </div>
                </div>
                <div class="days_div item">
                    <div class="1week week_div"></div>
                    <div class="2week week_div"></div>
                    <div class="3week week_div"></div>
                    <div class="4week week_div"></div>
                    <div class="5week week_div"></div>
                </div>
            </div>
            <div class="calendar_box after">
                <div class="title_name item">
                    ???월
                </div>
                <div class="item"></div>
                <div class="calendar_div item">
                    <div class="weekDay_div item">
                        <div><span>일</span></div>
                        <div><span>월</span></div>
                        <div><span>화</span></div>
                        <div><span>수</span></div>
                        <div><span>목</span></div>
                        <div><span>금</span></div>
                        <div><span>토</span></div>
                    </div>
                </div>
                <div class="days_div item">
                    <div class="1week week_div"></div>
                    <div class="2week week_div"></div>
                    <div class="3week week_div"></div>
                    <div class="4week week_div"></div>
                    <div class="5week week_div"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    let this_month = new Date().getMonth() + 1
    let this_year = new Date().getFullYear()
    $('#bt').off().on('click', () => {
        this_month = prompt()
        $('.week_div').empty()
        calendar(this_year, this_month, 'before')
        calendar(this_year, parseInt(this_month) + 1, 'after')
    })
    $(() => calendar(this_year, this_month, 'before'))
    $(() => calendar(this_year, this_month + 1, 'after'))

    function calendar(year, month, type) {
        let temp = 0;
        let week = 1;
        let date = new Date(year + '/' + month)
        let today = new Date()
        let target = $('.' + type)
        target.find('.title_name').html(month + '월')
        date.setDate(1)
        if (date.getDay() !== 0) {
            for (let num = 0; num < date.getDay(); num++) {
                target.find('.1week').append('<div></div>')
            }
        }
        for (let num = 1; num < 32; num++) {
            date.setDate(num)
            if (temp > date.getDay()) {
                week++
            }
            temp = date.getDay()
            let div = "<div class='day_bt' title=" + date.getDate() + "><a href='#'>" + date.getDate() + "</a></div>"
            if (date.getDate() < today.getDate()&&date.getMonth()===today.getMonth()) {
                div = "<div class='day_bt' title=" + date.getDate() + "><span style='color:rgba(0,0,0,0.2)'>" + date.getDate() + "</span></div>"
            }
            if (date.getDate() !== num) {
                continue
            }
            target.find('.' + week + 'week').append(div)
        }
        $(document).off().on('click', '.day_bt>a', () => {
            var target = $(event.target)
            target.parent().css('background-color', '#CCFFCC')
        })

        let result = true;
        let day;
        $(document).off().on('mouseenter', '.day_bt>a', () => {
            let target = $(event.target)
            if (result) {
                $(event.target).parent().css('background-color', 'rgba(0,0,0,0.1)')
            } else {
                var day2 = target.html()
                for (var num = 0; num < day2 - day; num++) {
                    target.parent().css('background-color', 'rgba(205, 255, 204,0.8)')
                    if (target.parent().prev().length !== 0) {
                        target = target.parent().prev().children('a')
                    } else {
                        target = target.parent().parent().prev().children(0)
                    }
                }
            }
            $(event.target).off().click(function () {
                if (result) {
                    $(this).parent().css('background-color', 'rgba(205, 255, 204,0.8)')
                    day = target.html()
                    result = false
                } else {
                    $(this).parent().css('background-color', '')
                    result = true
                }
                day = $(event.target).html()
            })
            $(event.target).mouseleave(function () {
                if (result) {
                    $(event.target).parent().css('background-color', '')
                } else {
                    for (var num = 0; num < day2 - day; num++) {
                        target = target.parent().next().children('a')
                        target.parent().css('background-color', '')
                    }
                }
            })
        })
    }
</script>
</body>