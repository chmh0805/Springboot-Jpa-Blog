<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<div class="container">
	<div>
		<input type="hidden" id="postId" value="${post.id}" />
		<button class="btn btn-secondary" onclick="history.go(-1);">뒤로가기</button>
		<c:if test="${post.user.id == principal.user.id}">
			<a href="/post/${post.id}/updateForm" class="btn btn-warning">수정</a>
			<button id="btn-delete" class="btn btn-danger">삭제</button>
		</c:if>
		<br/><br/>
		<div class="d-flex justify-content-between">
			<h5>
				글 번호 : ${post.id}
			</h5>
			<h5>
				작성자 : ${post.user.username}
			</h5>
		</div>
		<hr/>
		<div>
			<h3>${post.title}</h3>
		</div>
		<hr/>
		<div>
			<h3>${post.content}</h3>
		</div>
	</div>
	
		<!-- 댓글 시작 -->
	<div class="card">
	    <form>
			<div class="card-body">
				<textarea id="reply-content" class="form-control" rows="1"></textarea>
			</div>
			<div class="card-footer">
				<button type="button" onclick="postReply(${principal.user.id}, ${post.id})" class="btn btn-primary">등록</button>
			</div>
		</form>
	</div>
	<br />
	
	<div class="card">
		<div class="card-header">댓글 리스트</div>
		<ul id="reply-box" class="list-group">
			<c:forEach items="${post.replyList}" var="reply">
				<li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
					<div>${reply.content}</div>
					<div class="d-flex">
						<div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
						<c:if test="${principal.user.id == reply.user.id}">
							<button onClick="deleteReply(${reply.id})" class="badge">삭제</button>
						</c:if>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<!-- 댓글 끝 -->
</div>

<script>
	$("#btn-delete").on("click", (e) => {
		let id = $("#postId").value;

		$.ajax({
			type: "DELETE",
			url: "/post/" + id,
			dataType: "json"
		}).done(res => {
			if (res.statusCode == 1) {
				alert('글 삭제 완료');
				history.back();
			} else {
				alert('글 삭제 실패');
			}
		});
	});
	
	function deleteReply(replyId) {
		$.ajax({
			type: "DELETE",
			url: "/reply/" + replyId,
			dataType: "json"
		}).done(res => {
			if (res.statusCode == 1) {
				alert('댓글 삭제 완료');
				$("#reply-"+replyId).remove();
			} else {
				alert('댓글 삭제 실패');
			}
		});
	};
	
	function postReply(userId, postId) {
		let content = $("#reply-content").val();
		let data = {
			"content": content,
			"postId": postId
		};
		
		if (content === "" || content === null || userId === 0 || userId === null) {
			alert("내용을 입력하세요.");
			return;
		}
		
		$.ajax({
			type: "POST",
			url: "/reply",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			dataType: "json"
		}).done((res) => {
			if (res.statusCode === 1) {
				alert("댓글 등록 완료");
				location.reload();
			} else {
				alert("댓글 등록 실패");
			}
		});
	}
</script>

<%@include file="../layout/footer.jsp"%>