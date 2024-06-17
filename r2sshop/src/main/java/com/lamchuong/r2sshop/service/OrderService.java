package com.lamchuong.r2sshop.service;

import com.lamchuong.r2sshop.dto.request.OrderRequestDTO;
import com.lamchuong.r2sshop.dto.response.OrderResponseDTO;
import com.lamchuong.r2sshop.model.Cart;
import com.lamchuong.r2sshop.model.CartLine_Item;
import com.lamchuong.r2sshop.model.Order;
import com.lamchuong.r2sshop.repository.CartLineItemRepository;
import com.lamchuong.r2sshop.repository.CartRepository;
import com.lamchuong.r2sshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartLineItemRepository cartLineItemRepository;

    @Transactional
    public OrderResponseDTO createOrder(Long userId, OrderRequestDTO orderRequestDTO) {
        // Lấy giỏ hàng của người dùng
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        if (optionalCart.isEmpty()) {
            throw new RuntimeException("Cart not found");
        }

        Cart cart = optionalCart.get();

        // Tính tổng giá của giỏ hàng
        BigDecimal totalPrice = cart.getCartLineItemList().stream()
                .filter(cartLineItem -> !cartLineItem.getIsDeleted())
                .map(CartLine_Item::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Tạo đối tượng Order
        Order order = new Order();
        order.setAddress(orderRequestDTO.getAddress());
        order.setDeliveryTime(orderRequestDTO.getDeliveryTime());
        order.setTotalPrice(totalPrice.doubleValue());

        // Tách rời các CartLine_Item từ Cart và gắn kết với Order
        List<CartLine_Item> orderCartLineItems = cart.getCartLineItemList().stream()
                .map(cartLineItem -> {
                    CartLine_Item newItem = new CartLine_Item();
                    newItem.setCart(null);
                    newItem.setOrder(order);
                    newItem.setVariantProduct(cartLineItem.getVariantProduct());
                    newItem.setQuantity(cartLineItem.getQuantity());
                    newItem.setTotalPrice(cartLineItem.getTotalPrice());
                    newItem.setAddedDate(cartLineItem.getAddedDate());
                    newItem.setIsDeleted(true);
                    return newItem;
                }).collect(Collectors.toList());

        order.setCartLineItems(orderCartLineItems);

        // Lưu order vào cơ sở dữ liệu
        Order savedOrder = orderRepository.save(order);

        // Cập nhật các mục giỏ hàng để xóa mềm
        cart.getCartLineItemList().forEach(cartLineItem -> {
            cartLineItem.setIsDeleted(true);
            cartLineItemRepository.save(cartLineItem);
        });

        return new OrderResponseDTO(savedOrder);
    }
}
