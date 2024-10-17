package edu.school21.todoapp.controllers;

import edu.school21.todoapp.models.User;
import edu.school21.todoapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginRegistrationLogoutController {
    private final UserService userService;

    public LoginRegistrationLogoutController(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    private String checkAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication.getPrincipal() instanceof String)) {
            return "redirect:/";
        }
        return null;
    }

    @GetMapping("/login")
    public String login() {
        String redirect = checkAuthentication();
        return (redirect != null) ? redirect : "login";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        String redirect = checkAuthentication();
        if (redirect != null) {
            return redirect;
        }
        model.addAttribute("user", new User());
        return "registration";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "logout";
    }


    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        String redirect = checkAuthentication();
        if (redirect != null) {
            return redirect;
        }

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (userService.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("error", "Пользователь с такими данными уже существует");
            return "registration";
        }

        userService.save(user);
        return "redirect:/login";
    }
}
