package com.msedcl.main.transactions.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	@Override
	protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
	List<ObjectError> allErrors=ex.getAllErrors();
	Map<String,String> fieldErrorMap=new HashMap<>();
	
	for(ObjectError objectError:allErrors) {
		FieldError fieldError=(FieldError) objectError;
		String fieldName=fieldError.getField();
		String errorMessage=objectError.getDefaultMessage();
		fieldErrorMap.put(fieldName, errorMessage);	
	}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fieldErrorMap);
	}
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ProblemDetail> handleAccountNotFoundException (AccountNotFoundException ex){
		ProblemDetail problemDetail=ProblemDetail.forStatus(HttpStatusCode.valueOf(404));
		problemDetail.setStatus(HttpStatus.NOT_FOUND);
		problemDetail.setDetail(ex.getMessage());
		problemDetail.setTitle("ACCOUNT_NOT_FOUND");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
	}
	
	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<ProblemDetail> handleInsufficientBalanceException (InsufficientBalanceException ex){
		ProblemDetail problemDetail=ProblemDetail.forStatus(HttpStatusCode.valueOf(400));
		problemDetail.setStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setDetail(ex.getMessage());
		problemDetail.setTitle("LOW_BALANCE");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
	}	
	
}
