<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div style="min-height: 400px; margin-top: 100px; display: flex; justify-content: center;">
	<div style="width: 500px;">
		<h1>로그인 페이지</h1>
		<hr />
		<form action="/login" method="POST">
			<input style="width: 100%; margin-bottom: 10px; padding-left: 10px;" type="text" placeholder="Username" name="username" />
			<br />
			<input style="width: 100%; margin-bottom: 10px; padding-left: 10px;" type="password" placeholder="Password" name="password" />
			<br />
			<button class="btn btn-outline-primary" style="width: 100%; margin-bottom: 10px;">로그인</button>
			<br />
		</form>
		<div style="display: flex; align-items: center; margin-bottom: 10px;">
		<span><b>소셜 로그인도 가능합니다.</b></span>
		<a style="width: 30px; height: 30px; margin-left: 20px; margin-right: 20px;" href="/oauth2/authorization/google">
			<img style="width: 100%;" src="/image/google_login.png" />
		</a>
		<a style="width: 30px; height: 30px; margin-right: 20px;" href="/oauth2/authorization/facebook">
			<img style="width: 100%;" src="/image/facebook_login.png" />
		</a>
		<a style="width: 30px; height: 30px; margin-right: 20px;" href="/oauth2/authorization/naver">
			<img style="width: 100%;" src="/image/naver_login.png" />
		</a>
		<a style="width: 30px; height: 30px; margin-right: 20px;" href="/oauth2/authorization/kakao">
			<img style="width: 100%;" src="/image/kakao_login.png" />
		</a>
		</div>
		<span>아직 회원가입이 안되셨나요?</span>
		<a href="/joinForm">회원가입</a>
	</div>
</div>

<%@ include file="../layout/footer.jsp"%>