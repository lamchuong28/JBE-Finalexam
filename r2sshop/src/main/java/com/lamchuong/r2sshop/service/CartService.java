package com.lamchuong.r2sshop.service;

import com.lamchuong.r2sshop.dto.request.CartLineItemRequestDTO;
import com.lamchuong.r2sshop.dto.response.CartResponseDTO;
import com.lamchuong.r2sshop.model.Cart;
import com.lamchuong.r2sshop.model.CartLine_Item;
import com.lamchuong.r2sshop.model.VariantProduct;
import com.lamchuong.r2sshop.repository.CartLineItemRepository;
import com.lamchuong.r2sshop.repository.CartRepository;
import com.lamchuong.r2sshop.repository.VariantProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private VariantProductRepository variantProductRepository;

    @Autowired
    private CartLineItemRepository cartLineItemRepository;

    @Transactional
    public CartResponseDTO addVariantProductToCart(Long userId, CartLineItemRequestDTO cartLineItemRequestDTO) {
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        if (optionalCart.isEmpty()) {
            throw new RuntimeException("Cart not found");
        }

        Cart cart = optionalCart.get();
        VariantProduct variantProduct = variantProductRepository.findById(cartLineItemRequestDTO.getVariantProductId())
                .orElseThrow(() -> new RuntimeException("VariantProduct not found"));

        CartLine_Item cartLineItem = new CartLine_Item();
        cartLineItem.setCart(cart);
        cartLineItem.setVariantProduct(variantProduct);
        cartLineItem.setQuantity(cartLineItemRequestDTO.getQuantity().longValue());
        cartLineItem.setTotalPrice(variantProduct.getPrice().multiply(BigDecimal.valueOf(cartLineItemRequestDTO.getQuantity())));

        CartLine_Item savedCartLineItem = cartLineItemRepository.save(cartLineItem);

        BigDecimal newTotalPrice = cart.getCartLineItemList().stream()
                .map(CartLine_Item::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CartResponseDTO(cart, newTotalPrice);
    }
}
