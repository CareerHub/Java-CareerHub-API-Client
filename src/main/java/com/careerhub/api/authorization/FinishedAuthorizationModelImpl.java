package com.careerhub.api.authorization;

import java.util.Set;

public class FinishedAuthorizationModelImpl implements FinishedAuthorizationModel {
	private Set<String> scopes;
	
	private String accessToken;
	private long accessTokenExpiresIn;
	private String refreshToken;
	
	public FinishedAuthorizationModelImpl(
			Set<String> scopes, 
			String accessToken, long accessTokenExpiresIn, String refreshToken) {
		
		this.scopes = scopes;
		this.accessToken = accessToken;
		this.accessTokenExpiresIn = accessTokenExpiresIn;
		this.refreshToken = refreshToken;
	}
	
	@Override
	public Set<String> getScopes() {
		return scopes;
	}

	@Override
	public String getAccessToken() {
		return accessToken;
	}

	@Override
	public long getAccessTokenExpiresIn() {
		return accessTokenExpiresIn;
	}

	@Override
	public String getRefreshToken() {
		return refreshToken;
	}

}
