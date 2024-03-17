package com.example.project.repositories;

import com.example.project.model.Client;
import com.example.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {

    Optional<Client> findByEmail (String email);
}
