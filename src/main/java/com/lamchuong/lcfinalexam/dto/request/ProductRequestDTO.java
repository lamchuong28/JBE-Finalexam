package com.lamchuong.lcfinalexam.dto.request;

import org.springframework.lang.NonNull;

import com.lamchuong.lcfinalexam.model.Product;
import lombok.Data;

@Data
public class ProductRequestDTO {
	@NonNull
	private String name;
	@NonNull
	private Integer categoryId;

	public Product toProduct() {
		Product product = new Product();
		product.setName(this.name);
		return product;
	}
}
