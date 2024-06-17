package com.lamchuong.r2sshop.repository;

import com.lamchuong.r2sshop.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	List<Address> findByUserId(Long userId);

	Optional<Address> findByDetailAddress(String detailAddress);
}
