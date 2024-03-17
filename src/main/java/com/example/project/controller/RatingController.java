package com.example.project.controller;

import com.example.project.service.RateService;
import com.example.project.service.RateServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RatingController {
    @Autowired
    private RateService rateService;
    @PostMapping("/rate/{id}")
    public String rateProduct(@PathVariable Long id, int rating) {
        System.out.println("hello1");
//        rateService.rateUser(id, rating);
        System.out.println("hello2");
        return "redirect:/user-page/" + id;
    }

    @PostMapping("/rateFreelancer/{id}/{clientid}")
    public String rateFreelancer(@PathVariable Long id,@PathVariable Long clientid, int rating) {
//        System.out.println("hello1");
        rateService.rateUser(id, rating,clientid);
//        System.out.println("hello2");
        return "redirect:/freelancer/" + id;
    }
}
