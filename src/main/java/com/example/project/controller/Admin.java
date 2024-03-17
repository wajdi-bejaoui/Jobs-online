package com.example.project.controller;

import com.example.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class Admin {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserDetailsService userDetailsService;

    @GetMapping("/Admin")
    public String GetFreelancer(Model model){
        model.addAttribute("admin", userRepository.findAll());
        return "interfaceAdmin";
    }
    @GetMapping("admin-page")
    public String adminPage (Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("admin", userDetails);
        return "interfaceAdmin";
    }


}
