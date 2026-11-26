package com.arohau.web;

import com.arohau.service.DuplicateRegistrationException;
import com.arohau.service.RegistrationForm;
import com.arohau.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class AuthController {
    private final RegistrationService registrationService;

    public AuthController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registrationForm") RegistrationForm form,
                           BindingResult bindingResult) {
        if (!Objects.equals(form.getPassword(), form.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "password.mismatch", "Passwords do not match.");
        }

        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        try {
            registrationService.register(form);
        } catch (DuplicateRegistrationException exception) {
            bindingResult.rejectValue(exception.getField(), "duplicate", exception.getMessage());
            return "auth/register";
        }

        return "redirect:/login?registered";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("roles", currentUser.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .toList());
        return "auth/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "auth/admin";
    }
}
