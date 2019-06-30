package com.somecomp.promosimple.exception;

import org.springframework.util.StringUtils;

public class PromoSystemException  extends RuntimeException{
	private static final long serialVersionUID = 201906290000L;
	
	private String exceptionId;
	
	public PromoSystemException(){
		super();
		generateExceptionID("",null);
	}
	
	public PromoSystemException(String message){
		super(message);
		generateExceptionID(message,null);
	}
	
	public PromoSystemException(Throwable cause){
		super(cause);
		generateExceptionID(cause.getMessage(),cause);
	}
	
	public PromoSystemException(String message,Throwable cause){
		super(message,cause);
		generateExceptionID(message,cause);
	}
	
	private void generateExceptionID(String message,Throwable cause) {
		if(!(cause instanceof PromoSystemException) ) { //only generate one ID
			exceptionId = String.valueOf(System.currentTimeMillis()); // simple approach to ID for now
		}
	}
	
	public String getMessage() {
		StringBuffer buffy = new StringBuffer();
		
		String superMessage = super.getMessage();
		if(!StringUtils.isEmpty(superMessage)) {
			if(superMessage.indexOf("exception id") != -1){
				return superMessage;				
			}else {
				buffy.append(superMessage);
			}				
		}
		buffy.append("\n exception id: ");
		buffy.append(exceptionId);
		return buffy.toString();
	}
	
	public String getExceptionId() {
		return (getCause() instanceof PromoSystemException)
				? ((PromoSystemException) getCause()).getExceptionId()
				: exceptionId;
	}
	
	/**
	 * Return the throwable that originated this exception - the lowest level root cause of this exception
	 * @return Throwable that is the root cause of this exception
	 */
	public Throwable getRootCause() {
		Throwable lastCause = this.getCause();
		Throwable cause = lastCause;
		while(cause != null) {
			cause = lastCause.getCause();
			if(cause != null) {
				lastCause = cause;
			}			
		}
		return lastCause;
	}
}
