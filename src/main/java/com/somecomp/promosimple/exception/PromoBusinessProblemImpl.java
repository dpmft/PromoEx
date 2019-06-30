package com.somecomp.promosimple.exception;

public class PromoBusinessProblemImpl implements PromoBusinessProblem  {
	
	private final String errorKey;
	private final String errorMessage;
	
	public String getErrorKey() {
		return errorKey;
	}

	public String getErrorMessage() {
		return errorMessage;
	}	
	
	public PromoBusinessProblemImpl(String errorKey,String errorMessage) {
		this.errorKey = errorKey;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String toString() {
		return "PromoBusinessProblemImpl [errorKey=" + errorKey + ", errorMessage=" + errorMessage + "]";
	}

}
