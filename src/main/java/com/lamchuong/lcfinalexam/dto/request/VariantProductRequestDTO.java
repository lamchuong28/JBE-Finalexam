package com.lamchuong.lcfinalexam.dto.request;

import org.springframework.lang.NonNull;

import com.lamchuong.lcfinalexam.model.VariantProduct;

import lombok.Data;
@Data
public class VariantProductRequestDTO {
	@NonNull
	private String color;
	@NonNull
    private String size;
	@NonNull
    private String model;
	@NonNull
    private Double price;
	
	@NonNull
	private Integer productId;

	public VariantProduct toVariantProduct() {
		VariantProduct variantProduct = new VariantProduct();

		variantProduct.setColor(this.color);
		variantProduct.setSize(this.size);
		variantProduct.setModel(this.model);
		variantProduct.setPrice(this.price);
		
		return variantProduct;
	}
}
