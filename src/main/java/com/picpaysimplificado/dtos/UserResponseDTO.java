package com.picpaysimplificado.dtos;

import java.math.BigDecimal;

import com.picpaysimplificado.entities.UserType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDTO {
	private String firstName;
	private String lastName;
	private String email;
	private BigDecimal balance;
	private UserType userType;
}
