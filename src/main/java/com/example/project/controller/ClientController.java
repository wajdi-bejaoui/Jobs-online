package com.example.project.controller;

import com.example.project.configurations.JwtLocalStorage;
import com.example.project.dto.UserDto;
import com.example.project.model.Client;
import com.example.project.model.Project;
import com.example.project.model.User;
import com.example.project.repositories.ClientRepository;
import com.example.project.repositories.ProjectRepo;
import com.example.project.repositories.UserRepository;
import com.example.project.service.JwtService;
import com.example.project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/updateprofilclientt")
    public String afficherFormulaire(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();

        model.addAttribute("user", user);

        return "updateprofilclient";
    }

    @GetMapping("/editclient")
    public String editClient(@ModelAttribute("editClient") UserDto userDto) {
        return "editClient";

    }
    @GetMapping("/profilclient")
    public String getProject(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
        return "profilclient";

    }
    @PostMapping("/ajoutprojet")
    public String saveProjet(@ModelAttribute("project") Project project,Principal principal)
    {
        int clientId = jwtService.extractClaim(JwtLocalStorage.getJwt(),claims -> (int) claims.get("id"));

        User user = userRepository.findByEmail(principal.getName());
        Client client = new Client(user.getEmail(),user.getRole(), user.getFullname(),user.getAddresse(),user.getPhone(),user.getCountry(),user.getId());
        project.setClient(client);
        projectRepo.save(project);
        return "redirect:/user-page";
    }
    @GetMapping("/profilec")
    public String profilClient(@ModelAttribute("profilec") UserDto userDto) {
        return "profileC";

    }
    @GetMapping("/donneeClient")
    public String getClient(Model model) {
        List<User> clients = userRepository.findByRole("client");

        model.addAttribute("donneeClient", clients);
        return "donneeClient";
    }
    @GetMapping("/updateprofilclient")
    public String afficherProfil(Model model, Principal principal) {
        // Obtenez l'utilisateur connecté
        User utilisateur = userRepository.findByEmail(principal.getName());

        // Ajoutez l'utilisateur au modèle
        model.addAttribute("utilisateur", utilisateur);

        return "updateprofilclient";
    }

    @PostMapping("/profil")
    public String mettreAJourProfil(@ModelAttribute("utilisateur") User utilisateur,
                                    HttpServletRequest request) {
        // Récupérer le mot de passe du formulaire
        String nouveauMotDePasse = request.getParameter("nouveauMotDePasse");

        // Vérifier si le mot de passe a été fourni
        if (nouveauMotDePasse != null && !nouveauMotDePasse.isEmpty()) {
            // Encoder le nouveau mot de passe et le mettre à jour
            utilisateur.setPassword(bCryptPasswordEncoder.encode(nouveauMotDePasse));
        }

        // Mettre à jour d'autres détails de l'utilisateur
        userRepository.save(utilisateur);

        return "redirect:/updateprofilclient?miseAJourSucces";
    }
}
