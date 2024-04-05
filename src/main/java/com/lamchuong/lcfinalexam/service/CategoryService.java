package com.lamchuong.lcfinalexam.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lamchuong.lcfinalexam.dto.response.CategoryResponseDTO;
import com.lamchuong.lcfinalexam.exception.ValidationException;
import com.lamchuong.lcfinalexam.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryResponseDTO> getAllCategories() {
		return this.categoryRepository.findAll().stream().map(CategoryResponseDTO::new).toList();
	}
	
	public List<CategoryResponseDTO> findByName(String name, Pageable pageable) throws ValidationException {
		if (Objects.isNull(name) || name.isBlank()) {
			throw new ValidationException("category cannot be null");
		}
		return this.categoryRepository.findByNameContains(name, pageable).stream().map(CategoryResponseDTO::new).toList();

	}
}

