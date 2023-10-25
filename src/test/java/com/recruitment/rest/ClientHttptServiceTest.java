package com.recruitment.rest;

import com.recruitment.rest.client.ClientHttptService;
import com.recruitment.rest.model.User;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.assertEquals;


public class ClientHttptServiceTest {
    private static ClientHttptService client;

    @BeforeClass
    public static void setUp() {
        client = new ClientHttptService();
    }

    @Test
    public void integrationTest_ResponseOk() throws InterruptedException, IOException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/users/octocat"))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(response.statusCode(), 200);
    }

    @Test
    public void requestAboutOctocat_thenResponseOk() throws Exception {
        User response = client.syncJackson("octocat");
        User expected = TestHelper.getUserExample();
        assertEquals(expected.getId(), response.getId());
        assertEquals(expected.getLogin(), response.getLogin());
        assertEquals(expected.getName(), response.getName());
        assertEquals(expected.getType(), response.getType());
        assertEquals(expected.getCreatedAt(), response.getCreatedAt());
//        dynamic variables can be changed
//        assertEquals(expected.getAvatarUrl(), response.getAvatarUrl());
//        assertEquals(expected.getLiczbaFollowers(), response.getLiczbaFollowers());
//        assertEquals(expected.getLiczbaPublicRepos(), response.getLiczbaPublicRepos());
    }
}
