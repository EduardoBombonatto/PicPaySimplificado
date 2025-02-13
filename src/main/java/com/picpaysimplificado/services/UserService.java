package com.picpaysimplificado.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaysimplificado.dtos.UserRequestDTO;
import com.picpaysimplificado.entities.User;
import com.picpaysimplificado.entities.UserType;
import com.picpaysimplificado.exceptions.InsuficientAmountException;
import com.picpaysimplificado.exceptions.UserNotFoundException;
import com.picpaysimplificado.exceptions.UserTypeNotAuthorizeSenderException;
import com.picpaysimplificado.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User findUserById(Long id) {
		return this.repository.findUserById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	public List<User> getAllUsers() {
		return this.repository.findAll();
	}

	public User createUser(UserRequestDTO data) {
		User newUser = new User(data);
		this.saveUser(newUser);
		return newUser;
	}

	public void saveUser(User user) {
		this.repository.save(user);
	}

	public void validatedTransaction(User sender, BigDecimal amount) {
		if (sender.getUserType() == UserType.MERCHANT) {
			throw new UserTypeNotAuthorizeSenderException();
		}

		if (sender.getBalance().compareTo(amount) < 0) {
			throw new InsuficientAmountException();
		}
	}
}
