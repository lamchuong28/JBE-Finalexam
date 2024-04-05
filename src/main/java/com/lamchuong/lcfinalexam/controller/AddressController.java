package com.lamchuong.lcfinalexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lamchuong.lcfinalexam.dto.response.AddressResponseDTO;
import com.lamchuong.lcfinalexam.exception.AddressNotFoundException;
import com.lamchuong.lcfinalexam.exception.UserAlreadyExistsException;
import com.lamchuong.lcfinalexam.exception.UserNotFoundException;
import com.lamchuong.lcfinalexam.exception.ValidationException;
import com.lamchuong.lcfinalexam.model.Address;
import com.lamchuong.lcfinalexam.service.AddressService;
import com.lamchuong.lcfinalexam.utils.ResponseCode;
import com.lamchuong.lcfinalexam.dto.request.AddressRequestDTO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/addresses")
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	@GetMapping(path = "")
	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllAddress() {
		return BaseResponseController.success(this.addressService.getAllAddress());
	}

	@PostMapping("")
	public ResponseEntity<?> addAddress(@RequestBody AddressRequestDTO address) throws ValidationException {
		try {
			return BaseResponseController.success(new AddressResponseDTO(this.addressService.addAddress(address)));
		} catch (UserNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.USER_NOT_FOUND.getCode(),
					ResponseCode.USER_NOT_FOUND.getMessage());
		}
	}
	
	@PutMapping(path = "")
	public ResponseEntity<?> updateAddress(@RequestBody Address newAddress) {
		try {
			Address updatedAddress = this.addressService.updateAddress(newAddress);
			return BaseResponseController.success(updatedAddress);
		} catch (UserAlreadyExistsException e) {
			return BaseResponseController.fail(ResponseCode.USER_ALREADY_EXISTS.getCode(),
					ResponseCode.USER_ALREADY_EXISTS.getMessage());
		} catch (UserNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.USER_NOT_FOUND.getCode(),
					ResponseCode.USER_NOT_FOUND.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		}
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteAddress(@PathVariable(name = "id") int id) {
		try {
			return BaseResponseController.success(this.addressService.deleteAddress(id));
		} catch (AddressNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.ADDRESS_NOT_FOUND.getCode(),
					ResponseCode.ADDRESS_NOT_FOUND.getMessage());
		}
	}
	

}
