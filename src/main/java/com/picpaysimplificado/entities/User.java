package com.picpaysimplificado.entities;

import java.math.BigDecimal;

import com.picpaysimplificado.dtos.UserRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;

	@Column(unique = true)
	private String document;

	@Column(unique = true)
	private String email;
	private String password;
	private BigDecimal balance;

	@Enumerated(EnumType.STRING)
	private UserType userType;

	public User(UserRequestDTO data) {
		this.firstName = data.getFirstName();
		this.lastName = data.getLastName();
		this.document = data.getDocument();
		this.email = data.getEmail();
		this.balance = data.getBalance();
		this.password = data.getPassword();
		this.userType = data.getUserType();
	}
}