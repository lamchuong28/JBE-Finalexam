package com.lamchuong.lcfinalexam.dto.request;

import java.util.Date;

import org.springframework.lang.NonNull;

import lombok.Data;

@Data
public class OrderRequestDTO {
	@NonNull
	private Integer userId;
	@NonNull
	private String address;
	private Date deliveryTime;
	private Double totalPrice;

}
