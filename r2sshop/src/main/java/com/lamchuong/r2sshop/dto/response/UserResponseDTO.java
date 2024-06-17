package com.lamchuong.r2sshop.dto.response;

import com.lamchuong.r2sshop.model.User;
import lombok.Data;

@Data
public class UserResponseDTO {
	private Long id;
	private String username;
	private String email;
	private String fullName;

	public UserResponseDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.fullName = user.getFullName();
	}
}
