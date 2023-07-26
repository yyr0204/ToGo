<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>FAQ 게시판</title>
    <!-- Bootstrap JS (and other scripts) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- Your custom JavaScript files (if any) -->
    <!-- Your custom CSS (styles.css) -->
    <link href="${pageContext.request.contextPath}/resources/static/song/css/styles.css" rel="stylesheet" />
    
    <!-- Bootstrap JS (and other scripts) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>
	<div>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
		<div>
			<h3>자주 묻는 질문</h3>
			<c:choose>
				<c:when test="${(adminId != null) && (level == '3')}">
					<a href="${pageContext.request.contextPath}/board/faqWriteForm">
						<button  style="float:right;margin-bottom:20px;">FAQ 등록</button>
					</a>
				</c:when>
				<c:otherwise>
<%-- 					<a href="${pageContext.request.contextPath}/board/qnaWriteForm"> --%>
<!-- 						<button  style="float:right;margin-left:10;">1:1 문의내역</button> -->
<!-- 					</a> -->
					<a href="${pageContext.request.contextPath}/board/qnaWriteForm">
						<button style="float:right;margin-bottom:20px;">1:1 문의하기</button>
					</a>
				</c:otherwise>
			</c:choose>
			
			<div class="accordion" id="accordionExample">
				<c:forEach items="${faqList}" var="faqDTO">
					<div class="accordion-item">
						<h2 class="accordion-header" id="heading${faqDTO.faq_no}">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse"
								data-bs-target="#collapse${faqDTO.faq_no}" aria-expanded="false"
								aria-controls="collapse${faqDTO.faq_no}"><strong>
								${faqDTO.faq_title}</strong></button>
						</h2>
						<div id="collapse${faqDTO.faq_no}"
							class="accordion-collapse collapse"
							aria-labelledby="heading${faqDTO.faq_no}"
							data-bs-parent="#accordionExample">
							<div class="accordion-body">
								<c:if test="${not empty faqDTO.faq_content}">
									<p>${faqDTO.faq_content}</p>
								</c:if>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>