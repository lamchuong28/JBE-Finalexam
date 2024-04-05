package com.lamchuong.lcfinalexam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lamchuong.lcfinalexam.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	Optional<Address> findByDetailAddress(String detailAddress);
}
