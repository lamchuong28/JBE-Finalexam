package com.lamchuong.r2sshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "table_cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Chỉ tính toán hashCode dựa trên các trường được chỉ định
@ToString(exclude = "user") // Tránh vòng lặp trong toString
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "created_date", nullable = false)
	private LocalDateTime createdDate;

	@PrePersist
	protected void onCreate() {
		this.createdDate = LocalDateTime.now();
	}

	@OneToOne
	@JoinColumn(name = "User_id", referencedColumnName = "id")
	private User user;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CartLine_Item> cartLineItemList;
}
