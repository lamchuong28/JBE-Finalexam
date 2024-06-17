package com.lamchuong.r2sshop.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lamchuong.r2sshop.model.User;
import com.lamchuong.r2sshop.repository.UserRepository;

@Service
public class CustomUserServiceDetail implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			return new CustomUserDetail(user.get());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
