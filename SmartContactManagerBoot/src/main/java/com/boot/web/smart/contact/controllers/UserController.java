package com.boot.web.smart.contact.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
		return "signing";
	}

}
