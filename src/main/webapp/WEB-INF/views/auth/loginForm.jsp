<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그</title>
</head>
<body>
	<div class="d-flex justify-content-center">
		<h1>로그인 페이지</h1>
		<hr/>
		<form action="/login" method="POST">
			<input type="text" placeholder="Username" name="username" /> <br/>
			<input type="password" placeholder="Password" name="password" /> <br/>
			<button>로그인</button><br/>
		</form>
		<a href="/oauth2/authorization/google">구글 로그인</a><br/>
		<a href="/oauth2/authorization/facebook">페이스북 로그인</a><br/>
		<a href="/oauth2/authorization/naver">네이버 로그인</a><br/>
		<a href="/oauth2/authorization/kakao">카카오 로그인</a><br/>
		아직 회원가입이 안되셨나요? <a href="/joinForm">회원가입</a>
	</div>
</body>
</html>