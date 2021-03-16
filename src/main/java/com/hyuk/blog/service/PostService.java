package com.hyuk.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyuk.blog.domain.post.Post;
import com.hyuk.blog.domain.post.PostRepository;
import com.hyuk.blog.web.post.dto.PostUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

	private final PostRepository postRepository;

	@Transactional(readOnly = true)
	public Page<Post> 전체찾기(Pageable pageable) {
		return postRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Page<Post> 검색하기(String keyword, Pageable pageable) {
		return postRepository.findByKeyword(keyword, pageable);
	}

	@Transactional
	public Post 글쓰기(Post post) {
		return postRepository.save(post);
	}
	
	@Transactional(readOnly = true)
	public Post 상세보기(int id) {
		return postRepository.findById(id).get();
	}
	
	@Transactional
	public void 수정하기(int id, PostUpdateReqDto postUpdateReqDto) {
		Post postEntity = postRepository.findById(id).get();
		
		postEntity.setTitle(postUpdateReqDto.getTitle());
		postEntity.setContent(postUpdateReqDto.getContent());
	}
	
	@Transactional
	public void 삭제하기(int id) {
		postRepository.deleteById(id);
	}
}
