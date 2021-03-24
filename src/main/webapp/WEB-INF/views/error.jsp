<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="layout/header.jsp"%>

<div class="container" style="height: 500px">
	<h1>${msg}</h1>
	<button class="btn btn-secondary" onclick="history.go(-1);">뒤로가기</button>
</div>

<%@include file="layout/footer.jsp"%>