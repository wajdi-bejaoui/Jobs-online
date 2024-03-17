package com.example.project.controller;

import com.example.project.model.Project;
import com.example.project.model.WorkSample;
import com.example.project.repositories.WorkSampleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WorkSampleController {

    @Autowired
    private WorkSampleRepo workSampleRepo;

    @ResponseBody
    @RequestMapping(value = "/worktest", method = RequestMethod.GET)
    public ResponseEntity<Object> save() {
        System.out.println("hello");
        List<WorkSample> workSamples = workSampleRepo.findAllWorkSamplesWithFreelancer();
        System.out.println("hello2");
        return new ResponseEntity<>(workSamples , HttpStatus.OK);
    }
    @GetMapping("/listWorkSample")
    public String afficherListWorkSample(Authentication authentication, Model model) {
//        User user = (User) authentication.getPrincipal();

        List<WorkSample> workSamples = workSampleRepo.findAllWorkSamplesWithFreelancer();
        List<List<WorkSample>> chunkedList = chunkList(workSamples, 3); // Chunk the list into sublists of size 3


        model.addAttribute("chunkedList", chunkedList);

        return "list-worksamples";
    }

    private <T> List<List<T>> chunkList(List<T> list, int chunkSize) {
        List<List<T>> chunks = new ArrayList<>();
        for (int i = 0; i < list.size(); i += chunkSize) {
            chunks.add(list.subList(i, Math.min(i + chunkSize, list.size())));
        }
        return chunks;
    }

}
