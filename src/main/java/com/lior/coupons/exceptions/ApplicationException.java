package com.lior.coupons.exceptions;

import com.lior.coupons.enums.ErrorType;

public class ApplicationException extends Exception {


	private ErrorType errorType;



	public ApplicationException(ErrorType errorType) {
		super();
		this.errorType = errorType;
	}
	public ApplicationException(String message) {
	}
	public ApplicationException(ErrorType errorType, String message) {
		super(message);
		this.errorType = errorType;
	}

	public ApplicationException(Exception e, ErrorType errorType, String message) {
		super(message, e);
		this.errorType = errorType;
	}


	public ErrorType getErrorType() {
		return errorType;
	}
	@Override
	public String toString() {
		return "ApplicationException [errorType=" + errorType + ", getMessage()=" + getMessage() + ", getCause()="
				+ getCause() + "]";
	}





}
