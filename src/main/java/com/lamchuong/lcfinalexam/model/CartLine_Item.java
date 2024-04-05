package com.lamchuong.lcfinalexam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "table_cartline_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartLine_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "added_date", nullable = false)
    private Date addedDate;

    @Column(columnDefinition = " bit default 0")
    private Boolean isDeleted = false;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @ManyToOne
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
	@JsonBackReference
	private Cart cart;

    @ManyToOne
    @JoinColumn(name = "variantProduct_id", referencedColumnName = "id")
    @JsonBackReference
    private VariantProduct variantProduct;

}