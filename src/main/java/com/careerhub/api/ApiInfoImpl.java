package com.careerhub.api;

public final class ApiInfoImpl implements ApiInfo {
	private String baseUrl;
	private String version;
	private String[] supportedComponents;
		
	@Override
	public String getBaseUrl() {
		return baseUrl;
	}
	
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	@Override
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@Override
	public String[] getSupportedComponents() {
		return supportedComponents;
	}

	public void setSupportedComponents(String[] supportedComponents) {
		this.supportedComponents = supportedComponents;
	}
}
