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
        <a href="/ToGo/board/home">
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
                <a class="btn btn-success" href="/board/modify?cm_no=${dto.cm_no}" style="color: white;">수정</a>
                <a class="btn btn-danger bi bi-trash3" href="/board/delete?cm_no=${dto.cm_no}" style="color: white;">삭제</a>
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
                <a class="btn btn-dark" href="/board/login">
                    <i class="bi bi-person-up fs-1"></i>
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
						    <a class="btn btn-success" href="/ToGo/board/cmAjaxupdate?cm_no=${comment.cm_no}" data-comment="${comment.cm_content}">수정</a>
						    <a class="btn btn-danger bi bi-trash3" href="/ToGo/board/CmAjaxdelete?cm_no=${comment.cm_no}">삭제</a>
						    <a class="btn btn-primary d-none" href="#" data-comment-no="${comment.cm_no}">답글 달기</a>
						</div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </section>
</div>
<footer></footer>
<script src="//code.jquery.com/jquery-3.7.0.min.js"></script>
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
            var comments = "";

            if (result.length < 1) {
                comments = "등록된 댓글이 없습니다.";
            } else {
                $(result).each(function () {
                    comments += "<div class='comment'>";
                    comments += "<div class='fw-bold'>작성자: " + this.cm_writer + "</div>";
                    comments += "<div class='text-muted fst-italic'>작성일: " + formatDate(this.reg_date) + "</div>";
                    comments += "<div>내용: " + this.cm_content + "</div>";
                    comments += "<a class='btn btn-success' href='/ToGo/board/cmAjaxupdate?cm_no=" + this.cm_no + "' data-comment='" + this.cm_content + "'>수정</a>";
                    comments += "<a class='btn btn-danger bi bi-trash3' href='/ToGo/board/CmAjaxdelete?cm_no=" + this.cm_no + "'>삭제</a>";
                    comments += "<a class='btn btn-primary' href='/ToGo/board/replyForm?cm_no=" + this.cm_no + "'>답글달기</a>";
                    comments += "</div>";
                });
            }

            // 새로운 댓글 목록으로 HTML을 갱신
            $("#commentList").html(comments);
        }
    });

    $.ajax({
        url: '/ToGo/board/commentCnt?cm_no=' + $("#cm_no").val(),
        type: 'GET',
        dataType: 'json',
        success: function (commentCnt) {
            var cnt = '<span><i class="bi bi-chat-dots"></i></span> <span>댓글수 : ' + commentCnt + '</span>';
            $("#commentCnt").html(cnt);
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

                // 작성 후에 새로고침을 수행
                location.reload();
            } else {
                alert("댓글 작성에 실패했습니다.");
            }
        },
        error: function () {
            alert("댓글 작성에 실패했습니다.");
        }
    });
}

//수정 버튼 클릭 시 기존 댓글 내용을 가져와서 입력 필드 생성
$(document).on('click', '.btn-success', function (event) {
    event.preventDefault();
    var commentContent = $(this).data('comment');
    var commentElement = $(this).parent().find('.comment-content');
    var commentTextarea = $('<textarea class="form-control" rows="3" required></textarea>').val(commentContent);
    commentElement.html(commentTextarea);
    $(this).removeClass('btn-success').addClass('btn-primary').text('저장').next('.btn-primary').removeClass('d-none');
});

// 저장 버튼 클릭 시 수정된 댓글 내용을 서버에 전송
$(document).on('click', '.btn-primary', function (event) {
    event.preventDefault();
    var commentContent = $(this).prev('.comment-content').find('textarea').val();
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
            if (result === "success") {
                // 댓글 수정 후에 댓글 목록을 다시 가져와서 화면 갱신
                getCommentList();

                // 수정 완료 후 수정 버튼과 저장 버튼 상태 변경
                var commentElement = $('a[data-comment-no="' + commentNo + '"]').parent().find('.comment-content');
                commentElement.html(commentContent);
                commentElement.parent().find('.btn-primary').addClass('d-none').prev('.btn-success').removeClass('d-none');
            } else {
                alert("댓글 수정에 실패했습니다.");
            }
        },
        error: function () {
            alert("댓글 수정에 실패했습니다.");
        }
    });
});
</script>
</body>
</html>
