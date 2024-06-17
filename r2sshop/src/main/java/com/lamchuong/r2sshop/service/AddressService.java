package com.lamchuong.r2sshop.service;

import com.lamchuong.r2sshop.model.Address;
import com.lamchuong.r2sshop.model.User;
import com.lamchuong.r2sshop.repository.AddressRepository;
import com.lamchuong.r2sshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    //
    public List<Address> getAllAddresses(Long userId) {
        return addressRepository.findByUserId(userId);
    }

    //
    public Address addAddress(Address address, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        address.setUser(user);
        return addressRepository.save(address);
    }

    //
    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }

    //
    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }

    public Address getAddressById(Long addressId) {
        return addressRepository.findById(addressId).orElseThrow(() -> new RuntimeException("Address not found"));
    }
}
