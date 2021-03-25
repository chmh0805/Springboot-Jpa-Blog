<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div style="min-height: 400px; margin-top: 100px; display: flex; justify-content: center;">
	<div style="width: 500px;">
		<h1>회원가입</h1>
		<hr />
		<form action="/join" method="POST">
			<input style="width: 100%; margin-top: 10px; margin-bottom: 10px; padding-left: 10px;" type="text" placeholder="Username" name="username" />
			<br />
			<input style="width: 100%; margin-bottom: 10px; padding-left: 10px;" type="password" placeholder="Password" name="password" />
			<br />
			<input style="width: 100%; margin-bottom: 10px; padding-left: 10px;" type="email" placeholder="Email" name="email" />
			<br />
			<button class="btn btn-outline-primary" style="width: 100%; margin-bottom: 10px;">회원가입</button>
		</form>
		이미 회원가입이 되셨나요? <a href="/loginForm">로그인</a>
	</div>
</div>

<%@ include file="../layout/footer.jsp"%>