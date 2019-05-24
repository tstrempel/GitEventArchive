package com.example.fn;

import java.util.Map;
import java.util.HashMap;
import java.text.DecimalFormat;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.json.JSONObject;

public class RequestHandler {
    DecimalFormat df = new DecimalFormat("#.##");

    public String handleRequest() {

        String elasticUrl = "https://2cd68ee5bd894d4abdcd6526ab3f26e2.eu-central-1.aws.cloud.es.io:9243";
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "ApiKey bnZ4ZDVXb0JQR2Z5YlIzcUVlQ1U6RThOOFpKZ0NSUlN6OG1qeGZFLTFRZw==");

        String pushEventQuery = "{\"query\":{\"term\":{\"event_type.keyword\":\"PushEvent\"}}}";
        HttpResponse<JsonNode> responseEventsES = Unirest
                .post(elasticUrl + "/github_events/_search")
                .headers(headers)
                .body(pushEventQuery)
                .asJson();

        return responseEventsES.getBody().toString();
    }
}