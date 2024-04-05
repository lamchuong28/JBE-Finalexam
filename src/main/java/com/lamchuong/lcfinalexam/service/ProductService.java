package com.lamchuong.lcfinalexam.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamchuong.lcfinalexam.dto.request.ProductRequestDTO;
import com.lamchuong.lcfinalexam.dto.response.ProductResponseDTO;
import com.lamchuong.lcfinalexam.exception.CategoryNotFoundException;
import com.lamchuong.lcfinalexam.exception.ProductNotFoundException;
import com.lamchuong.lcfinalexam.exception.ValidationException;
import com.lamchuong.lcfinalexam.model.Category;
import com.lamchuong.lcfinalexam.model.Product;
import com.lamchuong.lcfinalexam.repository.CategoryRepository;
import com.lamchuong.lcfinalexam.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<ProductResponseDTO> getAllProduct() {
		return this.productRepository.findAll().stream().map(ProductResponseDTO::new).toList();
	}

	public Product findById(Integer id) throws ValidationException, ProductNotFoundException {
		if (Objects.isNull(id) || id <= 0) {
			throw new ValidationException("product.id must be positive");
		}

		Optional<Product> foundProduct = this.productRepository.findById(id);
		if (foundProduct.isEmpty()) {
			throw new ProductNotFoundException();
		}

		return foundProduct.get();
	}
	
	private void validateProduct(Product product) throws ValidationException {
		if (Objects.isNull(product)) {
			throw new ValidationException("product is null");
		}
		if (Objects.isNull(product.getName()) || product.getName().isBlank()) {
			throw new ValidationException("product.name cannot be blank");
		}
	}
	
	private void validateProduct(ProductRequestDTO productDTO) throws ValidationException {
		if (Objects.isNull(productDTO)) {
			throw new ValidationException("product is null");
		}
		if (Objects.isNull(productDTO.getName()) || productDTO.getName().isBlank()) {
			throw new ValidationException("product.name cannot be blank");
		}
	}
	
	public Product addProduct(ProductRequestDTO productDTO) throws CategoryNotFoundException, ValidationException {
		this.validateProduct(productDTO);

		Optional<Category> foundCategoryOptional = this.categoryRepository.findById(productDTO.getCategoryId());
		if (foundCategoryOptional.isEmpty()) {
			throw new CategoryNotFoundException();
		}
		Category foundCategory = foundCategoryOptional.get();
		Product product = productDTO.toProduct();
		product.setCategory(foundCategory);
		return this.productRepository.save(product);
	}
	
	public Product updateProduct(Product newProduct) throws ValidationException, ProductNotFoundException{
		validateProduct(newProduct);

		Optional<Product> foundProduct = this.productRepository.findById(newProduct.getId());
		if (foundProduct.isEmpty()) {
			throw new ProductNotFoundException();
		}
		return this.productRepository.save(newProduct);
	}
	
	

}
