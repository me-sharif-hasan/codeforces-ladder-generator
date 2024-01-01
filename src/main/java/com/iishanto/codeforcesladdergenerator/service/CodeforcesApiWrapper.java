package com.iishanto.codeforcesladdergenerator.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.iishanto.codeforcesladdergenerator.Constants;
import com.iishanto.codeforcesladdergenerator.entity.CfUser;
import com.iishanto.codeforcesladdergenerator.entity.Submissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Component
public class CodeforcesApiWrapper implements CodeforcesApiService{
    RestClient restClient=RestClient.create();
    ApplicationContext applicationContext;
    ObjectMapper objectMapper;
    @Autowired
    public CodeforcesApiWrapper(ObjectMapper objectMapper,ApplicationContext applicationContext){
        this.objectMapper=objectMapper;
        this.applicationContext=applicationContext;
    }
    @Override
    public ArrayList <Object> getRatedList() {
        JsonNode response=restClient.get().uri(Constants.CF_RANKING_URI).retrieve().body(JsonNode.class);
        ArrayNode arrayNode= (ArrayNode) response.get("result");
        for (JsonNode user:arrayNode){
            CfUser cfUser=objectMapper.convertValue(user, CfUser.class);
            System.out.println(cfUser.getSubmissionsBetween(1100,1400).toString());
            break;
        }
        return new ArrayList <Object>();
    }
    public List <Submissions> getSubmissionBetweenRating(long start,long end){
        JsonNode response=restClient.get().uri(Constants.CF_RANKING_URI).retrieve().body(JsonNode.class);
        assert response != null;
        ArrayNode arrayNode= (ArrayNode) response.get("result");
        List <JsonNode> allUsers=objectMapper.convertValue(arrayNode, new TypeReference<List<JsonNode>>() {});
        Collections.shuffle(allUsers,new Random(0));
        List <Submissions> allSubmissions=new ArrayList<>();
        int counter=0;
        for (JsonNode user:allUsers){
            CfUser cfUser=CfUser.getUser(user,applicationContext);
            if(cfUser.getRating()!=null&&cfUser.getRating()>start&&cfUser.getRating()<end+500){
                try {
                    List <Submissions> submissions=cfUser.getSubmissionsBetween(start,end);
                    System.out.println(cfUser.getHandle()+" "+(submissions!=null?submissions.size():0));
                    if(submissions!=null) allSubmissions.addAll(submissions);
                    counter++;
                    System.out.println("Currently taken is: "+counter);
                }catch (Throwable e){
                    System.err.println(e.getMessage());
                }

            }
            if(counter>350) break;
        }
        return allSubmissions;
    }
}
