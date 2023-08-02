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
    <!-- HEAD CONTENT -->
   <script src="${pageContext.request.contextPath}/resources/static/js/jquery.js"></script>
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
#file-input {
    display: none; /* 기존의 파일 입력 필드를 숨김 */
}
</style>


</head>
<body>
    <%@ include file="/WEB-INF/views/include/header.jsp" %>
    <!-- Page Content -->
    <div class="container mt-5">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <form name="modifyBoard" method="post" action="/ToGo/board/cmModifyPro">
                    <!-- Post Content -->
                    <article>
	    				<h3>커뮤니티 게시글 수정</h3>
                        <!-- Post Header -->
                        <header class="mb-4">
                            <input type="hidden" name="cm_no" value="${dto.cm_no}" />
                            <!-- 제목 -->
                            <div class="mx-3 mb-2">제목</div>
                            <textarea id="cm_title" name="cm_title" rows="1" class="form-control mb-3" required>${dto.cm_title}</textarea>
                            <!-- 작성자 -->
                            <div class="mx-3 mb-2">작성자</div>
                            <div class="form-control mb-3">${memId}</div>
                            <!-- 이미지들을 미리보기하는 부분 -->
							<div class="text-muted fst-italic mb-3 image-container">
							    <c:set var="imageFilenames" value="${fn:split(dto.filename, ',')}" />
							    <c:forEach var="filename" items="${imageFilenames}">
							        <label>
							            <img src="/ToGo/resources/static/cmImage/${filename.trim()}">
							        </label>
							    </c:forEach>
							</div>
							
							<!-- 이미지 추가 버튼과 파일 선택 input -->
							<div class="d-flex justify-content-center mb-3">
							    <button type="button" class="btn btn-primary" onclick="addImage()">사진 추가</button>
							    <input type="file" id="file-input" name="save" style="display: none;" multiple onchange="addImages(event)" />
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
    
    <footer>
        <!-- FOOTER CONTENT -->
    </footer>
    <script>
// 이미지 추가 함수
function addImage() {
    const fileInput = document.getElementById('file-input');
    fileInput.click();
}

// 이미지 교체
$(document).off('click').on('click','img',function(){
	const imageContainer = $('.image-container')
    const fileInput = document.getElementById('file-input');
    const imageIndex = Array.from($(event.target).parent().children()).indexOf($(event.target));
    fileInput.click();
    fileInput.onchange = function (event) {
        const newFiles = event.target.files;
        for (const file of newFiles) {
            const reader = new FileReader();
            reader.onload = function (e) {
            	let = "<img src='"+e.target.result+"'>"
   /*              const newImage = document.createElement('img');
                newImage.src = e.target.result; */
                newImageFilenames[imageIndex] = file.name;
            };
            reader.readAsDataURL(file);
        }
    };
})

// 이미지 추가 함수
function addImages(event) {
    const imageContainer = document.querySelector('.image-container');
    const files = event.target.files;
    for (const file of files) {
        const reader = new FileReader();
        reader.onload = function (e) {
            const image = document.createElement('img');
            image.src = e.target.result;
            imageContainer.appendChild(image);
            newImageFilenames.push(file.name);
        };
        reader.readAsDataURL(file);
    }
}

// 기존 이미지들의 파일명들을 배열로 가져옵니다.
let name = '${dto.filename}'
let existingImageFilenames = name.split(',');
console.log(existingImageFilenames)

// 추가된 이미지들의 파일명들을 배열에 담습니다.
const newImageFilenames = [];

// 이미지들의 파일명들을 컨트롤러로 전송하는 함수
function saveImages() {
    // 기존 이미지들과 추가된 이미지들의 파일명들을 합칩니다.
    const allImageFilenames = existingImageFilenames.concat(newImageFilenames);

    // 합쳐진 파일명들을 컨트롤러로 전송합니다. (예시: Ajax를 이용하여 POST 요청을 보냄)
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/saveImagesToDatabase", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                console.log("이미지 파일명들이 DB에 저장되었습니다.");
            } else {
                console.error("이미지 파일명들을 저장하는데 실패하였습니다.");
            }
        }
    };
    xhr.send(JSON.stringify(allImageFilenames));
}

// 이미지를 클릭하면 파일 선택 input을 띄우기 위한 함수
/* function changeImage(selectedImage) {
    const fileInput = document.getElementById('file-input');
    const imageIndex = Array.from(selectedImage.parentNode.children).indexOf(selectedImage);
    fileInput.click();
    fileInput.onchange = function (event) {
        const newFiles = event.target.files;
        for (const file of newFiles) {
            const reader = new FileReader();
            reader.onload = function (e) {
                const newImage = document.createElement('img');
                newImage.src = e.target.result;
                imageContainer.replaceChild(newImage, imageContainer.children[imageIndex]);
                newImageFilenames[imageIndex] = file.name;
            };
            reader.readAsDataURL(file);
        }
    };
} */
</script>
</body>
</html>
