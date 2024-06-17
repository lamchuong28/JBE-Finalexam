package com.lamchuong.r2sshop.dto.response;

import com.lamchuong.r2sshop.model.VariantProduct;
import lombok.Data;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Data
public class VariantProductResponseDTO {
    private Long id;
    private String color;
    private String size;
    private String model;
    private String price;

    public VariantProductResponseDTO(VariantProduct variantProduct) {
        this.id = variantProduct.getId();
        this.color = variantProduct.getColor();
        this.size = variantProduct.getSize();
        this.model = variantProduct.getModel();
        this.price = formatCurrency(variantProduct.getPrice());
    }

    private String formatCurrency(BigDecimal price){
        NumberFormat currencyFormatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return currencyFormatter.format(price) + "đ";
    }
}
