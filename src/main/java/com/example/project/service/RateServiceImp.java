package com.example.project.service;

import com.example.project.model.Client;
import com.example.project.model.Review;
import com.example.project.model.freelancer;
import com.example.project.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateServiceImp implements RateService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void rateUser(long id, int rating,long clientid) {
        Review review ;
        review= reviewRepository.getReviewByFreelancerId(id);

        System.out.println("hello3");
        if (review == null) review= new Review();
//        double totalRating = review.getAverageRating() * review.getTotalRatings();
        review.setTotalRatings(review.getTotalRatings() + 1);
//        review.setAverageRating((totalRating + rating) / review.getTotalRatings());
            review.setAverageRating(rating);
        freelancer freelancer = new freelancer();
        freelancer.setId(id);
        review.setFreelancer(freelancer);
        Client client = new Client();
        client.setId(clientid);
        review.setClient(client);
        System.out.println("hello4");
            reviewRepository.save(review);


    }
}
