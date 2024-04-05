package com.lamchuong.lcfinalexam.service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamchuong.lcfinalexam.dto.request.CartLineItemRequestDTO;
import com.lamchuong.lcfinalexam.exception.CartNotFoundException;
import com.lamchuong.lcfinalexam.exception.ValidationException;
import com.lamchuong.lcfinalexam.exception.VariantProductNotFoundException;
import com.lamchuong.lcfinalexam.model.Cart;
import com.lamchuong.lcfinalexam.model.VariantProduct;
import com.lamchuong.lcfinalexam.model.CartLine_Item;
import com.lamchuong.lcfinalexam.repository.CartRepository;
import com.lamchuong.lcfinalexam.repository.VariantProductRepository;
import com.lamchuong.lcfinalexam.repository.CartLineItemRepository;

@Service
public class CartLineItemService {
	@Autowired
	private CartLineItemRepository cartLineItemRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private VariantProductRepository variantProductRepository;

	private void validateCartLine_Item(CartLineItemRequestDTO cartLineItemDTO) throws ValidationException {
		if (Objects.isNull(cartLineItemDTO)) {
			throw new ValidationException("cartLineItem is null");
		}
		if (Objects.isNull(cartLineItemDTO.getQuantity()) || cartLineItemDTO.getQuantity()< 0) {
			throw new ValidationException("product.name must be positive");
		}
		if (Objects.isNull(cartLineItemDTO.getTotalPrice()) || cartLineItemDTO.getTotalPrice() < 0) {
			throw new ValidationException("product.name must be positive");
		}
	}

	public CartLine_Item addCartLineItem(CartLineItemRequestDTO dto)
			throws ValidationException, CartNotFoundException, VariantProductNotFoundException {
		this.validateCartLine_Item(dto);

		Optional<Cart> foundCartOptional = this.cartRepository.findById(dto.getCartId());
		if (foundCartOptional.isEmpty()) {
			throw new CartNotFoundException();
		}
		Cart foundCart = foundCartOptional.get();

		Optional<VariantProduct> foundVariantProductOptional = this.variantProductRepository
				.findById(dto.getVatiantProductId());
		if (foundVariantProductOptional.isEmpty()) {
			throw new VariantProductNotFoundException();
		}
		VariantProduct foundVariantProduct = foundVariantProductOptional.get();

		CartLine_Item cartLine_Item = new CartLine_Item();

		cartLine_Item.setCart(foundCart);
		cartLine_Item.setVariantProduct(foundVariantProduct);

		cartLine_Item.setQuantity(new Integer(0));
		cartLine_Item.setTotalPrice((foundVariantProduct.getPrice()) * (cartLine_Item.getQuantity()));
		cartLine_Item.setAddedDate(new Date());

		return this.cartLineItemRepository.save(cartLine_Item);
	}
}
