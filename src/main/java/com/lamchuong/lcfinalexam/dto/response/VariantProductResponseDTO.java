package com.lamchuong.lcfinalexam.dto.response;


import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.lamchuong.lcfinalexam.model.Product;
import com.lamchuong.lcfinalexam.model.VariantProduct;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
public class VariantProductResponseDTO {
	private Integer id;
	private String color;
    private String size;
    private String model;
    private Double price;
    
    private Integer productId;
    
    private List<CartLine_Item> cartLine_Items;
    
    public VariantProductResponseDTO (VariantProduct variantProduct) {
    	this.id = variantProduct.getId();
    	this.color = variantProduct.getColor();
    	this.size = variantProduct.getSize();
    	this.model = variantProduct.getModel();
    	this.price = variantProduct.getPrice();
    	
    	Product product = variantProduct.getProduct();
    	if(Objects.nonNull(product)) {
    		this.productId = product.getId();
    	}
    	
    	this.cartLine_Items = CartLine_Item.toCartLine_Item(variantProduct);
    }
    
    @Data
    @AllArgsConstructor
    private static class CartLine_Item{
    	private Integer cartLineItemsId;
        private Integer quantity;
        private Double totalPrice;
        private Date addedDate;
        private Boolean isDeleted;
        
        public static List<CartLine_Item> toCartLine_Item(VariantProduct variantProduct){
        	return variantProduct.getCartLineItemList().stream().filter(Objects::nonNull)
        			.map(cartLine_Items -> new CartLine_Item(cartLine_Items.getId(), cartLine_Items.getQuantity()
        					, cartLine_Items.getTotalPrice(), cartLine_Items.getAddedDate(), cartLine_Items.getIsDeleted())).toList();
        }


    }
}
