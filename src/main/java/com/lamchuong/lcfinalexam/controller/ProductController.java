package com.lamchuong.lcfinalexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lamchuong.lcfinalexam.dto.request.ProductRequestDTO;
import com.lamchuong.lcfinalexam.dto.response.ProductResponseDTO;
import com.lamchuong.lcfinalexam.exception.CategoryNotFoundException;
import com.lamchuong.lcfinalexam.exception.ProductNotFoundException;
import com.lamchuong.lcfinalexam.exception.ValidationException;
import com.lamchuong.lcfinalexam.model.Product;
import com.lamchuong.lcfinalexam.service.ProductService;
import com.lamchuong.lcfinalexam.utils.ResponseCode;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping(path = "")
	public ResponseEntity<?> getAllProduct() {
		return BaseResponseController.success(this.productService.getAllProduct());
	}

	@GetMapping(path = "/findById")
	public ResponseEntity<?> getByRequestParam(
			@RequestParam(name = "id", required = false, defaultValue = "0") int id) {
		try {
			Product foundProduct = this.productService.findById(id);
			return BaseResponseController.success(new ProductResponseDTO(foundProduct));
		} catch (ProductNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.PRODUCT_NOT_FOUND.getCode(),
					ResponseCode.PRODUCT_NOT_FOUND.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		}
	}

	@PostMapping("")
	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addProduct(@RequestBody ProductRequestDTO productDTO)
			throws ValidationException, CategoryNotFoundException {
		try {
			return BaseResponseController.success(new ProductResponseDTO(this.productService.addProduct(productDTO)));
		} catch (CategoryNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.CATEGORY_NOT_FOUND.getCode(),
					ResponseCode.CATEGORY_NOT_FOUND.getMessage());
		}
	}

	@PutMapping(path = "")
	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateProduct(@RequestBody Product newProduct) {
		try {
			Product updatedProduct = this.productService.updateProduct(newProduct);
			return BaseResponseController.success(updatedProduct);
		} catch (ProductNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.PRODUCT_NOT_FOUND.getCode(),
					ResponseCode.PRODUCT_NOT_FOUND.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		}
	}

}