package com.hyuk.blog.config.oauth;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.hyuk.blog.config.auth.PrincipalDetails;
import com.hyuk.blog.domain.user.RoleType;
import com.hyuk.blog.domain.user.User;
import com.hyuk.blog.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService {
	
	private final UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		// 1. AccessToken으로 회원정보를 받았다는 의미 (AccessToken은 userRequest가 들고 있음)
		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println("===================================================");
		System.out.println(oAuth2User.getAttributes());
		System.out.println("===================================================");
		
		return processOAuth2User(userRequest, oAuth2User);
	}
	
	// 구글 로그인 프로세스
	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
//		System.out.println(userRequest.getClientRegistration().getClientId());
//		System.out.println(userRequest.getClientRegistration().getClientSecret());
//		System.out.println(userRequest.getClientRegistration().getRedirectUri());
//		System.out.println(userRequest.getAccessToken());
//		System.out.println(userRequest.getClientRegistration().getClientName());
		String clientName = userRequest.getClientRegistration().getClientName();
		OAuth2UserInfo oAuth2UserInfo = null;
		
		// 1. 통합 클래스를 생성
		switch (clientName) {
			case "Google":
				oAuth2UserInfo = new GoogleInfo(oAuth2User.getAttributes());
				break;
			case "Facebook":
				oAuth2UserInfo = new FacebookInfo(oAuth2User.getAttributes());
				break;
			case "Naver":
				oAuth2UserInfo = new NaverInfo((Map)(oAuth2User.getAttributes().get("response")));
				break;
			case "Kakao":
				oAuth2UserInfo = new KakaoInfo((Map)oAuth2User.getAttributes());
				break;
			default:
				break;
			}
		
		// 2. 최초 로그인 : 회원가입+로그인 / 아니면 : 로그인
		User userEntity = userRepository.findByUsername(oAuth2UserInfo.getUsername());
		if (userEntity == null) {
			UUID uuid = UUID.randomUUID();
			String encPassword = new BCryptPasswordEncoder().encode(uuid.toString());
			
			User user = User.builder()
					.username(oAuth2UserInfo.getUsername())
					.password(encPassword)
					.email(oAuth2UserInfo.email())
					.role(RoleType.USER)
					.build();
			userEntity = userRepository.save(user);
			return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
		} else { // 최초 로그인이 아니면 (원래는 구글 정보가 변경될 수 있기 때문에 update를 해야됨)
			return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
		}
	}
}