package com.lamchuong.lcfinalexam.dto.response;

import java.util.Date;
import java.util.Objects;

import com.lamchuong.lcfinalexam.model.Cart;
import com.lamchuong.lcfinalexam.model.CartLine_Item;
import com.lamchuong.lcfinalexam.model.Order;
import com.lamchuong.lcfinalexam.model.VariantProduct;

import lombok.Data;
@Data
public class CartLineItemResponseDTO {
	private Integer id;
    private Integer quantity;
    private Double totalPrice;
    private Date addedDate;
    private Boolean isDeleted;
    
	private Integer cartId;
	private Integer vatiantProductId;
	private Integer orderId;
	
	public CartLineItemResponseDTO (CartLine_Item cartLine_Item) {
		this.id = cartLine_Item.getId();
		this.quantity = cartLine_Item.getQuantity();
		this.totalPrice = cartLine_Item.getTotalPrice();
		this.addedDate = cartLine_Item.getAddedDate();
		this.isDeleted = cartLine_Item.getIsDeleted();
		
		Cart cart = cartLine_Item.getCart();
		if(Objects.nonNull(cart)) {
			this.cartId = cart.getId();
		}
		
		VariantProduct variantProduct = cartLine_Item.getVariantProduct();
		if(Objects.nonNull(variantProduct)) {
			this.vatiantProductId = variantProduct.getId();
		}
		
		Order order = cartLine_Item.getOrder();
		if(Objects.nonNull(order)) {
			this.orderId = order.getId();
		}
	}


}
