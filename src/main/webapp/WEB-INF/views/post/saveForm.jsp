<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<div class="container">
	<form action="/post" method="POST">
		<div class="form-group">
			<input type="text" class="form-control" placeholder="Enter Title" name="title" />
		</div>
		<div class="form-group">
			<textarea id="content" rows="" cols="5" class="form-control" name="content" placeholder="Enter Content" ></textarea>
		</div>
		<div class="form-group form-check"></div>
		<button type="submit" class="btn btn-primary">글쓰기 완료</button>
	</form>
</div>

<script>
	$('#content').summernote({
	  placeholder: 'Enter Content',
	  tabsize: 2,
	  height: 300
	});
</script>

<%@include file="../layout/footer.jsp"%>