package com.example.project.repositories;

import com.example.project.model.Review;
import com.example.project.model.User;
import com.example.project.model.WorkSample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

//    Review getReviewByUserID

    @Query("SELECT u FROM Review u WHERE u.freelancer.id = :id")
    Review getReviewByFreelancerId(@Param("id") long id);

//    @Query("SELECT u FROM Review u WHERE u.freelancer.id = :id and u.client.id = :clientid")
//    Review getReviewByFreelancerIdAndClientId(@Param("id") long id,@Param("clientid") long clientid);

    @Query("SELECT new com.example.project.model.Review(p.id, p.averageRating, p.totalRatings, p.freelancer.id, p.client.id) FROM Review p WHERE p.freelancer.id = :id and p.client.id = :clientid")
    Review getReviewByFreelancerIdAndClientId(@Param("id") long id,@Param("clientid") long clientid);
}
