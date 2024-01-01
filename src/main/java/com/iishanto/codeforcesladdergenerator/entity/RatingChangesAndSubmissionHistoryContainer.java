package com.iishanto.codeforcesladdergenerator.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.iishanto.codeforcesladdergenerator.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Scope("prototype")
public class RatingChangesAndSubmissionHistoryContainer {
    private RestClient restClient=RestClient.create();
    private ObjectMapper objectMapper=new ObjectMapper();
    private CfUser cfUser;

    public void setCfUser(CfUser cfUser) {
        this.cfUser = cfUser;
    }
    public CfUser getCfUser() {
        return cfUser;
    }

    @Cacheable(value = "rating-history",key = "'rating-history'+#root.target.getCfUser().getHandle()")
    public List<RatingChange> getRatingHistory(){
        System.out.println("Getting ranking change of: "+cfUser.getHandle()+" on url: "+ Constants.getCfRatingHistoryUri(cfUser.getHandle()));
        List <RatingChange> ratingHistory=new ArrayList<>();
        JsonNode response=restClient.get().uri(Constants.getCfRatingHistoryUri(cfUser.getHandle())).retrieve().body(new ParameterizedTypeReference<JsonNode>() {});
        ArrayNode history= (ArrayNode) response.get("result");
        for (JsonNode ratingChange:history){
            RatingChange ratingChangeObject=objectMapper.convertValue(ratingChange,RatingChange.class);
            ratingHistory.add(ratingChangeObject);
        }
        try{
            TimeUnit.MILLISECONDS.sleep(300);
        }catch (Throwable $e){}
        return ratingHistory;
    }


    @Cacheable(value = "submission-history",key = "'submission-history'+#root.target.getCfUser().getHandle()")
    public List <Submissions> loadSubmissions(){
        System.out.println("Loading submission of: "+cfUser.getHandle()+" "+this);
        List <Submissions> submissionHistory=new ArrayList<>();
        JsonNode jsonNode=restClient.get().uri(Constants.getCfSubmissionHistoryUri(cfUser.getHandle())).retrieve().body(JsonNode.class);
        ArrayNode submissions= (ArrayNode) jsonNode.get("result");
        for(JsonNode submission:submissions){
            Submissions submissionObject=objectMapper.convertValue(submission,Submissions.class);
            submissionHistory.add(submissionObject);
        }
        submissionHistory.sort(new Comparator<Submissions>() {
            @Override
            public int compare(Submissions o1, Submissions o2) {
                long cmp=o1.getCreationTimeSeconds()-o2.getCreationTimeSeconds();
                if(cmp==0) return 0;
                else if(cmp>0) return 1;
                else return -1;
            }
        });
        try{
            TimeUnit.MILLISECONDS.sleep(300);
        }catch (Throwable $e){}
        return submissionHistory;
    }

}
