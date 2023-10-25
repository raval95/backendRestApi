package com.recruitment.rest.handler;

import com.recruitment.rest.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(User user, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", user.getId());
        map.put("login", user.getLogin());
        map.put("name", user.getName());
        map.put("type", user.getType());
        map.put("avatarUrl", user.getAvatarUrl());
        map.put("createdAt", user.getCreatedAt());
        //use double beacose result can have the remainder from division
        double firstPart = 6 / (double) user.getLiczbaFollowers();
        double secondPart = 2 + user.getLiczbaPublicRepos();
        double calculations = firstPart * secondPart;
        map.put("calculations", calculations);
        return new ResponseEntity<Object>(map, status);
    }
}
