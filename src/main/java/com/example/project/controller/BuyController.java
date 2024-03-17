package com.example.project.controller;
import com.example.project.configurations.JwtLocalStorage;
import com.example.project.dto.UserDto;
import com.example.project.model.Client;
import com.example.project.model.Project;
import com.example.project.model.User;
import com.example.project.repositories.ClientRepository;
import com.example.project.repositories.ProjectRepo;
import com.example.project.repositories.UserRepository;
import com.example.project.service.EmailSenderService;
import com.example.project.service.JwtService;
import com.example.project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BuyController {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private EmailSenderService senderService;

    @ResponseBody
    @RequestMapping(value = "/projettest", method = RequestMethod.GET)
    public ResponseEntity<Object> save() {

        List<Project> projets = projectRepo.findAllProjectsWithClient();
        return new ResponseEntity<>(projets , HttpStatus.OK);
    }


    @GetMapping("/buywork/{workid}/{freelanceremail}")
    public String sendEmailToFreelancer(Authentication authentication, Model model,@PathVariable String workid ,@PathVariable String freelanceremail,Principal principal) {
        System.out.println("email is1 "+freelanceremail);

        senderService.sendSimpleEmail(freelanceremail,
                "noreply",
                "<html>" +
                        "<body>" +
                        "Client "+ principal.getName()  +" would like to purchase your work "+ workid + " " +
                        "<br>" +
                        "<a href='http://localhost:8081/'>Confirmer </a>" +
                        "<a href='http://localhost:8081/'>  Annuler</a>" +
                        "</body>" +
                        "</html>");
        System.out.println("email is "+freelanceremail);
        return "redirect:/";
    }

    @GetMapping("/applywork/{offreid}/{clientemail}")
    public String sendEmailToClient(Authentication authentication, Model model,@PathVariable String offreid ,@PathVariable String clientemail,Principal principal) {
        System.out.println("nabil "+clientemail);

        senderService.sendSimpleEmail(clientemail,
                "noreply",
                "<html>" +
                        "<body>" +
                        "Freelancer "+ principal.getName()  +" would like to work on your project "+ offreid + " " +
                        "<br>" +
                        "<a href='http://localhost:8081/'>Confirmer </a>" +
                        "<a href='http://localhost:8081/'>  Annuler</a>" +
                        "</body>" +
                        "</html>");
        System.out.println("email is "+clientemail);
        return "redirect:/";
    }

    @GetMapping("/buywork")
    public String afficherListProject2(Authentication authentication, Model model) {

        System.out.println("working2");
        return "about-us";
    }



    // Chunking method to break list into sublists of specified size

}
