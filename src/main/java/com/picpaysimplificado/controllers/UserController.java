package com.picpaysimplificado.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpaysimplificado.dtos.UserRequestDTO;
import com.picpaysimplificado.dtos.UserResponseDTO;
import com.picpaysimplificado.entities.User;
import com.picpaysimplificado.responses.ApiResponse;
import com.picpaysimplificado.services.UserService;
import com.picpaysimplificado.utils.ResponseUtil;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<UserResponseDTO> getAllUsers() {
		List<User> allUsers = this.userService.getAllUsers();
		return allUsers.stream().map(user -> new UserResponseDTO(user.getFirstName(), user.getLastName(),
				user.getEmail(), user.getBalance(), user.getUserType())).toList();
	}

	@PostMapping
	public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(@RequestBody UserRequestDTO userDto) {
		User user = userService.createUser(userDto);
		UserResponseDTO returnedUser =  new UserResponseDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getBalance(),
				user.getUserType());
		ApiResponse<UserResponseDTO> successResponse = ResponseUtil.success(returnedUser, "Usuario criado com sucesso!", null);
		return ResponseEntity.ok().body(successResponse);
	}

}
