<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp" %>

<body>
	<h1>회원정보 수정 페이지</h1>
	<hr/>
	<form>
		<input type="hidden" id="id" value="${id}" />
		<input type="text" value="${principal.user.username}" placeholder="Username" id="username" readonly="readonly"/> <br/>
		<input type="password" value="" placeholder="Password" id="password" /> <br/>
		<input type="email" value="${principal.user.email}" placeholder="Email" id="email" /> <br/>
		<button id="btn-update">회원정보 수정</button>
	</form>

<script>
	$("#btn-update").on("click", (e) => {
		e.preventDefault();
		let data = {
			"username": $("#username").val(),
			"password": $("#password").val(),
			"email": $("#email").val()
		};
		
		let id = $("#id").val();

		if (data.password === '') {
			alert('비밀번호를 입력하세요.');
			return;
		}
		if (data.email === '') {
			alert('이메일을 입력하세요.');
			return;
		}
		
		$.ajax({
			method: "PUT",
			url: "/user/"+id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done((res) => {
			console.log(res);
			if (res.statusCode === 1) {
				alert('수정 성공');
				location.href = "/";
			} else {
				alert(res.data);
			}
		});
	});
</script>
<%@include file="../layout/footer.jsp" %>