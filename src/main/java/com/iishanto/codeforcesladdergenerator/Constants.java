package com.iishanto.codeforcesladdergenerator;

public class Constants {
    public static String CF_RANKING_URI="http://127.0.0.1:8080/cache/ranklist.json";//"https://codeforces.com/api/user.ratedList?activeOnly=true&includeRetired=false";
    public static String CF_RATING_HISTORY_URI="https://codeforces.com/api/user.rating?handle=";
    public static String CF_SUBMISSION_HISTORY_URI="https://codeforces.com/api/user.status?handle=";
    public static String getCfRatingHistoryUri(String handle){
        return CF_RATING_HISTORY_URI+handle;
    }
    public static String getCfSubmissionHistoryUri(String handle){
        return CF_SUBMISSION_HISTORY_URI+handle;
    }
}
