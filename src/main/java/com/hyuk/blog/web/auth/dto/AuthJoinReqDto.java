package com.hyuk.blog.web.auth.dto;

import com.hyuk.blog.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Valid 나중에 처리하자.

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthJoinReqDto {
	private String username;
	private String password;
	private String email;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.build();
	}
}
