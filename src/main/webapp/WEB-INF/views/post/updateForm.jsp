<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<div class="container">
	<form> 
		<div class="form-group">
			<input type="text" class="form-control" placeholder="Enter Title" id="title" value="${post.title}"/>
		</div>
		<div class="form-group">
			<textarea rows="" cols="5" class="form-control" id="content" placeholder="Enter Content" >
				${post.content}
			</textarea>
		</div>
		<div class="form-group form-check"></div>
		<button id="btn-update" class="btn btn-primary" value="${post.id}">수정완료</button>
	</form>
</div>

<script>
	$('#content').summernote({
	  placeholder: 'Enter Content',
	  tabsize: 2,
	  height: 300
	});
	
	$("#btn-update").on("click", (e) => {
		e.preventDefault();
		
		let id = e.currentTarget.value;
		let data = {
			"title": $("#title").val(),
			"content": $("#content").val()
		};
		
		
		$.ajax({
			type: "PUT",
			url: "/post/" + id,
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			dataType: "json"
		}).done((res) => {
			if (res.statusCode === 1) {
				alert("수정 완료");
				location.href = "/post/" + id;
			} else {
				alert(res.data);
			}
		});
	});
</script>

<%@include file="../layout/footer.jsp"%>