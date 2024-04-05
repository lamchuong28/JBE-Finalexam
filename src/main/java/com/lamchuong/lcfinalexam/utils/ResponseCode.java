package com.lamchuong.lcfinalexam.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
	SUCCESS("success", "success"),
	
	USER_ALREADY_EXISTS("user.exists", "user already exists"),
	
	USER_NOT_FOUND("user.notfound", "user not found"),
	PRODUCT_NOT_FOUND("product.notfound", "product not found"),
	CART_NOT_FOUND("cart.notfound", "cart not found"),
	ADDRESS_NOT_FOUND("address.notfound", "address not found"),
	CATEGORY_NOT_FOUND("category.notfound", "category not found"),
	VARIANTPRODUCT_NOT_FOUND("variantProduct.notfound", "variantProduct not found"),
	
	VALIDATION_EXCEPTION("validation", null);

	private String code;
	private String message;
}
