package com.lamchuong.r2sshop.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

	SUCCESS("200", "Success"),
	USER_ALREADY_EXISTS("409", "User already exists"),
	USER_NOT_FOUND("404", "User not found"),
	INVALID_CREDENTIALS("401", "Invalid credentials"),
	UNKNOWN_ERROR("500", "Unknown error"),

	PRODUCT_NOT_FOUND("product.notfound", "product not found"),
	CART_NOT_FOUND("cart.notfound", "cart not found"),
	ADDRESS_NOT_FOUND("address.notfound", "address not found"),
	CATEGORY_NOT_FOUND("category.notfound", "category not found"),
	VARIANTPRODUCT_NOT_FOUND("variantProduct.notfound", "variantProduct not found"),
	
	VALIDATION_EXCEPTION("validation", null);

	private final String code;
	private final String message;
}
