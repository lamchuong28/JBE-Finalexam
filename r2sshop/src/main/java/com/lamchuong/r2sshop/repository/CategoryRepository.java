package com.lamchuong.r2sshop.repository;

import com.lamchuong.r2sshop.model.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByName(String name);

	List<Category> findByNameContains(String name, Pageable pageable);
}
