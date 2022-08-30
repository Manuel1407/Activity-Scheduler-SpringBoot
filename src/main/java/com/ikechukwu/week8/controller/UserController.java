package com.ikechukwu.week8.controller;

import com.ikechukwu.week8.dto.UserDTO;
import com.ikechukwu.week8.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    UserService userservice;

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

}
