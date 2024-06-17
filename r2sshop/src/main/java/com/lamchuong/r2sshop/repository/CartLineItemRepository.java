package com.lamchuong.r2sshop.repository;

import com.lamchuong.r2sshop.model.Cart;
import com.lamchuong.r2sshop.model.CartLine_Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartLineItemRepository extends JpaRepository<CartLine_Item, Long>{
    List<CartLine_Item> findByCartAndIsDeleted(Cart cart, boolean isDeleted);

    List<CartLine_Item> findByCartId(Long cartId);
}
