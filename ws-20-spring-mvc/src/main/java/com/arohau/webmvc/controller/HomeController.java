package com.arohau.webmvc.controller;

import com.arohau.webmvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class HomeController {

	@ModelAttribute("user")
	public User userModel(){
		return new User();
	}

	@GetMapping
	public String home(Locale locale, Model model) {
		System.out.println("HomeController GET /");

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		return "home";
	}

	@GetMapping("/home")
	public String home(@RequestParam(required = false, name = "serverMessage") String serverMessage, Model model) {
		System.out.println("HomeController GET /home");
		model.addAttribute("serverMessage", serverMessage);
		model.addAttribute("user", model.getAttribute("user"));
		return "home";
	}

	@GetMapping("/ae")
	public String generateArithmeticException(Model model) {
		throwArithmeticException();
		return "home";
	}
	private static void throwArithmeticException() {
		throw new ArithmeticException("My ArithmeticException");
	}

	@GetMapping("/npe")
	public String generateNullPointerException(Model model) {
		throwNullPointerException();
		return "home";
	}
	private static void throwNullPointerException() {
		throw new NullPointerException("My NullPointerException");
	}

	@ExceptionHandler(ArithmeticException.class)
	public String handleArithmeticException(Model model) {
		System.out.println("ArithmeticException handled");
		model.addAttribute("msg", "ArithmeticException");
		return "error";
	}
}
