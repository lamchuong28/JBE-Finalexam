package com.lamchuong.r2sshop.dto.response;

import com.lamchuong.r2sshop.model.Order;
import lombok.Data;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Data
public class OrderResponseDTO {
    private Long id;
    private String address;
    private Date deliveryTime;
    private String totalPrice;
    private List<CartLineItemResponseDTO> cartLineItems;

    public OrderResponseDTO(Order order) {
        this.id = order.getId();
        this.address = order.getAddress();
        this.deliveryTime = order.getDeliveryTime();
        this.totalPrice = formatCurrency(BigDecimal.valueOf(order.getTotalPrice()));
        this.cartLineItems = order.getCartLineItems().stream()
                .map(CartLineItemResponseDTO::new)
                .collect(Collectors.toList());
    }

    private String formatCurrency(BigDecimal price){
        NumberFormat currencyFormatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return currencyFormatter.format(price) + "đ";
    }
}
