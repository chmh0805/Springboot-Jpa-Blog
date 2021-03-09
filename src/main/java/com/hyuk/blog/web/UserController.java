package com.hyuk.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String home() {
		return "index"; // WEB-INF/views/home.jsp 파일 찾음
	}
}
