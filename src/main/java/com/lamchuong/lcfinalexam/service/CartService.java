package com.lamchuong.lcfinalexam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamchuong.lcfinalexam.model.Cart;
import com.lamchuong.lcfinalexam.repository.CartRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;

	public List<Cart> getAll() {
		return this.cartRepository.findAll();
	}
}
