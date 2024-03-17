package com.example.project.repositories;

import com.example.project.model.Project;
import com.example.project.model.WorkSample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project,Long> {
    List<Project> findByClientId(Long clientId);
    @Query("SELECT c FROM Project c WHERE c.client.id = :client_id")
     List<Project> findByClient(@Param("client_id") int client_id) ;

    @Query("SELECT p FROM Project p,Client c  WHERE p.client.id = c.id")
    List<Project> findAllProjects() ;

//    @Query("SELECT p FROM Project p JOIN FETCH p.client")
//    List<Project> findAllProjectsWithClient();

    @Query("SELECT new com.example.project.model.Project(p.id, p.description, p.budget, p.status, p.Title, p.client.id, p.client.email, p.client.fullname) FROM Project p")
    List<Project> findAllProjectsWithClient();



}
