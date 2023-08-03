<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>여행기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="${pageContext.request.contextPath}/resources/static/song/css/styles.css" rel="stylesheet" />
<style>
.container2 {
	max-width: 800px;
	margin: 0 auto;
}
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

.image {
	max-width: 50px;
	height: auto; /* 이미지의 가로세로 비율을 유지하도록 함 */
}

/* Custom CSS for buttons */
.btn-submit {
	padding: 10px 20px;
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.btn-submit:hover {
	background-color: #0056b3;
}
.text-right {
  text-align: right;
}
.comment-divider {
  border-top: 1px solid #ccc;
  margin: 10px 0;
}
</style>
</head>

<script language="javascript">
	function checkIn() {
		var content = document.chat.content.value;
		if (!content) {
			alert("댓글을 입력해주세요");
			document.chat.content.focus();
			return false;
		}
		if (${memId == null}  && ${level !='3'}) {
			alert("로그인후 다시 작성하시기바랍니다.");
			return false;
		}
	}
</script>

<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<div class="container2">
		<article>
			<header class="mb-4">
				<%-- 			<input type="hidden" id="cm_no" value="${dto.cm_no}"> --%>
				<h1 class="fw-bolder mb-3">제목 : ${dto.subject}</h1>
				<div class="mx-3" style="float: left;">작성일 :</div>
				<div class="text-muted fst-italic">
					<fmt:formatDate value="${dto.reg_date}"
						pattern="yyyy년 MM월 dd일 a hh시 mm분 " />
				</div>
				<div class="mx-3" style="float: left;">글쓴이 :</div>
				<div class="text-muted fst-italic mb-2">${dto.writer}</div>
				<div class="mx-3" style="float: left;">조회수 :</div>
				<div class="text-muted fst-italic mb-3">${dto.readcount}</div>
			</header>
			<div class="mx-3" style="float: left;">여행일정 :</div>
			<div class="text-muted fst-italic mb-2">
				<a href="">${"?박?일"}</a>
			</div>
			<div id="carouselExampleIndicators" class="carousel slide"
				data-bs-ride="carousel">
				<div class="carousel-indicators">
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="0" class="active" aria-current="true"
						aria-label="Slide 1"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="1" aria-label="Slide 2"></button>
				</div>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="/ToGo/resources/static/song/upload/${dto.thumbnail}"
							class="d-block w-100" alt="...">
					</div>
					<div class="carousel-item">
						<img src="/ToGo/resources/static/song/upload/${dto.image}"
							class="d-block w-100" alt="...">
					</div>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
			<section class="mb-2 card">
				<div class="mx-3" style="float: left;">
					글내용
					<div class="text-muted fst-italic mb-2">${dto.content}</div>
				</div>
			</section>
				<td>
					<c:if test="${(memId != null) || (level=='3')}">
						<c:if test="${memId.equals(dto.writer)}">
							<input type="button" value="글수정" class="btn btn-success"
								onclick="document.location.href='updateForm?num=${dto.num}&pageNum=${pageNum}'">
							<input type="button" value="글삭제" class="btn btn-danger bi bi-trash3"
								onclick="document.location.href='contentDelete?num=${dto.num}&pageNum=${pageNum}'">
						</c:if>
						<c:if test="${level=='3'}">
							<input type="button" value="글삭제" class="btn btn-danger bi bi-trash3" onclick="document.location.href='contentDelete?num=${dto.num}&pageNum=${pageNum}'">
						</c:if>
					</c:if>
					<input type="button" value="글목록" class="btn btn-secondary"	onclick="document.location.href='list?pageNum=${pageNum}'">
				</td>
			<!-- ---------------------------------------------------------------------------------------------- -->
			<form name="chat" action="contentPro" method="post"	onSubmit="return checkIn()" class="form-horizontal">
				<c:if test="${(memId != null) || (level=='3')}">
				<div class="commentarea row mb-2">
					<div class="col-md-11">
						<c:choose>
							<c:when test="${(memId != null) || (level=='3')}">
								<textarea id="content" name="content" class="form-control"
									rows="3" placeholder="댓글을 남겨주세요." required></textarea>
							</c:when>
							<c:otherwise>
								<textarea id="content" name="content" class="form-control"
									rows="3" placeholder="로그인 후 이용가능합니다." readonly></textarea>
							</c:otherwise>
						</c:choose>
					</div>
					<input type="hidden" name="writer" value="${dto2.nickname}">
					<input type="hidden" name="num" value="${dto.num}">
					<input type="hidden" name="pageNum" value="${pageNum}">
					<input type="hidden" name="pr_pageNum" value="${pr_pageNum}">
					<div class="col-md-1">
						<c:choose>
							<c:when test="${(memId != null) || (level=='3')}">
								<input type="submit" value="댓글작성" class="btn btn-dark btn-sm">
								<input type="radio" name="pick" value="">
							</c:when>
							<c:otherwise>
								<a class="btn btn-dark btn-sm" href="/ToGo/login/loginMain">
									<i class="bi bi-person-up" style="font-size: 14px;">로그인하기</i>
								</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				</c:if>
				<c:if test="${(memId != null) || (level=='3')}">
				<tr>
					<td>
						<input type="hidden" name="num" value="${dto.num}">
						<input type="hidden" name="pageNum" value="${pageNum}">
						<input type="hidden" name="pr_pageNum" value="${pr_pageNum}">
					</td>
				</tr>
				</c:if>
				<div class="reCommentForm mt-2" >
					<c:if test="${count != 0}">
					<c:forEach var="contentBoard" items="${contentBoard}">
						<div class="comment-divider"></div>
						<c:if test="${contentBoard.ref == contentBoard.num}">
							<div class="comment">
								<div class="comment-content-wrapper">
									<div class="fw-bold">작성자: ${contentBoard.writer}</div>
									<div class="text-muted fst-italic">
										작성일:<fmt:formatDate value="${contentBoard.reg_date}" pattern="yyyy년 MM월 dd일 a hh시 mm분 " />
									<input type="radio" name="pick" value="${contentBoard.num}"	style="width: 10px; height: 10px; border: 1px;">
									</div>
									<c:if
										test="${memId.equals(contentBoard.writer) || (level=='3')}">
										<input type="button" value="글삭제"
											onclick="document.location.href='subDelete?num=${dto.num}&contentnum=${contentBoard.num}&pageNum=${pageNum}'">
									</c:if>
									<div class="comment-content">${contentBoard.content}</div>
								</div>
							</div>
						</c:if>
						<c:if test="${contentBoard.ref != contentBoard.num}">
							<div class="comment">
								<div class="comment-content-wrapper">
								<c:forEach var="i" begin="1" end="${contentBoard.re_level}" step="1">
										&nbsp;&nbsp;&nbsp;
								</c:forEach>
									<div class="fw-bold"><img src="/ToGo/resources/static/song/images/re.gif">작성자: ${contentBoard.writer}</div>
									<div class="text-muted fst-italic">
									&nbsp;&nbsp;&nbsp;작성일:<fmt:formatDate value="${contentBoard.reg_date}" pattern="yyyy년 MM월 dd일 a hh시 mm분 " />
										<c:if test="${memId.equals(contentBoard.writer) || (level=='3')}">
											<input type="button" value="글삭제" onclick="document.location.href='subDelete?num=${dto.num}&contentnum=${contentBoard.num}&pageNum=${pageNum}&pr_pageNum=${pr_pageNum}'">
										</c:if> 
										<input type="radio" name="pick" value="${contentBoard.num}"	style="width: 10px; height: 10px; border: 1px;">
									</div>
									<c:if
										test="${memId.equals(contentBoard.writer) || (level=='3')}">
										<input type="button" value="글삭제"
											onclick="document.location.href='subDelete?num=${dto.num}&contentnum=${contentBoard.num}&pageNum=${pageNum}&pr_pageNum=${pr_pageNum}'">
									</c:if>
									<div class="comment-content">&nbsp;&nbsp;&nbsp;&nbsp;${contentBoard.content}</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
					</c:if>
				</div>
			</form>
			<tr>
				<td><c:if test="${count > 0}">
						<c:if test="${startPage > 10}">
							<a
								href="/ToGo/imageboard1/contentForm?num=${dto.num}&pageNum=${pageNum}&pr_pageNum=${startPage - 10}">[이전]</a>
						</c:if>
						<c:forEach var="i" items="${page}">
							<a
								href="/ToGo/imageboard1/contentForm?num=${dto.num}&pageNum=${pageNum}&pr_pageNum=${i}">[${i}]</a>
						</c:forEach>
						<c:if test="${endPage < pageCount}">
							<a
								href="/ToGo/imageboard1/contentForm?num=${dto.num}&pageNum=${pageNum}&pr_pageNum=${startPage + 10}">[다음]</a>
						</c:if>
					</c:if></td>
			</tr>
		</article>
	</div>

</body>
</html>