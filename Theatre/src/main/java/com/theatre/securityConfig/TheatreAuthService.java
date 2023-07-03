package com.theatre.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.theatre.Exception.TheatreNotFoundException;
import com.theatre.model.Theatre;
import com.theatre.repository.TheatreRepository;

@Service
public class TheatreAuthService implements UserDetailsService{
	
	@Autowired
	TheatreRepository theatreRepository;

	@Override
	public UserDetails loadUserByUsername(String theatreName) throws TheatreNotFoundException {
		
		Theatre theatre = theatreRepository.findByName(theatreName);
		
		if(theatre == null) {
			throw new TheatreNotFoundException("Theater not found with Name " + theatreName);
		}
		
		return new RegisteredTheatre(theatre);
	}
}
