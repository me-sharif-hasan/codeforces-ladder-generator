package com.iishanto.codeforcesladdergenerator.service;

import com.iishanto.codeforcesladdergenerator.entity.Submissions;

import java.util.ArrayList;
import java.util.List;

public interface CodeforcesApiService {
    public ArrayList getRatedList();
    public List<Submissions> getSubmissionBetweenRating(long start, long end);
}
