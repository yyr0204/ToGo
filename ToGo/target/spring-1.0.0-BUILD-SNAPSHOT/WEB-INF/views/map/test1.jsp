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
    background: gray;
  }
  .mainContainer{
    width: 60vw;
    height: 60vh;
  }
  .main{
    background-color: white;
    display: grid;
    width: calc(10vw*9/2);
    height: calc(10vh*16/2);;
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
  }

  .weekDay_div {
    width: 300px;
    display: grid;
    grid-template-columns: repeat(7, 1fr);
  }

  .days_div {
    display: grid;
    grid-template-rows: repeat(5, 30px);
    width: 300px;
    height: 100%;
    align-items: center;
  }

  .week_div {
    display: grid;
    width: 100%;
    align-items: center;
    grid-template-columns: repeat(7, 1fr);
  }

  .day_bt {
    cursor: pointer;
  }
  .top span{
    margin-top: 20px;
    font-size: 30px;
    font-weight: 500;
  }
</style>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<input type="button" id="bt" value="달력">
<img src="https://cdn.pixabay.com/animation/2022/12/26/19/49/19-49-19-662_512.gif" style='position: relative; display: block; margin: 0px auto;width: 50px;height: 50px'/>";
<div class="mainContainer">
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
      let div = "<div class='day_bt' value=" + date.getDate() + ">" + date.getDate() + "</div>"
      if (date.getDate() !== num) {
        continue
      }
      target.find('.' + week + 'week').append(div)
    }
    $(document).off('click').on('onclick', '.day_bt', () => {
      alert($(this).val())
      $(this).css('backgroun-color', 'pink')
    })
  }
</script>
</body>