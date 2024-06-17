package com.lamchuong.r2sshop.controller;

import com.lamchuong.r2sshop.dto.request.UserRequestDTO;
import com.lamchuong.r2sshop.dto.response.UserResponseDTO;
import com.lamchuong.r2sshop.exception.UserAlreadyExistsException;
import com.lamchuong.r2sshop.exception.UserNotFoundException;
import com.lamchuong.r2sshop.exception.ValidationException;
import com.lamchuong.r2sshop.model.User;
import com.lamchuong.r2sshop.service.UserService;
import com.lamchuong.r2sshop.utils.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserRequestDTO userDto) {
        try {
            User user = userService.saveUser(userDto);
            UserResponseDTO userResponseDTO = new UserResponseDTO(user);
            return BaseResponseController.success(userResponseDTO);
        } catch (UserAlreadyExistsException e) {
            return BaseResponseController.fail(ResponseCode.USER_ALREADY_EXISTS.getCode(), e.getMessage());
        } catch (ValidationException e) {
            return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
        }
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Object> getUserDetails() {
        try {
            UserResponseDTO user = userService.getUserDetails();
            return BaseResponseController.success(user);
        } catch (UserNotFoundException e) {
            return BaseResponseController.fail(ResponseCode.USER_NOT_FOUND.getCode(), e.getMessage());
        } catch (Exception e) {
            return BaseResponseController.fail(ResponseCode.UNKNOWN_ERROR.getCode(), e.getMessage());
        }
    }

    @PutMapping("/me")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Object> updateUserDetails(@RequestBody UserRequestDTO userUpdateRequestDTO) {
        try {
            UserResponseDTO user = userService.updateUserDetails(userUpdateRequestDTO);
            return BaseResponseController.success(user);
        } catch (UserNotFoundException | UserAlreadyExistsException | ValidationException e) {
            return BaseResponseController.fail(ResponseCode.USER_NOT_FOUND.getCode(), e.getMessage());
        } catch (Exception e) {
            return BaseResponseController.fail(ResponseCode.UNKNOWN_ERROR.getCode(), e.getMessage());
        }
    }
}
