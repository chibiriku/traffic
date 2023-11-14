package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.Users;
import com.example.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService service;

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

		Users loginUser = service.findLoginUser(mail);

		if(loginUser == null) {
			throw new UsernameNotFoundException("user not found");
		}

		String role = loginUser.getRole();

		List<GrantedAuthority> authorities = new ArrayList<>();

		if("2".equals(role)) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}


		return new org.springframework.security.core.userdetails.User(
	            loginUser.getMail(),
	            loginUser.getPassword(),
	            authorities
	    );
	}
}
