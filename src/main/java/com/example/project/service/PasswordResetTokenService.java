package com.example.project.service;

import com.example.project.model.PasswordResetToken;
import com.example.project.model.User;
import com.example.project.repositories.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class PasswordResetTokenService {
    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    public PasswordResetToken createToken(User user) {
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(calculateExpiryDate());
        return tokenRepository.save(resetToken);
    }

    public PasswordResetToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void deleteToken(PasswordResetToken token) {
        tokenRepository.delete(token);
    }

    private Date calculateExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1); // Expiration en 1 heure
        return calendar.getTime();
    }
}
