package com.lamchuong.r2sshop.dto.response;

import com.lamchuong.r2sshop.model.Address;
import lombok.Data;

@Data
public class AddressResponseDTO {
    private Long id;
    private String detailAddress;

    public AddressResponseDTO(Address address) {
        this.id = address.getId();
        this.detailAddress = address.getDetailAddress();
    }
}
