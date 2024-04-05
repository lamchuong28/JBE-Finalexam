package com.lamchuong.lcfinalexam.dto.response;

import java.util.List;
import java.util.Objects;

import com.lamchuong.lcfinalexam.model.Category;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CategoryResponseDTO {
	private Integer id;
	private String name;
	private String description;
	
	private List<Product> products;

	public CategoryResponseDTO(Category category) {
		this.id = category.getId();
		this.name = category.getName();
		this.description = category.getDescription();
		
		this.products = Product.toProduct(category);
	}
	
	@Data
	@AllArgsConstructor
	private static class Product {
		private Integer productId;
		private String productName;

		public static List<Product> toProduct(Category category) {
			return category.getProducts().stream().filter(Objects::nonNull)
					.map(product -> new Product(product.getId(), product.getName())).toList();
		}
	}
	
}