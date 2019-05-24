package com.example.fn;

import java.util.HashMap;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.json.JSONObject;

public class ElasticDataProvider {
/*
    public HashMap<String, Integer> handleRequest() {
        HttpResponse<JsonNode> getResponse = Unirest
                .get("localhost:8080/t/GitEventArchive/scraper")
                .asJson();

        HashMap<String, String> headers = new HashMap<String, String>();
        String elasticUrl = "https://2cd68ee5bd894d4abdcd6526ab3f26e2.eu-central-1.aws.cloud.es.io:9243";
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "ApiKey bnZ4ZDVXb0JQR2Z5YlIzcUVlQ1U6RThOOFpKZ0NSUlN6OG1qeGZFLTFRZw==");
        HashMap<String, Integer> hits = new HashMap<String, Integer>();

        int allHits = getAllHits(headers, elasticUrl);
        hits.put("allHits", allHits);
        int pushEventHits = getPushHits(headers, elasticUrl);
        hits.put("pushEventHits", pushEventHits);
        int createEventHits = getCreateHits(headers, elasticUrl);
        hits.put("createdEventHits", createEventHits);
        int issuesEventHits = getIssuesHits(headers, elasticUrl);
        hits.put("issuesEventHits", issuesEventHits);
        int deleteEventHits = getDeleteHits(headers, elasticUrl);
        hits.put("deleteEventHits", deleteEventHits);

        return hits;
    }
*/
    public static class Result {
        public int allHits;
        public int pushEventHits;
        public int createEventHits;
        public int issuesEventHits;
        public int deleteEventHits;
    }

    public Result handleRequest() {
        Result result = new Result();

        HashMap<String, String> headers = new HashMap<String, String>();
        String elasticUrl = "https://2cd68ee5bd894d4abdcd6526ab3f26e2.eu-central-1.aws.cloud.es.io:9243";
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "ApiKey bnZ4ZDVXb0JQR2Z5YlIzcUVlQ1U6RThOOFpKZ0NSUlN6OG1qeGZFLTFRZw==");


        result.allHits = getAllHits(headers, elasticUrl);
        result.pushEventHits = getPushHits(headers, elasticUrl);
        result.createEventHits = getCreateHits(headers, elasticUrl);
        result.issuesEventHits = getIssuesHits(headers, elasticUrl);
        result.deleteEventHits = getDeleteHits(headers, elasticUrl);

        return result;
    }

    private static int getAllHits (HashMap<String, String> headers, String elasticUrl) {
        String allHitsQuery = "{\"query\":{\"match_all\":{}}}";

        HttpResponse<JsonNode> responseEventsES = Unirest
                .post(elasticUrl + "/github_events/_search")
                .headers(headers)
                .body(allHitsQuery)
                .asJson();

        JSONObject jsonEventsES = new JSONObject(responseEventsES.getBody());

        return (int) jsonEventsES.getJSONArray("array").getJSONObject(0).getJSONObject("hits")
                .getJSONObject("total").get("value");
    }

    private static int getPushHits (HashMap<String, String> headers, String elasticUrl) {
        String pushEventQuery = "{\"query\":{\"term\":{\"event_type.keyword\":\"PushEvent\"}}}";

        HttpResponse<JsonNode> responseEventsES = Unirest
                .post(elasticUrl + "/github_events/_search")
                .headers(headers)
                .body(pushEventQuery)
                .asJson();

        JSONObject jsonEventsES = new JSONObject(responseEventsES.getBody());

        return (int) jsonEventsES.getJSONArray("array").getJSONObject(0).getJSONObject("hits")
                .getJSONObject("total").get("value");
    }

    private static int getCreateHits (HashMap<String, String> headers, String elasticUrl) {
        String createEventQuery = "{\"query\":{\"term\":{\"event_type.keyword\":\"CreateEvent\"}}}";

        HttpResponse<JsonNode> responseEventsES = Unirest
                .post(elasticUrl + "/github_events/_search")
                .headers(headers)
                .body(createEventQuery)
                .asJson();

        JSONObject jsonEventsES = new JSONObject(responseEventsES.getBody());

        return (int) jsonEventsES.getJSONArray("array").getJSONObject(0).getJSONObject("hits")
                .getJSONObject("total").get("value");
    }

    private static int getIssuesHits (HashMap<String, String> headers, String elasticUrl) {
        String issuesEventQuery = "{\"query\":{\"term\":{\"event_type.keyword\":\"IssuesEvent\"}}}";

        HttpResponse<JsonNode> responseEventsES = Unirest
                .post(elasticUrl + "/github_events/_search")
                .headers(headers)
                .body(issuesEventQuery)
                .asJson();

        JSONObject jsonEventsES = new JSONObject(responseEventsES.getBody());

        return (int) jsonEventsES.getJSONArray("array").getJSONObject(0).getJSONObject("hits")
                .getJSONObject("total").get("value");
    }

    private static int getDeleteHits (HashMap<String, String> headers, String elasticUrl) {
        String deleteEventQuery = "{\"query\":{\"term\":{\"event_type.keyword\":\"DeleteEvent\"}}}";

        HttpResponse<JsonNode> responseEventsES = Unirest
                .post(elasticUrl + "/github_events/_search")
                .headers(headers)
                .body(deleteEventQuery)
                .asJson();

        JSONObject jsonEventsES = new JSONObject(responseEventsES.getBody());

        return (int) jsonEventsES.getJSONArray("array").getJSONObject(0).getJSONObject("hits")
                .getJSONObject("total").get("value");
    }
}