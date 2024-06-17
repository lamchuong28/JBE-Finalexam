package com.lamchuong.r2sshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "table_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Chỉ tính toán hashCode dựa trên các trường được chỉ định
@ToString(exclude = "users")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "col_name", nullable = false, unique = true)
	private String name;

	@Column(name = "col_description", nullable = false)
	private String description;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;
}
