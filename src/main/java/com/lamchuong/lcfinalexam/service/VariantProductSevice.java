package com.lamchuong.lcfinalexam.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamchuong.lcfinalexam.dto.request.VariantProductRequestDTO;
import com.lamchuong.lcfinalexam.dto.response.VariantProductResponseDTO;
import com.lamchuong.lcfinalexam.exception.ValidationException;
import com.lamchuong.lcfinalexam.exception.VariantProductNotFoundException;
import com.lamchuong.lcfinalexam.model.Product;
import com.lamchuong.lcfinalexam.model.VariantProduct;
import com.lamchuong.lcfinalexam.repository.ProductRepository;
import com.lamchuong.lcfinalexam.repository.VariantProductRepository;

@Service
public class VariantProductSevice {
	@Autowired
	private VariantProductRepository variantProductRepository;
	
	@Autowired
	private ProductRepository productRepository;

	public List<VariantProductResponseDTO> getAllVarianProduct() {
		return this.variantProductRepository.findAll().stream().map(VariantProductResponseDTO::new).toList();
	}
	
	private void validateVariantProduct(VariantProduct variantProduct) throws ValidationException {
		if (Objects.isNull(variantProduct)) {
			throw new ValidationException("variantProduct is null");
		}
		if (Objects.isNull(variantProduct.getColor()) || variantProduct.getColor().isBlank()) {
			throw new ValidationException("variantProduct.color cannot be blank");
		}
		if (Objects.isNull(variantProduct.getSize()) || variantProduct.getSize().isBlank()) {
			throw new ValidationException("variantProduct.size cannot be blank");
		}
		if (Objects.isNull(variantProduct.getModel()) || variantProduct.getModel().isBlank()) {
			throw new ValidationException("variantProduct.model cannot be blank");
		}
		if (Objects.isNull(variantProduct.getPrice()) || variantProduct.getPrice() < 0) {
			throw new ValidationException("variantProduct.price must be positive");
		}

	}

	private void validateVariantProduct(VariantProductRequestDTO variantProductDTO) throws ValidationException {
		if (Objects.isNull(variantProductDTO)) {
			throw new ValidationException("variantProduct is null");
		}
		if (Objects.isNull(variantProductDTO.getColor()) || variantProductDTO.getColor().isBlank()) {
			throw new ValidationException("variantProduct.color cannot be blank");
		}
		if (Objects.isNull(variantProductDTO.getSize()) || variantProductDTO.getSize().isBlank()) {
			throw new ValidationException("variantProduct.size cannot be blank");
		}
		if (Objects.isNull(variantProductDTO.getModel()) || variantProductDTO.getModel().isBlank()) {
			throw new ValidationException("variantProduct.model cannot be blank");
		}
		if (Objects.isNull(variantProductDTO.getPrice()) || variantProductDTO.getPrice() < 0) {
			throw new ValidationException("variantProduct.price must be positive");
		}
	}

	public VariantProduct addVariantProduct(VariantProductRequestDTO variantProductDTO) throws VariantProductNotFoundException, ValidationException {
		this.validateVariantProduct(variantProductDTO);

		Optional<Product> foundProductOptional = this.productRepository.findById(variantProductDTO.getProductId());
		if (foundProductOptional.isEmpty()) {
			throw new VariantProductNotFoundException();
		}
		Product foundProduct = foundProductOptional.get();
		VariantProduct variantProduct = variantProductDTO.toVariantProduct();
		variantProduct.setProduct(foundProduct);
		return this.variantProductRepository.save(variantProduct);
	}
	
	public VariantProduct updateVariantProduct(VariantProduct newVariantProduct) throws ValidationException, VariantProductNotFoundException{
		validateVariantProduct(newVariantProduct);

		Optional<VariantProduct> foundVariantProduct = this.variantProductRepository.findById(newVariantProduct.getId());
		if (foundVariantProduct.isEmpty()) {
			throw new VariantProductNotFoundException();
		}
		return this.variantProductRepository.save(newVariantProduct);
	}
	
	
}
