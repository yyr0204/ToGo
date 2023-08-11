<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script
	src="${pageContext.request.contextPath}/resources/static/js/jquery.js"></script>
<meta charset="UTF-8">
<title>Admin Reward</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<script src="//code.jquery.com/jquery-3.7.0.min.js"></script>
<style>
.container {
	max-width: 80%;
	margin: 0 auto;
}
/* 테이블 스타일링 */
.board-table {
  border-collapse: collapse;
  margin-top: 20px;
}

.board-table th {
  padding: 10px;
  text-align: center;
  border: 1px solid #ddd;
}
.board-table td {
  padding: 10px;
  text-align: left;
  border: 1px solid #ddd;
}

.board-table th {
  background-color: #f2f2f2;
}

/* 버튼 스타일링 */
button {
  padding: 5px 10px;
  background-color: #3498db;
  color: white;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s;
}

button i {
  margin-right: 5px;
}

button:hover {
  background-color: #2980b9;
}

/* 선택 상태 스타일링 */
.select-status {
  font-weight: bold;
  color: #e74c3c;
}

</style>
</head>

<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<div class="container">
		<table class="board-table">
			<tr>
				<th >신청자 이메일</th>
				<th>주 소</th>
				<th >상품명</th>
				<th  >배송 상태</th>
			</tr>
			<c:forEach items="${list}" var="list" varStatus="status">
				<tr>
					<td style="display:none;"><input type="hidden" id="id" name="id" value="${list.id}" /></td>
					<td>${list.k_email}</td>
					<td>${list.address}</td>
					<td>${list.goods}</td>
					<td>
						<select name="status" class="status">
							<option value="미배송" ${list.status == '미배송' ? 'selected' : ''}>미배송</option>
						    <option value="배송" ${list.status == '배송' ? 'selected' : ''}>배송</option>
						    <option value="취소" ${list.status == '취소' ? 'selected' : ''}>취소</option>	
						</select>
						<button onclick="return confirm('상태를 변경하시겠습니까?')">
							<i class="fa-regular fa-circle-check">변경</i>
						</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script>
	
		$('button').click(()=>{

			  let form = {
			    status:$(event.target).closest('tr').find('.status').val(),
			    id: $(event.target).closest('tr').find('#id').val()
			  }

			$.ajax({
				data:form,
				url:"/ToGo/User/Admin_reward",
				type:"POST",
				success:function(){
					
					window.location.reload()
					
				}
			})
		})
		</script>
</body>
</html>