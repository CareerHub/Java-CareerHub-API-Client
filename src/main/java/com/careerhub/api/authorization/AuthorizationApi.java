package com.careerhub.api.authorization;

import java.util.Set;

import com.careerhub.api.CareerHubApiException;

public interface AuthorizationApi {
	//void AuthorizationModel startAuthoizationGrant(String[] scopes);
	//FinishedAuthorizationModel finishAuthorizationGrant();
	
	FinishedAuthorizationModel doClientCredentialsGrant(Set<String> scopes) throws CareerHubApiException;
	FinishedAuthorizationModel doTokenRefresh(String refreshToken) throws CareerHubApiException;
}
