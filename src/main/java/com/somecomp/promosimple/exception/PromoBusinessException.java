package com.somecomp.promosimple.exception;

import java.util.ArrayList;
import java.util.List;

public class PromoBusinessException extends Exception{
	
	private static final long serialVersionUID = 201906290000L;
	
	List<PromoBusinessProblem> problems = new ArrayList<PromoBusinessProblem>();
	
	protected PromoBusinessException() {
		super();
	}	

	public PromoBusinessException(List<PromoBusinessProblem> problemList) {
		problems.addAll(problemList);
	}
	
	public List<PromoBusinessProblem> getProblems() {
		return problems;
	}
	
	@Override
	public String toString() {
		return "PromoBusinessException [problems=" + problems + "]";
	}
}
