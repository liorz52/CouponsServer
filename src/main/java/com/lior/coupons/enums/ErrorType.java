package com.lior.coupons.enums;

public enum ErrorType {


	GENERAL_ERROR(501, "General error", true), 
	LOGIN_FAILED(502, "Login failed", false),
	NAME_ALREADY_EXISTS(503, "The name you chose already exists. Please enter another name", false), 
	MUST_BE_2_CHARACTERS(504,"Must be more than 2 characters",false),
	MUST_ENTER_NAME(505, "Can not insert an null/empty name", false),
	MUST_ENTER_ADDRESS(506, "Can not insert an null/empty address", false),
	ID_DOES_NOT_EXIST(507, "This ID does'nt exist", false),
	ID_MUST_BE_POSITIVE(508, "ID must be positive",false),
	INVALID_PHONE_NUMBER(509,"Invalid phone number",false),
	INVALID_PASSWORD(510, "Password must contain at least 8 charaters", false),
	NOT_ENOUGH_COUPONS_LEFT(511, "Not enough coupons left to purchase the amount requested", false),
	COUPON_EXPIERED(512, "The coupon is expiered", false),
	INVALID_COUPON(513,"invalid coupon",false),
	INVALID_PRICE(514, "Coupon price must be more than 0", false),
	INVALID_EMAIL(515, "Email address is InValid, Please enter a valid email address", false),
	INVALID_AMOUNT(516, "Coupon's amount must be more than 0", false), 
	INVALID_DATES(517, "The dates you've entered are wrong", false),
	MUST_INSERT_A_VALUE(518, "Must insert a value", false),
	INVALID_COMMPANY_ID(519, "you can only insert your company id", false),
	PRICE_MUST_BE_POSITIVE(521, "price must be positive", false);



	private int errorNumber;
	private String errorMessage;
	private boolean isShowStackTrace;

	private ErrorType(int errorNumber, String internalMessage, boolean isShowStackTrace) {
		this(errorNumber,internalMessage);
		this.isShowStackTrace = isShowStackTrace;
	}

	private ErrorType(int errorNumber, String internalMessage) {
		this.errorNumber = errorNumber;
		this.errorMessage = internalMessage;
	}



	public String getErrorMessage() {
		return errorMessage;
	}

	public boolean isShowStackTrace() {
		return isShowStackTrace;
	}

	public int getErrorNumber() {
		return errorNumber;
	}

}



