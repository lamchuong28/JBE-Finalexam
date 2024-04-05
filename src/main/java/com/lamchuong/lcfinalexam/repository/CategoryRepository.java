package com.lamchuong.lcfinalexam.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lamchuong.lcfinalexam.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	List<Category> findByName(String name);

	List<Category> findByNameContains(String name, Pageable pageable);
}
