<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page_list">
    <c:if test="${page.curBlock gt 1}">
        <a class="page_first" onclick="go_page(1)">처음</a>
        <a class="page_prev" onclick="go_page(${page.beginPage - page.blockPage})">이전</a>
    </c:if>

    <!-- step: 지정하지 않아도 디폴트 1 -->
    <c:forEach var="no" begin="${page.beginPage}" end="${page.endPage}" step="1">
        <c:choose>
            <c:when test="${no eq page.curPage}">
                <span class="page_on">${no}</span>
            </c:when>
            <c:otherwise>
                <a class="page_off" onclick="go_page(${no})">${no}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${page.curBlock lt page.totalBlock}">
        <a class="page_next" onclick="go_page(${page.endPage + 1})">다음</a>
        <a class="page_last" onclick="go_page(${page.totalPage})">마지막</a>
    </c:if>
</div>

<style>
.page_list a {
    cursor: pointer;
}
</style>

<script>
    function go_page(no) {
        document.querySelector('[name=curPage]').value = no;
        document.querySelector('#list').submit();
    }
</script>