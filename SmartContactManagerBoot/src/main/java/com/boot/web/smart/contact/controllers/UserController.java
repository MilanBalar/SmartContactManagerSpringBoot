package com.boot.web.smart.contact.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.boot.web.smart.contact.Entitys.TblContactDetails;
import com.boot.web.smart.contact.Entitys.TblUserLogin;
import com.boot.web.smart.contact.messageshelper.MessagesHelper;
import com.boot.web.smart.contact.repository.ContactRepository;
import com.boot.web.smart.contact.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;


	@ModelAttribute
	public void addCommonUser(Model m,HttpSession session) {
		TblUserLogin user = (TblUserLogin) session.getAttribute("user");
        m.addAttribute("user", user);
	}


	@PostMapping("/process-contact")
	public String processContact(@ModelAttribute("contact") TblContactDetails contact,@RequestParam("profileImage") MultipartFile file ,Model m,HttpSession session,Principal principal) {

       try {
    	   if(file.isEmpty())
    	   {
      		 throw new Exception("File is Empty !!");
    	   }
    	   File saveFile=new ClassPathResource("static/images").getFile();

    	   Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());

    	   Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
    	   System.out.println("file Uploaded");

           String name= principal.getName();
           TblUserLogin tblUserLogin = userRepository.getUserByEmail(name);

           TblContactDetails results=null;
           if(!ObjectUtils.isEmpty(contact) && !ObjectUtils.isEmpty(contact)) {
        	  contact.setImage(file.getOriginalFilename());
			  contact.setTblUserLogin(tblUserLogin);
          	 results = this.contactRepository.save(contact);
            }

           session.setAttribute("message", new MessagesHelper("Contact is added !! add more.. ", "alert-success"));

   		return "user/addContact";

	} catch (Exception e) {
		session.setAttribute("message", new MessagesHelper("Something wrong !!", "alert-danger"));
		return "user/addContact";
	}
  }

	@GetMapping("/addContact")
	public String addContact(Model m) {
        m.addAttribute("title", "Add Contact - SmartContactManager");
        m.addAttribute("contact", new TblContactDetails());
		return "user/addContact";
	}

	@GetMapping("/show-contact/{page}")
	public String showContact(Model m,Principal principal,@PathVariable("page") int page) {
        m.addAttribute("title", "View Contact - SmartContactManager");
        String name= principal.getName();
        TblUserLogin tblUserLogin = userRepository.getUserByEmail(name);

        //paggination
        Pageable pageable = PageRequest.of(page, 5);
        Page<TblContactDetails> contacts = contactRepository.getContactByUserId(tblUserLogin.getUserId(),pageable);

        m.addAttribute("contacts", contacts);
        m.addAttribute("currentPage", page);
        m.addAttribute("TotalPages", contacts.getTotalPages());

		return "user/show_contact";
	}

    @GetMapping("/userDashBoard")
	public String signInPost2(Model m,Principal principal) {

		/*
		 * //String name = principal.getName(); System.out.println("name is "+name);
		 *
		 * TblUserLogin user = userRepository.getUserByEmail(name);
		 * System.out.println("user is "+user);
		 */
		 m.addAttribute("title", "SignIn - SmartContactManager");
         return "user/userDashBoard";
	}

	@GetMapping("/")
	public String home(Model m) {
         m.addAttribute("title", "Home - SmartContactManager");
		return "home";
	}
	@PostMapping("/dologin")
	public String signInPost(Model m) {
         m.addAttribute("title", "SignIn - SmartContactManager");
         return "user/userDashBoard";
	}
	@GetMapping("/signIn")
	public String signIn(Model m) {
         m.addAttribute("title", "SignIn - SmartContactManager");
         return "signIn";
	}
	@GetMapping("/signUp")
	public String signingPage(Model m) {
         m.addAttribute("title", "SignUp - SmartContactManager");
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
             user.setPassword(passwordEncoder.encode(user.getPassword()));
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
