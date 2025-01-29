package com.picpaysimplificado.dtos;

import java.math.BigDecimal;

import com.picpaysimplificado.entities.UserType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "document")
public class UserRequestDTO {
	private String firstName;
	private String lastName;
	private String document;
	private String email;
	private String password;
	private BigDecimal balance;
	private UserType userType;
}
