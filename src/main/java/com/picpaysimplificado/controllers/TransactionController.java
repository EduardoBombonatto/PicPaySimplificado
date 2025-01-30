package com.picpaysimplificado.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.entities.Transaction;
import com.picpaysimplificado.responses.ApiResponse;
import com.picpaysimplificado.services.TransactionService;
import com.picpaysimplificado.utils.ResponseUtil;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping
	public ResponseEntity<ApiResponse<TransactionDTO>> createTransaction(@RequestBody TransactionDTO transaction)
			throws Exception {
		Transaction newTransaction = this.transactionService.createTransaction(transaction);
		TransactionDTO returnedTransaction = new TransactionDTO(newTransaction.getAmount(),
				newTransaction.getSender().getId(), newTransaction.getReceiver().getId(),
				newTransaction.getTimestamp());

		ApiResponse<TransactionDTO> successResponse = ResponseUtil.success(returnedTransaction,
				"Transação realizada com sucesso.", null);
		return ResponseEntity.ok().body(successResponse);
	}
}
