package com.lamchuong.r2sshop.controller;

import com.lamchuong.r2sshop.dto.request.OrderRequestDTO;
import com.lamchuong.r2sshop.dto.response.OrderResponseDTO;
import com.lamchuong.r2sshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long userId = getUserIdFromUserDetails(userDetails);

        OrderResponseDTO orderResponseDTO = orderService.createOrder(userId, orderRequestDTO);
        return BaseResponseController.success(orderResponseDTO);
    }

    private Long getUserIdFromUserDetails(UserDetails userDetails) {
        // Implement logic to retrieve userId from UserDetails
        // This can be done by modifying UserDetails to include userId
        // or by retrieving user information from the database using username
        // Example:
        // return userService.findUserByUsername(userDetails.getUsername()).getId();
        return 1L; // Placeholder, replace with actual logic
    }
}
