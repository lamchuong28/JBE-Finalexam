package com.lamchuong.r2sshop.dto.request;

import lombok.Data;

@Data
public class CartLineItemRequestDTO {
    private Long variantProductId;
    private Integer quantity;
}
