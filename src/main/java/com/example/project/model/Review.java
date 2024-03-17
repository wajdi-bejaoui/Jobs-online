package com.example.project.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;


    @Column(name= "averageRating")
    private  double averageRating;

    @Column(name="totalRatings")
    private int totalRatings;

    public Review(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(int totalRatings) {
        this.totalRatings = totalRatings;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="comment")
    private  String email;

    @ManyToOne
    @JoinColumn(name="freelancer_id")
    private freelancer freelancer;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;


    public Review(Long id, double averageRating, int totalRatings, long freelancer, long client) {
        this.freelancer = new freelancer();
        this.client = new Client();
        this.id = id;
        this.averageRating = averageRating;
        this.totalRatings = totalRatings;
        this.freelancer.setId(freelancer);
        this.client.setId(client);
    }

    //    public  void calculReview();
    public void setFreelancer(freelancer freelancer) {
        this.freelancer = freelancer;
    }

    public void setClient(Client client) {
        this.client = client;
    }



}
