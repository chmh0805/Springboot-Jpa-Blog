package com.hyuk.blog.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration // IoC 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// IoC 등록만 하면 AuthenticationManager가 BCrypt로 검증해준다.
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/user/**", "/post/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") // 인증필요(권한을 확인한다.)
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll() // 나머지는 다 접근 허가
			.and()
			.formLogin() //x-www-form-urlencoded
			.loginPage("/loginForm")
			.loginProcessingUrl("/login") // 1. /login으로 Post요청이 오면 SpringSecurity가 낚아챈다.
//			.successHandler(new AuthenticationSuccessHandler() {
//				@Override
//				public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//						Authentication authentication) throws IOException, ServletException {
//					response.sendRedirect("/");
//				}
//			});
			.defaultSuccessUrl("/"); // 목적지 url이 없이 로그인에 성공했을 때
	}
}
