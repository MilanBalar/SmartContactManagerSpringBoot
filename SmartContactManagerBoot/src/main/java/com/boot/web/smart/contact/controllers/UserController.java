package com.boot.web.smart.contact.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.web.smart.contact.Entitys.TblUserLogin;
import com.boot.web.smart.contact.messageshelper.MessagesHelper;
import com.boot.web.smart.contact.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String home(Model m) {
         m.addAttribute("title", "Home - SmartContactManager");
		return "home";
	}

	@GetMapping("/signUp")
	public String signingPage(Model m) {
         m.addAttribute("title", "Signing - SmartContactManager");
         m.addAttribute("user", new TblUserLogin());
		 return "signUp";
	}
	@PostMapping("/saveUser")
	public String saveUserPage(@Valid @ModelAttribute("user") TblUserLogin user,BindingResult result,@RequestParam(value = "isChecked",defaultValue = "false") boolean isChecked,HttpSession session,Model m) {
        try {
        	TblUserLogin results=null;
        	 if(!isChecked) {
        		 throw new Exception("you have not agreed the terms and conditions !!");
        	 }
             if(result.hasErrors()) {
            	 m.addAttribute("user", user);
            	 System.out.println("Error is:::"+result.toString());
            	 return "signUp";
             }
             m.addAttribute("title", "Signing - SmartContactManager");
             user.setImageUrl("/default.png");
             user.setUserRole("USER");
             user.setActive(true);
             if(!ObjectUtils.isEmpty(user) && !ObjectUtils.isEmpty(user)) {
            	 results = this.userRepository.save(user);
              }
             m.addAttribute("user", results);
             session.setAttribute("message", new MessagesHelper("Registration Successfully !!", "alert-success"));
    		 return "signIn";

		} catch (Exception e) {
			session.setAttribute("message", new MessagesHelper("Something wrong !!", "alert-danger"));
			e.printStackTrace();
			return "signUp";


		}
	}

}
