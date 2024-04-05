package com.lamchuong.lcfinalexam.dto.response;

import java.util.Objects;

import com.lamchuong.lcfinalexam.model.Address;
import com.lamchuong.lcfinalexam.model.User;

import lombok.Data;

@Data
public class AddressResponseDTO {
	private Integer id;
	private String detailAddress;
	private Integer userId;

	public AddressResponseDTO(Address address) {
		this.id = address.getId();
		this.detailAddress = address.getDetailAddress();
		
		User user = address.getUser();
		if (Objects.nonNull(user)) {
			this.userId = user.getId();
		}
	}
}
