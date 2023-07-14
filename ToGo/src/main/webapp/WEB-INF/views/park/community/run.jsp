<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <!-- HEAD CONTENT -->
</head>
<body>
    <c:if test="${login == 0}">
        <script type="text/javascript">
            alert("로그인 성공!");
            location.href = "home";
        </script>
    </c:if>
    <c:if test="${logout == 0}">
        <script type="text/javascript">
            alert("안녕히 가세요!");
            location.href = "home";
        </script>
    </c:if>
    <c:if test="${delete == 1}">
        <script type="text/javascript">
            alert("삭제되었습니다!");
            location.href = "home";
        </script>
    </c:if>
    <c:if test="${deleteC == 1}">
        <script type="text/javascript">
            var referrer = document.referrer;
            location.href = referrer;
        </script>
    </c:if>
    <c:if test="${modify == 1}">
        <script type="text/javascript">
            alert("수정되었습니다!");
            var referrer = document.referrer;
            if (referrer === "mypost") {
                location.href = "mypost";
            } else {
                location.href = "home";
            }
        </script>
    </c:if>
    <c:if test="${modifyC == 1}">
        <script type="text/javascript">
            var referrer = document.referrer;
            location.href = referrer;
        </script>
    </c:if>
</body>
</html>