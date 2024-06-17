package com.lamchuong.r2sshop.dto.request;

import com.lamchuong.r2sshop.model.Product;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class ProductRequestDTO {
    private String name;
    private Long categoryId;
}
