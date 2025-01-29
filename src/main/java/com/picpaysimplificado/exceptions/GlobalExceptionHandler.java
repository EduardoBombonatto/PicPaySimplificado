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

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> threatInternalServerError(EntityNotFoundException exception) {
		ApiResponse<Object> errorResponse = ResponseUtil.error(Arrays.asList(exception.getMessage()), "", 500, null);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
}
