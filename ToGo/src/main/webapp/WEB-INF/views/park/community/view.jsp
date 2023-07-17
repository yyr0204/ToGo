<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>community</title>
</head>
<body>
	<!-- NAV -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a href="/togo/board/home">
				<h3 class="navbar-brand">ToGo</h3>
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<c:choose>
						<c:when test="${loginId == null}">
							<li class="nav-item"><a class="btn btn-secondary"
								href="/togo/board/home">홈</a></li>
							<li class="nav-item"><a class="btn btn-secondary mx-1"
								href="/togo/board/register">회원가입</a></li>
							<li class="nav-item"><a class="btn btn-secondary"
								href="/togo/board/login">로그인</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="btn btn-secondary"
								href="/togo/board/home">홈</a></li>
							<li class="nav-item"><a class="btn btn-secondary mx-1"
								href="/togo/board/write">글쓰기</a></li>
							<li class="nav-item"><a class="btn btn-secondary"
								href="/togo/board/logout">로그아웃</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
	<!-- NAV 끝 -->
	<!-- Page content-->
	<div class="container p-5">
		<article>
			<header class="mb-4">
				<input type="hidden" id="cm_no" value="${dto.cm_no}">
				<!-- 제목 -->
				<h1 class="fw-bolder mb-3">${dto.cm_title}</h1>
				<!-- 게시일 -->
				<div class="mx-3" style="float: left;">작성일</div>
				<div class="text-muted fst-italic">${dto.reg_date}</div>
				<!-- 작성자 -->
				<div class="mx-3" style="float: left;">글쓴이</div>
				<div class="text-muted fst-italic mb-2">${dto.cm_writer}</div>
			</header>
			<!-- 내용 -->
			<section class="mb-2 card">
				<section class="p-4 mb-5">${dtoc}</section>
			</section>
			<div class="btn_wrap text-end mb-5">
				<c:if test="${loginId == dto.cm_writer}">
					<a class="btn btn-success"
						href="/board/modify?cm_no=${dto.cm_no}" style="color: white;">수정</a>
					<a class="btn btn-danger bi bi-trash3"
						href="/board/delete?cm_no=${dto.cm_no}" style="color: white;"></a>
				</c:if>
			</div>
		</article>
		<!-- Comments section-->
		<section class="mb-5">
			<div class="card bg-light">
				<div class="card-body">
					<div id="commentCnt" class="p-1 mb-2"></div>
					<!-- 댓글 입력 form-->
					<form id="commentForm" name="addBoard" class="form-horizontal"
						method="post">
						<!-- hidden -->
						<input type="hidden" id="cm_group" name="cm_group"
							value="<c:out value="${dto.cm_no}" />"> <input
							type="hidden" id="depth" name="depth" value="2">
						<div class="commentarea row mb-2">
							<div class="col-md-11">
								<!-- 입력 영역 -->
								<c:choose>
									<c:when test="${loginId != null}">
										<textarea id="comment" name="comment" class="form-control"
											rows="3" placeholder="댓글을 남겨주세요." required></textarea>
									</c:when>
									<c:otherwise>
										<textarea id="content" name="content" class="form-control"
											rows="3" placeholder="로그인 후 이용가능합니다." readonly></textarea>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="col-md-1">
								<c:choose>
									<c:when test="${loginId != null}">
										<button class="btn btn-dark" type="button" id="cWrite"
											onclick="insertComment(event)" value="댓글작성"
											style="height: 100%; width: 100%;">
											<i class="bi bi-send fs-1" style="font-size: 100%;"></i>
										</button>
									</c:when>
									<c:otherwise>
										<a class="btn btn-dark" href="/board/login"
											style="color: white; height: 100%; width: 100%;"> <i
											class="bi bi-person-up fs-1"></i>
										</a>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</form>
					<!-- 댓글 리스트 -->
					<div class="mb-5" id="commentList"></div>
				</div>
			</div>
		</section>
	</div>
	<footer></footer>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			getCommentList();
		});

		function getCommentList() {
			var url = "/board/commentList?cm_no=" + $("#cm_no").val();
			var id = "";

			$.ajax({
				url : "/board/loginCheck", // 서버 요청 주소
				type : "GET",
				dataType : "json",
				success : function(data) {
					id = data.loginId; // 서버에서 반환한 세션 값
				},
				error : function() {
					alert("세션 값 가져오기 실패");
				}
			});

			$
					.ajax({
						url : url,
						type : 'GET',
						dataType : 'json',
						success : function(result) {
							var comments = "";
							if (result.length < 1) {
								comments = "등록된 댓글이 없습니다.";
							} else {
								$(result)
										.each(
												function() {
													if (this.depth == 2) {
														comments += '<div class="d-flex commentList">';
													}
													if (this.depth == 3) {
														comments += '<div class="d-flex commentList ms-5" >';
													}
													comments += '<div class="flex-shrink-0">';
													comments += '<img class="rounded-circle" id="commenterImg" src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png" style="width: 50px;" />';
													comments += '</div>';
													comments += '<div class="ms-3">';
													comments += '<input type="hidden"id="commentStep" class="commentStep" value="' + this.step + '">';
													comments += '<input type="hidden"id="commentIn" class="commentNo" value="' + this.cm_no + '">';
													comments += '<div id="commentNo" style="display: none;">'
															+ this.cm_no
															+ '</div>'
													comments += '<div class="fw-bold" style="float: left;">'
															+ this.id
															+ '</div>';
													comments += '<div class="mx-3 opacity-50" style="float: left;">'
															+ this.reg_date
															+ '</div>';
													if (this.depth == 2) {
														comments += '<button class="btn btn-outline-primary btn-sm border-0" style="font-size: 10px;" onclick="reCommentForm(event)">답글달기</button>';
													}
													if (id == this.id) {
														comments += '<button class="btn btn-outline-success btn-sm mx-1" style="font-size: 10px; " onclick="modifyCommentForm(event)">수정</button>';
														comments += '<button class="btn btn-outline-danger btn-sm" style=" font-size: 10px;" onclick="deleteComment(event)"><i class="bi bi-trash3"></i></button>';
													}
													comments += '<div class="comment mb-3">'
															+ this.content
															+ '</div>';
													comments += '</div></div>';

													// 댓글 수정 폼 
													comments += '<div class="modifyCForm mt-2 ms-5" style="display: none; width: 50%;">';
													comments += '<div class=commentNo style="display: none;">'
															+ this.cm_no
															+ '</div>'
													comments += '<input type="hidden" name="depth" id="hDepth" value=2><input type="hidden" id="hcm_group"  value="" " name="cm_group"><input type="hidden" id="hcm_no" value="'
    													+ this.cm_no
    													+ '" name="cm_no">';
													comments += '<textarea id="modifyarea" class="form-control" rows="1" name="content" placeholder=" '+ this.content +' " required></textarea>';
													comments += '<div class="text-end mt-2">';
													comments += '<button class="btn btn-outline-dark btn-sm" type="button" style="font-size: 10px;" onclick="modifyComment(event)"><i class="bi bi-send"></i></button>';
													comments += '<button type="button" class="re-btn btn btn-secondary btn-sm" style="font-size: 10px;" onclick="cancelModifyC(event)"><i class="bi bi-x-lg"></i></button>';
													comments += '</div></div></div>';

													// 대댓글 작성 폼 
													comments += '<div class="reCommentForm mt-2 ms-5" style="display: none; width: 50%;">';
													comments += '<input type="hidden"id="commentStep" class="commentStep" value="' + this.step + '">';
													comments += '<input type="hidden" name="reDepth" id="reDepth" value=3>';
													comments += '<input type="hidden" name="id" id="hReStep" value=" ' + id + ' ">';
													if (id != null) {
														comments += '<textarea id="reComment" name="comment" rows="1" class="form-control" placeholder="대댓글을 남겨주세요." required></textarea>';
														comments += '<div class="text-end mt-2">';
														comments += '<button class="btn btn-outline-dark btn-sm" type="button" style="font-size: 10px;" onclick="insertReComment(event)"><i class="bi bi-send"></i></button>';
														comments += '</div>';
													}
													if (id == null) {
														comments += '<textarea id="reComment" name="content" rows="1" class="form-control mb-3" placeholder="로그인 후 이용가능합니다." readonly></textarea>'
													}
													comments += '</div></div>';

												});
							}
							;
							$("#commentList").html(comments);
						}
					});

			$
					.ajax({
						url : '/board/commentCnt?cm_no=' + $("#cm_no").val(),
						type : 'GET',
						dataType : 'json',
						success : function(commentCnt) {
							var cnt = '<span><i class="bi bi-chat-dots"></i></span> <span>'
									+ commentCnt + '</span>';
							$("#commentCnt").html(cnt);
						}
					})

		};

		// 댓글 작성 ajax 
		function insertComment(event) {
			if (!$("#comment").val()) {
				alert("내용을 입력하세요.");
			} else {
				var dto = {
					cm_group : $("#cm_group").val(),
					depth : $("#depth").val(),
					content : $("#comment").val()
				};
				$.ajax({
					url : "/board/addC",
					data : dto,
					type : 'POST',
					success : function(result) {
						getCommentList();
						$("#comment").val("");
					}
				})
			}
		}

		// 대댓글 작성창
		function reCommentForm(event) {
			var reCommentForm = event.target.closest(".commentList").nextElementSibling;
			while (reCommentForm) {
				if (reCommentForm.classList.contains("reCommentForm")) {
					break;
				}
				reCommentForm = reCommentForm.nextElementSibling;
			}
			if (reCommentForm) {
				reCommentForm.style.display = reCommentForm.style.display === "none" ? "block"
						: "none";
			}
		}

		// 대댓글 작성 ajax 
		function insertReComment(event) {
			var commentStep = parseInt($(event.target)
					.closest('.reCommentForm').find('#commentStep').val());

			/* 		alert("그룹" + $('#cm_group').val())
					alert("스텝" + commentStep)
					alert("뎁스" + $('#reDepth').val())
					alert("내용" + $(event.target).closest('.reCommentForm').find('#reComment').val())		
			 */
			var dto = {
				cm_group : $('#cm_group').val(),
				step : commentStep,
				depth : $("#reDepth").val(),
				content : $(event.target).closest('.reCommentForm').find(
						'#reComment').val()
			};

			$.ajax({
				url : "/board/addC",
				data : dto,
				type : 'POST',
				success : function(result) {
					getCommentList();
					$("#reComment").val("");
				}
			})

		}

		// 댓글 삭제 
		function deleteComment(event) {
			var cm_no = $(event.target).closest('.ms-3').find('#commentNo')
					.text();
			var url = '/board/ajaxdelete?cm_no=' + cm_no;

			$.ajax({
				url : url,
				type : 'POST',
				success : function(result) {
					getCommentList();
					$("#comment").val("");
				},
				error : function() {
					alert('댓글 삭제 실패');
				}
			});
		}
		// 댓글 수정 
		function modifyComment(event) {
			var cm_no = $(event.target).closest('.modifyCForm').find(
					'.commentNo').text();
			var content = $(event.target).closest('.modifyCForm').find(
					'#modifyarea').text();
			var dto = {
				depth : $(event.target).closest('.modifyCForm').find('#hDepth')
						.val(),
				cm_group : $(event.target).closest('.modifyCForm').find(
						'#hcm_group').val(),
				content : $(event.target).closest('.modifyCForm').find(
						'#modifyarea').val(),
				cm_no : $(event.target).closest('.modifyCForm').find('#hcm_no')
						.val()
			};
			$.ajax({
				url : '/board/ajaxupdate?cm_no=' + cm_no,
				type : 'POST',
				data : dto,
				success : function(result) {
					getCommentList();
					$("#modifyarea").val("");
				},
				error : function() {
					alert('댓글 수정 실패');
				}
			});
		}

		// 댓글 수정 창
		function modifyCommentForm(event) {
			var modifyCForm = event.target.closest(".commentList").nextElementSibling;
			while (modifyCForm) {
				if (modifyCForm.classList.contains("modifyCForm")) {
					break;
				}
				modifyCForm = modifyCForm.nextElementSibling;
			}
			if (modifyCForm) {
				modifyCForm.style.display = modifyCForm.style.display === "none" ? "block"
						: "none";
			}
		}
		function cancelModifyC(event) {
			var modifyCForm = event.target.closest(".modifyCForm");
			modifyCForm.style.display = "none";
		}
	</script>
</body>
</html>
