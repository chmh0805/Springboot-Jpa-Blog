package com.hyuk.blog.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hyuk.blog.service.AuthService;
import com.hyuk.blog.web.auth.dto.AuthJoinReqDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	// 주소 : 인증안되었을 때 /user, /post, /loginForm
	@GetMapping("/loginForm")
	public String loginForm() {
		return "auth/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "auth/joinForm";
	}
	
	@PostMapping("/join")
	public String join(@Valid AuthJoinReqDto authJoinReqDto, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			for (FieldError error : bindingResult.getFieldErrors()) {
				System.out.println(error.getField() + " : " + error.getDefaultMessage());
			}
			
			return "redirect:/joinForm";
		}
		
		authService.회원가입(authJoinReqDto.toEntity());
		return "redirect:/loginForm"; // loginForm() 함수 재활용 문법
	}
}
