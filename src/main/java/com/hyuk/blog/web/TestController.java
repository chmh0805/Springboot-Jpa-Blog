package com.hyuk.blog.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyuk.blog.config.auth.PrincipalDetails;
import com.hyuk.blog.domain.post.Post;
import com.hyuk.blog.domain.post.PostRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {

	private final PostRepository postRepository;
	
	@GetMapping("/test/post/list")
	public Page<Post> findAll(Model model, 
		@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 3) Pageable pageable,
		@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam(value = "keyword", defaultValue = "") String keyword) {
		Page<Post> posts = postRepository.findByKeyword("내용1", pageable);
		model.addAttribute("posts", posts);
		return posts;
	}
	
}
