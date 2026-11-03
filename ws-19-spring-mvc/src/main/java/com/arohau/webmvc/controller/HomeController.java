package com.arohau.webmvc.controller;

import com.arohau.webmvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class HomeController {
//
//	@ModelAttribute("user")
//	public User userModel(){
//		return new User();
//	}

	@GetMapping("/")
	public String home(Locale locale, Model model) {
		System.out.println("HomeController GET /");

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		return "home";
	}

	@GetMapping("/home")
	public String home(Model model) {
		System.out.println("HomeController GET /home");
		model.addAttribute("username", "John Doe");
		return "home";
	}
}
