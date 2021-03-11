package com.lior.coupons.exceptions;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lior.coupons.dto.ErrorDto;
import com.lior.coupons.enums.ErrorType;
import com.lior.coupons.exceptions.ApplicationException;



@RestControllerAdvice
public class ExceptionsHandler {


	@ExceptionHandler
	@ResponseBody
	public ErrorDto toResponse(Throwable throwable, HttpServletResponse response) {


		if(throwable instanceof ApplicationException) {

			ApplicationException appException = (ApplicationException) throwable;

			ErrorType errorType = appException.getErrorType(); 
			int errorNumber = errorType.getErrorNumber();
			String errorMessage = errorType.getErrorMessage();
			String errorName = errorType.name();
			response.setStatus(errorNumber);	

			ErrorDto error = new ErrorDto(errorNumber, errorName ,errorMessage); 
			if(appException.getErrorType().isShowStackTrace()) {
				appException.printStackTrace();
			}

			return error;
		}

		response.setStatus(600);

		String errorMessage = throwable.getMessage();
		ErrorDto error = new ErrorDto(601, "General error", errorMessage);
		throwable.printStackTrace();

		return error;
	}

}




