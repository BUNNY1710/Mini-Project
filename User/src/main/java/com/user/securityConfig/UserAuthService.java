package com.user.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.user.Exception.UserNotFoundException;
import com.user.model.User;
import com.user.repository.UserRepository;

@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UserNotFoundException {

		User user = userRepository.findByEmail(username);
		
		if (user == null) {
			throw new UserNotFoundException("User not found with Name " + username);
		}
		
		System.out.println("user to log is " + user);
		return new RegisteredUser(user);
	}
}
