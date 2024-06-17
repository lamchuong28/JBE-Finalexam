package com.lamchuong.r2sshop.controller;

import com.lamchuong.r2sshop.dto.request.AuthRequest;
import com.lamchuong.r2sshop.dto.response.AuthResponse;
import com.lamchuong.r2sshop.utils.JwtUtils;
import com.lamchuong.r2sshop.utils.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            String token = jwtUtils.generateToken(authentication.getName());
            return BaseResponseController.success(new AuthResponse(token, "Login successful"));
        } catch (Exception e) {
            return BaseResponseController.fail(ResponseCode.INVALID_CREDENTIALS.getCode(), "Invalid credentials");
        }
    }
}
