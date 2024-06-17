package com.lamchuong.r2sshop.dto.response;

import com.lamchuong.r2sshop.model.Cart;
import lombok.Data;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Data
public class CartResponseDTO {
    private Long id;
    private LocalDateTime createdDate;
    private Long userId;
    private String totalPrice;
    private List<CartLineItemResponseDTO> cartLineItems;

    public CartResponseDTO(Cart cart, BigDecimal totalPrice) {
        this.id = cart.getId();
        this.createdDate = cart.getCreatedDate();
        this.userId = cart.getUser().getId();
        this.totalPrice = formatCurrency(totalPrice);
        this.cartLineItems = cart.getCartLineItemList().stream()
                .map(CartLineItemResponseDTO::new)
                .collect(Collectors.toList());
    }

    private String formatCurrency(BigDecimal price){
        NumberFormat currencyFormatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return currencyFormatter.format(price) + "đ";
    }
}
