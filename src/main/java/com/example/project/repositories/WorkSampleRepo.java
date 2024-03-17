package com.example.project.repositories;

import com.example.project.model.Project;
import com.example.project.model.User;
import com.example.project.model.WorkSample;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkSampleRepo extends JpaRepository<WorkSample,Long> {
    @Query("SELECT w FROM WorkSample w WHERE w.freelance.id = :freelancer_id")
    List<WorkSample> findByFreelancer(@Param("freelancer_id") int freelancer_id);

    @Query("SELECT new com.example.project.model.WorkSample(p.id, p.description, p.price, p.title, p.freelance.id, p.freelance.email, p.freelance.fullname) FROM WorkSample p")
    List<WorkSample> findAllWorkSamplesWithFreelancer();




}
