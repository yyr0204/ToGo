<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="now" value="<%=new java.util.Date()%>" />
<fmt:formatDate value="${now}" type="date" dateStyle="full" var="nowDate"/>
<script src="${pageContext.request.contextPath}/resources/static/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/city_select.js"></script>
<link href="${pageContext.request.contextPath}/resources/static/css/plan_css.css" type="text/css" rel="stylesheet">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Coming Soon - Start Bootstrap Theme</title>
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link rel="preconnect" href="https://fonts.gstatic.com"/>
    <link href="https://fonts.googleapis.com/css2?family=Tinos:ital,wght@0,400;0,700;1,400;1,700&amp;display=swap"
          rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,wght@0,400;0,500;0,700;1,400;1,500;1,700&amp;display=swap"
          rel="stylesheet"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="${pageContext.request.contextPath}/resources/static/song/css/styles3.css?ver=2" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/static/css/park_css.css?ver=2" rel="stylesheet"/>
</head>
<body>
<!-- Background Video-->
<video class="bg-video" playsinline="playsinline" autoplay="autoplay" muted="muted" loop="loop">
    <source src="${pageContext.request.contextPath}/resources/static/song/assets/mp4/beach.mp4" type="video/mp4"/>
</video>
<!-- Masthead-->
<div class="masthead">

    <nav class="navbar navbar-expand-lg navbar-dark" style="position:absolute;top:25px;left:25px">
        <div class="container px-5">
            <a class="navbar-brand" href="/ToGo/trip/main"><h1>ToGo</h1></a>
        </div>
    </nav>

    <div class="masthead-content text-white">
        <div class="container-fluid px-4 px-lg-0">
            <h1 class="fst-italic lh-1 mb-4">여행 일정를 입력해주세요</h1>
            <p class="mb-5">여행지와 날짜를 선택해주세요..</p>
            <!-- * * * * * * * * * * * * * * *-->
            <!-- * * SB Forms Contact Form * *-->
            <!-- * * * * * * * * * * * * * * *-->
            <!-- This form is pre-integrated with SB Forms.-->
            <!-- To make this form functional, sign up at-->
            <!-- https://startbootstrap.com/solution/contact-forms-->
            <!-- to get an API token!-->
            <form method="post" name="planform" action="/ToGo/map/tourMap">
                <!-- address input-->
                <div class="row input-group-newsletter">
                    <div class="col">
                        <input class="form-control" name="area" id="area" type="text" placeholder="여행지를 골라주세요"
                               readonly/>
                        <label for="area"></label>
                    </div>
                    <%--
                    <div class="col-auto"><input type="button" value="지역추가" id="area_bt"></div>
                    --%>
                </div>
                <br/>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroup-sizing-default">시작일</span>
						</div>
						<input type="date" class="form-control" aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-default"  name="startDay" value="${nowDate}">
					</div>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroup-sizing-default">종료일</span>
						</div>
						<input type="date" class="form-control" aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-default" name="endDay">
					</div>
					<%--
					<div class="row input-group-newsletter">
	                    <label for="strDay">시작일 :</label>
	                    <input id="strDay" type="date" name="startDay"/>
	                </div>
	                <div class="row input-group-newsletter">
	                    <label for="endDay">종료일 :</label>
	                    <input id="endDay" type="date" name="endDay"/>
	                </div>
                 --%>
                <button type="submit" class="btn btn-primary" id="park-submit-bt" style="background-color:#0b5ed7;">확인</button>
            </form>
        </div>
    </div>
</div>
<!-- Social Icons-->
<!-- For more icon options, visit https://fontawesome.com/icons?d=gallery&p=2&s=brands-->
<!-- <div class="social-icons"> -->
<!--     <div class="d-flex flex-row flex-lg-column justify-content-center align-items-center h-100 mt-3 mt-lg-0"> -->
<!--         <a class="btn btn-dark m-3" href="#!"><i class="fab fa-twitter"></i></a> -->
<!--         <a class="btn btn-dark m-3" href="#!"><i class="fab fa-facebook-f"></i></a> -->
<!--         <a class="btn btn-dark m-3" href="#!"><i class="fab fa-instagram"></i></a> -->
<!--     </div> -->
<!-- </div> -->
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="${pageContext.request.contextPath}/resources/static/song/js/scripts3.js"></script>
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<!-- * *                               SB Forms JS                               * *-->
<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
<script>
    $('#area_bt,#area').click(select_open)
    $(document).on('click', '.close', select_close)
    $(document).on('click', '.cityName', () => {
        $('#area').val($(event.target).text())
        $('label[for=area]').html(null);
        select_close()
    })
    $(document).submit(() => {
        let str = new Date($('#strDay').val())
        let end = new Date($('#endDay').val())
        if (str > end) {
            if(end.getMonth()+1<10) {
                end = end.getFullYear() + '-0' +parseInt(end.getMonth() + 1) + '-' + end.getDate()
            }else{
                end = end.getFullYear() + '-' + parseInt(end.getMonth() + 1) + '-' + end.getDate()
            }
            if(str.getMonth()+1<10) {
                str = str.getFullYear() + '-0' + parseInt(str.getMonth() + 1) + '-' + str.getDate()
            }else{
                str = str.getFullYear() + '-' + parseInt(str.getMonth() + 1) + '-' + str.getDate()
            }
            $('#strDay').val(end)
            $('#endDay').val(str)
            console.log($('#strDay').val())
            console.log($('#endDay').val())
        }
        if ($('[name=endDay]').val() === "" || $('[name=startDay]').val() === "") {
            $('label').html('날짜를 정해주세요').css('color', 'red',).css('font-weight', '600')
            event.preventDefault()
        }
        if ($('#area').val() === "") {
            console.log('area')
            $('label[for=area]').html('관광지를 선택해주세요').css('color', 'red',).css('font-weight', '600')
            event.preventDefault()
        }
        if(end.getDate()-str.getDate()>5||end.getDate()-str.getDate()<-5){
            $('label').html('4일 이내로 골라주세요').css('color', 'red',).css('font-weight', '600')
            event.preventDefault()
        }
    })
    $('input').change(function () {
        $('label[for=' + $(event.target).attr('id') + ']').html(null);
    })
</script>
</body>
</html>