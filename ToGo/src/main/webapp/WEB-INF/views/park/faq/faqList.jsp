<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 게시판</title>
</head>
<body>
	<div>
		<div>
			<h3>자주 묻는 질문</h3>
			<c:choose>
				<c:when test="${sessionScope.memId == 'admin'}">
					<a href="${pageContext.request.contextPath}/faq/faqWriteForm">
						<button>FAQ 등록</button>
					</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/qna/qnaWriteForm">
						<button>1:1 문의내역</button>
					</a>
					<a href="${pageContext.request.contextPath}/qna/qnaWriteForm">
						<button>1:1 문의하기</button>
					</a>
				</c:otherwise>
			</c:choose>
			
			<div>
			    <c:forEach items="${faqList}" var="faqDTO">
			        <input type="radio" name="accordion" id="answer${faqDTO.faq_no}">
			        <label for="answer${faqDTO.faq_no}">
			            ${faqDTO.faq_title}
			            <i class="fas fa-angle-down"></i>
			        </label>
			        <div>
			            <c:if test="${not empty faqDTO.faq_content}">
			                <p>${faqDTO.faq_content}</p>
			            </c:if>
			        </div>
			    </c:forEach>
			</div>
			
		</div>
	</div>
</body>
</html>