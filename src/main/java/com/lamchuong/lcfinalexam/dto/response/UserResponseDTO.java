package com.lamchuong.lcfinalexam.dto.response;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lamchuong.lcfinalexam.model.Cart;
import com.lamchuong.lcfinalexam.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserResponseDTO {
	private Integer id;
	private String userName;
	private String fullName;
	private String email;
	
	private List<Address> addresses;

	@JsonFormat(timezone = "Asia/Ho_Chi_Minh"
//			pattern = "dd-MM-yyyy"
	)
	private Date createdDate;

	public UserResponseDTO(User user) {
		this.id = user.getId();
		this.userName = user.getUserName();	
		this.fullName = user.getFullName();
		this.email = user.getEmail();

		Cart cart = user.getCart();
		if (Objects.nonNull(cart)) {
			this.createdDate = cart.getCreatedDate();
		}
		this.addresses = Address.toAddresses(user);
	}

	@Data
	@AllArgsConstructor
	private static class Address {
		private Integer addressId;
		private String detailAddress;

		public static List<Address> toAddresses(User user) {
			return user.getAddresses().stream().filter(Objects::nonNull)
					.map(address -> new Address(address.getId(), address.getDetailAddress())).toList();
		}
	}
}
