package com.hyuk.blog.web.reply.dto;

import com.hyuk.blog.domain.reply.Reply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplySaveReqDto {
	private String content;
	private int postId;
	
	public Reply toEntity() {
		return Reply.builder()
				.content(content)
				.build();
	}
}
