package com.hyuk.blog.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyuk.blog.domain.user.User;
import com.hyuk.blog.domain.user.UserRepository;
import com.hyuk.blog.web.user.dto.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public User 회원정보수정(Integer id, UserUpdateReqDto userUpdateReqDto) {
		User userEntity = userRepository.findById(id).get();
		if (userUpdateReqDto.getPassword() != null && !(userUpdateReqDto.getPassword().equals(""))) {
			String rawPassword = userUpdateReqDto.getPassword();
			String encPassword = bCryptPasswordEncoder.encode(rawPassword);
			userEntity.setPassword(encPassword);
		}
		
		userEntity.setEmail(userUpdateReqDto.getEmail());
		
		return userEntity;
	} // 더티체킹
}
