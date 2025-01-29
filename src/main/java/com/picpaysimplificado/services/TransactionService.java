package com.picpaysimplificado.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaysimplificado.dtos.TransactionDTO;
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

	@Autowired
	private NotificationService notificationService;

	public Transaction createTransaction(TransactionDTO transaction) throws Exception {
		User sender = this.userService.findUserById(transaction.getSenderId());
		User receiver = this.userService.findUserById(transaction.getReceiverId());

		userService.validatedTransaction(sender, transaction.getAmount());

		boolean isAuthorized = this.authorizeTransaction();
		if (!isAuthorized) {
			throw new Exception("Transação não autorizada");
		}

		Transaction newTransaction = new Transaction();
		newTransaction.setAmount(transaction.getAmount());
		newTransaction.setSender(sender);
		newTransaction.setReceiver(receiver);
		newTransaction.setTimestamp(LocalDateTime.now());

		this.changeBalanceFromUsers(sender, receiver, transaction.getAmount());

		this.repository.save(newTransaction);
		this.userService.saveUser(receiver);
		this.userService.saveUser(sender);

		//this.sendNotificationToUsers(sender, receiver); //notificação de serviço fora do ar, logo retirado

		return newTransaction;
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

	public void changeBalanceFromUsers(User sender, User receiver, BigDecimal value) {
		sender.setBalance(sender.getBalance().subtract(value));
		receiver.setBalance(receiver.getBalance().add(value));
	}

	public void sendNotificationToUsers(User sender, User receiver) throws Exception {
		this.notificationService.sendNotification(sender, "Transação enviada com sucesso");
		this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");
	}

}
