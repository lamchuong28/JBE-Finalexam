package com.lamchuong.lcfinalexam.dto.response;

import java.util.List;
import java.util.Objects;

import com.lamchuong.lcfinalexam.model.Category;
import com.lamchuong.lcfinalexam.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ProductResponseDTO {
	private Integer id;
	private String name;
	private Integer categoryId;

	private List<VariantProduct> variantProducts ;
	
	

		public ProductResponseDTO(Product product) {
			this.id = product.getId();
			this.name = product.getName();
			
			Category category = product.getCategory();
			if (Objects.nonNull(category)) {
				this.categoryId = category.getId();
			}
			
			this.variantProducts = VariantProduct.toVariantProduct(product);
		}

	@Data
	@AllArgsConstructor
	private static class VariantProduct {
		private Integer variantProductId;
		private String Color;
		private String Size;
		private String Model;
		private Double Price;

		public static List<VariantProduct> toVariantProduct(Product product) {
			return product.getVariantProducts().stream().filter(Objects::nonNull)
					.map(variantProduct -> new VariantProduct(variantProduct.getId()
							, variantProduct.getColor(), variantProduct.getSize()
							, variantProduct.getModel(), variantProduct.getPrice())).toList();
		}
	}
}
