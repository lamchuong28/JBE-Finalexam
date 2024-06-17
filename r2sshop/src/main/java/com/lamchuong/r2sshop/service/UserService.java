package com.lamchuong.r2sshop.service;

import com.lamchuong.r2sshop.dto.request.UserRequestDTO;
import com.lamchuong.r2sshop.dto.response.UserResponseDTO;
import com.lamchuong.r2sshop.exception.UserAlreadyExistsException;
import com.lamchuong.r2sshop.exception.UserNotFoundException;
import com.lamchuong.r2sshop.exception.ValidationException;
import com.lamchuong.r2sshop.model.Cart;
import com.lamchuong.r2sshop.model.Role;
import com.lamchuong.r2sshop.model.User;
import com.lamchuong.r2sshop.repository.CartRepository;
import com.lamchuong.r2sshop.repository.RoleRepository;
import com.lamchuong.r2sshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(UserRequestDTO userDto) throws UserAlreadyExistsException, ValidationException {
        validateUser(userDto);

        Optional<User> foundUser = userRepository.findByUsername(userDto.getUsername());
        if (foundUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        User user = userDto.toUser();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Assign default role to the user
        Role defaultRole = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(Set.of(defaultRole));

        User savedUser = userRepository.save(user);

        // Create a new cart for the user
        Cart cart = new Cart();
        cart.setUser(savedUser);
        cart.setCreatedDate(LocalDateTime.now());
        cartRepository.save(cart);

        return savedUser;
    }


    public UserResponseDTO getUserDetails() throws UserNotFoundException {
        String username = getCurrentUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        return new UserResponseDTO(user);
    }

    public UserResponseDTO updateUserDetails(UserRequestDTO userUpdateRequestDTO) throws UserNotFoundException, UserAlreadyExistsException, ValidationException {
        String username = getCurrentUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        validateUpdateUser(userUpdateRequestDTO, user);

        user.setEmail(userUpdateRequestDTO.getEmail());
        user.setFullName(userUpdateRequestDTO.getFullName());

        if (userUpdateRequestDTO.getPassword() != null && !userUpdateRequestDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userUpdateRequestDTO.getPassword()));
        }

        User updatedUser = userRepository.save(user);
        return new UserResponseDTO(updatedUser);
    }

    private void validateUser(UserRequestDTO user) throws ValidationException {
        if (Objects.isNull(user)) {
            throw new ValidationException("User is null");
        }

        if (Objects.isNull(user.getUsername()) || user.getUsername().isBlank()) {
            throw new ValidationException("Username cannot be blank");
        }

        if (Objects.isNull(user.getPassword()) || user.getPassword().isBlank()) {
            throw new ValidationException("Password cannot be blank");
        }

        if (Objects.isNull(user.getFullName()) || user.getFullName().isBlank()) {
            throw new ValidationException("Full name cannot be blank");
        }

        if (Objects.isNull(user.getEmail()) || user.getEmail().isBlank()) {
            throw new ValidationException("Email cannot be blank");
        }
    }

    private void validateUpdateUser(UserRequestDTO userDto, User existingUser) throws ValidationException, UserAlreadyExistsException {
        if (Objects.isNull(userDto)) {
            throw new ValidationException("User data is null");
        }

        if (Objects.isNull(userDto.getEmail()) || userDto.getEmail().isBlank()) {
            throw new ValidationException("Email cannot be blank");
        }

        if (Objects.isNull(userDto.getFullName()) || userDto.getFullName().isBlank()) {
            throw new ValidationException("Full name cannot be blank");
        }

        Optional<User> foundUser = userRepository.findByUsername(userDto.getUsername());
        if (foundUser.isPresent() && (existingUser == null || !existingUser.getId().equals(foundUser.get().getId()))) {
            throw new UserAlreadyExistsException();
        }
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
