package com.example.fn;

import java.util.HashMap;
import java.util.Map;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.json.JSONObject;
import org.json.JSONArray;

public class Scraper {

    public String handleRequest() {
        Map<String, String> headers = new HashMap<String, String>();
        String elasticUrl = "https://2cd68ee5bd894d4abdcd6526ab3f26e2.eu-central-1.aws.cloud.es.io:9243";
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "ApiKey bnZ4ZDVXb0JQR2Z5YlIzcUVlQ1U6RThOOFpKZ0NSUlN6OG1qeGZFLTFRZw==");
        
        HttpResponse<String> responseEvents = Unirest
        .get("https://api.github.com/events")
        .asString();

        JSONObject obj = responseEvents.getBody().asJson();

        return obj.getBody().toString();
        /*
        HttpResponse<JsonNode> responseEvents = Unirest
                .get("https://api.github.com/events")
                .asJson();
        JSONObject jsonEvents = new JSONObject(responseEvents.getBody());
        return jsonEvents.getJSONArray("array").toString();
        *//*
        for(int i = 0; i < jsonEvents.getJSONArray("array").length(); i++) {
            String id = jsonEvents.getJSONArray("array").getJSONObject(i).getString("id");
            String type = jsonEvents.getJSONArray("array").getJSONObject(i).getString("type");
            jsonEvents.getJSONArray("array").getJSONObject(i).remove("type");
            jsonEvents.getJSONArray("array").getJSONObject(i).put("event_type", type);

            /*
            HttpResponse<JsonNode> putEvents = Unirest
                    .put(elasticUrl + "/github_events/_doc" + "/" + id)
                    .headers(headers)
                    .body(jsonEvents.getJSONArray("array").getJSONObject(i))
                    .asJson();
        }*/
        /*
        getEvents(headers, elasticUrl);
        getNetworkEvents(headers, elasticUrl);
        getOrgEvents(headers, elasticUrl);
        getReceivedEvents(headers, elasticUrl);
        getReceivedEventsPublic(headers, elasticUrl);
        getRepoEvents(headers, elasticUrl);
        getUserEvents(headers, elasticUrl);
        getUserEventsPublic(headers, elasticUrl); */      
    }

    private static void getEvents(Map<String, String> headers, String url) {
        HttpResponse<JsonNode> responseEvents = Unirest
                .get("https://api.github.com/events")
                .asJson();
        JSONObject jsonEvents = new JSONObject(responseEvents.getBody());

        for(int i = 0; i < jsonEvents.getJSONArray("array").length(); i++) {
            String id = jsonEvents.getJSONArray("array").getJSONObject(i).getString("id");
            String type = jsonEvents.getJSONArray("array").getJSONObject(i).getString("type");
            jsonEvents.getJSONArray("array").getJSONObject(i).remove("type");
            jsonEvents.getJSONArray("array").getJSONObject(i).put("event_type", type);

            HttpResponse<JsonNode> putEvents = Unirest
                    .put(url + "/github_events/_doc" + "/" + id)
                    .headers(headers)
                    .body(jsonEvents.getJSONArray("array").getJSONObject(i))
                    .asJson();
        }
    }

    private static void getRepoEvents (Map<String, String> headers, String url) {
        HttpResponse<JsonNode> responseRepoEvents = Unirest
                .get("https://api.github.com/repos/Plan4BA/Plan4BA-App/events")
                .asJson();
        JSONObject jsonRepoEvents = new JSONObject(responseRepoEvents.getBody());

        for(int i = 0; i < jsonRepoEvents.getJSONArray("array").length(); i++) {
            String id = jsonRepoEvents.getJSONArray("array").getJSONObject(i).getString("id");
            String type = jsonRepoEvents.getJSONArray("array").getJSONObject(i).getString("type");
            jsonRepoEvents.getJSONArray("array").getJSONObject(i).remove("type");
            jsonRepoEvents.getJSONArray("array").getJSONObject(i).put("event_type", type);

            HttpResponse<JsonNode> putEvents = Unirest
                    .put(url + "/github_events/_doc" + "/" + id)
                    .headers(headers)
                    .body(jsonRepoEvents.getJSONArray("array").getJSONObject(i))
                    .asJson();
        }
    }

    private static void getReceivedEvents (Map<String, String> headers, String url) {
        HttpResponse<JsonNode> responseReceivedEvents = Unirest
                .get("https://api.github.com/users/Plan4BA/received_events")
                .asJson();
        JSONObject jsonReceivedEvents = new JSONObject(responseReceivedEvents.getBody());

        for(int i = 0; i < jsonReceivedEvents.getJSONArray("array").length(); i++) {
            String id = jsonReceivedEvents.getJSONArray("array").getJSONObject(i).getString("id");
            String type = jsonReceivedEvents.getJSONArray("array").getJSONObject(i).getString("type");
            jsonReceivedEvents.getJSONArray("array").getJSONObject(i).remove("type");
            jsonReceivedEvents.getJSONArray("array").getJSONObject(i).put("event_type", type);

            HttpResponse<JsonNode> putEvents = Unirest
                    .put(url + "/github_events/_doc" + "/" + id)
                    .headers(headers)
                    .body(jsonReceivedEvents.getJSONArray("array").getJSONObject(i))
                    .asJson();
        }
    }

    private static void getReceivedEventsPublic (Map<String, String> headers, String url) {
        HttpResponse<JsonNode> responseReceivedEventsPublic = Unirest
                .get("https://api.github.com/users/Plan4BA/received_events/public")
                .asJson();
        JSONObject jsonReceivedEventsPublic = new JSONObject(responseReceivedEventsPublic.getBody());

        for(int i = 0; i < jsonReceivedEventsPublic.getJSONArray("array").length(); i++) {
            String id = jsonReceivedEventsPublic.getJSONArray("array").getJSONObject(i).getString("id");
            String type = jsonReceivedEventsPublic.getJSONArray("array").getJSONObject(i).getString("type");
            jsonReceivedEventsPublic.getJSONArray("array").getJSONObject(i).remove("type");
            jsonReceivedEventsPublic.getJSONArray("array").getJSONObject(i).put("event_type", type);

            HttpResponse<JsonNode> putEvents = Unirest
                    .put(url + "/github_events/_doc" + "/" + id)
                    .headers(headers)
                    .body(jsonReceivedEventsPublic.getJSONArray("array").getJSONObject(i))
                    .asJson();
        }
    }

    private static void getUserEvents (Map<String, String> headers, String url) {
        HttpResponse<JsonNode> responseUserEvents = Unirest
                .get("https://api.github.com/users/elastic/events")
                .asJson();
        JSONObject jsonUserEvents = new JSONObject(responseUserEvents.getBody());

        for(int i = 0; i < jsonUserEvents.getJSONArray("array").length(); i++) {
            String id = jsonUserEvents.getJSONArray("array").getJSONObject(i).getString("id");
            String type = jsonUserEvents.getJSONArray("array").getJSONObject(i).getString("type");
            jsonUserEvents.getJSONArray("array").getJSONObject(i).remove("type");
            jsonUserEvents.getJSONArray("array").getJSONObject(i).put("event_type", type);

            HttpResponse<JsonNode> putEvents = Unirest
                    .put(url + "/github_events/_doc" + "/" + id)
                    .headers(headers)
                    .body(jsonUserEvents.getJSONArray("array").getJSONObject(i))
                    .asJson();
        }
    }

    private static void getNetworkEvents (Map<String, String> headers, String url) {
        HttpResponse<JsonNode> responseNetworkEvents = Unirest
                .get("https://api.github.com/networks/Plan4BA/Plan4BA-App/events")
                .asJson();
        JSONObject jsonNetworkEvents = new JSONObject(responseNetworkEvents.getBody());

        for(int i = 0; i < jsonNetworkEvents.getJSONArray("array").length(); i++) {
            String id = jsonNetworkEvents.getJSONArray("array").getJSONObject(i).getString("id");
            String type = jsonNetworkEvents.getJSONArray("array").getJSONObject(i).getString("type");
            jsonNetworkEvents.getJSONArray("array").getJSONObject(i).remove("type");
            jsonNetworkEvents.getJSONArray("array").getJSONObject(i).put("event_type", type);

            HttpResponse<JsonNode> putEvents = Unirest
                    .put(url + "/github_events/_doc" + "/" + id)
                    .headers(headers)
                    .body(jsonNetworkEvents.getJSONArray("array").getJSONObject(i))
                    .asJson();
        }
    }

    private static void getOrgEvents (Map<String, String> headers, String url) {
        HttpResponse<JsonNode> responseOrgEvents = Unirest
                .get("https://api.github.com/orgs/Plan4BA/events")
                .asJson();
        JSONObject jsonOrgEvents = new JSONObject(responseOrgEvents.getBody());

        for(int i = 0; i < jsonOrgEvents.getJSONArray("array").length(); i++) {
            String id = jsonOrgEvents.getJSONArray("array").getJSONObject(i).getString("id");
            String type = jsonOrgEvents.getJSONArray("array").getJSONObject(i).getString("type");
            jsonOrgEvents.getJSONArray("array").getJSONObject(i).remove("type");
            jsonOrgEvents.getJSONArray("array").getJSONObject(i).put("event_type", type);

            HttpResponse<JsonNode> putEvents = Unirest
                    .put(url + "/github_events/_doc" + "/" + id)
                    .headers(headers)
                    .body(jsonOrgEvents.getJSONArray("array").getJSONObject(i))
                    .asJson();
        }
    }

    private static void getUserEventsPublic (Map<String, String> headers, String url) {
        HttpResponse<JsonNode> responseUserEventsPublic = Unirest
                .get("https://api.github.com/users/Plan4BA/events/public")
                .asJson();
        JSONObject jsonUserEventsPublic = new JSONObject(responseUserEventsPublic.getBody());

        for(int i = 0; i < jsonUserEventsPublic.getJSONArray("array").length(); i++) {
            String id = jsonUserEventsPublic.getJSONArray("array").getJSONObject(i).getString("id");
            String type = jsonUserEventsPublic.getJSONArray("array").getJSONObject(i).getString("type");
            jsonUserEventsPublic.getJSONArray("array").getJSONObject(i).remove("type");
            jsonUserEventsPublic.getJSONArray("array").getJSONObject(i).put("event_type", type);

            HttpResponse<JsonNode> putEvents = Unirest
                    .put(url + "/github_events/_doc" + "/" + id)
                    .headers(headers)
                    .body(jsonUserEventsPublic.getJSONArray("array").getJSONObject(i))
                    .asJson();
        }
    }
}