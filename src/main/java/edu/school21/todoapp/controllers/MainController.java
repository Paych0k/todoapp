package edu.school21.todoapp.controllers;

import edu.school21.todoapp.components.SecurityUtils;
import edu.school21.todoapp.models.User;
import edu.school21.todoapp.repository.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class MainController {


    private final SecurityUtils securityUtils;


    public MainController(UserRepository userRepository, SecurityUtils securityUtils) {
        this.securityUtils = securityUtils;
    }


    @GetMapping("/")
    public String hello(Model model) {
        User user = securityUtils.getCurrentUser();
        model.addAttribute("user", user);
        return "hello";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
