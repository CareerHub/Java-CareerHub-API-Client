package com.careerhub.api;

public class CareerHubApiException extends Exception {

	private static final long serialVersionUID = -8977609633958010959L;

	public CareerHubApiException(String message) {
		super(message);
	}
	
	public CareerHubApiException(Throwable inner) {
		super(inner);
	}

	public CareerHubApiException(String message, Throwable inner) {
		super(message, inner);
	}
}
