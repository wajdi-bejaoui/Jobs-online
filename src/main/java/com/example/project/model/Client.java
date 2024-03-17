package com.example.project.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Entity

public class Client extends User {
    public Client(String email, String role, String fullname, String addresse, String phone, String country,long id) {
        super(email, role, fullname, addresse, phone, country,id);
    }
    public Client() {
        super();
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public void resetPwd() {

    }
    @OneToMany(mappedBy = "client")
    private List<Project> projects= new ArrayList<>();

    @ManyToMany (mappedBy = "clients")
    Set<WorkSample> Worksamples;

    @ManyToMany
    @JoinTable(
            name = "client_reviews",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id")
    )
    private List<Review> reviews = new ArrayList<>();


}
