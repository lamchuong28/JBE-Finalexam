package com.lamchuong.lcfinalexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lamchuong.lcfinalexam.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}