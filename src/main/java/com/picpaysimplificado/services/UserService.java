package com.picpaysimplificado.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaysimplificado.entities.User;
import com.picpaysimplificado.entities.UserType;
import com.picpaysimplificado.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public void validatedTransaction(User sender, BigDecimal amount) throws Exception {
		if(sender.getUserType() == UserType.MERCHANT) {
			throw new Exception("Lojistas não estão autorizados a realizar transações.");
		}
		
		if(sender.getBalance().compareTo(amount) < 0) {
			throw new Exception("Saldo insuficiente.");
		}
	}
	
	public User findUserById(Long id) throws Exception{
		return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
	}
	
	public void saveUser(User user) {
		this.repository.save(user);
	}
}
