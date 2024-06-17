package com.lamchuong.r2sshop.dto.response;

import com.lamchuong.r2sshop.model.CartLine_Item;
import lombok.Data;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

@Data
public class CartLineItemResponseDTO {
    private Long id;
    private String variantProductName;
    private Integer quantity;
    private String price;
    private String totalPrice;
    private LocalDateTime addedDate;

    public CartLineItemResponseDTO(CartLine_Item cartLineItem) {
        this.id = cartLineItem.getId();
        this.variantProductName = cartLineItem.getVariantProduct().getProduct().getName()
                + " - " + cartLineItem.getVariantProduct().getColor()
                + " - " + cartLineItem.getVariantProduct().getSize();
        this.quantity = cartLineItem.getQuantity().intValue();
        this.price = formatCurrency(cartLineItem.getVariantProduct().getPrice());
        this.totalPrice = formatCurrency(cartLineItem.getTotalPrice());
        this.addedDate = cartLineItem.getAddedDate();
    }

    private String formatCurrency(BigDecimal price){
        NumberFormat currencyFormatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return currencyFormatter.format(price) + "đ";
    }
}
