package com.krakenforce.app.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.krakenforce.app.exception.TokenRefreshException;

@RestControllerAdvice
public class TokenControllerAdvice {

//	@ExceptionHandler(value = TokenRefreshException.class)
//	@ResponseStatus(HttpStatus.FORBIDDEN)
//	public ErrorM handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
//		return new ErrorMessage(HttpStatus.FORBIDDEN.value(), new Date(), ex.getMessage(),
//				request.getDescription(false));
//	}
}
