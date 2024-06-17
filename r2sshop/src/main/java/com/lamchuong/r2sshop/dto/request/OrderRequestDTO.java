package com.lamchuong.r2sshop.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class OrderRequestDTO {
    private String address;
    private Date deliveryTime;
}
