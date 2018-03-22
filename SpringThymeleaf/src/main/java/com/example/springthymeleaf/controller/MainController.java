package com.example.springthymeleaf.controller;

import com.example.springthymeleaf.model.User;
import com.example.springthymeleaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String mainPage(ModelMap map) {
        map.addAttribute("user", new User());
        map.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @PostMapping(value = "/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/deleteUser/{id}")
    public String delete(@PathVariable("id") int id) {
        userRepository.delete(id);
        return "redirect:/";
    }
}
