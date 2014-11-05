package com.careerhub.api.authorization;

import java.util.Set;

public interface FinishedAuthorizationModel {
	public Set<String> getScopes();
	
	public String getAccessToken();
	public long getAccessTokenExpiresIn();
	public String getRefreshToken();
}
