package com.iishanto.codeforcesladdergenerator.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.iishanto.codeforcesladdergenerator.entity.Submissions;
import com.iishanto.codeforcesladdergenerator.service.CodeforcesApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RatingController {
    CodeforcesApiService codeforcesApiService;
    @Autowired
    public RatingController(CodeforcesApiService codeforcesApiService){
        this.codeforcesApiService=codeforcesApiService;
    }
    @CrossOrigin
    @GetMapping("/get-rating-list")
    public List<Submissions> getSubmissions(){
//        return codeforcesApiService.getSubmissionBetweenRating(1200,1500);
        RestClient restClient=RestClient.create();
        ObjectMapper o=new ObjectMapper();
        ArrayNode arrayNode=restClient.get().uri("http://localhost:8080/cache/dummy.json").retrieve().body(ArrayNode.class);
        List <Submissions> allSub=new ArrayList<>();
        for (JsonNode s:arrayNode){
            Submissions sub=o.convertValue(s,Submissions.class);
            allSub.add(sub);
        }
        return allSub;
    }
}
