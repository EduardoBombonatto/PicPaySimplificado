package com.picpaysimplificado.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.entities.Transaction;
import com.picpaysimplificado.services.TransactionService;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping
	public TransactionDTO createTransaction(@RequestBody TransactionDTO transaction) throws Exception {
		Transaction newTransaction = this.transactionService.createTransaction(transaction);

		return new TransactionDTO(newTransaction.getAmount(), newTransaction.getSender().getId(),
				newTransaction.getReceiver().getId(), newTransaction.getTimestamp());
	}
}
