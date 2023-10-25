package com.recruitment.rest.service;

import com.recruitment.rest.exception.ResourceNotFoundException;
import com.recruitment.rest.model.RequestsCount;
import org.springframework.stereotype.Service;

@Service
public interface RequestsCountService {
    RequestsCount createRequestsCount(RequestsCount requestsCount);

    void updateRequestsCount(RequestsCount requestsCount) throws ResourceNotFoundException;

    RequestsCount getRequestsCountById(String login) throws ResourceNotFoundException;

}
