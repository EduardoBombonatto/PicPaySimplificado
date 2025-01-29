package com.picpaysimplificado.responses;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
	private boolean success;
    private String message;
    private T data;
    private List<String> errors;
    private int errorCode;
    private LocalDateTime timestamp;
}
