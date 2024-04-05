package com.lamchuong.lcfinalexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lamchuong.lcfinalexam.dto.request.CartLineItemRequestDTO;
import com.lamchuong.lcfinalexam.exception.CartNotFoundException;
import com.lamchuong.lcfinalexam.exception.ValidationException;
import com.lamchuong.lcfinalexam.exception.VariantProductNotFoundException;
import com.lamchuong.lcfinalexam.service.CartLineItemService;
import com.lamchuong.lcfinalexam.utils.ResponseCode;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/cartlineitems")
public class CartLineItemController {
	@Autowired
	private CartLineItemService cartLineItemService;

	@PostMapping("")
	public ResponseEntity<?> addCartLineItem(@RequestBody CartLineItemRequestDTO dto) {
		try {
			return BaseResponseController.success(this.cartLineItemService.addCartLineItem(dto));
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		} catch (CartNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.CART_NOT_FOUND.getCode(),
					ResponseCode.CART_NOT_FOUND.getMessage());
		} catch (VariantProductNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.VARIANTPRODUCT_NOT_FOUND.getCode(),
					ResponseCode.VARIANTPRODUCT_NOT_FOUND.getMessage());
		}
	}

}
