package com.lamchuong.r2sshop.dto.response;

import com.lamchuong.r2sshop.model.Category;
import lombok.Data;


@Data
public class CategoryResponseDTO {
	private Long id;
	private String name;
	private String description;

	public CategoryResponseDTO(Category category) {
		this.id = category.getId();
		this.name = category.getName();
		this.description = category.getDescription();
	}
}