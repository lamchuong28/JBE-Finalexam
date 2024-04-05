package com.lamchuong.lcfinalexam.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.lamchuong.lcfinalexam.dto.request.UserRequestDTO;
import com.lamchuong.lcfinalexam.dto.response.UserResponseDTO;
import com.lamchuong.lcfinalexam.exception.UserAlreadyExistsException;
import com.lamchuong.lcfinalexam.exception.UserNotFoundException;
import com.lamchuong.lcfinalexam.exception.ValidationException;
import com.lamchuong.lcfinalexam.model.User;
import com.lamchuong.lcfinalexam.repository.RoleRepository;
import com.lamchuong.lcfinalexam.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<UserResponseDTO> getAll() {
		return this.userRepository.findAll().stream().map(UserResponseDTO::new).toList();
	}

	private void validateUser(User user) throws ValidationException {
		if (Objects.isNull(user)) {
			throw new ValidationException("user is null");
		}

		if (Objects.isNull(user.getUserName()) || user.getUserName().isBlank()) {
			throw new ValidationException("user.userName cannot be blank");
		}

		if (Objects.isNull(user.getFullName()) || user.getFullName().isBlank()) {
			throw new ValidationException("user.name cannot be blank");
		}

//		if (Objects.isNull(user.getEmail()) || user.getEmail().isBlank()) {
//			throw new ValidationException("user.email cannot be blank");
//		}

	}

	private void validateUser(UserRequestDTO user) throws ValidationException {
		if (Objects.isNull(user)) {
			throw new ValidationException("user is null");
		}

		if (Objects.isNull(user.getUserName()) || user.getUserName().isBlank()) {
			throw new ValidationException("user.userName cannot be blank");
		}

		if (Objects.isNull(user.getPassWord()) || user.getPassWord().isBlank()) {
			throw new ValidationException("user.passWord cannot be blank");
		}

		if (Objects.isNull(user.getFullName()) || user.getFullName().isBlank()) {
			throw new ValidationException("user.name cannot be blank");
		}

		if (Objects.isNull(user.getEmail()) || user.getEmail().isBlank()) {
			throw new ValidationException("user.email cannot be blank");
		}
	}

	public User saveUser(UserRequestDTO user) throws UserAlreadyExistsException, ValidationException {
		this.validateUser(user);

		Optional<User> foundUser = this.userRepository.findByUserName(user.getUserName());
		if (foundUser.isPresent()) {
			throw new UserAlreadyExistsException();
		}
		User insertedUser = user.toUser();
		insertedUser.setPassWord(this.passwordEncoder.encode(user.getPassWord()));
		insertedUser.setRoles(List.of(this.roleRepository.findById(2).get()));
		return this.userRepository.save(insertedUser);
	}

	public User updateUser(User newUser) throws ValidationException, UserNotFoundException, UserAlreadyExistsException {
		validateUser(newUser);

		Optional<User> foundUser = this.userRepository.findById(newUser.getId());
		if (foundUser.isEmpty()) {
			throw new UserNotFoundException();
		}

		foundUser = this.userRepository.findByUserName(newUser.getUserName());
		if (foundUser.isPresent() && !newUser.getId().equals(foundUser.get().getId())) {
			throw new UserAlreadyExistsException();
		}
		User user = foundUser.get();
		user.setEmail(newUser.getEmail());

		return this.userRepository.save(user);
	}

}
