package com.lamchuong.r2sshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "table_cartline_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartLine_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "added_date", nullable = false)
    private LocalDateTime addedDate;

    @Column(columnDefinition = "bit default 0")
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variantProduct_id", referencedColumnName = "id")
    private VariantProduct variantProduct;

    @PrePersist
    protected void onCreate() {
        this.addedDate = LocalDateTime.now();
    }
}
