<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Question Page</title>
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link
	href="${pageContext.request.contextPath}/resources/static/song/css/styles.css"
	rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<style>
/* Add your custom CSS styles here */
body {
	font-family: Arial, sans-serif;
	margin: 20px;
	background-color: #f2f2f2;
}

h1 {
	text-align: center;
	margin-bottom: 20px;
}

form {
	max-width: 600px;
	margin: 0 auto;
	background-color: #fff;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

fieldset {
	margin-bottom: 20px;
	border: 1px solid #ccc;
	padding: 10px;
	border-radius: 5px;
}

legend {
	font-weight: bold;
	margin-bottom: 10px;
}

label {
	display: block;
	margin-bottom: 10px;
}

button[type="submit"] {
	background-color: #007bff;
	color: #fff;
	border: none;
	padding: 10px 20px;
	border-radius: 5px;
	cursor: pointer;
}

.result {
	font-weight: bold;
}

.result-link {
	display: block;
	text-align: center;
	margin-top: 20px;
	color: #007bff;
}

.result-link:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<h1>당신의 여행성향은?</h1>
	<form action="/ToGo/question" method="post">
		<input type="hidden" name="questionId" value="${questionId}">

		<fieldset data-question-id="1">
			<legend>Question 1</legend>
			<p>로또에 당첨되면 모셔두지않고 바로 여행간다.</p>
			<label><input type="radio" name="answer" value="yes">
				Yes</label> <label><input type="radio" name="answer" value="no">
				No</label>
			<button type="submit">Submit</button>
		</fieldset>

		<fieldset data-question-id="2-1" style="display: none;">
			<legend>Question 2-1</legend>
			<p>오래 걷는 것을 별로 좋아하지 않는다.</p>
			<label><input type="radio" name="answer" value="yes">
				Yes</label> <label><input type="radio" name="answer" value="no">
				No</label>
			<button type="submit">Submit</button>
		</fieldset>

		<fieldset data-question-id="2-2" style="display: none;">
			<legend>Question 2-2</legend>
			<p>맘에 안드는 여행 계획에는 바로 이의를 제기한다.</p>
			<label><input type="radio" name="answer" value="yes">
				Yes</label> <label><input type="radio" name="answer" value="no">
				No</label>
			<button type="submit">Submit</button>
		</fieldset>

		<fieldset data-question-id="3-1" style="display: none;">
			<legend>Question 3-1</legend>
			<p>유명 맛집은 꼭 찾아가 보는 편이다.</p>
			<label><input type="radio" name="answer" value="yes">
				Yes</label> <label><input type="radio" name="answer" value="no">
				No</label>
			<button type="submit">Submit</button>
		</fieldset>

		<fieldset data-question-id="3-2" style="display: none;">
			<legend>Question 3-2</legend>
			<p>인터넷이 없으면 3시간도 버티기 힘들다.</p>
			<label><input type="radio" name="answer" value="yes">
				Yes</label> <label><input type="radio" name="answer" value="no">
				No</label>
			<button type="submit">Submit</button>
		</fieldset>

		<fieldset data-question-id="3-3" style="display: none;">
			<legend>Question 3-3</legend>
			<p>서핑이나 수영보다 일광욕이 좋다.</p>
			<label><input type="radio" name="answer" value="yes">
				Yes</label> <label><input type="radio" name="answer" value="no">
				No</label>
			<button type="submit">Submit</button>
		</fieldset>

		<fieldset data-question-id="4-1" style="display: none;">
			<legend>Question 4-1</legend>
			<p>조용하고 오붓한 여행을 하고 싶다.</p>
			<label><input type="radio" name="answer" value="yes">
				Yes</label> <label><input type="radio" name="answer" value="no">
				No</label>
			<button type="submit">Submit</button>
		</fieldset>

		<fieldset data-question-id="4-2" style="display: none;">
			<legend>Question 4-2</legend>
			<p>예쁜 것을 보면 인증샷을 남기고 싶다.</p>
			<label><input type="radio" name="answer" value="yes">
				Yes</label> <label><input type="radio" name="answer" value="no">
				No</label>
			<button type="submit">Submit</button>
		</fieldset>

		<fieldset data-question-id="4-3" style="display: none;">
			<legend>Question 4-3</legend>
			<p>낯선 곳보다 익숙한 곳이 좋다.</p>
			<label><input type="radio" name="answer" value="yes">
				Yes</label> <label><input type="radio" name="answer" value="no">
				No</label>
			<button type="submit">Submit</button>
		</fieldset>

		<fieldset data-question-id="4-4" style="display: none;">
			<legend>Question 4-4</legend>
			<p>무인도에서도 살아 남을 자신이 있다.</p>
			<label><input type="radio" name="answer" value="yes">
				Yes</label> <label><input type="radio" name="answer" value="no">
				No</label>
			<button type="submit">Submit</button>
		</fieldset>

		<fieldset data-question-id="5-1" style="display: none;">
			<legend>Question 5-1</legend>
			<p>산티아고 순례길보다 한적한 크루즈 여행이 좋다.</p>
			<label><input type="radio" name="answer" value="yes">
				Yes</label> <label><input type="radio" name="answer" value="no">
				No</label>
			<button type="submit">Submit</button>
		</fieldset>

		<fieldset data-question-id="5-2" style="display: none;">
			<legend>Question 5-2</legend>
			<p>미술관보다 야시장이 좋다.</p>
			<label><input type="radio" name="answer" value="yes">
				Yes</label> <label><input type="radio" name="answer" value="no">
				No</label>
			<button type="submit">Submit</button>
		</fieldset>

		<fieldset data-result-id="A" style="display: none;">
			<legend>로맨틱 여행자 형</legend>
			<p class="result">당신은 여행의 철학을 가지고 있습니다.</p>
			<a href="/ToGo/login/loginMain">확인</a>
		</fieldset>

		<fieldset data-result-id="B" style="display: none;">
			<legend>느긋한 휴양자 형</legend>
			<p class="result">당신은 음식과 문화를 즐기는 여행자입니다.</p>
			<a href="/ToGo/login/loginMain">확인</a>
		</fieldset>

		<fieldset data-result-id="C" style="display: none;">
			<legend>열혈 탐험가 형</legend>
			<p class="result">당신은 자유로운 영혼의 여행자입니다.</p>
			<a href="/ToGo/login/loginMain">확인</a>
		</fieldset>

	</form>
</body>
<script>
	$(document)
			.ready(
					function() {
						var id = $
						{
							sessionScope.memId
						}
						;

						$("form")
								.on(
										"submit",
										function(event) {
											event.preventDefault();
											var form = $(this);
											var formData = form.serialize();
											formData += "&id=" + id;

											var questionFieldsets = form
													.find("fieldset[data-question-id]");

											// 모든 질문 fieldset을 순회하며 응답이 선택되었는지 확인합니다.
											for (var i = 0; i < questionFieldsets.length; i++) {
												var currentQuestionFieldset = $(questionFieldsets[i]);
												var currentQuestionId = currentQuestionFieldset
														.data("question-id");
												var currentQuestionAnswer = currentQuestionFieldset
														.find("input[name='answer'][value!='']:checked").length;

												if (currentQuestionFieldset
														.is(":visible")
														&& currentQuestionAnswer === 0) {
													alert("질문 "
															+ currentQuestionId
															+ "에 응답을 선택해주세요."); // 알림 창 띄우기
													return;
												}
											}

											// 모든 질문에 응답이 선택되었다면 폼을 제출합니다.
											$
													.ajax({
														type : "POST",
														url : form
																.attr("action"),
														data : formData,
														success : function(
																response) {
															var nextQuestionId = response.nextQuestionId;
															var result = response.result;
															if (nextQuestionId) {
																form
																		.find(
																				"fieldset")
																		.hide();
																form
																		.find(
																				"fieldset[data-question-id='"
																						+ nextQuestionId
																						+ "']")
																		.show();
																form
																		.find(
																				"input[name='questionId']")
																		.val(
																				nextQuestionId);
															} else if (result) {
																form
																		.find(
																				"fieldset")
																		.hide();
																form
																		.find(
																				"fieldset[data-result-id='"
																						+ result
																						+ "']")
																		.show();

																// 서버로 결과를 저장하는 요청을 보냅니다.
																$
																		.ajax({
																			type : "POST",
																			url : "/ToGo/save-result",
																			data : {
																				result : result,
																				id : id
																			}, // 결과 데이터와 사용자 ID를 서버로 전송
																			success : function(
																					response) {
																				console
																						.log("succ");
																			},
																			error : function() {
																				console
																						.error("An error occurred while saving the result.");
																			}
																		});
															}
														},
														error : function() {
															console
																	.error("An error occurred while processing the request.");
														}
													});
										});
					});
</script>
</html>