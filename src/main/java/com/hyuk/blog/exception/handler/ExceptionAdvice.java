package com.hyuk.blog.exception.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hyuk.blog.exception.myexception.NotExistException;

@ControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler
	public String notExistPostException(NotExistException e, Model model) {
		model.addAttribute("msg", e.getMessage());
		return "error";
	}
}
