package com.lamchuong.r2sshop.controller;

import com.lamchuong.r2sshop.dto.request.AddressRequestDTO;
import com.lamchuong.r2sshop.dto.response.AddressResponseDTO;
import com.lamchuong.r2sshop.model.Address;
import com.lamchuong.r2sshop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<?> getAllAddresses() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long userId = getUserIdFromUserDetails(userDetails);

        List<Address> addresses = addressService.getAllAddresses(userId);
        List<AddressResponseDTO> responseDTOs = addresses.stream()
                .map(AddressResponseDTO::new)
                .collect(Collectors.toList());

        return BaseResponseController.success(responseDTOs);
    }

    @PostMapping
    public ResponseEntity<?> addAddress(@RequestBody AddressRequestDTO addressRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long userId = getUserIdFromUserDetails(userDetails);

        Address address = new Address();
        address.setDetailAddress(addressRequestDTO.getDetailAddress());

        Address savedAddress = addressService.addAddress(address, userId);
        AddressResponseDTO responseDTO = new AddressResponseDTO(savedAddress);

        return BaseResponseController.success(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id, @RequestBody AddressRequestDTO addressRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long userId = getUserIdFromUserDetails(userDetails);

        Address address = addressService.getAddressById(id);
        address.setDetailAddress(addressRequestDTO.getDetailAddress());

        Address updatedAddress = addressService.updateAddress(address);
        AddressResponseDTO responseDTO = new AddressResponseDTO(updatedAddress);

        return BaseResponseController.success(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return BaseResponseController.success("Address deleted successfully");
    }

    private Long getUserIdFromUserDetails(UserDetails userDetails) {
        // Implement logic to retrieve userId from UserDetails
        // This can be done by modifying UserDetails to include userId
        // or by retrieving user information from the database using username
        // Example:
        // return userService.findUserByUsername(userDetails.getUsername()).getId();
        return 1L; // Placeholder, replace with actual logic
    }
}
