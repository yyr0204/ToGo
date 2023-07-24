<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="sub" items="${weather}" varStatus="vs">
    <h1> ${vs.count+2} 일후 : ${sub} </h1>
    <br/>
</c:forEach>
