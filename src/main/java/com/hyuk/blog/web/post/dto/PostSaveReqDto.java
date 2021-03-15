package com.hyuk.blog.web.post.dto;

import com.hyuk.blog.domain.post.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSaveReqDto {
	private String title;
	private String content;
	
	public Post toEntity() {
		return Post.builder()
				.title(title)
				.content(content)
				.build();
	}
}
