package com.hyuk.blog.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hyuk.blog.config.auth.PrincipalDetails;
import com.hyuk.blog.domain.post.Post;
import com.hyuk.blog.domain.post.dto.PostSaveReqDto;
import com.hyuk.blog.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {

	private final PostService postService;
	
	@GetMapping("/")
	public String findAll(Model model, 
			@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 3) Pageable pageable) {
		Page<Post> posts = postService.전체찾기(pageable);
		model.addAttribute("posts", posts);
		return "post/list";
	}

	@GetMapping("/post/saveForm")
	public String saveForm() {
		return "post/saveForm";
	}

	@PostMapping("/post")
	public String save(PostSaveReqDto postSaveReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		Post post = postSaveReqDto.toEntity();
		post.setUser(principalDetails.getUser());
		Post postEntity = postService.글쓰기(post);
		if (postEntity == null) {
			return "post/saveForm";
		} else {
			return "redirect:/";
		}
	}
}