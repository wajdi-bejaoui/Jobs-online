package com.example.project.controller;

import com.example.project.model.PasswordResetToken;
import com.example.project.model.User;
import com.example.project.repositories.PasswordResetTokenRepository;
import com.example.project.repositories.UserRepository;
import com.example.project.service.PasswordResetTokenService;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForgotPasswordController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenService tokenService;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam String email, Model model) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            PasswordResetToken token = tokenService.createToken(user);

            // Vous pouvez envoyer le lien de réinitialisation par e-mail ici
            model.addAttribute("message", "Un lien de réinitialisation a été envoyé à votre adresse e-mail.");
            model.addAttribute("token", token.getToken());
        } else {
            model.addAttribute("error", "Aucun utilisateur n'a été trouvé avec cet e-mail.");
        }

        return "reset-password";
    }

//    @GetMapping("/reset-password")
//    public String showResetPasswordForm(@RequestParam String token, Model model) {
//        PasswordResetToken resetToken = passwordResetTokenRepository.findByTokenQuery(token);
//
//      f  if (resetToken != null && !resetToken.isExpired()) {
//            model.addAttribute("token", token);
//            return "reset-password";
//        } else {
//            model.addAttribute("error", "Le lien de réinitialisation est invalide ou a expiré.");
//            return "forgot-password";
//        }
//    }

    @PostMapping("/reset-password")
    public String processResetPassword(@RequestParam String token, @RequestParam String password, Model model) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByTokenQuery(token);
        if (resetToken != null && !resetToken.isExpired()) {
            User user = resetToken.getUser();
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            tokenService.deleteToken(resetToken);
            model.addAttribute("message", "Votre mot de passe a été réinitialisé avec succès.");
        } else {
            model.addAttribute("error", "Le lien de réinitialisation est invalide ou a expiré.");
        }

        return "SignIn";
    }
}
