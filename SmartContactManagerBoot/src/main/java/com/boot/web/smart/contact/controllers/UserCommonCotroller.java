package com.boot.web.smart.contact.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserCommonCotroller {


	@RequestMapping("/user/userDashBoard")
	public String userdashboard(Model m) {
         m.addAttribute("title", "DashBoard - SmartContactManager");
		return "user/userDashBoard";
	}
}

