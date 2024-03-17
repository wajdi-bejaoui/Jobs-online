package com.example.project.repositories;

import com.example.project.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long> {
    PasswordResetToken findByToken(String token);

    @Query("SELECT t FROM PasswordResetToken t WHERE t.token = :token")
    PasswordResetToken findByTokenQuery(String token);
}

