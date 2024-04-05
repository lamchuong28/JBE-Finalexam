package com.lamchuong.lcfinalexam.dto.request;

import org.springframework.lang.NonNull;

import com.lamchuong.lcfinalexam.model.Category;

import lombok.Data;

@Data
public class CategoryRequestDTO {
	@NonNull
	private String name;
	@NonNull
	private String description;

	public Category toCategory() {
		Category category = new Category();

		category.setName(this.name);
		category.setDescription(this.description);

		return category;
	}
}
