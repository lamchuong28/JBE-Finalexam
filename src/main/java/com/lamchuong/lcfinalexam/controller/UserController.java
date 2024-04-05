package com.lamchuong.lcfinalexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lamchuong.lcfinalexam.dto.response.UserResponseDTO;
import com.lamchuong.lcfinalexam.exception.UserAlreadyExistsException;
import com.lamchuong.lcfinalexam.exception.UserNotFoundException;
import com.lamchuong.lcfinalexam.exception.ValidationException;
import com.lamchuong.lcfinalexam.model.User;
import com.lamchuong.lcfinalexam.service.UserService;
import com.lamchuong.lcfinalexam.utils.ResponseCode;
import com.lamchuong.lcfinalexam.dto.request.UserRequestDTO;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping(path = "")
	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAll() {
		return BaseResponseController.success(this.userService.getAll());
	}
	
	@GetMapping(path = "/userlogin")
	public ResponseEntity<?> getAllLogin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		return BaseResponseController.success(userName);
	}

	@PostMapping("")
	public ResponseEntity<?> addUser(@RequestBody UserRequestDTO user) {
		try {
			User addedUser = this.userService.saveUser(user);
			return BaseResponseController.success(new UserResponseDTO(addedUser));
		} catch (UserAlreadyExistsException e) {
			return BaseResponseController.fail(ResponseCode.USER_ALREADY_EXISTS.getCode(),
					ResponseCode.USER_ALREADY_EXISTS.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		}
	}

	@PutMapping(path = "")
	public ResponseEntity<?> updateUser(@RequestBody User newUser) {
		try {
			User updatedUser = this.userService.updateUser(newUser);
			return BaseResponseController.success(updatedUser);
		} catch (UserAlreadyExistsException e) {
			return BaseResponseController.fail(ResponseCode.USER_ALREADY_EXISTS.getCode(),
					ResponseCode.USER_ALREADY_EXISTS.getMessage());
		} catch (UserNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.USER_NOT_FOUND.getCode(),
					ResponseCode.USER_NOT_FOUND.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		}
	}
	
	@PostMapping("/register-user")
	public ResponseEntity<?> signUp(@RequestBody UserRequestDTO userDTO) {
		return this.addUser(userDTO);
	}
}
