package com.hyuk.blog.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyuk.blog.config.auth.PrincipalDetails;
import com.hyuk.blog.domain.post.Post;
import com.hyuk.blog.service.PostService;
import com.hyuk.blog.web.dto.CommonRespDto;
import com.hyuk.blog.web.post.dto.PostSaveReqDto;
import com.hyuk.blog.web.post.dto.PostUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {

	private final PostService postService;
	
	@GetMapping("/")
	public String findAll(Model model, 
			@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 3) Pageable pageable,
			@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam(value = "keyword", defaultValue = "") String keyword) {
		if (keyword.equals("") || keyword == null) {
			Page<Post> posts = postService.전체찾기(pageable);
			model.addAttribute("posts", posts);
			return "post/list";			
		} else {
			Page<Post> posts = postService.검색하기(keyword, pageable);
			model.addAttribute("posts", posts);
			return "post/list";
		}
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
	
	@GetMapping("/post/{id}")
	public String detail(@PathVariable int id, Model model) {
		Post postEntity = postService.상세보기(id);
		model.addAttribute("post", postEntity);
		return "post/detail";
	}
	
	@GetMapping("/post/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		Post postEntity = postService.상세보기(id);
		model.addAttribute("post", postEntity);
		return "post/updateForm";
	}
	
	@PutMapping("/post/{id}")
	public @ResponseBody CommonRespDto<?> update(@PathVariable int id, @RequestBody PostUpdateReqDto postUpdateReqDto) {
		postService.수정하기(id, postUpdateReqDto);
		return new CommonRespDto<>(1, null);
	}
	
	@DeleteMapping("/post/{id}")
	public @ResponseBody CommonRespDto<?> deleteById(@PathVariable int id) {
		postService.삭제하기(id);
		return new CommonRespDto<>(1, null);
	}
}
