package com.picpaysimplificado.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaysimplificado.entities.Transaction;
import com.picpaysimplificado.entities.User;
import com.picpaysimplificado.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionRepository repository;

	@Autowired
	private RestTemplate restTemplate;

	void createTransaction(Transaction transaction) throws Exception {
		User sender = this.userService.findUserById(transaction.getSender().getId());
		User receiver = this.userService.findUserById(transaction.getReceiver().getId());

		userService.validatedTransaction(sender, transaction.getAmount());

		boolean isAuthorized = this.authorizeTransaction();
		if (!isAuthorized) {
			throw new Exception("Transação não autorizada");
		}

		sender.setBalance(sender.getBalance().subtract(transaction.getAmount()));
		receiver.setBalance(receiver.getBalance().add(transaction.getAmount()));

		this.repository.save(transaction);
		this.userService.saveUser(sender);
		this.userService.saveUser(receiver);
	}

	public boolean authorizeTransaction() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> authorizationResponse = restTemplate
				.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

		if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
			String message = (String) authorizationResponse.getBody().get("status");
			return "success".equalsIgnoreCase(message);
		} else {
			return false;
		}
	}
}
