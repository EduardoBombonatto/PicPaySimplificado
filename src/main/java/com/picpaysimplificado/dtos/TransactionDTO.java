package com.picpaysimplificado.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

	private BigDecimal amount;
	private Long senderId;
	private Long receiverId;
	private LocalDateTime timestamp;
}