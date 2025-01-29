package com.picpaysimplificado.exceptions;

import java.util.Arrays;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.picpaysimplificado.responses.ApiResponse;
import com.picpaysimplificado.utils.ResponseUtil;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiResponse<Object>> threatDuplicateEntry(DataIntegrityViolationException exception) {
		ApiResponse<Object> errorResponse = ResponseUtil.error(Arrays.asList(exception.getMessage()),
				"Usuário já cadastrado", 400, null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> threatNotFound(EntityNotFoundException exception) {
		ApiResponse<Object> errorResponse = ResponseUtil.error(Arrays.asList(exception.getMessage()),
				"Entidade não encontrado", 404, null);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	@ExceptionHandler(UserTypeNotAuthorizeSenderException.class)
	public ResponseEntity<ApiResponse<Object>> threatUserTypeNotAuthorizeSender(
			UserTypeNotAuthorizeSenderException exception) {
		ApiResponse<Object> errorResponse = ResponseUtil.error(Arrays.asList(exception.getMessage()),
				"Lojistas não estão autorizados a realizar transações.", 401, null);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
	}

	@ExceptionHandler(InsuficientAmountException.class)
	public ResponseEntity<ApiResponse<Object>> threatInsuficientAmount(InsuficientAmountException exception) {
		ApiResponse<Object> errorResponse = ResponseUtil.error(Arrays.asList(exception.getMessage()),
				"Saldo insuficiente.", 401, null);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> threatUserNotFound(UserNotFoundException exception) {
		ApiResponse<Object> errorResponse = ResponseUtil.error(Arrays.asList(exception.getMessage()),
				"Usuário não encontrado", 404, null);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> threatInternalServerError(EntityNotFoundException exception) {
		ApiResponse<Object> errorResponse = ResponseUtil.error(Arrays.asList(exception.getMessage()), "", 500, null);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
}
