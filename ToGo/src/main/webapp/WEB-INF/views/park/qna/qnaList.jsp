<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
.container {
   max-width: 800px;
   margin: 0 auto;
}

.search-box {
   margin-bottom: 20px;
   padding: 10px;
   background-color: #f2f2f2;
   border-radius: 5px;
   display: flex;
   justify-content: space-between;
}

.search-box select, .search-box input[type="text"], .search-box button[type="submit"]
   {
   padding: 5px 10px;
   border: 1px solid #ccc;
   border-radius: 5px;
}

.search-box select {
   min-width: 80px;
}

.search-box input[type="text"] {
   flex: 1;
   margin-right: 10px;
}

.search-box button[type="submit"] {
   background-color: #007bff;
   color: #fff;
   cursor: pointer;
}

.search-box button[type="submit"]:hover {
   background-color: #0056b3;
}

.board-table {
   width: 100%;
   border-collapse: collapse;
   margin-bottom: 20px;
}

.board-table th, .board-table td {
   border: 1px solid #ccc;
   padding: 8px;
   text-align: left;
}

.board-table th {
   background-color: #f2f2f2;
}

.board-table .left {
   text-align: left;
}

.board-table .image {
   width: 15px;
   height: 15px;
   vertical-align: middle;
}
.write-link a:hover {
    background-color: #007bff; 
    color: #fff;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
   <div class="container">
      <form method="post" action="qnaList" id="list">
         <input type="hidden" name="curPage" value="1" />
         <div class="search-box">
            <div>
               <select name="option">
                  <option value="all">전체</option>
                  <option value="title">제목</option>
                  <option value="content">내용</option>
                  <option value="id">작성자</option>
               </select> <input name="keyword" type="text" placeholder="검색어를 입력하세요..."
                  aria-describedby="button-search" />
               <button type="submit">
                  <i>검색</i>
               </button>
            </div>
         </div>
         <c:if test="${memId != null}">
            <a href="/ToGo/board/qnaWriteForm">글쓰기</a>
         </c:if>
      </form>
      <h3>총 게시글 수 : ${pr.total}</h3>
      <table class="board-table">
         <tr>
            <th class="w-px60">번호</th>
            <th>제목</th>
            <th class="w-px100">작성자</th>
            <th class="w-px120">작성일자</th>
            <th class="w-px60">조회수</th>
         </tr>
         <c:forEach items="${boardList }" var="dto">
            <tr>
               <td>${dto.num}</td>
               <td class="left"><c:forEach var="i" begin="1"
                     end="${dto.indent}">
                            ${i eq dto.indent ? "<img src='/ToGo/resources/static/img/re.png' class='image'/>" : "&nbsp;&nbsp;"}
                        </c:forEach> <a href="/ToGo/board/qnaDetail?num=${dto.num}">${dto.title}</a>
               </td>
               <td>${dto.writer}</td>
               <td><fmt:formatDate value="${dto.writedate}"
                     pattern="yyyy/MM/dd" /></td>
               <td>${dto.readcnt}</td>
            </tr>
         </c:forEach>
      </table>
   </div>
   <!-- Pagination-->
   <nav aria-label="Pagination">
      <hr class="my-0" />
      <ul class="pagination justify-content-center my-4">
         <c:if test="${pr.startPage > pr.pagePerBlock}">
            <li class="page-item"><a class="page-link"
               href="/ToGo/board/faqList?pageNum=1&option=${option}&keyword=${keyword}">처음<i
                  class="fs-3 bi bi-caret-left-fill"></i>
            </a></li>
            <li class="page-item"><a class="page-link"
               href="/ToGo/board/faqList?pageNum=${pr.startPage - 1}&option=${option}&keyword=${keyword}">이전<i
                  class="fs-3 bi bi-caret-left"></i>
            </a></li>
         </c:if>
         <c:forEach begin="${pr.startPage}" end="${pr.endPage}" var="pNum">
            <li class="page-item ${pr.page == pNum ? 'active' : ''}"
               aria-current="${pr.page == pNum ? 'page' : ''}"><a
               class="page-link"
               href="/ToGo/board/faqList?pageNum=${pNum}&option=${option}&keyword=${keyword}"
               name="pageNum">${pNum}</a></li>
         </c:forEach>
         <c:if test="${pr.endPage < pr.totalPage}">
            <li class="page-item"><a class="page-link"
               href="/ToGo/board/faqList?pageNum=${pr.endPage + 1}&option=${option}&keyword=${keyword}">다음<i
                  class="fs-3 bi bi-caret-right"></i>
            </a></li>
            <li class="page-item"><a class="page-link"
               href="/ToGo/board/faqList?pageNum=${pr.totalPage}&option=${option}&keyword=${keyword}">맨끝<i
                  class="fs-3 bi bi-caret-right-fill"></i>
            </a></li>
         </c:if>
      </ul>
   </nav>
</body>
<style>
.image {
   width: 12px;
   height: 12px;
}
</style>
</html>