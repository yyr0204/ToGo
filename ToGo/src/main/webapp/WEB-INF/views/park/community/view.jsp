<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>community</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a href="/ToGo/board/cmMain">
            <h3 class="navbar-brand">ToGo</h3>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <c:choose>
                    <c:when test="${memId == null}">
                        <li class="nav-item"><a class="btn btn-secondary" href="/ToGo/board/cmMain">홈</a></li>
                        <li class="nav-item"><a class="btn btn-secondary mx-1" href="/ToGo/login/loginMain">회원가입</a>
                        </li>
                        <li class="nav-item"><a class="btn btn-secondary" href="/ToGo/login/loginMain">로그인</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="btn btn-secondary" href="/ToGo/board/cmMain">홈</a></li>
                        <li class="nav-item"><a class="btn btn-secondary mx-1" href="/ToGo/board/cmWriteForm">글쓰기</a></li>
                        <li class="nav-item"><a class="btn btn-secondary" href="/ToGo/login/loginMain">로그아웃</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
<div class="container p-5">
    <article>
        <header class="mb-4">
            <input type="hidden" id="cm_no" value="${dto.cm_no}">
            <h1 class="fw-bolder mb-3">제목 : ${dto.cm_title}</h1>
            <div class="mx-3" style="float: left;">작성일 : </div>
            <div class="text-muted fst-italic"><fmt:formatDate value="${dto.reg_date}" pattern="yyyy년 MM월 dd일 a hh시 mm분 "/></div>
            <div class="mx-3" style="float: left;">글쓴이 : </div>
            <div class="text-muted fst-italic mb-2">${dto.cm_writer}</div>
        </header>
        <section class="mb-2 card">
            <section class="p-4 mb-5">${dto.cm_content}</section>
        </section>
        <div class="btn_wrap text-end mb-5">
            <c:if test="${memId == dto.cm_writer}">
                <a class="btn btn-success" href="/board/cmModifyForm?cm_no=${dto.cm_no}">수정</a>
                <a class="btn btn-danger bi bi-trash3" href="/board/cmDelete?cm_no=${dto.cm_no}" >삭제</a>
            </c:if>
        </div>
    </article>
    <section class="mb-5">
        <div class="card bg-light">
            <div class="card-body">
                <div id="commentCnt" class="p-1 mb-2"></div>
                <form id="commentForm" name="addBoard" class="form-horizontal">
                    <input type="hidden" id="cm_group" name="cm_group" value="${dto.cm_no}" />
                    <input type="hidden" id="depth" name="depth" value="2">
                    <div class="commentarea row mb-2">
                        <div class="col-md-11">
                            <c:choose>
                                <c:when test="${memId != null}">
                                    <textarea id="comment" name="comment" class="form-control" rows="3" placeholder="댓글을 남겨주세요." required></textarea>
                                </c:when>
                                <c:otherwise>
                                    <textarea id="comment" name="comment" class="form-control" rows="3" placeholder="로그인 후 이용가능합니다." readonly></textarea>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-md-1">
                            <c:choose>
                                <c:when test="${memId != null}">
                                    <button class="btn btn-dark" type="button" id="cWrite" onclick="insertComment(event)">
                                        <i class="bi bi-send fs-1" style="font-size: 100%;">댓글작성</i>
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <a class="btn btn-dark" href="/ToGo/login/loginMain">
                                        <i class="bi bi-person-up fs-1">로그인하기</i>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </form>
                <div class="mb-5" id="commentList">
                    <c:forEach var="comment" items="${commentList}">
					  <div class="comment">
					    <div class="fw-bold">작성자: ${comment.cm_writer}</div>
					    <div class="text-muted fst-italic">작성일: <fmt:formatDate value="${comment.reg_date}" pattern="yyyy년 MM월 dd일 a hh시 mm분 "/></div>
					    <div class="comment-content">${comment.cm_content}</div>
					    <%-- 작성자일 경우 수정, 삭제 가능 --%>
              			<c:if test="${memId == comment.cm_writer}">
					    	<a class="btn btn-success btn-update" href="#" data-comment-no="${comment.cm_no}" data-comment="${comment.cm_content}">수정</a>
					    	<a class="btn btn-danger btn-delete" href="#" data-comment-no="${comment.cm_no}">삭제</a>
						</c:if>
						<%-- 회원인 경우 답글 가능 --%>
                		<c:if test="${(memId != null) && (comment.depth == 2)}">
						    <a class="btn btn-primary" href="#" onclick="toggleReCommentForm(event)" data-comment-no="${comment.cm_no}" data-step="${comment.step}">답글 달기</a>

						    <div class="reCommentForm mt-2 ms-5" style="display: none; width: 50%;">
							     <input type="hidden" id="commentStep" class="commentStep" value="${comment.step}">
							    <input type="hidden" name="reDepth" id="reDepth" value="3">
							    <input type="hidden" name="id" id="hReStep" value="' + id + '">
							    <c:choose>
							        <c:when test="${memId != null}">
							            <textarea id="reComment" name="comment" rows="1" class="form-control" placeholder="대댓글을 남겨주세요." required></textarea>
							            <div class="text-end mt-2">
							                <button class="btn btn-outline-dark btn-sm" type="button" style="font-size: 10px;" onclick="insertReComment(event)"><i class="bi bi-send">대댓글 달기</i></button>
							            </div>
							        </c:when>
							        <c:otherwise>
							            <textarea id="reComment" name="content" rows="1" class="form-control mb-3" placeholder="로그인 후 이용가능합니다." readonly></textarea>
							        </c:otherwise>
							    </c:choose>
							</div>
													    
						</c:if>
						
					  </div>
					</c:forEach>
                </div>
                
            </div>
        </div>
    </section>
</div>
<footer></footer>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
  // 페이지 로드 시 자동으로 댓글 목록을 가져옴
  getCommentList();
});

function getCommentList() {
  var url = "/ToGo/board/cmCommentList?cm_no=" + $("#cm_no").val();

  $.ajax({
    url: url,
    type: 'GET',
    dataType: 'json',
    success: function (result) {
      // 새로운 댓글 목록으로 HTML을 갱신
      $("#commentList").html(comments);
      
	      refreshPage();
	    },
	    error: function () {
	      alert("댓글 목록을 가져오는 중에 오류가 발생했습니다.");
	    }
	  });

        $.ajax({
            url: '/ToGo/board/cmCommentCnt?cm_no=' + $("#cm_no").val(),
            type: 'GET',
            dataType: 'json',
            success: function (commentCnt) {
                var cnt = '<span><i class="bi bi-chat-dots"></i></span> <span>댓글수 : ' + commentCnt + '</span>';
                $("#commentCnt").html(cnt);
            },
            error: function () {
                alert("댓글 수를 가져오는 중에 오류가 발생했습니다.");
            }
        });
    }

    function insertComment(event) {
        event.preventDefault();

        if (!$("#comment").val()) {
          alert("내용을 입력하세요.");
          return;
        }

        var dto = {
          cm_group: $("#cm_no").val(),
          depth: 2,
          cm_content: $("#comment").val()
        };

        $.ajax({
          url: "/ToGo/board/cmAddC",
          data: JSON.stringify(dto),
          type: 'POST',
          contentType: 'application/json',
          success: function (result) {
            if (result === "success") {
              // 댓글 추가 후에 댓글 목록을 다시 가져와서 화면 갱신
              getCommentList();
              $("#comment").val("");
              
              // Call the refreshPage function here to refresh the page after the AJAX request is successful
              refreshPage();
            } else {
              alert("댓글 작성에 실패했습니다.");
            }
          },
          error: function () {
            alert("댓글 작성에 실패했습니다.");
          }
        });
      }
//댓글 수정
    $(document).on('click', '.btn-update', function (event) {
    	  event.preventDefault();
    	  var commentElement = $(this).parent().find('.comment-content');
    	  var commentContent = commentElement.text().trim();
    	  var commentTextarea = $('<textarea class="form-control" rows="3" required></textarea>').val(commentContent);
    	  commentElement.empty().append(commentTextarea);
    	  $(this).removeClass('btn-update').addClass('btn-save').text('저장').siblings('.btn-delete').addClass('d-none');
    	});

    	$(document).on('click', '.btn-save', function (event) {
    	  event.preventDefault();
    	  var commentElement = $(this).closest('.comment').find('.comment-content');
    	  var commentTextarea = commentElement.find('textarea');
    	  var commentContent = commentTextarea.val();
    	  var commentNo = $(this).data('comment-no');
    	  var dto = {
    	    cm_no: commentNo,
    	    cm_content: commentContent
    	  };

    	  $.ajax({
    	    url: "/ToGo/board/cmAjaxupdate",
    	    data: JSON.stringify(dto),
    	    type: 'POST',
    	    contentType: 'application/json',
    	    success: function (result) {
    	      if (result == "success") {
    	        // 댓글 수정 후에 댓글 목록을 다시 가져와서 화면 갱신
    	        getCommentList();

    	        // 수정 완료 후 수정 버튼과 저장 버튼 상태 변경
    	        commentElement.empty().text(commentContent);
    	        commentElement.parent().find('.btn-save').removeClass('btn-save').addClass('btn-update').text('수정').siblings('.btn-delete').removeClass('d-none');

    	        // AJAX 요청이 성공한 후 페이지 새로고침을 위해 refreshPage 함수 호출
    	        refreshPage();
    	      } else {
    	        alert("댓글 수정에 실패했습니다.");
    	      }
    	    },
    	    error: function () {
    	      alert("에러: 댓글 수정에 실패했습니다.");
    	    }
    	  });
    	});
//댓글 삭제 버튼
    	$(document).on('click', '.btn-delete', function (event) {
    	  event.preventDefault();
    	  var commentNo = $(this).data('comment-no');
    	  var deleteConfirmation = confirm("정말로 댓글을 삭제하시겠습니까?");
    	  if (deleteConfirmation) {
    	    $.ajax({
    	      url: "/ToGo/board/CmAjaxdelete?cm_no=" + commentNo,
    	      type: 'GET',
    	      success: function (result) {
    	        if (result == "success") {
    	          // 댓글 삭제 후에 댓글 목록을 다시 가져와서 화면 갱신
    	          getCommentList();

    	          // AJAX 요청이 성공한 후 페이지 새로고침을 위해 refreshPage 함수 호출
    	          refreshPage();
    	        } else {
    	          alert("댓글 삭제에 실패했습니다.");
    	        }
    	      },
    	      error: function () {
		        alert("에러: 댓글 삭제에 실패했습니다.");
    	      }
    	    });
    	  }
    	});
    	
    	// 대댓글 작성창 토글
    	function toggleReCommentForm(event) {
		    event.preventDefault();
		    var reCommentForm = $(event.target).closest('.comment').find('.reCommentForm');
		    var commentStep = $(event.target).data('step'); // 댓글의 step 값 가져오기
		    reCommentForm.find('#commentStep').val(commentStep); // 대댓글 작성창의 commentStep 필드에 값 설정
		    reCommentForm.toggle();
		}


    	// 대댓글 작성
    	function insertReComment(event) {
    	    event.preventDefault();

    	    var commentDepth = parseInt($(event.target).closest('.reCommentForm').find('#reDepth').val());
    	    var commentStep = $(event.target).closest('.reCommentForm').find('.commentStep').val();	// 댓글의 스텝 값 가져오기
    	    var dto = {
    	        cm_group: $("#cm_group").val(),
    	        depth: commentDepth,
    	        step: commentStep, // 댓글의 스텝 값을 대댓글의 스텝으로 사용
    	        cm_content: $(event.target).closest('.reCommentForm').find('#reComment').val()
    	    };

    	    $.ajax({
    	        url: "/ToGo/board/cmAddC",
    	        data: JSON.stringify(dto),
    	        type: 'POST',
    	        contentType: 'application/json',
    	        success: function (result) {
    	            if (result === "success") {
    	                // 대댓글 작성 후에 댓글 목록을 다시 가져와서 화면 갱신
    	                getCommentList();
    	                $(event.target).closest('.reCommentForm').find('#reComment').val("");

    	                // AJAX 요청이 성공한 후 페이지 새로고침을 위해 refreshPage 함수 호출
    	                refreshPage();
    	            } else {
    	                alert("대댓글 작성에 실패했습니다.");
    	            }
    	        },
    	        error: function () {
    	            alert("대댓글 작성에 실패했습니다.");
    	        }
    	    });
    	}

    	// 댓글 추가, 수정, 삭제 후에 페이지를 새로고침
    	function refreshPage() {
    	  location.reload();
    	}

</script>

</body>
</html>
