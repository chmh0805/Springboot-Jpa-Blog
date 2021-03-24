package com.hyuk.blog.web.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserUpdateReqDto {
	private String username;
	
	@NotBlank(message = "비밀번호를 입력하세요.")
	private String password;
	
	@NotEmpty(message = "이메일을 입력하세요.")
	private String email;
	
	// toEntity 안만드는 이유는 더티체킹 할 것이기 때문!!
}
