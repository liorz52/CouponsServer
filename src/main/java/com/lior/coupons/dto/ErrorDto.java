package com.lior.coupons.dto;

public class ErrorDto {


	private int errorNumber;
	private String errorMessage;
	private String errorName;


	public ErrorDto(int errorNumber, String errorMessage, String errorName) {
		super();
		this.errorNumber = errorNumber;
		this.errorMessage = errorMessage;
		this.errorName = errorName;
	}


	public int getErrorNumber() {
		return errorNumber;
	}


	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public String getErrorName() {
		return errorName;
	}


	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}


	@Override
	public String toString() {
		return "Error [errorNumber=" + errorNumber + ", errorMessage=" + errorMessage + ", errorName=" + errorName
				+ "]";
	}




}
