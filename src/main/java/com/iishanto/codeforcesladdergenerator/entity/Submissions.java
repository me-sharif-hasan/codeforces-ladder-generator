package com.iishanto.codeforcesladdergenerator.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Submissions {
    private String contestId;
    private Integer id;
    private Long creationTimeSeconds;
    private String verdict;
    private Problem problem;
    private JsonNode author;

    public String getContestId() {
        return contestId;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCreationTimeSeconds() {
        return creationTimeSeconds;
    }

    public void setCreationTimeSeconds(Long creationTimeSeconds) {
        this.creationTimeSeconds = creationTimeSeconds;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public JsonNode getAuthor() {
        return author;
    }

    public void setAuthor(JsonNode author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Submissions{" +
                "contestId='" + contestId + '\'' +
                ", id=" + id +
                ", creationTimeSeconds=" + creationTimeSeconds +
                ", verdict='" + verdict + '\'' +
                ", problem=" + problem +
                ", author=" + author +
                '}';
    }
}
