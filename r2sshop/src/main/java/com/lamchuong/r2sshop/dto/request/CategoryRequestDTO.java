package com.lamchuong.r2sshop.dto.request;

import com.lamchuong.r2sshop.model.Category;
import lombok.Data;
import org.springframework.lang.NonNull;

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
