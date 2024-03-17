package com.example.project.controller;

import com.example.project.configurations.JwtLocalStorage;
import com.example.project.model.Client;
import com.example.project.model.Project;
import com.example.project.model.User;
import com.example.project.repositories.ClientRepository;
import com.example.project.repositories.ProjectRepo;
import com.example.project.repositories.UserRepository;
import com.example.project.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {
    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/listOffres")
    public String afficherListProject(Authentication authentication, Model model) {
//        User user = (User) authentication.getPrincipal();

        List<Project> projets = projectRepo.findAllProjectsWithClient();
        List<List<Project>> chunkedList = chunkList(projets, 3); // Chunk the list into sublists of size 3


        model.addAttribute("chunkedList", chunkedList);

        return "list-projects";
    }

    private <T> List<List<T>> chunkList(List<T> list, int chunkSize) {
        List<List<T>> chunks = new ArrayList<>();
        for (int i = 0; i < list.size(); i += chunkSize) {
            chunks.add(list.subList(i, Math.min(i + chunkSize, list.size())));
        }
        return chunks;
    }

    @GetMapping("/listeProject/{clientId}")
    public String home(@PathVariable Long clientId, Model model) {
        // Récupérer le client
        Optional<Client> client = clientRepository.findById(clientId);

        if (client.isPresent()) {
            // Récupérer les projets associés au client
            List<Project> projets = projectRepo.findAll();

            // Ajouter les projets et le client à l'objet Model
            model.addAttribute("projets", projets);
            model.addAttribute("client", client.get());


            // Renvoyer le nom de la vue Thymeleaf
            return "client";
        } else {
            // Gérer le cas où le client n'est pas trouvé
            return "redirect:/erreur";
        }
    }

    @GetMapping("deleteprojett/{id}")
    public String deleteProjet(@PathVariable("id") Long id){
        projectRepo.deleteById(id);

        return "redirect:/user-page";

    }
    @GetMapping("updateProjett/{id}")
    public String updateProjet(@PathVariable("id") Long id , Model model){
        Optional<Project> temp = projectRepo.findById(id);
        Project projet = temp.get();
        model.addAttribute("projet", projet);
        return "updateprojet";
    }@PostMapping("/updateprojet/{id}")
    public String updateProjet(@PathVariable Long id, @ModelAttribute("projet") Project updatedProject, Principal principal) {
        int clientId = jwtService.extractClaim(JwtLocalStorage.getJwt(), claims -> (int) claims.get("id"));

        User user = userRepository.findByEmail(principal.getName());
        Client client = new Client(user.getEmail(), user.getRole(), user.getFullname(), user.getAddresse(), user.getPhone(), user.getCountry(), user.getId());

        // Retrieve the existing project from the repository by ID
        Project existingProject = projectRepo.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));

        // Update the existing project fields with the new data
        existingProject.setTitle(updatedProject.getTitle());
        existingProject.setBudget(updatedProject.getBudget());
        existingProject.setDescription(updatedProject.getDescription());
        existingProject.setStatus(updatedProject.getStatus());
        existingProject.setClient(client);

        // Save the updated project
        projectRepo.save(existingProject);

        return "redirect:/user-page";
    }
}


