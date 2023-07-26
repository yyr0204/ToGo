<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    body{
        height: 400px;
        width: 400px;
        position: relative;
    }
    .mainContainer {
        margin: 0 auto;
        background-color: ghostwhite;
        display: grid;
        grid-template-rows: 1fr 1fr;
    }

    .profile-top{
        display: grid;
        grid-template-columns: 1fr 4fr;
    }
</style>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="mainContainer">
    <div class="profile-top item">
        <div><img src="${profile.PROFILE_IMG}" style="height:20vh;width: 20vh"></div>
        <div><span class="user_name">${profile.k_NICK}</span></div>
    </div>
    <div class="profile-under item">
        <div><span>생일:${profile.k_BIRTH}</span></div>
        <div><span>이메일:${profile.k_EMAIL}</span></div>
        <div><span>성별:${profile.k_GENDER}</span></div>
    </div>
</div>
</body>
</html>
