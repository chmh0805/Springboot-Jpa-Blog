package com.hyuk.blog.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyuk.blog.config.auth.PrincipalDetails;
import com.hyuk.blog.domain.reply.Reply;
import com.hyuk.blog.domain.user.User;
import com.hyuk.blog.service.ReplyService;
import com.hyuk.blog.web.dto.CommonRespDto;
import com.hyuk.blog.web.reply.dto.ReplySaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReplyController {

	private final ReplyService replyService;

	@DeleteMapping("/reply/{id}")
	public CommonRespDto<?> deleteById(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		// 모든 컨트롤러에 삭제하기, 수정하기는 무조건 동일인물이 로그인했는지 확인!!
		return new CommonRespDto<>(replyService.삭제하기(id, principalDetails.getUser().getId()), null);
	}
	
	@PostMapping("/reply")
	public CommonRespDto<?> save(@RequestBody ReplySaveReqDto replySaveReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		User userEntity = principalDetails.getUser();
		Reply reply = replySaveReqDto.toEntity();
		reply.setUser(userEntity);

		replyService.댓글쓰기(reply, replySaveReqDto.getPostId());
		return new CommonRespDto<>(1, null);
	}

}
