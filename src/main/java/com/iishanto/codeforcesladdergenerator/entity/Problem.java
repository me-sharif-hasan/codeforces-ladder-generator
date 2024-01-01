package com.iishanto.codeforcesladdergenerator.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Problem {
    public String contestId;
    public String index;
    public String name;
    public Double points;
    public Integer rating;
    public String[] tags;

    public String getContestId() {
        return contestId;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "contestId='" + contestId + '\'' +
                ", index='" + index + '\'' +
                ", name='" + name + '\'' +
                ", points=" + points +
                ", rating=" + rating +
                ", tags=" + Arrays.toString(tags) +
                '}';
    }
}
