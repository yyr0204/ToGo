<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>문의글 수정</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Bootstrap icons-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="${pageContext.request.contextPath}/resources/static/song/css/styles.css" rel="stylesheet" />
<style>
  body {
    font-family: Arial, sans-serif;
  }
.container {
	max-width: 600px;
	margin: 0 auto;
}
  h3 {
    margin-top: 30px;
  }

  table {
    width: 100%;
    margin-bottom: 30px;
    border-collapse: collapse;
  }

  th, td {
    border: 1px solid #ccc;
    padding: 8px;
    text-align: left;
  }

  th {
    background-color: #f2f2f2;
  }

  input[type="text"],
  textarea {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
  }

  .btnSet {
    display: flex;
    justify-content: center;
    margin-top: 20px;
  }

  .btn-fill, .btn-empty {
    display: inline-block;
    padding: 10px 20px;
    color: #fff;
    border: none;
    border-radius: 5px;
    text-decoration: none;
    cursor: pointer;
    margin-right: 10px;
  }

  .btn-fill {
    background-color: #007bff;
  }

  .btn-empty {
    background-color: #fff;
    border: 1px solid #007bff;
    color: #007bff;
  }

  .btn-fill:hover, .btn-empty:hover {
    background-color: #0056b3;
  }

  /* Center the form */
  form {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<div class="container">
<h3>QNA 수정</h3>
<form id="form" action="qnaModifyPro" method="post">
  <input type="hidden" name="num" value="${dto.num }" />
  <table>
    <tr>
      <th class="w-px160">제목</th>
      <td><input type="text" name="title" value="${dto.title }" /></td>
    </tr>
    <tr>
      <th>내용</th>
      <td><textarea name="content">${dto.content }</textarea></td>
    </tr>
  </table>
</form>
<div class="btnSet">
  <a class="btn-fill" href="#" onclick="submitForm()">저장</a>
  <a class="btn-empty" href="qnaDetail?num=${dto.num}">취소</a>
</div>
</div>
<script>
    function submitForm() {
        document.getElementById('form').submit();
    }
</script>
</body>
</html>
