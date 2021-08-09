package com.boot.web.smart.contact.springsecurity.config;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.boot.web.smart.contact.Entitys.TblUserLogin;
import com.boot.web.smart.contact.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	HttpSession session;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		 TblUserLogin user = userRepository.getUserByEmail(username);
		 if(user==null)
		 {
			 throw new UsernameNotFoundException("Could't found the user !!");
		 }
		 session.setAttribute("user", user);
		 CustomUserDetails customUserDetails=new CustomUserDetails(user);
		 return customUserDetails;
	}

}
