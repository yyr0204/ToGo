<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
<script type="text/javascript">
	
		$.ajax({
			type:"POST"
			url:"spring/mypageMain",
			data: {
				id:id,
				
			}
			cache: false,
			success: function(){
				
			}
		})
</script>

	<div>
		<h1>예약내역</h1>
	</div>
	

</body>
</html>