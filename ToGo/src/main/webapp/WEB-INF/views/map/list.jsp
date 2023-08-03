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
<body>
<input type="button" id="bt" value="버튼">
<input type="button" id="bt2" value="버튼">
<script>
    $(main)
    function main(data){

        console.log(data)
        $('#bt').off().click(function (){
                main('1234')
            $('#bt2').off('click')
        })
        $(document).off().on('click','#bt2',function (){
            alert('ddd')
        })
    }

</script>
</body>