<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티</title>
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link
	href="${pageContext.request.contextPath}/resources/static/song/css/styles.css?ver=1"
	rel="stylesheet" />
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<style>
.container {
	max-width: 400px;
	margin: 0 auto;
}
.image {
	max-width: 50px;
	height: auto; /* 이미지의 가로세로 비율을 유지하도록 함 */
	display: inline-block; /* 이미지와 댓글 내용이 옆으로 표시되도록 함 */
	vertical-align: top; /* 이미지와 댓글 내용의 상단 정렬을 설정함 */
	margin-right: 10px; /* 이미지 오른쪽에 약간의 여백을 추가함 */
}
.image-container{
	display:flex;
	justify-content: center;
	flex-direction: column;
	align-items: center;
}
.image-container img {
    width: 500px;
    height: auto;
    text-align: center; /* 이미지를 가운데로 정렬 */
}
.comment-content-wrapper {
	display: inline-block; /* 이미지와 댓글 내용이 옆으로 표시되도록 함 */
}
/* 댓글들의 보더 라인 스타일 */
.comment {
	border-top: 1px solid #ccc;
	padding: 10px 0;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<div class="container p-5">
		<article>
			<header class="mb-4">
				<input type="hidden" id="cm_no" value="${dto.cm_no}">
				<h1 class="fw-bolder mb-3">제목 : ${dto.cm_title}</h1>
					<div class="mx-3" style="float: left;">작성일 :</div>
					<div class="text-muted fst-italic" id="comments-park-contents">
						<fmt:formatDate value="${dto.reg_date}"
							pattern="yyyy년 MM월 dd일 a hh시 mm분 " />
					</div>
				<div class="mx-3" style="float: left;">글쓴이 :</div>
				<div class="text-muted fst-italic mb-2">${dto.cm_writer}</div>
				<div class="mx-3" style="float: left;">조회수 :</div>
				<div class="text-muted fst-italic mb-3">${dto.readcount}</div>
			</header>
				<div class="text-muted fst-italic mb-3 image-container">
				    <c:set var="imageFilenames" value="${fn:split(dto.filename, ',')}" /> <!-- 이미지 파일명들을 쉼표로 분리하여 변수에 저장 -->
				    <c:forEach var="filename" items="${imageFilenames}">
				        <img src="/ToGo/resources/static/cmImage/${filename.trim()}"></br> <!-- 각 이미지 파일명 앞뒤에 있는 공백 제거 후 이미지 출력 -->
				    </c:forEach>
				</div>
			<section class="mb-2 card">
				<section class="p-4 mb-5">${dto.cm_content}</section>
			</section>
			<div class="btn_wrap text-end mb-5">
				<c:if test="${(memId == dto.cm_writer)}">
					<a class="btn btn-success"
						href="/ToGo/board/cmModifyForm?cm_no=${dto.cm_no}">수정</a>
					<a class="btn btn-danger bi bi-trash3"
						onclick="if(confirm('정말 삭제하시겠습니까?')) { href='/ToGo/board/cmDelete?cm_no=${dto.cm_no}' }">삭제</a>
					<a class="btn btn-secondary" href="/ToGo/board/cmMain">목록</a>
				</c:if>
				<c:if test="${level=='3'}">
					<a class="btn btn-danger bi bi-trash3"
						onclick="if(confirm('정말 삭제하시겠습니까?')) { href='/ToGo/board/cmDelete?cm_no=${dto.cm_no}' }">삭제</a>
					<a class="btn btn-secondary" href="/ToGo/board/cmMain">목록</a>
				</c:if>
			</div>
		</article>
		<section class="mb-5">
			<div class="card bg-light">
				<div class="card-body">
					<div id="commentCnt" class="p-1 mb-2"></div>
					<form id="commentForm" name="addBoard" class="form-horizontal">
						<input type="hidden" id="cm_group" name="cm_group"
							value="${dto.cm_no}" /> <input type="hidden" id="depth"
							name="depth" value="2">
						<div class="commentarea row mb-2">
							<div class="col-md-11">
								<c:choose>
									<c:when test="${(memId != null) || (level=='3')}">
										<textarea id="comment" name="comment" class="form-control"
											rows="3" placeholder="댓글을 남겨주세요." required></textarea>
									</c:when>
									<c:otherwise>
										<textarea id="comment" name="comment" class="form-control"
											rows="3" placeholder="로그인 후 이용가능합니다." readonly></textarea>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="col-md-1">
								<c:choose>
									<c:when test="${(memId != null) || (level=='3')}">
										<button class="btn btn-dark btn-sm" type="button" id="cWrite"
											onclick="insertComment(event)">
											<i class="bi bi-send" style="font-size: 14px;">댓글작성</i>
										</button>
									</c:when>
									<c:otherwise>
										<a class="btn btn-dark btn-sm" href="/ToGo/login/loginMain">
											<i class="bi bi-person-up" style="font-size: 14px;">로그인하기</i>
										</a>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</form>
					<div class="mb-5" id="commentList">
						<c:forEach var="comment" items="${commentList}">
							<div class="comment">
								<div class="comment-content-wrapper" style="width:100%;">
									<div class="for-flex-park-icon">
										<div>
											<c:if test="${comment.depth == 3}">
												<i class="fa-solid fa-arrow-turn-up fa-rotate-90 fa-xl"></i>
											</c:if>
										</div>
										<div  style="width:100%;">
											<div class="for-flex-part-comment">
												<div class="fw-bold">${comment.cm_writer}</div>
												<div class="text-muted fst-italic">
													<fmt:formatDate value="${comment.reg_date}" pattern="yyyy-MM-dd a hh:mm:ss " />
												</div>
											</div>
											<div class="comment-content for-flex-part-comment">
												<div class="comment-content2" style="width: 80%;">
													<c:if test="${comment.status eq 'Y'}">
														${comment.cm_content}
														<div id="comment-text"></div>
													</c:if>
													<c:if test="${comment.status eq 'N' }">
														작성자가 삭제한 글입니다.
														<input type="hidden" value="${comment.cm_content}" name="cm_content">
													</c:if>
												</div>
												<div>
													<%-- 작성자일 경우 수정, 삭제 가능 --%>
													<c:if test="${memId == comment.cm_writer && comment.status eq 'Y'}">
														<!-- 수정 -->
														<a class="btn btn-success btn-update" id="bt-park-comment-mod" href="#"
															data-comment-no="${comment.cm_no}"
															data-comment="${comment.cm_content}"><i class="fa-solid fa-pen-to-square"></i></a>
														<!-- 삭제 -->
														<a class="btn btn-danger btn-delete" id="bt-park-comment-mod" href="#"
															data-comment-no="${comment.cm_no}"><i class="fa-solid fa-trash-can"></i></a>
													</c:if>
												</div>
											</div>
											<%-- 관리자일 경우 삭제 가능 --%>
											<c:if test="${level=='3'}">
												<a class="btn btn-danger btn-delete" href="#"
													data-comment-no="${comment.cm_no}">삭제</a>
											</c:if>
											<%-- 회원인 경우 답글 가능 --%>
											<c:if
												test="${(memId != null || level=='3') && (comment.depth == 2) && comment.status eq 'Y'}">
												<a class="btn btn-primary" href="#"
													onclick="toggleReCommentForm(event)">답글 달기</a>
											</c:if>
										</div>
									</div>
								</div>
							</div>
							<div class="reCommentForm mt-2 ms-5"
								style="display: none; width: 50%;">
								<input type="hidden" id="commentStep" class="commentStep"
									value="${comment.step}"> <input type="hidden"
									name="reDepth" id="reDepth" value="3"> <input
									type="hidden" name="id" id="hReStep" value="' + id + '">
								<c:choose>
									<c:when test="${(memId != null) || (level=='3')}">
										<textarea id="reComment" name="comment" rows="1"
											class="form-control" placeholder="대댓글을 남겨주세요." required></textarea>
										<div class="text-end mt-2">
											<button class="btn btn-outline-dark btn-sm" type="button"
												style="font-size: 10px;" onclick="insertReComment(event)">
												<i class="bi bi-send">대댓글 달기</i>
											</button>
										</div>
									</c:when>
									<c:otherwise>
										<textarea id="reComment" name="content" rows="1"
											class="form-control mb-3" placeholder="로그인 후 이용가능합니다."
											readonly></textarea>
									</c:otherwise>
								</c:choose>
							</div>
						</c:forEach>
					</div>

				</div>
			</div>
		</section>
	</div>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<script src="/ToGo/resources/js/cmView.js"></script>
</body>
</html>
