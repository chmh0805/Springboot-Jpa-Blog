package com.hyuk.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	
	@GetMapping("/user")
	public @ResponseBody String hello() { // @Controller + @ResponseBody = @RestController
		return "User";
	}
	
	@GetMapping({"", "/"})
	public String home() {
		return "index"; // WEB-INF/views/home.jsp 파일 찾음
	}
}
