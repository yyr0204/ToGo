<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>community</title>
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="${pageContext.request.contextPath}/resources/static/song/css/styles.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery.js"></script>
    <!-- HEAD CONTENT -->
<style>
.image-container{
	display:flex;
	justify-content: center;
	flex-direction: column;
	align-items: center;
}
.image-container img {
    width: 500px;
    height: auto;
    cursor: pointer; /* 이미지에 마우스 커서를 손가락 모양으로 변경 */
}
#file-input:after{
padding-left:10px;
width:10px;
height:10px;
content: "\00d7";
cursor: pointer;
}
</style>

</head>
<body>
    <%@ include file="/WEB-INF/views/include/header.jsp" %>
    <!-- Page Content -->
    <div class="container mt-5">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <form name="modifyBoard" method="post" action="/ToGo/board/cmModifyPro" enctype="multipart/form-data" onsubmit="updateFormBeforeSubmit()">
                    <!-- Post Content -->
                    <article>
	    				<h3>커뮤니티 게시글 수정</h3>
                        <!-- Post Header -->
                        <header class="mb-4">
                        	<input type="hidden" id="imageNamesInput" name="imageNames" value="" />
<%--                         	<input type="hidden" name="originalFileName" value="${dto.filename}" /> --%>
                            <input type="hidden" name="cm_no" value="${dto.cm_no}" />
                            <!-- 제목 -->
                            <div class="mx-3 mb-2">제목</div>
                            <textarea id="cm_title" name="cm_title" rows="1" class="form-control mb-3" required>${dto.cm_title}</textarea>
                            <!-- 작성자 -->
                            <div class="mx-3 mb-2">작성자</div>
                            <div class="form-control mb-3">${memId}</div>
							                            
                            <!-- 이미지 -->
                             <div class="text-muted fst-italic mb-3 image-container">
						        <c:set var="imageFilenames" value="${fn:split(dto.filename, ',')}" />
						        <!-- 이미지 출력 -->
						        <c:forEach var="filename" items="${imageFilenames}" varStatus="loop">
						            <img src="/ToGo/resources/static/cmImage/${filename.trim()}" onclick="changeImage(this)">
						            <input type="checkbox" name="imageSelect" value="${filename.trim()}">삭제
						        </c:forEach>
						    </div>
							<div class="file_div">
                            	사진 추가 : <input type="file" id="file-input" name="save" /> <br />
							</div>
                            <!-- 내용 -->
                            <div class="mx-3 mb-2">내용</div>
                            <textarea id="cm_content" name="cm_content" class="form-control" required style="width: 100%; height: 400px;">${dto.cm_content}</textarea>
                        </header>
                        <div class="btn_wrap text-end mb-5">
                            <button class="btn btn-success" type="submit" id="write" value="등록">등록</button>
                            <a class="btn btn-danger waves-effect waves-light" href="/ToGo/board/cmView?cm_no=${dto.cm_no}" style="color: white;">취소</a>
                        </div>
                    </article>
                </form>
            </div>
        </div>
    </div>
<script>
function updateFormBeforeSubmit() {
    // 이미지 삭제 체크박스 처리
    var checkboxes = document.getElementsByName("imageSelect");
    var selectedFilenames = [];

    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            selectedFilenames.push(checkboxes[i].value);
        }
    }

    var imageNamesInput = document.getElementById("imageNamesInput");
    imageNamesInput.value = selectedFilenames.join(',');

    // 이미지 추가 처리
    var fileInput = document.getElementById("file-input");
    var addedFiles = fileInput.files;
    var addedFilenames = [];

    for (var i = 0; i < addedFiles.length; i++) {
        addedFilenames.push(addedFiles[i].name);
    }

    if (addedFilenames.length > 0) {
        // 이미지 추가된 경우에만 업데이트
        if (selectedFilenames.length > 0) {
            imageNamesInput.value += "," + addedFilenames.join(',');
        } else {
            imageNamesInput.value = addedFilenames.join(',');
        }
    }

    return true;
}
</script>
<script>
$('.file_div').on('change', 'input[name=save]', function() {
    let div1 = '<label for="file-input">사진 추가 :&nbsp</label><input type="file" id="file-input" name="save" /> <br />';
    $('.file_div').append(div1);
});
</script>

</body>
</html>