package com.careerhub.api.authorization;

import java.util.Set;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.OAuthRequestBuilder;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder;
import org.apache.oltu.oauth2.client.response.OAuthAuthzResponse;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;

import com.careerhub.api.CareerHubApiException;

class AuthorizationApiImpl implements AuthorizationApi {
	private String tokenUrl;
	private String authUrl;
	
	private String clientId;
	private String clientSecret;
		
	public AuthorizationApiImpl(String baseUrl, String clientId, String clientSecret) {
		this.tokenUrl = baseUrl + "/oauth/token";
		this.authUrl = baseUrl + "/oauth/auth";
		
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}
	
	@Override
	public FinishedAuthorizationModel doClientCredentialsGrant(Set<String> requestedScopes) throws CareerHubApiException {
		
		TokenRequestBuilder builder = OAuthClientRequest
					.tokenLocation(this.tokenUrl)
					.setGrantType(GrantType.CLIENT_CREDENTIALS)
					.setClientId(this.clientId)
					.setClientSecret(this.clientSecret);
		
		if(requestedScopes != null) {
			String requestedScope = OAuthUtils.encodeScopes(requestedScopes);
			builder.setScope(requestedScope);
		}		
		OAuthClientRequest request;
		
		try {
			request =  builder.buildQueryMessage();
		} catch(OAuthSystemException e) {
			throw new CareerHubApiException(e);
		}
		
		OAuthClient client = new OAuthClient(new URLConnectionClient());
		
		try {
			OAuthJSONAccessTokenResponse response = client.accessToken(request);
			
			String accessToken = response.getAccessToken();
			long expiresIn = response.getExpiresIn();
			
			String refreshToken = response.getRefreshToken();
			
			String returnedScope = response.getScope();
			Set<String> returnedScopes = OAuthUtils.decodeScopes(returnedScope);
			
			
			return new FinishedAuthorizationModelImpl(
					returnedScopes, accessToken, expiresIn, refreshToken
			);
		} catch(OAuthSystemException e) {
			throw new CareerHubApiException(e);			
		} catch(OAuthProblemException e) {
			throw new CareerHubApiException(e);			
		} finally {
			client.shutdown();
		}

	}

	@Override
	public FinishedAuthorizationModel doTokenRefresh(String refreshToken) throws CareerHubApiException {


		TokenRequestBuilder builder = OAuthClientRequest
					.tokenLocation(this.tokenUrl)
					.setGrantType(GrantType.REFRESH_TOKEN)
					.setRefreshToken(refreshToken)
					.setClientId(this.clientId)
					.setClientSecret(this.clientSecret);
		
		OAuthClientRequest request;
		
		try {
			request =  builder.buildQueryMessage();
		} catch(OAuthSystemException e) {
			throw new CareerHubApiException(e);
		}
		
		OAuthClient client = new OAuthClient(new URLConnectionClient());
		
		try {
			OAuthJSONAccessTokenResponse response = client.accessToken(request);
			
			String accessToken = response.getAccessToken();
			long expiresIn = response.getExpiresIn();
			
			String newRefreshToken = response.getRefreshToken();
			
			String returnedScope = response.getScope();
			Set<String> returnedScopes = OAuthUtils.decodeScopes(returnedScope);
			
			
			return new FinishedAuthorizationModelImpl(
					returnedScopes, accessToken, expiresIn, newRefreshToken
			);
		} catch(OAuthSystemException e) {
			throw new CareerHubApiException(e);			
		} catch(OAuthProblemException e) {
			throw new CareerHubApiException(e);			
		} finally {
			client.shutdown();
		}
	}
	
}
