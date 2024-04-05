package com.lamchuong.lcfinalexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lamchuong.lcfinalexam.exception.ValidationException;
import com.lamchuong.lcfinalexam.service.CategoryService;
import com.lamchuong.lcfinalexam.utils.ResponseCode;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping(path = "")
	public ResponseEntity<?> getAllCategories() {
		return BaseResponseController.success(this.categoryService.getAllCategories());
	}
	
	@GetMapping(path = "/searchByName")
	public ResponseEntity<?> search(@RequestParam(name = "name", required = false, defaultValue = "") String name,Pageable pageable){
		try {
			return BaseResponseController.success(this.categoryService.findByName(name, pageable));
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		}
	}

}
