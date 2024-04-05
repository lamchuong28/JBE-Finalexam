package com.lamchuong.lcfinalexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lamchuong.lcfinalexam.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
