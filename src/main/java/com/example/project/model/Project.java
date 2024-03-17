package com.example.project.model;

import jakarta.persistence.*;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;
    @Column(name= "Title")
    private  String Title;



    @Column(name="description")
    private  String description;


    @Column(name="budget")
    private  String budget;


    @Column(name="status")
    private  String status;

    @ManyToOne
    @JoinColumn(name="freelancer_id")
    private freelancer freelancers;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name="pr_id")
    private Project pr;

    public Project() {}
//    p.id, p.description, p.budget, p.status, p.Title, p.client.id, p.client.email, p.client.fullname
    public Project(Long id, String description, String budget, String status, String title, long client_id,String client_email,String client_fullname) {
        this.id = id;
        this.Title = title;
        this.description = description;
        this.budget = budget;
        this.status = status;
        this.client = new Client();
        this.client.setId(client_id);
        this.client.setEmail(client_email);
        this.client.setFullname(client_fullname);
    }

    public void setClient(Client client) {
        this.client=client;
    }

    public String getTitle() {
        return Title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return this.client;
    }

}
