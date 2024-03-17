package com.example.project.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity

public class freelancer extends User {


    public freelancer(String email, String role, String fullname, String addresse, String phone, String country,long id) {
        super(email, role, fullname, addresse, phone, country,id);
    }
    public freelancer(String email, String encode, String role, String fullname, String addresse, String country, String phone) {
        super();
    }

    public freelancer() {
        super();
    }

    @Override
    public void resetPwd() {
    }

     @OneToMany(mappedBy = "freelance")
    private List<WorkSample>workSamples= new ArrayList<>();

    @OneToMany(mappedBy = "freelancers")
    private List<Project>projects= new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "freelancer_reviews",
            joinColumns = @JoinColumn(name = "freelancer_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id")
    )
    private List<Review> reviews = new ArrayList<>();



}
