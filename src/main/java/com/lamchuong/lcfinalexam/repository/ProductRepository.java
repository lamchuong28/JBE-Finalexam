package com.lamchuong.lcfinalexam.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lamchuong.lcfinalexam.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {


}
