<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 답글</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Bootstrap icons-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="${pageContext.request.contextPath}/resources/static/song/css/styles.css" rel="stylesheet" />	
    <style>
        body {
            padding: 20px;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        .left {
            text-align: left;
        }

        .btnSet {
            margin-top: 20px;
            text-align: center;
        }

        .btn-fill {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            margin-right: 10px;
        }

        .btn-fill:hover {
            background-color: #0056b3;
        }

        .btn-fill:last-child {
            margin-right: 0;
        }

        .btn-empty {
            padding: 10px 20px;
            background-color: #fff;
            color: #007bff;
            border: 1px solid #007bff;
            border-radius: 5px;
            text-decoration: none;
        }

        .btn-empty:hover {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<h3>답글 쓰기</h3>
<div class="container">
        <h3>답글 작성</h3>
        <form id="form" action="qnaReplyPro" method="post">
            <input type="hidden" name="root" value="${dto.root}" />
            <input type="hidden" name="step" value="${dto.step}" />
            <input type="hidden" name="indent" value="${dto.indent}" />
            <table>
                <tr>
                    <th class="w-px160">제목</th>
                    <td><input type="text" name="title" class="need form-control" /></td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${adminId}</td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea name="content" class="need form-control"></textarea></td>
                </tr>
            </table>
        </form>
        <div class="btnSet">
            <a class="btn-fill" href="#" onclick="submitForm()">저장</a>
            <a class="btn-empty" href="qnaList">취소</a>
        </div>
    </div>

<!-- 실시간 갱신을 위해 getTime을 붙여준다 -->
<script type="text/javascript" src="/ToGo/resources/js/need_check.js?v=<%=new java.util.Date().getTime() %>"></script>
<script>
    function submitForm() {
        document.getElementById('form').submit();
    }
</script>
</body>
</html>