package com.recruitment.rest.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recruitment.rest.model.User;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClientHttptService {
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public String sampleApiRequest(String login) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/users/" + login))
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        return response.body();
    }

    public User syncJackson(String login) throws Exception {
        String response = sampleApiRequest(login);
        User user = objectMapper.readValue(response, User.class);

        return user;
    }
}