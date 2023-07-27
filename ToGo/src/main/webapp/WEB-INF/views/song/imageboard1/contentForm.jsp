<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
   integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
   crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
   crossorigin="anonymous">
</script>

<html>
<head>
<title>게시판</title>
<style>
	
  /* Custom CSS to control the size of the carousel */
  .carousel-container {
    width: 100%;
    max-width: 700px; /* Set a maximum width for the carousel */
    overflow: hidden; /* Hide any overflow to prevent scrollbars */
  }

  /* This will ensure the carousel images fit within the container */
  .carousel-inner .carousel-item img {
    width: 100%;
    height: 500px;
  }
  
</style>
<style>
	.table{
    border: 1px,solid,black !important;
    opacity:1 !important;
    width:700px;
  	}
  	.table>tr>td{
  	border: 1px,solid,black !important;
  	}
</style>
</head>

		<script language = "javascript" >
			function checkIn() {
				var content = document.chat.content.value;
				if(!content) {
					alert("댓글을 입력해주세요");
					document.chat.content.focus();
					return false;
				}
				if('${memId} == null || ${memId} == ""') {
					alert("로그인후 다시 작성하시기바랍니다.");
					return false;
				}
			}
		</script>

<center><b>글내용 보기</b>
<br>
	<table class="table table-bordered border border-dark" border="1" width = "700" cellspacing = "0" cellpadding = "0" align  ="center">
		<tr height = "30">
			<td align = "center" width = "100" >글번호</td>
			<td align = "center" width = "300" align = "center" >
				${dto.num}</td>
			<td align = "center" width = "100" >조회수</td>
			<td align = "center" width = "300" align = "center" >
				${dto.readcount}</td>
		</tr>
		<tr height = "30" >
			<td align = "center" width = "100" >작성자</td>
			<td align = "center" width = "300" align = "center" >
				${dto.writer}</td>
			<td align = "center" width = "100" >작성일</td>
			<td align = "center" width = "300" align = "center">
				${sdf.format(dto.reg_date)}</td>
		</tr>
		<tr height = "30">
			<td align = "center" width ="200" >여행일정</td>
			<td align = "center" width = "500" align = "center" colspan = "3">
				<a href="">
					${"?박?일"}
				</a>
			</td>
		</tr>
		<tr height = "30">
			<td align = "center" width ="200" >글제목</td>
			<td align = "center" width = "500" align = "center" colspan = "3">
				${dto.subject}</td>
		</tr>
		<tr>
			<td align = "center" width = "700" colspan = "4">글내용</td>
		</tr>
		
		<tr>
			<td align = "left" width = "700" colspan = "4">
				<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
					<div class="carousel-indicators">
						<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
						<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
					</div>
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="/ToGo/resources/static/song/upload/${dto.thumbnail}" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="/ToGo/resources/static/song/upload/${dto.image}" class="d-block w-100" alt="...">
						</div>
					</div>
					<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</td>
		</tr>
		
		<tr>
			<td>
				<pre>${dto.content}</pre>
			</td>
		</tr>
		<tr>
			<td>

			<c:if test="${memId != null}">
				<c:if test="${memId.equals(dto.writer)}">
					<input type = "button" value = "글수정"
					onclick = "document.location.href='updateForm?num=${dto.num}&pageNum=${pageNum}'">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type = "button" value = "글삭제"
					onclick = "document.location.href='contentDelete?num=${dto.num}&pageNum=${pageNum}'">
					&nbsp;&nbsp;&nbsp;&nbsp;
				</c:if>
				<c:if test="${memId.equals('admin')}">
					<input type = "button" value = "답글쓰기"
					onclick = "document.location.href='writeForm?num=${dto.num}&ref=${dto.ref}&re_step=${dto.re_step}&re_level=${dto.re_level}'">
					&nbsp;&nbsp;&nbsp;&nbsp;
				</c:if>
			</c:if>
			<input type = "button" value = "글목록"
			onclick="document.location.href='list?pageNum=${pageNum}'">
			</td>
		</tr>
<!-- ---------------------------------------------------------------------------------------------- -->
		
		
		<form name = "chat" action = "contentPro" method = "post" onSubmit = "return checkIn()" >
		<tr>
			<td >
				${memId}
				<input type = "hidden" name = "writer" value = "${memId}" >
				<input type = "hidden" name = "num" value = "${dto.num}" >
				<input type = "hidden" name = "pageNum" value = "${pageNum}" >
				<input type = "hidden" name = "pr_pageNum" value = "${pr_pageNum}" >
			</td>
			<td colspan = "2" align = "left" >
				<input type = "text" name = "content" >
				<input type = "submit" value = "댓글" align = "right" >
			</td>
			<td align = "center" >
				<input type = "radio" name = "pick" value = "" >
			</td>
		</tr>
		<tr>
			<td colspan = "4" align = "center" >
				댓글 목록
			</td>
		</tr>
		<c:if test="${count == 0}" >
		</c:if>
		<c:if test="${count != 0}" >
			<c:if test="${memId != null}" >
				<c:forEach var = "contentBoard" items = "${contentBoard}" >
					<c:if test="${contentBoard.ref == contentBoard.num}" >
						<tr>	
							<td>
								&nbsp;&nbsp;
								<b>${contentBoard.writer}</b>
								<font size = "1px"> ${contentBoard.reg_date} </font>
								<c:if test="${memId.equals(contentBoard.writer)}" >
									<input type = "button" value = "글삭제"
									onclick = "document.location.href='subDelete?num=${dto.num}&contentnum=${contentBoard.num}&pageNum=${pageNum}&pr_pageNum=${pr_pageNum}'">
								</c:if>
								<input type = "radio" name = "pick" value = "${contentBoard.num}" style="width:10px;height:10px;border:1px;">
								<br />
								&nbsp;&nbsp;
								${contentBoard.content}
							</td>
						</tr>
					</c:if>
					<c:if test="${contentBoard.ref != contentBoard.num}" >
						<tr>	
							<td>
								<c:forEach var="i" begin="1" end="${contentBoard.re_level}" step="1" >
									&nbsp;&nbsp;&nbsp;
								</c:forEach>
								&nbsp;&nbsp;
								<img src = "/ToGo/resources/static/song/images/re.gif">
								<b>${contentBoard.writer}</b>
								<font size = "1px"> ${contentBoard.reg_date} </font>
								<c:if test="${memId.equals(contentBoard.writer)}" >
									<input type = "button" value = "글삭제"
									onclick = "document.location.href='subDelete?num=${dto.num}&contentnum=${contentBoard.num}&pageNum=${pageNum}&pr_pageNum=${pr_pageNum}'">
								</c:if>
								<input type = "radio" name = "pick" value = "${contentBoard.num}" style="width:10px;height:10px;border:1px;">
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								${contentBoard.content}
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${memId == null}" >
				<c:forEach var = "contentBoard" items = "${contentBoard}" >
					<c:if test="${contentBoard.ref == contentBoard.num}" >
						<tr>	
							<td>
								&nbsp;&nbsp;
								<b>${contentBoard.writer}</b>
								<font size = "1px"> ${contentBoard.reg_date} </font>
								<br />
								&nbsp;&nbsp;
								${contentBoard.content}>
							</td>
						</tr>
					</c:if>
					<c:if test="${contentBoard.ref != contentBoard.num}" >
						<tr>	
							<td>
								<c:forEach var="i" begin="1" end="${contentBoard.re_level}" step="1" >
									&nbsp;&nbsp;&nbsp;
								</c:forEach>
								&nbsp;&nbsp;
								<img src = "/ToGo/resources/static/song/images/re.gif">
								<b>${contentBoard.writer}</b>
								<font size = "1px"> ${contentBoard.reg_date} </font>
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								${contentBoard.content}
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:if>
		</c:if>
		</form>
		</tr>
		<tr>
			<td>
				<c:if test="${count > 0}" >
					<c:if test="${startPage > 10}" >
						<a href = "/ToGo/imageboard1/contentForm?num=${dto.num}&pageNum=${pageNum}&pr_pageNum=${startPage - 10}">[이전]</a>
					</c:if>	
					<c:forEach var = "i" items = "${page}" >
						<a href = "/ToGo/imageboard1/contentForm?num=${dto.num}&pageNum=${pageNum}&pr_pageNum=${i}">[${i}]</a>
					</c:forEach>
					<c:if test="${endPage < pageCount}" >
						<a href = "/ToGo/imageboard1/contentForm?num=${dto.num}&pageNum=${pageNum}&pr_pageNum=${startPage + 10}">[다음]</a>
					</c:if>
				</c:if>
			</td>
		</tr>		
	</table>
</body>
</html>