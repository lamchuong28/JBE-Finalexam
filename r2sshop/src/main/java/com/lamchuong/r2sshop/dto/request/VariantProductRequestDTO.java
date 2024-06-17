package com.lamchuong.r2sshop.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class VariantProductRequestDTO {
    private String color;
    private String size;
    private String model;
    private BigDecimal price;
    private Long productId;
}
