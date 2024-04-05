package com.lamchuong.lcfinalexam.dto.request;

import java.util.Date;
import java.util.Objects;

import org.springframework.lang.NonNull;

import com.lamchuong.lcfinalexam.model.Cart;
import com.lamchuong.lcfinalexam.model.User;

import lombok.Data;

@Data
public class UserRequestDTO {
	@NonNull
	private String userName;
	@NonNull
	private String passWord;
	@NonNull
	private String fullName;
	@NonNull
	private String email;
	
	@NonNull
	private Date createdDate;

	public User toUser() {
		User user = new User();
		
		user.setUserName(this.userName);
		user.setFullName(this.fullName);
		user.setEmail(this.email);
		
		if (Objects.nonNull(createdDate)) {
			Cart cart = new Cart();
			cart.setCreatedDate(createdDate);
			
			user.setCart(cart);
		}

		return user;
	}
}
