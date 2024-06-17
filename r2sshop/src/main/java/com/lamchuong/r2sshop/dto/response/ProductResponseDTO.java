package com.lamchuong.r2sshop.dto.response;

import com.lamchuong.r2sshop.model.Product;
import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String categoryName;

    public ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.categoryName = product.getCategory().getName();
    }
}
