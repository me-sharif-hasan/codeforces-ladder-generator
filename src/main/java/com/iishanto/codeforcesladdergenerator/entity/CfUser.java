package com.iishanto.codeforcesladdergenerator.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.iishanto.codeforcesladdergenerator.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
@Component
public class CfUser {
    public String firstName;
    private String handle;
    public Integer rating;
    private RestClient restClient=RestClient.create();
    private List <RatingChange> ratingHistory;
    private List <Submissions> submissionHistory;
    private ObjectMapper objectMapper=new ObjectMapper();

    private RatingChangesAndSubmissionHistoryContainer ratingChangesAndSubmissionHistoryContainer;
    public CfUser(RatingChangesAndSubmissionHistoryContainer ratingChangesAndSubmissionHistoryContainer){
        this.ratingChangesAndSubmissionHistoryContainer=ratingChangesAndSubmissionHistoryContainer;
        ratingChangesAndSubmissionHistoryContainer.setCfUser(this);
    }
    public static CfUser getUser(JsonNode user,ApplicationContext context){
        CfUser cfUser=context.getBean(CfUser.class);
//        System.out.println("Rating isss: "+user.get("rating").toString());
        if(user.get("handle")!=null) cfUser.setHandle(user.get("handle").asText(""));
        if(user.get("rating")!=null) cfUser.setRating(user.get("rating").asInt(0));
        if(user.get("firstName")!=null) cfUser.setFirstName(user.get("firstName").asText("N/A"));
        return cfUser;
    }

    public List<Submissions> getSubmissionsBetween(long startRating, long endRating){
        return getSubmissionsBetween(startRating, endRating, true);
    }

    public List<Submissions> getSubmissionsBetween(long startRating, long endRating, boolean firstTime){
        System.out.println("Rating is: "+rating+" "+handle);
        ratingHistory=ratingChangesAndSubmissionHistoryContainer.getRatingHistory();
        List <Long> timeInBetween=new ArrayList<>();
        boolean okay=false;
        for (RatingChange ratingChange:ratingHistory){
            if(ratingChange.getOldRating()>=startRating){
                timeInBetween.add(ratingChange.getRatingUpdateTimeSeconds());
                if(ratingChange.getNewRating()>=endRating) {
                    okay=true;
                    break;
                };
            }
        }
        if(!okay) return null;
        long startTime=timeInBetween.getFirst();
        long endTime=timeInBetween.getLast();

        submissionHistory=ratingChangesAndSubmissionHistoryContainer.loadSubmissions();

        List <Submissions> submissions=new ArrayList<>();
        for (Submissions s:submissionHistory){
            if(s.getVerdict().equals("OK") &&s.getCreationTimeSeconds()>=startTime&&s.getCreationTimeSeconds()<=endTime){
                submissions.add(s);
            }
        }
        return submissions;
    }


    public String getHandle() {
        return handle;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public RestClient getRestClient() {
        return restClient;
    }

    public void setRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public void setRatingHistory(List<RatingChange> ratingHistory) {
        this.ratingHistory = ratingHistory;
    }

    public List<Submissions> getSubmissionHistory() {
        return submissionHistory;
    }

    public void setSubmissionHistory(List<Submissions> submissionHistory) {
        this.submissionHistory = submissionHistory;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
