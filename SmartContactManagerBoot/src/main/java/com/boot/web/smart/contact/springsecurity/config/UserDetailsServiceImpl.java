package com.boot.web.smart.contact.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.boot.web.smart.contact.Entitys.TblUserLogin;
import com.boot.web.smart.contact.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		 TblUserLogin user = userRepository.getUserByEmail(username);
		 if(user==null)
		 {
			 throw new UsernameNotFoundException("Could't found the user !!");
		 }
		 CustomUserDetails customUserDetails=new CustomUserDetails(user);
		 return customUserDetails;
	}

}
