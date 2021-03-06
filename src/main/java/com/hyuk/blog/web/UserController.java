package com.hyuk.blog.web;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyuk.blog.config.auth.PrincipalDetails;
import com.hyuk.blog.domain.user.User;
import com.hyuk.blog.service.UserService;
import com.hyuk.blog.web.dto.CommonRespDto;
import com.hyuk.blog.web.user.dto.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	// 로그인, 로그아웃, 회원가입, 회원정보 변경 => AuthController
	private final UserService userService;
	
	@GetMapping("/user")
	public @ResponseBody String findAll(@AuthenticationPrincipal PrincipalDetails principalDetails) { // @Controller + @ResponseBody = @RestController
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//		System.out.println(principalDetails);
		System.out.println(principalDetails.getUsername());
		return "User";
	}

	@GetMapping("/user/{id}")
	public String updateForm(@PathVariable Integer id, Model model) {
		model.addAttribute("id", id);
		return "user/updateForm";
	}

	@PutMapping("/user/{id}")
	public @ResponseBody CommonRespDto<?> update(@PathVariable Integer id, @Valid @RequestBody UserUpdateReqDto userUpdateReqDto,
			@AuthenticationPrincipal PrincipalDetails principalDetails, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String message = "";
			for (FieldError error : bindingResult.getFieldErrors()) {
				System.out.println(error.getField() + " : " + error.getDefaultMessage());
				message = message + error.getDefaultMessage() + "\n";
			}
			return new CommonRespDto<>(-1, message);
		}
		
		if (principalDetails.getUser().getId() == id) {
			User userEntity = userService.회원정보수정(id, userUpdateReqDto);			
			// 세션값 변경하기
			principalDetails.setUser(userEntity);
			userEntity.setPassword(null);
		}
//		Authentication authentication =
//				new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword());
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new CommonRespDto<>(1, null);
	}
}
