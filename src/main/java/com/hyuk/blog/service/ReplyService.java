package com.hyuk.blog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyuk.blog.domain.post.Post;
import com.hyuk.blog.domain.post.PostRepository;
import com.hyuk.blog.domain.reply.Reply;
import com.hyuk.blog.domain.reply.ReplyRepository;
import com.hyuk.blog.exception.myexception.NotExistException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {
	private final ReplyRepository replyRepository;
	private final PostRepository postRepository;
	
	@Transactional
	public int 삭제하기(int id, int userId) {
		Reply replyEntity = replyRepository.findById(id).orElseThrow(() -> {throw new NotExistException("존재하지 않는 댓글입니다.");});
		if (replyEntity.getUser().getId() == userId) {
			replyRepository.deleteById(id);
			return 1;
		} else {
			return -1;
		}
	}
	
	@Transactional
	public void 댓글쓰기(Reply reply, int postId) {
		Post postEntity = postRepository.findById(postId).orElseThrow(() -> {throw new NotExistException("존재하지 않는 글입니다.");});
		reply.setPost(postEntity);
		replyRepository.save(reply);
	}
}
