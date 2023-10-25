package com.recruitment.rest.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "RequestsCount")
public class RequestsCount {
    private String login;

    private Integer request_count;

    public RequestsCount() {
    }

    public RequestsCount(String login, Integer request_count) {
        this.login = login;
        this.request_count = request_count;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getRequest_count() {
        return request_count;
    }

    public void setRequest_count(Integer request_count) {
        this.request_count = request_count;
    }
}
