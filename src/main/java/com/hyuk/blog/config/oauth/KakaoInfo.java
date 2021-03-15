package com.hyuk.blog.config.oauth;

import java.util.Map;

public class KakaoInfo extends OAuth2UserInfo {
	
	private Map<String, Object> properties;

	public KakaoInfo(Map<String, Object> attributes) {
		super(attributes);
		properties = (Map)attributes.get("properties");
	}

	@Override
	public String getId() {
		return attributes.get("id").toString();
	}

	@Override
	public String getName() {
		return (String)properties.get("nickname");
	}

	@Override
	public String email() {
		return (String)properties.get("email");
	}

	@Override
	public String getImageUrl() {
		return (String)properties.get("profile_image");
	}

	@Override
	public String getUsername() {
		return "Kakao_" + this.getId();
	}

}
