package com.lamchuong.lcfinalexam.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamchuong.lcfinalexam.dto.request.AddressRequestDTO;
import com.lamchuong.lcfinalexam.dto.response.AddressResponseDTO;
import com.lamchuong.lcfinalexam.exception.AddressNotFoundException;
import com.lamchuong.lcfinalexam.exception.UserAlreadyExistsException;
import com.lamchuong.lcfinalexam.exception.UserNotFoundException;
import com.lamchuong.lcfinalexam.exception.ValidationException;
import com.lamchuong.lcfinalexam.model.Address;
import com.lamchuong.lcfinalexam.model.User;
import com.lamchuong.lcfinalexam.repository.AddressRepository;
import com.lamchuong.lcfinalexam.repository.UserRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;
	
	public List<AddressResponseDTO> getAllAddress() {
		return this.addressRepository.findAll().stream().map(AddressResponseDTO::new).toList();
	}
	
	private void validateAddress(Address address) throws ValidationException {
		if (Objects.isNull(address)) {
			throw new ValidationException("address is null");
		}
		if (Objects.isNull(address.getDetailAddress()) || address.getDetailAddress().isBlank()) {
			throw new ValidationException("user.name cannot be blank");
		}
	}
	
	private void validateAddress(AddressRequestDTO address) throws ValidationException {
		if (Objects.isNull(address)) {
			throw new ValidationException("address is null");
		}
		if (Objects.isNull(address.getDetailAddress()) || address.getDetailAddress().isBlank()) {
			throw new ValidationException("address.DetailAddress cannot be blank");
		}
	}

	public Address addAddress(AddressRequestDTO addressDTO) throws UserNotFoundException, ValidationException {
		this.validateAddress(addressDTO);

		Optional<User> foundUserOptional = this.userRepository.findById(addressDTO.getUserId());
		if (foundUserOptional.isEmpty()) {
			throw new UserNotFoundException();
		}
		User foundUser = foundUserOptional.get();
		Address address = addressDTO.toAddress();
		address.setUser(foundUser);
		return this.addressRepository.save(address);
	}
	
	public Address updateAddress(Address newAddress) throws ValidationException, UserNotFoundException, UserAlreadyExistsException {
		validateAddress(newAddress);

		Optional<Address> foundAddress = this.addressRepository.findById(newAddress.getId());
		if (foundAddress.isEmpty()) {
			throw new UserNotFoundException();
		}
		
		Address address = foundAddress.get();
		address.setDetailAddress(newAddress.getDetailAddress());
		address.setUser(newAddress.getUser());
		
		foundAddress = this.addressRepository.findByDetailAddress(newAddress.getDetailAddress());
		if (foundAddress.isPresent() && !newAddress.getId().equals(foundAddress.get().getId())) {
			throw new UserAlreadyExistsException();
		}

		return this.addressRepository.save(newAddress);
	}
	
	public boolean deleteAddress(Integer id) throws AddressNotFoundException {

		Optional<Address> foundAddress = this.addressRepository.findById(id);
		if (foundAddress.isEmpty()) {
			throw new AddressNotFoundException();
		}
		this.addressRepository.deleteById(id);
		return true;
	}
}
