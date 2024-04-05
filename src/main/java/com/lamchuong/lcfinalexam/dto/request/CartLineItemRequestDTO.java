package com.lamchuong.lcfinalexam.dto.request;

import java.util.Date;

import org.springframework.lang.NonNull;

import com.lamchuong.lcfinalexam.model.CartLine_Item;

import lombok.Data;

@Data
public class CartLineItemRequestDTO {
	@NonNull
	private Integer cartId;
	@NonNull
	private Integer vatiantProductId;
	@NonNull
	private Integer quantity;
	@NonNull
	private Double totalPrice;
	@NonNull
	private Date addedDate;
	
	public CartLine_Item toCartLineItem() {
		CartLine_Item cartLine_Item = new CartLine_Item();
		cartLine_Item.setQuantity(quantity);
		cartLine_Item.setTotalPrice(totalPrice);
		cartLine_Item.setAddedDate(addedDate);
		
		return cartLine_Item;
	}
}
