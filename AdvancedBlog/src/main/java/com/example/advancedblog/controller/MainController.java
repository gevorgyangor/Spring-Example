package com.example.advancedblog.controller;

import com.example.advancedblog.model.User;
import com.example.advancedblog.model.UserType;
import com.example.advancedblog.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping(value = "/loginPage")
    public String login() {
        return "signIn";
    }

    @GetMapping("/loginSuccess")
    public String loginUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = ((CurrentUser) userDetails).getUser();
        if (user.getType() == UserType.ADMIN) {
            return "redirect:/admin";
        }

        return "redirect:/";
    }
}

