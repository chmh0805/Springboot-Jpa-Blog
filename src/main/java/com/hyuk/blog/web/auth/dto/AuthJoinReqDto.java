package com.hyuk.blog.web.auth.dto;

import javax.validation.constraints.NotEmpty;

import com.hyuk.blog.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Valid 나중에 처리하자.

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthJoinReqDto {
	@NotEmpty(message = "아이디를 입력해주세요")
	private String username;
	
	@NotEmpty(message = "비밀번호를 입력해주세요")
	private String password;
	
	@NotEmpty(message = "이메일을 입력해주세요")
	private String email;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.build();
	}
}
