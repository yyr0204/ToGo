<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 인덱스</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<style>
.container {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 70%; /* 전체 묶음의 넓이 조절 */
    padding: 20px; /* 내용과 컨테이너 간의 간격 */
}
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
}
header, footer {
    background-color: #333;
    color: #fff;
    text-align: center;
    padding: 10px 0;
    width: 30%;
    margin: 0 auto; /* 가운데 정렬을 위한 추가 스타일 */
}
main {
    padding: 20px;
    text-align: center;
}

/* 팀원 명 목록 스타일 */
.team-section h2 {
    font-size: 1.5rem;
    margin-bottom: 10px;
}

.team-section ul {
    list-style: none;
    padding: 0;
}

.team-section li {
    margin-bottom: 5px;
}

/* 주제 스타일 */
.topic-section h2 {
    font-size: 1.5rem;
    margin-bottom: 10px;
}

/* 홈페이지 이동 버튼 스타일 */
footer a {
    color: #fff;
    padding: 5px 10px;
    border: 1px solid #fff;
    border-radius: 5px;
}

footer a:hover {
    background-color: #fff;
    color: #333;
}
button[disabled] {
	background-color: #fff;
	color: #000;
}
</style> 
</head>
<body>
<div class="container">
    <header>
    	<h1>ToGo</h1>
    </header>
    <main>
        <section class="team-section">
            <h2><i class="fa-solid fa-people-group"> 팀원 명</i></h2>
            <ul>
                <li><i class="fa-solid fa-user">송제현 - 팀장</i></li>
                <li><i class="fa-solid fa-user">윤상구 - 팀원</i></li>
                <li><i class="fa-solid fa-user">김성수 - 팀원</i></li>
                <li><i class="fa-solid fa-user">박지훈 - 팀원</i></li>
                <!-- 팀원 추가 -->
            </ul>
        </section>
        <section class="topic-section">
            <h2>SNS분석을 기반으로한 상권 분석 플랫폼 서비스개발</h2>
        </section>
    </main>
    <footer onclick="window.location.href='/ToGo/trip/main';">
        <div>
            <button disabled id="home-button">홈페이지 이동</button>
        </div>
    </footer>
</div>
</body>
</html>
