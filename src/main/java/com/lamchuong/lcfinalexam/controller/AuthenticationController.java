package com.lamchuong.lcfinalexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lamchuong.lcfinalexam.dto.response.AuthResponse;
import com.lamchuong.lcfinalexam.utils.JwtUtils;
import com.lamchuong.lcfinalexam.dto.request.AuthRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	// generate jwt
	@PostMapping("/login")
	public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		String token = JwtUtils.generateToken(authRequest.getUsername());
		AuthResponse authResponse = new AuthResponse(token, "Đăng nhập thành công");

		return BaseResponseController.success(authResponse);

	}

}
