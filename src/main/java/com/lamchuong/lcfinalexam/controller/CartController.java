package com.lamchuong.lcfinalexam.controller;

import org.springframework.web.bind.annotation.RestController;

import com.lamchuong.lcfinalexam.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "/cart")
public class CartController {
	@Autowired
	private CartService cartService;

	@GetMapping("")
	public ResponseEntity<?> getAll() {
		return BaseResponseController.success(this.cartService.getAll());
	}

}
