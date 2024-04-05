package com.lamchuong.lcfinalexam.dto.request;

import org.springframework.lang.NonNull;

import com.lamchuong.lcfinalexam.model.Address;

import lombok.Data;

@Data
public class AddressRequestDTO {
	@NonNull
	private Integer userId;
	@NonNull
	private String detailAddress;

	public Address toAddress() {
		Address address = new Address();
		
		address.setDetailAddress(detailAddress);

		return address;
	}
}
