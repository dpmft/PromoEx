package com.somecomp.promosimple.exception;

public interface PromoBusinessProblem {
	static final long serialVersionUID = 201906290000L;
	
	public String getErrorKey();
	
	public String getErrorMessage();
}
