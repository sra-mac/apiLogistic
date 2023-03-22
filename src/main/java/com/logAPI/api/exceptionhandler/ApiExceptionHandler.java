package com.logAPI.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.logAPI.domain.exception.NotFoundEntityException;
import com.logAPI.domain.exception.TradeException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Problem problem = new Problem();
		problem.setStatus(status.value());
		problem.setDatetime(OffsetDateTime.now());
		problem.setTitle("Um ou mais campos estão inválidos. Por favor, revise os dados inseridos.");
		
		List<Problem.Field> fields = new ArrayList<>();
		for (ObjectError error: ex.getBindingResult().getAllErrors()) {
			//pegar em Cache o campo que está com erro
			String name = ((FieldError) error).getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			//String message = error.getDefaultMessage();
			fields.add(new Problem.Field(name, message));
		}
		problem.setFields(fields);
		return handleExceptionInternal(ex, problem , headers, status, request);
	}

	@ExceptionHandler(NotFoundEntityException.class)
	public ResponseEntity<Object> handleNotFoundEntity(NotFoundEntityException ex, WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		Problem problem = new Problem();
		problem.setStatus(status.value());
		problem.setDatetime(OffsetDateTime.now());
		problem.setTitle(ex.getMessage());
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(TradeException.class)
	public ResponseEntity<Object> handleTrade(TradeException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problem problem = new Problem();
		problem.setStatus(status.value());
		problem.setDatetime(OffsetDateTime.now());
		problem.setTitle(ex.getMessage());
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
}
