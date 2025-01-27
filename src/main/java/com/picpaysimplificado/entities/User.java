package com.picpaysimplificado.entities;

import java.math.BigDecimal;

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
import lombok.Setter;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@SuppressWarnings("unused")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fisrtName;
	private String lastName;
	
	@Column(unique = true)
	private String cpf;
	
	@Column(unique = true)
	private String email;
	private String password;
	private BigDecimal balance;
	
	@Enumerated(EnumType.STRING)
	private UserType userType;
}
