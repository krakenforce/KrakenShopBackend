package com.krakenforce.app.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.krakenforce.app.security.common.MessageResponse;

@RestControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

	public ResponseEntity<MessageResponse> handleMaxSizeException(MaxUploadSizeExceededException exc){
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse("File too large!"));
	}
	
}
