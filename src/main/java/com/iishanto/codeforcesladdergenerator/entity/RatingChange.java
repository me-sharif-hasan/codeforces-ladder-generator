package com.iishanto.codeforcesladdergenerator.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RatingChange {
    private long ratingUpdateTimeSeconds;
    private String contestName;
    private long oldRating;
    private long newRating;
    private long rank;
    public String contestId;


    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("Contest Name: "+contestName);
        stringBuilder.append("\n");
        stringBuilder.append("Contest ID: "+contestId);
        stringBuilder.append("\n");
        stringBuilder.append("Update Time: "+ Instant.ofEpochMilli(ratingUpdateTimeSeconds).atZone(ZoneId.systemDefault()).toLocalTime());
        stringBuilder.append("\n");
        stringBuilder.append("Old Rating: "+ oldRating);
        stringBuilder.append("\n");
        stringBuilder.append("New Rating: "+ newRating);
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public void getSubmissionsBetween(long startRating,long endRating){

    }

    public long getRatingUpdateTimeSeconds() {
        return ratingUpdateTimeSeconds;
    }

    public void setRatingUpdateTimeSeconds(long ratingUpdateTimeSeconds) {
        this.ratingUpdateTimeSeconds = ratingUpdateTimeSeconds;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public long getOldRating() {
        return oldRating;
    }

    public void setOldRating(long oldRating) {
        this.oldRating = oldRating;
    }

    public long getNewRating() {
        return newRating;
    }

    public void setNewRating(long newRating) {
        this.newRating = newRating;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public String getContestId() {
        return contestId;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }
}
