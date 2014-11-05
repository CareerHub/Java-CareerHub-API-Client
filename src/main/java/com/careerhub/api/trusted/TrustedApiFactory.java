package com.careerhub.api.trusted;

import com.careerhub.api.ApiInfo;
import com.careerhub.api.trusted.experiences.ExperiencesApi;
import com.careerhub.api.trusted.experiences.ExperiencesApiImpl;

public class TrustedApiFactory {

	private ApiInfo info;
	private String accessToken;

	public TrustedApiFactory(ApiInfo info, String accessToken) {
		this.info = info;
		this.accessToken = accessToken;
	}
	
	public ExperiencesApi GetExperiencesApi() {
		return new ExperiencesApiImpl(info.getBaseUrl(), accessToken);
	}
}
