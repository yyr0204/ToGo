/**
 * 
 */
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
      var comments = getElementById('fool-jihoon');
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
    	  var commentElement = $(this).parents('.comment-content').find('.comment-content2');
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
    var reCommentForm = $(event.target).closest('.comment').next('.reCommentForm');
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