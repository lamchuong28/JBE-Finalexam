package com.lamchuong.lcfinalexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lamchuong.lcfinalexam.dto.request.VariantProductRequestDTO;
import com.lamchuong.lcfinalexam.dto.response.VariantProductResponseDTO;
import com.lamchuong.lcfinalexam.exception.ValidationException;
import com.lamchuong.lcfinalexam.exception.VariantProductNotFoundException;
import com.lamchuong.lcfinalexam.model.VariantProduct;
import com.lamchuong.lcfinalexam.service.VariantProductSevice;
import com.lamchuong.lcfinalexam.utils.ResponseCode;

@RestController
@RequestMapping(path = "/variantProduct")
public class VariantProductController {
	@Autowired
	private VariantProductSevice variantProductSevice;
	
	@GetMapping(path = "")
	public ResponseEntity<?> getAllVarianProduct(){
		return BaseResponseController.success(this.variantProductSevice.getAllVarianProduct());
	}
	
	@PostMapping("")
	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addVariantProduct(@RequestBody VariantProductRequestDTO variantProductDTO) throws ValidationException, VariantProductNotFoundException {
		try {
			return BaseResponseController.success(new VariantProductResponseDTO(this.variantProductSevice.addVariantProduct(variantProductDTO)));
		} catch (VariantProductNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.VARIANTPRODUCT_NOT_FOUND.getCode(),
					ResponseCode.VARIANTPRODUCT_NOT_FOUND.getMessage());
		}
	}
	
	@PutMapping(path = "")
	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateVariantProduct(@RequestBody VariantProduct newVariantProduct) {
		try {
			VariantProduct updatedVariantProduct = this.variantProductSevice.updateVariantProduct(newVariantProduct);
			return BaseResponseController.success(updatedVariantProduct);
		} catch (VariantProductNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.VARIANTPRODUCT_NOT_FOUND.getCode(),
					ResponseCode.VARIANTPRODUCT_NOT_FOUND.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		}
	}
}
