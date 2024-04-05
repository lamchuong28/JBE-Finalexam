package com.lamchuong.lcfinalexam.dto.response;

import java.util.Date;
import lombok.Data;

@Data
public class OrderResponseDTO {
	private String address;
	private Date deliveryTime;
	private Double totalPrice;
}
