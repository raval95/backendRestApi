package com.recruitment.rest.controller;

import com.recruitment.rest.client.ClientHttptService;
import com.recruitment.rest.handler.ResponseHandler;
import com.recruitment.rest.model.RequestsCount;
import com.recruitment.rest.model.User;
import com.recruitment.rest.service.RequestsCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private RequestsCountService requestsCountService;

    @GetMapping("/users/{login}")
    public ResponseEntity<Object> getUsertByLogin(@PathVariable String login) throws Exception {
        ClientHttptService clienHttptService = new ClientHttptService();
        User user = clienHttptService.syncJackson(login);
        RequestsCount requestsCount = requestsCountService.getRequestsCountById(login);
        Integer count = requestsCount.getRequest_count();
        if (count != null) {
            requestsCount.setRequest_count(count + 1);
        } else {
            requestsCount.setRequest_count(1);
        }
        this.requestsCountService.updateRequestsCount(requestsCount);
        return ResponseHandler.generateResponse(user, HttpStatus.OK);
    }
}