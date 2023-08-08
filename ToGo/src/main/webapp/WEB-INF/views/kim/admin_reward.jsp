<%@ page language="java" contentType="text/html; charset=UTF-8"
<<<<<<< HEAD
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
=======
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
>>>>>>> develop

<!DOCTYPE html>
<html>
<head>
<<<<<<< HEAD
<script
	src="${pageContext.request.contextPath}/resources/static/js/jquery.js"></script>
<meta charset="UTF-8">
<title>Admin Reward</title>
</head>
<body>

		<table class="board-table">
			<tr>
				<th class="w-px60">신청자 이메일</th>
				<th class="w-px100">주 소</th>
				<th class="w-px120">상품명</th>
				<th class="w-px60">배송 상태</th>
			</tr>
			<c:forEach items="${list}" var="list" varStatus="status">
				<tr>
					<td style="display:none;"><input type="hidden" id="id" name="id" value="${list.id}" /></td>
					<td>${list.k_email}</td>
					<td>${list.address}</td>
					<td>${list.goods}</td>
					<td>${list.status}
						<select name="status" class="status">
								<option value="미배송">미배송</option>
								<option value="배송">배송</option>
								<option value="취소">취소</option>
						</select>
						<button>
							<i>${list.id}</i>
						</button>
					</td>
				</tr>
			</c:forEach>
		</table>
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
=======
    <script
            src="${pageContext.request.contextPath}/resources/static/js/jquery.js"></script>
    <meta charset="UTF-8">
    <title>Admin Reward</title>
</head>
<body>

<table class="board-table">
    <tr>
        <th class="w-px60">신청자 이메일</th>
        <th class="w-px100">주 소</th>
        <th class="w-px120">상품명</th>
        <th class="w-px60">배송 상태</th>
    </tr>
    <c:forEach items="${list}" var="list" varStatus="status">
        <input type="hidden" name="id" value="${list.id }"/>
        <tr>
            <td>${list.k_email}</td>
            <td>${list.address}</td>
            <td>${list.goods}</td>
            <td>${list.status}
                <select name="status" class="status">
                    <option value="미배송">미배송</option>
                    <option value="배송">배송</option>
                    <option value="취소">취소</option>
                </select>
                <button>
                    <i>변경</i>
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
<script>
    $('select').change(() => {
        $(event.target).parent().html($(event.target).val())
    })
    $('button').click(() => {
        console.log($(event.target).parant().parant())
        let form = {status: $('.status').val()}
        $.ajax({
            data: form,
            url: "/ToGo/user/test",
            type: "POST",
            succese: function{
                alert('..')
            }
        })
    })
</script>
>>>>>>> develop
</body>
</html>