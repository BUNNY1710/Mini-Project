package com.theatre.securityConfig;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.theatre.model.Theatre;

public class RegisteredTheatre implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	Theatre theatre;
	public RegisteredTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return theatre.getPassword();
	}

	@Override
	public String getUsername() {
		return theatre.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
