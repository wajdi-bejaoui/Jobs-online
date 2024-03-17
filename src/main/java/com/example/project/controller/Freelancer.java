package com.example.project.controller;

import com.example.project.configurations.JwtLocalStorage;
import com.example.project.dto.UserDto;
import com.example.project.model.*;
import com.example.project.repositories.ReviewRepository;
import com.example.project.repositories.UserRepository;
import com.example.project.repositories.WorkSampleRepo;
import com.example.project.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Freelancer {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;
@Autowired
private WorkSampleRepo workSampleRepo;

    @Autowired
    private UserDetailsService  userDetailsService;
    @Autowired
    private JwtService jwtService;
    @GetMapping("/quiz")
    public String getQuizPage(@ModelAttribute("quiz")UserDto userDto) {
        return "quiz";

    }
    @GetMapping("/ajoutwork")
    public String getProfil(Model model) {
        WorkSample work  = new WorkSample();
        model.addAttribute("work",work);
        return "addWorksampleFreelancer";

    }
    @PostMapping("/addworksample")
public String saveWork(@ModelAttribute("work") WorkSample work, Principal principal)
    {


        User user = userRepository.findByEmail(principal.getName());
        freelancer fr = new freelancer(user.getEmail(),user.getRole(), user.getFullname(),user.getAddresse(),user.getPhone(),user.getCountry(),user.getId());
        work.setFreelancer(fr);
        workSampleRepo.save(work);
        return "redirect:/freelancer-page";
    }


    @GetMapping("/listefreelancer")
    public String afficherListProject(Authentication authentication, Model model) {
//        User user = (User) authentication.getPrincipal();

        List<User> freelances = userRepository.findByRole("freelancer");
        List<List<User>> chunkedList = chunkList(freelances, 3); // Chunk the list into sublists of size 3


        model.addAttribute("chunkedList", chunkedList);

        return "listefreelancer";
    }

    private <T> List<List<T>> chunkList(List<T> list, int chunkSize) {
        List<List<T>> chunks = new ArrayList<>();
        for (int i = 0; i < list.size(); i += chunkSize) {
            chunks.add(list.subList(i, Math.min(i + chunkSize, list.size())));
        }
        return chunks;
    }

    @GetMapping("/client")
    public String getClienPage(@ModelAttribute("client")UserDto userDto) {
        return "client";

    }


    @GetMapping("freelancer-page")
    public String adminPage (Model model, Principal principal) {
        int freelancerId = jwtService.extractClaim(JwtLocalStorage.getJwt(),claims -> (int) claims.get("id"));
        List<WorkSample> workSamples =workSampleRepo.findByFreelancer(freelancerId);
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        model.addAttribute("worksamples", workSamples);
        return "freelancer";
    }

    @GetMapping("freelancer/{id}")
    public String userPage (@PathVariable("id") int id, Model model, Principal principal) {
//		int clientId = jwtService.extractClaim(JwtLocalStorage.getJwt(),claims -> (int) claims.get("id"));
        List<WorkSample> workSamples =workSampleRepo.findByFreelancer(id);
        User user = userRepository.findUserById((long)id);
        System.out.println("user is "+user.getEmail());
        Review review =null;
        review= reviewRepository.getReviewByFreelancerIdAndClientId((long)id,user.getId());
        if (review != null) System.out.println("review is "+review.getAverageRating());
        if (review == null) System.out.println("empty");
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        model.addAttribute("user", userDetails);
        model.addAttribute("review", review);
        model.addAttribute("id", id);
        model.addAttribute("clientid", user.getId());
        model.addAttribute("worksamples", workSamples);
        return "publicFreelancer";
    }



    @GetMapping("/updateprofilfreelancer")
    public String afficherProfil(Model model, Principal principal) {
        // Obtenez l'utilisateur connecté
        User utilisateur = userRepository.findByEmail(principal.getName());

        // Ajoutez l'utilisateur au modèle
        model.addAttribute("utilisateur", utilisateur);

        return "updateprofilfreelancer";
    }
    @GetMapping("/donneefreelancer")
    public String getFreelancer(Model model) {
        List<User> freelancers = userRepository.findByRole("freelancer");

        model.addAttribute("donneefreelancer", freelancers);
        return "donneefreelancer";
    }


}
