package com.boot.web.smart.contact.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.web.smart.contact.Entitys.TblUserLogin;

@Controller
public class UserController {

	@GetMapping("/")
	public String home(Model m) {
         m.addAttribute("title", "Home - SmartContactManager");
		return "home";
	}

	@GetMapping("/signing")
	public String signingPage(Model m) {
         m.addAttribute("title", "Signing - SmartContactManager");
         m.addAttribute("user", new TblUserLogin());
		return "signing";
	}
	@PostMapping("/saveUser")
	public String saveUserPage(@Valid @ModelAttribute("user") TblUserLogin user,BindingResult result,@RequestParam(value = "isChecked",defaultValue = "false") boolean isChecked,HttpSession session,Model m) {
         if(result.hasErrors()) {
        	 System.out.println("Error is:::"+result);
        	 return "signing";
         }
         m.addAttribute("title", "Signing - SmartContactManager");
         System.out.println(user.toString());
         System.out.println(isChecked);
         m.addAttribute("user", user);
		return "signing";
	}

}
